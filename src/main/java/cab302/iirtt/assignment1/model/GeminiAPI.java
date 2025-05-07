package cab302.iirtt.assignment1.model;

import cab302.iirtt.assignment1.MainApplication;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.util.Properties;

public class GeminiAPI {
    /**
     * Takes in a prompt and returns a text response from the GeminiAPI.
     * @param prompt The Question that the AI will be responding to.
     * @return The response from the GeminiAPI in String format.
     */
    public String run(String prompt) {
        String response = "";
        try {
            // Connects to Gemini 2.0 Flash API and sets content type to json.
            Properties properties = new Properties();
            try (InputStream inputStream = GeminiAPI.class.getClassLoader().getResourceAsStream("config.properties")) {
                properties.load(inputStream);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + properties.getProperty("API_KEY")))   // The link to the API + API_KEY
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
            response = parts.getString("text");

            // Uncomment the line below to print out additional information from the GeminiAPI.
            // System.out.println(postResponse.body());

            // Printing out the response
            // System.out.println(response);

            return response;

        } catch (Exception e) { // catches all exceptions
            System.out.println(e.getMessage());
            System.out.println("Something went wrong");
            return response;
        }

    }

}
