package cab302.iirtt.assignment1.model;

import java.util.List;

public interface IStudyMaterial {
    /**
     * Creates a StudyMaterial Object and stores it into the studyMaterials Table Database.
     */
    abstract void createStudyMaterial();

    /**
     * Delete this studyMaterial object from the studyMaterials Table Database.
     */
    abstract void deleteStudyMaterial();

    /**
     * Allows logged-in User to view their StudyMaterials
     */
    abstract List<StudyMaterial> viewStudyMaterial();

    /**
     * Updates this StudyMaterial with new data and reflect this change onto the studyMaterials Table Database.
     * @param studyMaterial The studyMaterial object with updated information.
     */
    abstract void modifyStudyMaterial(StudyMaterial studyMaterial);

}
