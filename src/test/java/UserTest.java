import cab302.iirtt.assignment1.model.IUser;
import cab302.iirtt.assignment1.model.User;
import cab302.iirtt.assignment1.model.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    User user;
    UserDAO userDAO;

    @BeforeEach
    public void setup() {
        userDAO = new UserDAO();
        userDAO.addUser(new User("John", "Doe", "johndoe21", "$Password123", "neutral", "2024-04-23", LocalDate.now().toString(), 1));
        user = userDAO.getUserByUsername("johndoe21");
        user.setUserID(1);
    }

    @Test
    public void testGetUserID() {
        assertEquals(1, user.getUserID());

    }

    @Test
    public void testGetFirstName() {
        assertEquals("John", user.getFirstName());

    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", user.getLastName());
    }

    @Test
    public void testGetUsername() {
        assertEquals("johndoe21", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("$Password123", user.getPassword());
    }

    @Test
    public void testGetMood() {
        assertEquals("neutral", user.getMood());
    }

    @Test
    public void testGetMemberSince() {
        assertEquals("2024-04-23", user.getMemberSince());
    }

    @Test
    public void testGetDateLoggedIn() {
        assertEquals(LocalDate.now().toString(), user.getDateLoggedIn());
    }

    @Test
    public void testGetStreak() {
        assertEquals(1, user.getStreak());
    }

    @Test
    public void testSetUserID() {
        user.setUserID(2);
        assertEquals(2, user.getUserID());
    }

    @Test
    public void testSetFirstName() {
        user.setFirstName("Jane");
        assertEquals("Jane", user.getFirstName());
    }

    @Test
    public void testSetLastName() {
        user.setLastName("Smith");
        assertEquals("Smith", user.getLastName());
    }

    @Test
    public void testSetUsername() {
        user.setUsername("janesmith");
        assertEquals("janesmith", user.getUsername());
    }

    @Test
    public void testSetPassword_Valid() {
        user.setPassword("NewPass1!");
        assertEquals("NewPass1!", user.getPassword());
    }

    @Test
    public void testSetMood() {
        user.setMood("happy");
        assertEquals("happy", user.getMood());
    }

    @Test
    public void testSetMemberSince() {
        user.setMemberSince("2025-01-01");
        assertEquals("2025-01-01", user.getMemberSince());
    }

    @Test
    public void testSetDateLoggedIn() {
        user.setDateLoggedIn("2025-01-02");
        assertEquals("2025-01-02", user.getDateLoggedIn());
    }

    @Test
    public void testSetStreak() {
        user.setStreak(10);
        assertEquals(10, user.getStreak());
    }

    @Test
    public void testUserRegistration() {
        IUser.userRegistration("Bob", "Lee", "boblee", "Bobbly123$");
        User registeredUser = userDAO.getUserByUsername("boblee");
        assertEquals("boblee", registeredUser.getUsername());
    }

    @Test
    public void testUserLogin() {
        User registeredUser = IUser.userLogin("johndoe21", "$Password123");
        assertEquals("johndoe21", registeredUser.getUsername());
    }

    @Test void testModifyUser() {
        user.modifyUser("firstName", "lastName", "username", "password", "mood", "memberSince");
        assertEquals("firstName", user.getFirstName());
        assertEquals("lastName", user.getLastName());
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("mood", user.getMood());
        assertEquals("memberSince", user.getMemberSince());
    }

}
