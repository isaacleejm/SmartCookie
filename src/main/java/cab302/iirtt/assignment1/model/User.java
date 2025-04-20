package cab302.iirtt.assignment1.model;

public class User implements IUser {
    // User Variables
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mood;
    private String memberSince;


    // Constructor
    public User(String firstName, String lastName, String email, String password, String mood, String memberSince) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.mood = mood;
        this.memberSince = memberSince;
//        this.mood = "neutral";
//        this.memberSince = LocalDate.now().toString();
    }


//    public User(int userID, String firstName, String lastName, String email, String password) {
//        this.userID = userID;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.mood = "neutral";
//        this.memberSince = LocalDate.now().toString();
//    }

    // Getter and Setter
    public int getUserID() { return userID; }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getMood() {
        return mood;
    }
    public String getMemberSince() {
        return memberSince;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setMood(String mood) {
        this.mood = mood;
    }
    public void setMemberSince(String memberSince) {
        this.memberSince = memberSince;
    }


    @Override
    public void userLogin(String email, String password) {
        // NOT YET IMPLEMENTED
    }

    @Override
    public void userRegistration(String firstName, String lastName, String email, String password, String confirmPassword) {
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
