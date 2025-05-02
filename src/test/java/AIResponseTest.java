import cab302.iirtt.assignment1.model.AIResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AIResponseTest {
    AIResponse aiResponse;

    @BeforeEach
    public void setup() {
        this.aiResponse = new AIResponse(AIResponse.ResponseType.valueOf("FORTUNE_COOKIE"), 9, "\" + LocalDate.now() + \"", "one - two - three - four - five - six - seven - eight - nine - ten", "Can you count from one to ten like this, one - two -...", false, 3);
        this.aiResponse.setAIResponseID(1);
    }

    @Test
    public void testGetAIResponseID() {
        Assertions.assertEquals(1, this.aiResponse.getAIResponseID());
    }

    @Test
    public void testGetResponseType() {
        Assertions.assertEquals(AIResponse.ResponseType.valueOf("FORTUNE_COOKIE"), this.aiResponse.getResponseType());
    }

    @Test
    public void testGetResponseRating() {
        Assertions.assertEquals(9, this.aiResponse.getResponseRating());
    }

    @Test
    public void testGetResponseDate() {
        Assertions.assertEquals("\" + LocalDate.now() + \"", this.aiResponse.getResponseDate());
    }

    @Test
    public void testGetResponseText() {
        Assertions.assertEquals("one - two - three - four - five - six - seven - eight - nine - ten", this.aiResponse.getResponseText());
    }

    @Test
    public void testGetUserInput() {
        Assertions.assertEquals("Can you count from one to ten like this, one - two -...", this.aiResponse.getUserInput());
    }

    @Test
    public void testGetFavourite() {
        Assertions.assertFalse(this.aiResponse.getFavourite());
    }

    @Test
    public void testGetUserID() {
        Assertions.assertEquals(3, this.aiResponse.getUserID());
    }

    @Test
    public void testSetAIResponseID() {
        aiResponse.setAIResponseID(2);
        Assertions.assertEquals(2, aiResponse.getAIResponseID());
    }

    @Test
    public void testSetResponseType() {
        aiResponse.setResponseType(AIResponse.ResponseType.valueOf("MOTIVATIONAL_QUOTE"));
        Assertions.assertEquals(AIResponse.ResponseType.valueOf("MOTIVATIONAL_QUOTE"), aiResponse.getResponseType());
    }

    @Test
    public void testSetResponseRating() {
        aiResponse.setResponseRating(10);
        Assertions.assertEquals(10, aiResponse.getResponseRating());
    }

    @Test
    public void testSetResponseDate() {
        aiResponse.setResponseDate("\" + LocalDate.of(2025, 04, 29) + \"");
        Assertions.assertEquals("\" + LocalDate.of(2025, 04, 29) + \"", aiResponse.getResponseDate());
    }

    @Test
    public void testSetResponseText() {
        aiResponse.setResponseText("ten - nine - eight - seven - six - five - four - three - two - one");
        Assertions.assertEquals("ten - nine - eight - seven - six - five - four - three - two - one", aiResponse.getResponseText());
    }

    @Test
    public void testSetUserInput() {
        aiResponse.setUserInput("Count backwards from ten");
        Assertions.assertEquals("Count backwards from ten", aiResponse.getUserInput());
    }

    @Test
    public void testSetUserID() {
        aiResponse.setUserID(5);
        Assertions.assertEquals(5, aiResponse.getUserID());
    }

    @Test
    public void testSetFavourite() {
        aiResponse.setFavourite(true);
        Assertions.assertEquals(true, aiResponse.getFavourite());
    }
}
