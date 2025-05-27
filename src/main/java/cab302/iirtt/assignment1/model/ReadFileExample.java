package cab302.iirtt.assignment1.model;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class ReadFileExample {
    public static void main(String[] args) {
        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/cab302/iirtt/assignment1/study_material/study_material_text.txt"));

            // Print the lines
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
