package cab302.iirtt.assignment1.model;

import java.time.LocalDate;

public class AIResponse implements IAIResponse{
    // AI Responses Variables.  ---> type (fortune cookie, motivational quote, advice/tips)
    public enum ResponseType {
        MOTIVATIONAL_QUOTE,
        ADVICE_TIP,
        FUN_PREDICTION,
        FORTUNE_COOKIE
    }
    private int responseID;  //Unique ID for response.
    private ResponseType responseType;
    private int responseRating; //User rating given for response.
    private String userInput;    //user input which response is based on.
    private int userID;
    private String responseDate;
    private String responseText;
    private boolean favourite;

    // Constructor
    public AIResponse (ResponseType responseType, int responseRating, String responseDate, String responseText, String userInput, boolean favourite, int userID) {
//        this.responseID = responseID; // id auto
        // specifies the type of AI repsoonse "is it fortuneCookie, adviceTip, motivationalQuotes, funPrediction"
        this.responseType = responseType;  //This is just a placeholder/default, it can be changed for user preference.
        this.responseRating = responseRating;
        this.userInput = userInput;
        this.userID = userID;
        this.responseDate = responseDate;
        this.responseText = responseText;
        this.favourite = favourite;
    }

    // Getter and Setter
    public int getAIResponseID() { return responseID; }
    public ResponseType getResponseType() {return responseType;}
    public int getResponseRating() {return responseRating;}
    public String getResponseDate() { return responseDate; }
    public String getResponseText() {return responseText;}
    public String getUserInput() {return userInput;}
    public int getUserID() {return userID;}
    public Boolean getFavourite() {return favourite;}

    public void setAIResponseID(int responseID) { this.responseID = responseID; }
    public void setResponseType(ResponseType responseType) {this.responseType = responseType;}
    public void setResponseRating(int responseRating) { this.responseRating = responseRating; }
    public void setResponseDate(String responseDate) { this.responseDate = responseDate; }
    public void setResponseText(String responseText) {this.responseText = responseText;}
    public void setUserInput(String userInput) {this.userInput = userInput;}
    public void setUserID(int userID) {this.userID = userID;}
    public void setFavourite(boolean favourite) {this.favourite = favourite;}


    @Override
    public void generateResponse(String userInput, String responseType, String mood) {
        // NOT YET IMPLEMENTED
    }

    @Override   //Function to provide fun prediction tailored to user's mood.
    public void generatePrediction(String mood , int userID) {
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