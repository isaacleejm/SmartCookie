package cab302.iirtt.assignment1.model;

public class StudyGoal implements IStudyGoal {

    private int studyGoalID;
    private String studyGoalTitle;
    private String studyGoalDescription;
    private String studyGoalPriority;
    private boolean studyGoalStatus;
    private boolean pinned;
    private String dueDate;
    private String lastModified;
    private String dateCreated;
    private int userID;

    // Constructor
    public StudyGoal(String studyGoalTitle, String studyGoalDescription, String studyGoalPriority, boolean studyGoalStatus, boolean pinned, String dueDate, String lastModified, String dateCreated, int userID) {
        this.studyGoalTitle = studyGoalTitle;
        this.studyGoalDescription = studyGoalDescription;
        this.studyGoalPriority = studyGoalPriority;
        this.studyGoalStatus = studyGoalStatus;
        this.pinned = pinned;
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
    public boolean getPinned() {
        return pinned;
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
    public void setPinned(boolean pinned) {
        this.pinned = pinned;
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

    @Override
    public void newStudyGoal(String studyGoalTitle, String studyGoalDescription, String studyGoalPriority, boolean studyGoalStatus, boolean pinned, String dueDate, String dateCreated, int userID) {

    }

    @Override
    public void deleteStudyGoal() {

    }

    @Override
    public void changeStudyGoalPriority(String studyGoalPriority) {

    }

    @Override
    public void toggleStudyGoalStatus() {

    }

    @Override
    public void togglePinStudyGoal() {

    }

    @Override
    public void changeStudyGoalDueDate(String dueDate) {

    }

    @Override
    public void changeStudyGoalTitle(String studyGoalTitle) {

    }

    @Override
    public void changeStudyGoalDescription(String studyGoalDescription) {

    }
}
