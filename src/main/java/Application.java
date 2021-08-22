import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Application {

    public static void main(String[] args) {

        try {
            if (args.length == 0) {
                System.out.println("Index of coincidence. Pass file name as args (T1 to T7)");
            } else {
                String fileName = args[0];
                byte[] textBytes = Files.readAllBytes(Path.of("src/main/resources/" + fileName + ".txt"));
                double index = Core.computeIndexOfCoincidence(textBytes);
                System.out.println("Index of coincidence for file " + fileName + ": " + index);
            }
        } catch (Exception e) {
            System.err.println("Unable to process file.");
            System.err.println(e.getCause().getMessage());
        }

    }

}
