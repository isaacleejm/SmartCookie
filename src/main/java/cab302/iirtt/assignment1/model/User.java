package cab302.iirtt.assignment1.model;

import cab302.iirtt.assignment1.MainApplication;

import java.util.List;

/**
 * Class that implements IUser Interface  for Users that holds User variables and have CRUD methods for the User class.
 */
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
    private final UserDAO userDAO = new UserDAO();
    private final StudyGoalDAO studyGoalDAO = new StudyGoalDAO();
    private final StudyMaterialDAO studyMaterialDAO = new StudyMaterialDAO();
    private final AIResponseDAO aiResponseDAO = new AIResponseDAO();


    /**
     * Default User Constructor
     */
    public User() {

    }

    /**
     * User Constructor that creates a User Object without userID
     * @param firstName The user's first name
     * @param lastName The user's last name
     * @param username The account's username
     * @param password The account's password
     * @param mood The user's current mood
     * @param memberSince The date of which the user created this account
     * @param dateLoggedIn The date of which the user last logged in
     * @param streak Number of consecutive days the User logged in
     */
    public User(String firstName, String lastName, String username, String password, String mood, String memberSince, String dateLoggedIn, int streak) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.mood = mood;
        this.memberSince = memberSince;
        this.dateLoggedIn = dateLoggedIn;
        this.streak = streak;
    }

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
        this.password = password;
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

    /**
     * Deletes all data related to this User, including account, studyGoals, pastResponses and studyMaterial
     */
    @Override
    public void deleteUser() {
        List<StudyMaterial> studyMaterialList = studyMaterialDAO.getStudyMaterialByUserID(userID);
        List<StudyGoal> studyGoalList = studyGoalDAO.getStudyGoalsByUserID(userID);
        List<AIResponse> aiResponseList = aiResponseDAO.getAIResponsesByUserID(userID);
        for (StudyMaterial studyMaterial : studyMaterialList) {
            studyMaterialDAO.deleteStudyMaterialByID(studyMaterial.getStudyMaterialID());
        }
        for (StudyGoal studyGoal : studyGoalList) {
            studyGoalDAO.deleteStudyGoal(studyGoal);
        }
        for (AIResponse aiResponse : aiResponseList) {
            aiResponseDAO.deleteAIResponse(aiResponse);
        }
        userDAO.deleteUserByID(this);
    }

    /**
     * Update the current User's account information with the new user data.
     * @param firstName The user's first name
     * @param lastName The user's last name
     * @param username The account's username
     * @param password The account's password
     * @param mood The user's current mood
     * @param memberSince The date of which the user created the account
     * @return Return true if update is successful; otherwise, return false.
     */
    @Override
    public boolean modifyUser(String firstName, String lastName, String username, String password, String mood, String memberSince) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.mood = mood;
        this.memberSince = memberSince;
        MainApplication.currentUser = this;
        userDAO.updateUser(this);
        return false;
    }
}
