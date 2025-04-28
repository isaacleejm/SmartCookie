package cab302.iirtt.assignment1.model;

public interface IStudyGoal {

    // The user has pressed the "New Goal +" button in the study goals page and has input all relevant information to create a new study goal, then pressed confirm to create the Study Goal.
    // Takes parameters of all the necessary fields to construct a studyGoal, not including the studyGoalID as it is auto-generated.
    abstract void newStudyGoal(String studyGoalTitle, String studyGoalDescription, String studyGoalPriority, boolean studyGoalStatus, boolean pinned, String dueDate, String dateCreated, int userID);

    // The user has pressed "Remove Goal" and confirmed "Yes" that they are sure of removing the goal
    // Deletes the current StudyGoal Object
    abstract void deleteStudyGoal();

    // The user has selected "Edit Goal" then clicked on one of the buttons for High, Medium, or Low. This sets the priority of the Goal to the priority level selected.
    // Replaces the current StudyGoal Object's studyGoalPriority to new studyGoalPriority option.
    abstract void changeStudyGoalPriority(String studyGoalPriority);

    // The user has selected to complete a goal, or reset a goal back to in progress by clicking on the study goal and pressing the complete goal or reset goal button.
    // Checks studyGoalStatus value of the current StudyGoal Object, and if it is false (In Progress), then set it to True (Completed), otherwise, set it to False (In Progress).
    abstract void toggleStudyGoalStatus();

    // The user has selected to pin the study goal after clicking on the goal then pressing Pin Goal, or pressing Pin Goal in the "Edit Goal" section.
    // Checks pinned value, and if it is true (pinned), then set it to False (unpinned), otherwise, set it to True (pinned).
    abstract void togglePinStudyGoal ();

    // The User has selected to change the due date of the goal by clicking on the goal, then pressing "Edit Goal", then entering a dueDate and clicking "Generate Changes".
    // Takes the studyGoalID to know which study goal to edit, and the string dueDate which is entered into through a text box.
    abstract void changeStudyGoalDueDate(String dueDate);

    // The User has selected to change the studyGoalTitle of the goal by clicking on the goal, then pressing "Edit Goal", then entering a studyGoalTitle and clicking "Generate Changes".
    // Replaces the current StudyGoal Object's studyGoalTitle to the new studyGoalTitle string text through a text box.
    abstract void changeStudyGoalTitle(String studyGoalTitle);

    // The User has selected to change the studyGoalDescription of the goal by clicking on the goal, then pressing "Edit Goal", then entering a studyGoalDescription and clicking "Generate Changes".
    // Replaces the current StudyGoal Object's studyGoalDescription to the new studyGoalDescription string text through a text box. The maximum character size for a studyGoalDescription is 500.
    abstract void changeStudyGoalDescription(String studyGoalDescription);

}
