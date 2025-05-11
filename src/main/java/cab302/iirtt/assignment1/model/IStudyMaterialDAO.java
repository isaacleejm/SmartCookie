package cab302.iirtt.assignment1.model;

import java.util.List;

/**
 * Interface for the StudyMaterial Data Access Object that handles
 * the CRUD operations for the StudyMaterial class with the database.
 */
public interface IStudyMaterialDAO {
    /**
     * Adds a new StudyMaterial object to the database.
     * @param studyMaterial The Study Material to add
     */
    abstract void addStudyMaterial(StudyMaterial studyMaterial);

    /**
     * Updates an existing Study Material in the database
     * @param studyMaterial The Study Material to update
     */
    abstract void updateStudyMaterial(StudyMaterial studyMaterial);

    /**
     * Deletes a Study Material from the database.
     * @param studyMaterialID The id of the Study Material to delete.
     */
    abstract void deleteStudyMaterialByID(int studyMaterialID);

    /**
     * Retrieves a Study Material from the database.
     * @param studyMaterialID The id of the Study Material to retrieve.
     * @return If the Study Material was found, return the Study Material with the given id; otherwise, return null.
     */
    abstract StudyMaterial getStudyMaterialByID(int studyMaterialID);

    /**
     * Retrieves all Study Materials from the database.
     * @return A list of all Study Materials from the database.
     */
    abstract List<StudyMaterial> getAllStudyMaterial();

    /**
     * Retrieves all Study Materials with the given userID from the database.
     * @param userID The id of the user which the Study Materials belongs to.
     * @return A list of Study Materials with the given id from the database.
     */
    abstract List<StudyMaterial> getStudyMaterialByUserID(int userID);
}
