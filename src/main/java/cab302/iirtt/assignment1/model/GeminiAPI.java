package cab302.iirtt.assignment1.model;

import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse;

public class GeminiAPI {
    //
    public void run(String prompt) {
        try {
            // User's prompt
//            String prompt = "How does AI work? Please explain in less than 40 words.";

            // Connects to Gemini 2.0 Flash API and sets content type to json.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + Constants.API_KEY))   // The link to the API + API_KEY
                    .header("Content-Type", "application/json")     // Determines the type of content (prompt) the API accepts
                    .POST(BodyPublishers.ofString("{\"contents\": [{\"parts\": [{\"text\": \"" + prompt + "\"}]}]}"))   // The text prompt that the API receives
                    .build();   // builds

            // Gets the response from the API as a JSON in string format
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpClient.send(request, BodyHandlers.ofString());  // gets the response from the request
            httpClient.close();

            // Isolating the text response from the API
            JSONObject jsonObject = new JSONObject(postResponse.body());
            JSONArray candidates = jsonObject.getJSONArray("candidates");
            JSONObject parts = candidates.getJSONObject(0).getJSONObject("content").getJSONArray("parts").getJSONObject(0);
            String response = parts.getString("text");

            // Printing out the response
            System.out.println(response);


        } catch (Exception e) { // catches all exceptions
            System.out.println(e.getMessage());
            System.out.println("Something went wrong");
        }

    }

}
