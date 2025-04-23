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
    public void testUserID() {
        assertEquals(1, user.getUserID());

    }

    @Test
    public void testFirstName() {
        assertEquals("John", user.getFirstName());

    }

    @Test
    public void testLastName() {
        assertEquals("Doe", user.getLastName());
    }

    @Test
    public void testUsername() {
        assertEquals("johndoe", user.getUsername());
    }

    @Test
    public void testPassword() {
        assertEquals("$Password123", user.getPassword());
    }

    @Test
    public void testMood() {
        assertEquals("neutral", user.getMood());
    }

    @Test
    public void testMemberSince() {
        assertEquals("2024-04-23", user.getMemberSince());
    }

    @Test
    public void testDateLoggedIn() {
        assertEquals("2024-04-23", user.getDateLoggedIn());
    }

    @Test
    public void testStreak() {
        assertEquals(0, user.getStreak());
    }

}
