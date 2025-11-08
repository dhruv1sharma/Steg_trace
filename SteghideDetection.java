import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SteghideDetection {

    /**
     * Checks if the given image contains a hidden message using Steghide.
     * If a hidden message is detected, it reads and returns the message.
     *
     * @param imagePath The path to the image file to be analyzed.
     * @param passphrase The passphrase for extracting hidden data (optional).
     * @return The extracted hidden message, or null if no message is found.
     */
    public static String checkHiddenMessage(String imagePath, String passphrase) {
        try {
            // Specify the full path to steghide executable if it's not in the PATH
            String steghidePath = "C:\\Program Files\\Steghide\\steghide.exe";  // Update this to your Steghide path

            // Build the command for extracting hidden message using Steghide
            ProcessBuilder builder = new ProcessBuilder(steghidePath, "extract", "-sf", imagePath, "-p", passphrase, "-xf", "temp_output.txt");
            builder.redirectErrorStream(true);  // Redirect errors to standard output

            // Print command and working directory for debugging
            System.out.println("Executing command: " + String.join(" ", builder.command()));
            System.out.println("Working directory: " + System.getProperty("user.dir"));

            // Start the process
            Process process = builder.start();

            // Read the output of the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Wait for the process to complete and check the exit code
            int exitCode = process.waitFor();

            // Print the output and exit code for debugging
            System.out.println("Steghide output: \n" + output);
            System.out.println("Process exited with code: " + exitCode);

            // Check if the steghide process completed successfully
            if (exitCode == 0) {
                // Check if the temporary file has been created with content
                File tempFile = new File("temp_output.txt");
                if (tempFile.exists() && tempFile.length() > 0) {
                    // Read and print the hidden message from the temporary file
                    StringBuilder hiddenMessage = new StringBuilder();
                    try (BufferedReader tempReader = new BufferedReader(new FileReader(tempFile))) {
                        String tempLine;
                        while ((tempLine = tempReader.readLine()) != null) {
                            hiddenMessage.append(tempLine).append("\n");
                        }
                    }

                    // Clean up temporary file after reading the message
                    tempFile.delete();
                    System.out.println("Hidden message detected and extracted successfully.");
                    return hiddenMessage.toString();  // Return the hidden message
                } else {
                    System.out.println("No hidden message detected.");
                }
            } else {
                System.out.println("Steghide extraction failed. It may be due to no hidden message or an incorrect passphrase.");
                System.out.println("Steghide Output: " + output.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;  // Return null if no hidden message was found
    }

    public static void main(String[] args) {
        // Scanner for taking user input for image path and passphrase
        Scanner scanner = new Scanner(System.in);

        // Get the image file path from the user
        System.out.print("Enter the path of the image file (e.g., C:\\Users\\sample.jpg): ");
        String imagePath = scanner.nextLine();

        // Get the passphrase from the user
        System.out.print("Enter the passphrase (leave empty if none): ");
        String passphrase = scanner.nextLine();

        // Check if the image contains a hidden message and retrieve it
        String hiddenMessage = checkHiddenMessage(imagePath, passphrase);

        // Print the result
        if (hiddenMessage != null) {
            System.out.println("Result: The image contains a hidden message.");
            System.out.println("Hidden Message:\n" + hiddenMessage);  // Print the extracted hidden message
        } else {
            System.out.println("Result: No hidden message found in the image.");
        }

        scanner.close();
    }
}
