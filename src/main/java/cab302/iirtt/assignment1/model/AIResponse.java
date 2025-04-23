package cab302.iirtt.assignment1.model;

import java.time.LocalDate;

public class AIResponse implements IAIResponse{
    // AI Responses Variables.  ---> type (fortune cookie, motivational quote, advice/tips)
    private int responseID;  //Unique ID for response.
    private String responseType;    //Tone of the response
    private int responseRating; //User rating given for response.
    private String userInput;    //user input which response is based on.
    private int userID;
    private String responseDate;
    private int responseTime;   // Time taken to generate response.
    private String responseText;
    // boolean favourite

    // Constructor
    public AIResponse (int responseID, int responseRating, String responseText, String userInput) {
        this.responseID = responseID; // id auto

        // specifies the type of AI repsoonse "is it fortuneCookie, adviceTip, motivationalQuotes, funPrediction"
        this.responseType = "fun";  //This is just a placeholder/default, it can be changed for user preference.
        this.responseRating = responseRating;
        this.userInput = userInput;
        this.userID = userID;
        this.responseDate = responseDate;
        this.responseTime = responseTime; //
        this.responseText = responseText;
        // add favourite/saved/pinned/liked boolean
    }

    // Getter and Setter
    public int getAIResponseID() { return responseID; }
    public String getResponseType() {return responseType;}
    public int getResponseRating() {return responseRating;}
    public String getResponseDate() { return responseDate; }
    public int getResponseTime() {return responseTime; }
    public String getResponseText() {return responseText;}
    public String getUserInput() {return userInput;}
    public int getUserID() {return userID;}

    public void setAIResponseID(int aiResponseID) { this.responseID = aiResponseID; }
    public void setResponseType(String aiResponseType) {this.responseType = aiResponseType;}
    public void setResponseRating(int aiResponseRating) { this.responseRating = aiResponseRating; }
    public void setResponseDate(String aiResponseDate) { this.responseDate = aiResponseDate; }
    public void setResponseTime(int aiResponseTime) { this.responseTime = aiResponseTime; }
    public void setResponseText(String aiResponseText) {this.responseText = aiResponseText;}
    public void setUserInput(String userInput) {this.userInput = userInput;}
    public void setUserID(int userID) {this.userID = userID;}

    @Override
    public void generateResponse(String userInput, String responseType, String mood) {
        // NOT YET IMPLEMENTED
    }

    @Override   //Function to provide fun prediction tailored to user's mood.
    public void generatePrediction(String mood , String userID) {
        // NOT YET IMPLEMENTED
    }

    @Override
    public void rateResponse(int responseID, int responseRating) {
        // NOT YET IMPLEMENTED
    }

    @Override
    public void viewResponses(String userID) { // maybe
        // NOT YET IMPLEMENTED
    }

    @Override
    public void toggleFavouriteResponse(int responseID) {
        // NOT YET IMPLEMENTED
    }

    @Override
    public void changeType(String responseType) {
        // NOT YET IMPLEMENTED
    }
}