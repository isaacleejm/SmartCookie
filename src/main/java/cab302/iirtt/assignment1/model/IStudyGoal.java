package cab302.iirtt.assignment1.model;

public interface IStudyGoal {

    //The user has pressed the "New Goal +" button in the study goals page and has input all relevant information to create a new study goal, then pressed confirm to create the Study Goal.
    //Takes parameters of all the necessary fields to construct a studyGoal, including the studyGoalID.
    abstract void newStudyGoal(int studyGoalID, String studyGoalTitle, String studyGoalDescription, String studyGoalPriority, String studyGoalStatus, String pinned, String dueDate, String dateCreated, String userID);

    //The user has pressed "Remove Goal" and confirmed "Yes" that they are sure of removing the goal
    //Takes the parameter of the studyGoalID to know which study goal to delete based on its unique identifier.
    abstract void deleteStudyGoal(int studyGoalID);

    //The user has selected "Edit Goal" then clicked on one of the buttons for High, Medium, or Low. This sets the priority of the Goal to the priority level selected.
    //Takes the parameters of the studyGoalID to know which study goal to edit, along with the studyGoalPriority of that study goal.
    abstract void changeStudyGoalPriority(int studyGoalID, String studyGoalPriority);

    //The user has selected to complete a goal, or reset a goal back to in progress by clicking on the study goal and pressing the complete goal or reset goal button.
    //Takes the parameters of the studyGoalID to know which study goal, and the Boolean of studyGoalStatus. In Progress = False and Completed = True. This Boolean for the status
    //specifies the status as "complete", if a goal is In Progress it is not complete, if it is completed it is.
    abstract void changeStudyGoalStatus(int studyGoalID, Boolean studyGoalStatus);

    // The user has selected to pin the study goal after clicking on the goal then pressing Pin Goal, or pressing Pin Goal in the "Edit Goal" section.
    // Takes parameters of the studyGoalID to know which study goal, and the Boolean of Pinned. If it is not pinned, it is False, if it is pinned it is True.
    abstract void pinStudyGoal (int studyGoalID, Boolean pinned);

    // The User has selected to change the due date of the goal by clicking on the goal, then pressing "Edit Goal", then entering a dueDate and clicking "Generate Changes".
    // Takes the studyGoalID to know which study goal to edit, and the string dueDate which is entered into through a text box.
    abstract void changeStudyGoalDueDate(int studyGoalID, String dueDate);

    // The User has selected to change the studyGoalTitle of the goal by clicking on the goal, then pressing "Edit Goal", then entering a studyGoalTitle and clicking "Generate Changes".
    // Takes the studyGoalID to know which study goal to edit, and the string studyGoalTitle which is entered into through a text box.
    abstract void changeStudyGoalTitle(int studyGoalID, String studyGoalTitle);

    // The User has selected to change the studyGoalDescription of the goal by clicking on the goal, then pressing "Edit Goal", then entering a studyGoalDescription and clicking "Generate Changes".
    // Takes the studyGoalID to know which study goal to edit, and the string studyGoalDescription which is entered into through a text box. The maximum character size for a studyGoalDescription is 500.
    abstract void changeStudyGoalDescription(int studyGoalID, String studyGoalDescription);

}
