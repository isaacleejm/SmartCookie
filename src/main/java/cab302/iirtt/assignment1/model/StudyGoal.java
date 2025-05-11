package cab302.iirtt.assignment1.model;

public class StudyGoal implements IStudyGoal {

    private int studyGoalID;
    private String studyGoalTitle;
    private String studyGoalDescription;
    private String studyGoalPriority;
    private boolean studyGoalStatus;
    private boolean studyGoalPinned;
    private String dueDate;
    private String lastModified;
    private String dateCreated;
    private int userID;

    /**
     * Study goal constructor that creates a StudyGoal object without studyGoalID
     * @param studyGoalTitle The study goal's title
     * @param studyGoalDescription The study goal's description
     * @param studyGoalPriority The study goal's priority (Low, Medium, High)
     * @param studyGoalStatus The study goal's status (In Progress, Completed)
     * @param studyGoalPinned Whether the study goal is pinned or not
     * @param dueDate The study goal's assigned due date
     * @param lastModified The date of when the study goal was last modified
     * @param dateCreated The date of when the study goal was created
     * @param userID The userID of the User who created the StudyGoal
     */
    public StudyGoal(String studyGoalTitle, String studyGoalDescription, String studyGoalPriority, boolean studyGoalStatus, boolean studyGoalPinned, String dueDate, String lastModified, String dateCreated, int userID) {
        this.studyGoalTitle = studyGoalTitle;
        this.studyGoalDescription = studyGoalDescription;
        this.studyGoalPriority = studyGoalPriority;
        this.studyGoalStatus = studyGoalStatus;
        this.studyGoalPinned = studyGoalPinned;
        this.dueDate = dueDate;
        this.lastModified = lastModified;
        this.dateCreated = dateCreated;
        this.userID = userID;

//        this.mood = "neutral";
//        this.memberSince = LocalDate.now().toString();
    }

    // Getter and Setter
    public int getStudyGoalID() { return studyGoalID; }
    public String getStudyGoalTitle() {
        return studyGoalTitle;
    }
    public String getStudyGoalDescription() {
        return studyGoalDescription;
    }
    public String getStudyGoalPriority() { return studyGoalPriority; }
    public boolean getStudyGoalStatus() {return studyGoalStatus; }
    public boolean getStudyGoalPinned() {
        return studyGoalPinned;
    }
    public String getDueDate() {
        return dueDate;
    }
    public String getLastModified() {
        return lastModified;
    }
    public String getDateCreated() {
        return dateCreated;
    }
    public int getUserID() {
        return userID;
    }

    public void setStudyGoalID(int studyGoalID) { this.studyGoalID = studyGoalID; }
    public void setStudyGoalTitle(String studyGoalTitle) {
        this.studyGoalTitle = studyGoalTitle;
    }
    public void setStudyGoalDescription(String studyGoalDescription) { this.studyGoalDescription = studyGoalDescription; }
    public void setStudyGoalPriority(String studyGoalPriority) { this.studyGoalPriority = studyGoalPriority; }
    public void setStudyGoalStatus(boolean studyGoalStatus) { this.studyGoalStatus = studyGoalStatus; }
    public void setStudyGoalPinned(boolean studyGoalPinned) {
        this.studyGoalPinned = studyGoalPinned;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public void setLastModified(String lastModified) { this.lastModified = lastModified; }
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * newStudyGoal is a new study goal including the parameters, and also dateCreated and userID which are not allowed to be set by the user.
     * @param studyGoalTitle The study goal's title
     * @param studyGoalDescription The study goal's description
     * @param studyGoalPriority The study goal's priority (Low, Medium, High)
     * @param studyGoalStatus The study goal's status (In Progress, Completed)
     * @param studyGoalPinned Whether the study goal is pinned or not
     * @param dueDate The study goal's assigned due date
     * @param dateCreated The date of when the study goal was created
     * @param userID The userID of the User who created the StudyGoal
     */
    @Override
    public void newStudyGoal(String studyGoalTitle, String studyGoalDescription, String studyGoalPriority, boolean studyGoalStatus, boolean studyGoalPinned, String dueDate, String dateCreated, int userID) {

    }

    /**
     * Deletes the chosen study goal
     */
    @Override
    public void deleteStudyGoal() {

    }

    /**
     * Changes the study goal priority from one of the three options (Low, Medium, High) by accessing the studyGoalPriority
     * @param studyGoalPriority
     */
    @Override
    public void changeStudyGoalPriority(String studyGoalPriority) {

    }

    /**
     * Toggles the study goal status to one of the two options, being whichever is not currently selected (In Progress, Completed)
     */
    @Override
    public void toggleStudyGoalStatus() {

    }

    /**
     * Toggles whether the study goal is pinned, being True if pinned and False if not pinned.
     */
    @Override
    public void togglePinStudyGoal() {

    }

    /**
     * Changes the study goal's due date set by the user for the parameter.
     * @param dueDate
     */
    @Override
    public void changeStudyGoalDueDate(String dueDate) {

    }

    /**
     * Changes the study goal title set by the user for the parameter.
     * @param studyGoalTitle
     */
    @Override
    public void changeStudyGoalTitle(String studyGoalTitle) {

    }

    /**
     * Changes the study goal description set by the user for the description.
     * @param studyGoalDescription
     */
    @Override
    public void changeStudyGoalDescription(String studyGoalDescription) {

    }
}
