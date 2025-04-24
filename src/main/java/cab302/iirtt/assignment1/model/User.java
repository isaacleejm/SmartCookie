package cab302.iirtt.assignment1.model;

import java.time.LocalDate;

public class User implements IUser {
    // User Variables
    private int userID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String mood;
    private String memberSince;
    private String dateLoggedIn;
    private int streak;


    // Default Constructor
    public User() {

    }
    // Constructor
    public User(String firstName, String lastName, String username, String password, String mood, String memberSince, String dateLoggedIn, int streak) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.mood = mood;
        this.memberSince = memberSince;
        this.dateLoggedIn = dateLoggedIn;
        this.streak = streak;  

//        this.mood = "neutral";
//        this.memberSince = LocalDate.now().toString();
    }


//    public User(int userID, String firstName, String lastName, String username, String password) {
//        this.userID = userID;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.username = username;
//        this.password = password;
//        this.mood = "neutral";
//        this.memberSince = LocalDate.now().toString();
//    }

    // Getter and Setter
    public int getUserID() { return userID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getMood() { return mood; }
    public String getMemberSince() { return memberSince; }
    public String getDateLoggedIn() { return dateLoggedIn; }
    public int getStreak() { return streak; }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        if (!password.trim().isEmpty()) {
            if (password.length() > 7) {
                boolean hasUpper = false;
                boolean hasLower = false;
                boolean hasDigit = false;
                boolean hasSpaces = false;
                boolean hasSpecial = false;
                for (int i = 0; i < password.length(); i++) {
                    char character = password.charAt(i);
                    if (Character.isLowerCase(character)) {
                        hasUpper = true;
                    }
                    if (Character.isUpperCase(character)) {
                        hasLower = true;
                    }
                    if (Character.isDigit(character)) {
                        hasDigit = true;
                    }
                    if (Character.isSpaceChar(character)) {
                        hasSpaces = true;
                    }
                    if (!Character.isLetter(character) && !Character.isDigit(character) && !Character.isWhitespace(character)) {
                        hasSpecial = true;
                    }
                }
                if (!hasSpaces) {
                    if (hasUpper && hasLower) {
                        if (hasDigit) {
                            if (hasSpecial) {
                                System.out.println("ALL CONDITIONS OF THE PASSWORD HAVE BEEN MET"); // ONCE ALL CONDITIONS WAS MET AND THE PASSWORD IS VALID
                                this.password = password;
                            } else {
                                System.out.println("Password must have at least one Special Characters");
                            }
                        } else {
                            System.out.println("Password must have at least one number");
                        }
                    } else {
                        System.out.println("Password must have both Upper and Lower Cases");
                    }
                } else {
                    System.out.println("Password cannot have spaces");
                }
            } else {
                System.out.println("Password must be more than 7");
            }
        } else {
            System.out.println("Password cannot be empty!");
        }
    }
    public void setMood(String mood) {
        this.mood = mood;
    }
    public void setMemberSince(String memberSince) {
        this.memberSince = memberSince;
    }
    public void setDateLoggedIn(String dateLoggedIn) {
        this.dateLoggedIn = dateLoggedIn;
    }
    public void setStreak(int streak) {
        this.streak = streak;
    }

    @Override
    public void userLogout() {
        // NOT YET IMPLEMENTED
    }

    @Override
    public boolean deleteUser() {
        // NOT YET IMPLEMENTED
        return false;
    }

    @Override
    public boolean modifyUser(String firstName, String lastName, String username, String password, String mood, String memberSince) {
        // NOT YET IMPLEMENTED
        return false;
    }
}
