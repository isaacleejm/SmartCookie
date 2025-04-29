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
    public void testGetDueDate() {
        Assertions.assertEquals("\" + LocalDate.of(2025, 04, 15) + \"", this.studyGoal.getDueDate());
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

}
