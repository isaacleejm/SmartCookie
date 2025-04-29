package cab302.iirtt.assignment1.model;

import java.util.List;

public interface IAIResponseDAO {
    /**
     * Adds a new AI response to the database.
     * @param aiResponse The AI Response to add.
     */
    public void addAIResponse(AIResponse aiResponse);
    /**
     * Updates an existing AI Response in the database.
     * @param aiResponse The AI Response to update.
     */
    public void updateAIResponse(AIResponse aiResponse);
    /**
     * Deletes an AI Response from the database.
     * @param aiResponse The AI Response to delete.
     */
    public void deleteAIResponse(AIResponse aiResponse);
    /**
     * Retrieves an AI Response from the database.
     * @param aiResponseID The id of the AI Response to retrieve.
     * @return The AI Response with the given id, or null if not found.
     */
    public AIResponse getAIResponse(int aiResponseID);
    /**
     * Retrieves all AI Responses from the database.
     * @return A list of all AI Responses in the database.
     */
    public List<AIResponse> getAllAIResponse();
    /**
     * Retrieves all AI Responses with the selected userID from the database.
     * @return A list of all AI Responses in the database.
     */
    public List<AIResponse> getAIResponsesByUserID(int userID);
}
