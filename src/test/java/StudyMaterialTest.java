import cab302.iirtt.assignment1.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudyMaterialTest {
    StudyMaterial studyMaterial;
    StudyMaterialDAO studyMaterialDAO;

    @BeforeEach
    public void setup() {
        studyMaterialDAO = new StudyMaterialDAO();
        studyMaterial = studyMaterialDAO.getStudyMaterialByID(studyMaterialDAO.addStudyMaterial(new StudyMaterial("Lecture 5 Part B", "CAB123", "Learning about Object Oriented Programming", LocalDate.now().toString(), LocalDate.now().toString(), 1)));
        studyMaterial.setStudyMaterialID(1);
    }

    @Test
    public void testGetStudyMaterialID() {
        assertEquals(1, studyMaterial.getStudyMaterialID());
    }

    @Test
    public void testGetStudyMaterialTitle() {
        assertEquals("Lecture 5 Part B", studyMaterial.getStudyMaterialTitle());
    }

    @Test
    public void testGetStudyMaterialSubject() {
        assertEquals("CAB123", studyMaterial.getStudyMaterialSubject());
    }

    @Test
    public void testGetStudyMaterialDescription() {
        assertEquals("Learning about Object Oriented Programming", studyMaterial.getStudyMaterialDescription());
    }

    @Test
    public void testGetDateModified() {
        assertEquals(LocalDate.now().toString(), studyMaterial.getDateModified());
    }

    @Test
    public void testGetDateCreated() {
        assertEquals(LocalDate.now().toString(), studyMaterial.getDateCreated());
    }

    @Test
    public void testGetUserID() {
        assertEquals(1, studyMaterial.getUserID());
    }

    @Test
    public void testSetStudyMaterialID() {
        studyMaterial.setStudyMaterialID(5);
        assertEquals(5, studyMaterial.getStudyMaterialID());
    }

    @Test
    public void testSetStudyMaterialTitle() {
        studyMaterial.setStudyMaterialTitle("Tutorial 5 Part A");
        assertEquals("Tutorial 5 Part A", studyMaterial.getStudyMaterialTitle());
    }

    @Test
    public void testSetStudyMaterialSubject() {
        studyMaterial.setStudyMaterialSubject("CAB321");
        assertEquals("CAB321", studyMaterial.getStudyMaterialSubject());
    }

    @Test
    public void testSetStudyMaterialDescription() {
        studyMaterial.setStudyMaterialDescription("There are static and dynamic list.");
        assertEquals("There are static and dynamic list.", studyMaterial.getStudyMaterialDescription());
    }

    @Test
    public void testSetDateModified() {
        studyMaterial.setDateModified(LocalDate.of(2025, 5,13).toString());
        assertEquals(LocalDate.of(2025, 5,13).toString(), studyMaterial.getDateModified());
    }

    @Test
    public void testSetDateCreated() {
        studyMaterial.setDateCreated(LocalDate.of(2025, 5,25).toString());
        assertEquals(LocalDate.of(2025, 5,25).toString(), studyMaterial.getDateCreated());
    }

    @Test
    public void testSetUserID() {
        studyMaterial.setUserID(2);
        assertEquals(2, studyMaterial.getUserID());
    }

    @Test
    public void testCreateStudyMaterial() {
        int studyMaterialID = IStudyMaterial.createStudyMaterial("My New Subject", "ABC123", "This subject is all about alphabets.", LocalDate.of(2025,4, 24).toString(), LocalDate.of(2025, 4, 22).toString(), 1);
        StudyMaterial newStudyMaterial = studyMaterialDAO.getStudyMaterialByID(studyMaterialID);
        assertEquals("My New Subject", newStudyMaterial.getStudyMaterialTitle());
        assertEquals("ABC123", newStudyMaterial.getStudyMaterialSubject());
        assertEquals("This subject is all about alphabets.", newStudyMaterial.getStudyMaterialDescription());
        assertEquals(LocalDate.of(2025,4, 24).toString(), newStudyMaterial.getDateModified());
        assertEquals(LocalDate.of(2025, 4, 22).toString(), newStudyMaterial.getDateCreated());
        assertEquals(1, newStudyMaterial.getUserID());
    }

    @Test
    public void testGetAllStudyMaterials() {
        List<StudyMaterial> studyMaterialList = studyMaterialDAO.getAllStudyMaterial();
        assertFalse(studyMaterialList.isEmpty());
    }

    @Test
    public void testDeleteStudyMaterial() {
        List<StudyMaterial> studyMaterialList = studyMaterialDAO.getAllStudyMaterial();
        StudyMaterial oldFirstStudyMaterial = studyMaterialList.getFirst();
        IStudyMaterial.deleteStudyMaterial(studyMaterialList.getFirst().getStudyMaterialID());
        studyMaterialList = studyMaterialDAO.getAllStudyMaterial();
        assertNotEquals(oldFirstStudyMaterial.getStudyMaterialTitle(), studyMaterialList.getFirst().getStudyMaterialTitle());
    }

    @Test
    public void testModifyStudyMaterial() {
        StudyMaterial updatedStudyMaterial = studyMaterial;
        updatedStudyMaterial.setStudyMaterialTitle("New Title");
        updatedStudyMaterial.setStudyMaterialSubject("New Subject");
        updatedStudyMaterial.setStudyMaterialDescription("New Description");
        updatedStudyMaterial.setDateModified(LocalDate.of(2025, 1, 14).toString());
        updatedStudyMaterial.setDateCreated(LocalDate.of(2025, 2, 23).toString());
        updatedStudyMaterial.setUserID(1);
        studyMaterial.modifyStudyMaterial(updatedStudyMaterial);
        assertEquals(updatedStudyMaterial.getStudyMaterialTitle(), studyMaterial.getStudyMaterialTitle());
        assertEquals(updatedStudyMaterial.getStudyMaterialSubject(), studyMaterial.getStudyMaterialSubject());
        assertEquals(updatedStudyMaterial.getStudyMaterialDescription(), studyMaterial.getStudyMaterialDescription());
        assertEquals(updatedStudyMaterial.getDateModified(), studyMaterial.getDateModified());
        assertEquals(updatedStudyMaterial.getDateCreated(), studyMaterial.getDateCreated());
        assertEquals(updatedStudyMaterial.getUserID(), studyMaterial.getUserID());

    }

}
