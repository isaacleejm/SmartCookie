package cab302.iirtt.assignment1.model;

import cab302.iirtt.assignment1.MainApplication;

import java.util.List;

/**
 * Interface for the StudyMaterial Object with methods for the StudyMaterial class.
 */
public interface IStudyMaterial {
    StudyMaterialDAO studyMaterialDAO = new StudyMaterialDAO();
    /**
     * Creates a StudyMaterial Object and stores it into the studyMaterials Table Database.
     */
    public static void createStudyMaterial(String studyMaterialTitle, String studyMaterialSubject, String studyMaterialDescription, String dateModified, String dateCreated, int userID) {
        StudyMaterial newStudyMaterial = new StudyMaterial(studyMaterialTitle, studyMaterialSubject, studyMaterialDescription, dateModified, dateCreated, userID);
        studyMaterialDAO.addStudyMaterial(newStudyMaterial);
    }

    /**
     * Delete this studyMaterial object from the studyMaterials Table Database.
     * @param studyMaterialID The id of the Study Material to delete
     */
    public static void deleteStudyMaterial(int studyMaterialID) {
        studyMaterialDAO.deleteStudyMaterialByID(studyMaterialID);
    }

    /**
     * Allows logged-in User to view their StudyMaterials
     */
    public static List<StudyMaterial> getStudyMaterials() {
        return studyMaterialDAO.getStudyMaterialByUserID(MainApplication.currentUser.getUserID());
    }

    /**
     * Updates this StudyMaterial with new data and reflect this change onto the studyMaterials Table Database.
     * @param studyMaterial The studyMaterial object with updated information.
     */
    abstract void modifyStudyMaterial(StudyMaterial studyMaterial);

}
