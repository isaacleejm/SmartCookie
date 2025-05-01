package cab302.iirtt.assignment1.model;

import java.util.List;

public interface IAIResponseDAO {
    /**
     * Adds a new AI response to the database.
     * @param aiResponse The AI Response to add.
     */
    abstract void addAIResponse(AIResponse aiResponse);
    /**
     * Updates an existing AI Response in the database.
     * @param aiResponse The AI Response to update.
     */
    abstract void updateAIResponse(AIResponse aiResponse);
    /**
     * Deletes an AI Response from the database.
     * @param aiResponse The AI Response to delete.
     */
    abstract void deleteAIResponse(AIResponse aiResponse);
    /**
     * Retrieves an AI Response from the database.
     * @param aiResponseID The id of the AI Response to retrieve.
     * @return The AI Response with the given id, or null if not found.
     */
    abstract AIResponse getAIResponse(int aiResponseID);
    /**
     * Retrieves a today's fortune cookie AI Response from the database.
     * @param userID The id of the user which the AI response belongs to.
     * @return The Fortune Cookie AI Response generated today with the given id, or null if not found.
     */
    abstract AIResponse getTodayFortune(int userID);
    /**
     * Retrieves all AI Responses from the database.
     * @return A list of all AI Responses in the database.
     */
    abstract List<AIResponse> getAllAIResponse();
    /**
     * Retrieves all AI Responses with the selected userID from the database.
     * @param userID The id of the user which the AI response belongs to.
     * @return A list of AI Responses in the database.
     */
    abstract List<AIResponse> getAIResponsesByUserID(int userID);
    /**
     * Retrieves all AI Responses with the selected userID and response type from the database.
     * @param userID The id of the user which the AI response belongs to.
     * @param responseType The type of the AI response.
     * @return A list of AI Responses in the database.
     */
    abstract List<AIResponse> getAIResponsesByType(int userID, AIResponse.ResponseType responseType);
}
