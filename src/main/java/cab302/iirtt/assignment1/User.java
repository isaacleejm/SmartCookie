package cab302.iirtt.assignment1;

import java.time.LocalDate;

public class User implements IUser {
    // User Variables
    private String userID;
    private String name;
    private String email;
    private String password;
    private String mood;
    private String memberSince;


    // Constructor
    public User(String userID, String name, String email, String password) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.mood = "neutral";
        this.memberSince = LocalDate.now().toString();
    }


    @Override
    public void userLogin(String email, String password) {
        // NOT YET IMPLEMENTED
    }

    @Override
    public void userRegistration(String name, String password, String confirmPassword, String phoneNumber) {
        // NOT YET IMPLEMENTED
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
    public boolean modifyUser() {
        // NOT YET IMPLEMENTED
        return false;
    }
}
