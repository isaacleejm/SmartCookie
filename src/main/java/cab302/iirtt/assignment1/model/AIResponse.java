package cab302.iirtt.assignment1.model;

import java.util.List;

public class AIResponse implements IAIResponse{

    /**
     * Enum of all types of Responses
     */
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

    /**
     * Default Constructor of AIResponse Class
     */
    public AIResponse() {

    }

    /**
     * Declares an AIResponse object
     * @param responseType The response's Type
     * @param responseRating The response's rating given by the user
     * @param responseDate The date of which the response was generated
     * @param responseText The response given by the AI
     * @param userInput The user's prompt
     * @param favourite In the user's favourite
     * @param userID The id of the user this response belongs to
     */
    public AIResponse (ResponseType responseType, int responseRating, String responseDate, String responseText, String userInput, boolean favourite, int userID) {
//        this.responseID = responseID; // id auto
        // specifies the type of AI response "is it fortuneCookie, adviceTip, motivationalQuotes, funPrediction"
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

    /**
     * Generating an AI response based on userInput
     * @param userInput The prompt for the AI.
     */
    @Override
    public void generateResponse(String userInput) {
        System.out.println("NOT YET IMPLEMENTED");
    }

    /**
     * Allows the user to rate their received response out of 10.
     * @param responseID Unique identification number for AI generated response.
     * @param responseRating Rating given by user.
     */
    @Override
    public void rateResponse(int responseID, int responseRating) {
        // NOT YET IMPLEMENTED
    }
    /**
     * Allows user to view past responses.
     * @param userID Unique identification number for specific user.
     * @return A list of AI responses generated for a particular user.
     */
    @Override
    public List<AIResponse> viewResponses(String userID) { // maybe
        // NOT YET IMPLEMENTED
        return null;
    }
    /**
     * This function lets the user save responses they like as a favourite.
     * @param responseID Unique identification number for AI generated response.
     */
    @Override
    public void toggleFavouriteResponse(int responseID) {
        // NOT YET IMPLEMENTED
    }
    /**
     * This function allows the user to alter the tone of the AI response.
     * @param responseType One of four options (fortune cookie, motivational quote, fun prediction, advice/tips).
     */
    @Override
    public void changeType(String responseType) {
        // NOT YET IMPLEMENTED
    }
}