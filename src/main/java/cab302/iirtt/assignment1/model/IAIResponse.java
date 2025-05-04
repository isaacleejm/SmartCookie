package cab302.iirtt.assignment1.model;

import java.util.List;

public interface IAIResponse {

    /**
     * Generates AI message based on user input and is one of either a fortune cookie, motivational quote, fun prediction or advice/tips.
     * @param userInput The study material uploaded by the user.
     */
    abstract void generateResponse(String userInput);

    /**
     * Allows the user to rate their received response out of 10.
     * @param responseID Unique identification number for AI generated response.
     * @param responseRating Rating given by user.
     */
    abstract void rateResponse(int responseID, int responseRating) ;

    /**
     * Allows user to view past responses.
     * @param userID Unique identification number for specific user.
     * @return A list of AI responses generated for a particular user.
     */
    abstract List<AIResponse> viewResponses(String userID);

    // User has the option to save their favourite messages
    // Each user has their own unique list of favourite messages

    /**
     * This function lets the user save responses they like as a favourite.
     * @param responseID Unique identification number for AI generated response.
     */
    abstract void toggleFavouriteResponse(int responseID);

    // This feature allows the user to change the tone of the messages being generated
    // It takes responseType as its only parameter

    /**
     * This function allows the user to alter the tone of the AI response.
     * @param responseType One of four options (fortune cookie, motivational quote, fun prediction, advice/tips).
     */
    abstract void changeType(String responseType) ;
}
