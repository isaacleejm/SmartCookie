import cab302.iirtt.assignment1.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    User user;

    @BeforeEach
    public void setup() {
        user = new User("John", "Doe", "johndoe", "$Password123", "neutral", "2024-04-23", "2024-04-23", 0);
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
        assertEquals("johndoe", user.getUsername());
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
        assertEquals("2024-04-23", user.getDateLoggedIn());
    }

    @Test
    public void testGetStreak() {
        assertEquals(0, user.getStreak());
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

}
