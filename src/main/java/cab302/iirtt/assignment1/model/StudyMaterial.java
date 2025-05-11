package cab302.iirtt.assignment1.model;

import cab302.iirtt.assignment1.MainApplication;

import java.time.LocalDate;
import java.util.List;

/**
 * Class for StudyMaterials that holds StudyMaterial variables and have CRUD methods for the StudyMaterial class.
 */
public class StudyMaterial implements IStudyMaterial{
    private int studyMaterialID;
    private String studyMaterialTitle;
    private String studyMaterialSubject;
    private String studyMaterialDescription;
    private String dateModified;
    private String dateCreated;
    private int userID;
    private final StudyMaterialDAO studyMaterialDAO = new StudyMaterialDAO();

    /**
     * StudyMaterial Constructor that creates a StudyMaterial Object without studyMaterialID
     * @param studyMaterialTitle The title of the Study Material
     * @param studyMaterialSubject The subject of the Study Material
     * @param studyMaterialDescription The description of the Study Material
     * @param dateModified The date of which the details of the study material was updated
     * @param dateCreated The date of which the study material was created
     * @param userID The userID of the User this study material belongs to
     */
    public StudyMaterial(String studyMaterialTitle, String studyMaterialSubject, String studyMaterialDescription, String dateModified, String dateCreated, int userID) {
        this.studyMaterialTitle = studyMaterialTitle;
        this.studyMaterialSubject = studyMaterialSubject;
        this.studyMaterialDescription = studyMaterialDescription;
        this.dateModified = dateModified;
        this.dateCreated = dateCreated;
        this.userID = userID;
    }

    // Getter and Setter
    public int getStudyMaterialID() {
        return studyMaterialID;
    }
    public String getStudyMaterialTitle() {
        return studyMaterialTitle;
    }
    public String getStudyMaterialSubject() {
        return studyMaterialSubject;
    }
    public String getStudyMaterialDescription() {
        return studyMaterialDescription;
    }
    public String getDateModified() {
        return dateModified;
    }
    public String getDateCreated() {
        return dateCreated;
    }
    public int getUserID() {
        return userID;
    }

    public void setStudyMaterialID(int studyMaterialID) {
        this.studyMaterialID = studyMaterialID;
    }
    public void setStudyMaterialTitle(String studyMaterialTitle) {
        this.studyMaterialTitle = studyMaterialTitle;
    }
    public void setStudyMaterialSubject(String studyMaterialSubject) {
        this.studyMaterialSubject = studyMaterialSubject;
    }
    public void setStudyMaterialDescription(String studyMaterialDescription) {
        this.studyMaterialDescription = studyMaterialDescription;
    }
    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Updates this StudyMaterial with new data and reflect this change onto the studyMaterials Table Database.
     *
     * @param studyMaterial The studyMaterial object with updated information.
     */
    @Override
    public void modifyStudyMaterial(StudyMaterial studyMaterial) {
        this.studyMaterialTitle = studyMaterial.getStudyMaterialTitle();
        this.studyMaterialSubject = studyMaterial.getStudyMaterialSubject();
        this.studyMaterialDescription = studyMaterial.getStudyMaterialDescription();
        this.dateModified = LocalDate.now().toString();
        this.dateCreated = studyMaterial.getDateCreated();
        this.userID = studyMaterial.getUserID();
        studyMaterialDAO.updateStudyMaterial(this);
    }
}
