package cab302.iirtt.assignment1.model;

import java.util.List;

/**
 * Interface for the Study Goal Data Access Object that handles
 * the CRUD operations for the Contact class with the database.
 */
public interface IStudyGoalDAO {
    /**
     * Adds a new Study Goal to the database.
     * @param studyGoal The studyGoal to add.
     */
    public void addStudyGoal(StudyGoal studyGoal);
    /**
     * Updates an existing studyGoal in the database.
     * @param studyGoal The studyGoal to update.
     */
    public void updateStudyGoal(StudyGoal studyGoal);
    /**
     * Deletes a studyGoal from the database.
     * @param studyGoal The StudyGoal to delete.
     */
    public void deleteStudyGoal(StudyGoal studyGoal);
    /**
     * Retrieves a study goal from the database.
     * @param studyGoalID The id of the study goal to retrieve.
     * @return The study goal with the given id, or null if not found.
     */
    public StudyGoal getStudyGoal(int studyGoalID);
    /**
     * Retrieves all study goals from the database.
     * @return A list of all contacts in the database.
     */
    public List<StudyGoal> getAllStudyGoal();
}
