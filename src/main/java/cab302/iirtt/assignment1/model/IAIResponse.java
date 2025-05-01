package cab302.iirtt.assignment1.model;

import java.util.List;

public interface IAIResponse {

    // Generates AI message
    // This method will have three parameters (String userInput, String responseType, String mood).
    // Message generated is different to messages already received by user.
    // Response type is set by user in settings
    // Tone of message is based on user mood and tailored to study material (userInput)
    abstract void generateResponse(String userInput, String mood);

    // User has the option to rate the response given from 1 to 5
    // Each response has a unique ID
    // rating is stored in responseRating and linked to corresponding responseID
    abstract void rateResponse(int responseID, int responseRating) ;

    // User has the option to view past responses.
    // Responses are stored in a database and given a unique ID
    // User can view the message and corresponding ratings and ID
    abstract List<AIResponse> viewResponses(String userID);

    // User has the option to save their favourite messages
    // Each user has their own unique list of favourite messages
    abstract void toggleFavouriteResponse(int responseID);

//    // This feature generates a fun prediction based on the user's mood
//    // Each user has their mood history stored
//    // predictions are plausible but absurd
//    abstract void generatePrediction(String mood , int userID);

    // This feature allows the user to change the tone of the messages being generated
    // It takes responseType as its only parameter
    abstract void changeType(String responseType) ;
}
