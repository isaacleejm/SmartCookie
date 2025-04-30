import cab302.iirtt.assignment1.model.StudyGoal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudyGoalTest {
    StudyGoal studyGoal;

    public StudyGoalTest() {
    }

    @BeforeEach
    public void setup() {
        this.studyGoal = new StudyGoal("Revise for CAB123", "Revise lectures 3 and 7 for CAB123 test", "High", false, true, "\" + LocalDate.of(2025, 04, 15) + \"", "\" + LocalDate.now() + \"", "\" + LocalDate.now() + \"", 2);
        this.studyGoal.setStudyGoalID(1);
    }

    @Test
    public void testGetStudyGoalID() {
        Assertions.assertEquals(1, this.studyGoal.getStudyGoalID());
    }

    @Test
    public void testGetStudyGoalTitle() {
        Assertions.assertEquals("Revise for CAB123", this.studyGoal.getStudyGoalTitle());
    }

    @Test
    public void testGetStudyGoalDescription() {
        Assertions.assertEquals("Revise lectures 3 and 7 for CAB123 test", this.studyGoal.getStudyGoalDescription());
    }

    @Test
    public void testGetStudyGoalPriority() {
        Assertions.assertEquals("High", this.studyGoal.getStudyGoalPriority());
    }

    @Test
    public void testGetStudyGoalStatus() {
        Assertions.assertEquals(false, this.studyGoal.getStudyGoalStatus());
    }

    @Test
    public void testGetStudyGoalPinned() {
        Assertions.assertEquals(true, this.studyGoal.getStudyGoalPinned());
    }

    @Test
    public void testGetStudyGoalDueDate() {
        Assertions.assertEquals(false, this.studyGoal.getStudyGoalStatus());
    }

    @Test
    public void testGetLastModified() {
        Assertions.assertEquals("\" + LocalDate.now() + \"", this.studyGoal.getLastModified());
    }

    @Test
    public void testGetDateCreated() {
        Assertions.assertEquals("\" + LocalDate.now() + \"", this.studyGoal.getDateCreated());
    }

    @Test
    public void testGetUserID() {
        Assertions.assertEquals(2, this.studyGoal.getUserID());
    }

    @Test
    public void testSetGoalTitle() {
        studyGoal.setStudyGoalTitle("Full marks on maths");
        Assertions.assertEquals("Full marks on maths", studyGoal.getStudyGoalTitle());
    }

    @Test
    public void testSetGoalDescription() {
        studyGoal.setStudyGoalDescription("No exceptions, only 100");
        Assertions.assertEquals("No exceptions, only 100", studyGoal.getStudyGoalDescription());
    }

    @Test
    public void testSetGoalPinned() {
        studyGoal.setStudyGoalPinned(true);
        Assertions.assertTrue(studyGoal.getStudyGoalPinned());
    }

    @Test
    public void testSetGoalPriority() {
        studyGoal.setStudyGoalPriority("high");
        Assertions.assertEquals("high", studyGoal.getStudyGoalPriority());
    }

    @Test
    public void testSetGoalStatus() {
        studyGoal.setStudyGoalStatus(true);
        Assertions.assertTrue(studyGoal.getStudyGoalStatus());
    }

    @Test
    public void testSetGoalID() {
        studyGoal.setStudyGoalID(1421);
        Assertions.assertEquals(1421, studyGoal.getStudyGoalID());
    }

    @Test
    public void testSetDueDate() {
        studyGoal.setDueDate("\" + LocalDate.of(2025, 05, 19) + \"");
        Assertions.assertEquals("\" + LocalDate.of(2025, 05, 19) + \"", studyGoal.getDueDate());
    }

    @Test
    public void testSetLastModified() {
        studyGoal.setLastModified("\" + LocalDate.of(2025, 03, 20) + \"");
        Assertions.assertEquals("\" + LocalDate.of(2025, 03, 20) + \"", studyGoal.getLastModified());
    }

    @Test
    public void testSetDateCreated() {
        studyGoal.setDateCreated("\" + LocalDate.now() + \"");
        Assertions.assertEquals("\" + LocalDate.now() + \"", studyGoal.getDateCreated());
    }

    @Test
    public void testSetUserID() {
        studyGoal.setUserID(156);
        Assertions.assertEquals(156, studyGoal.getUserID());
    }
}
