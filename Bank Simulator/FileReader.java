import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;


/**
 * This program demonstrates how to read from text files.  It brings
 * up a file-selection dialog box and, if the user selects a file,
 * copies the file to the screen, one line at a time.
 */
public class FileReader {
    
    public static void main(String[] args) {
        // Create a file-selection dialog object
        JFileChooser chooser = new JFileChooser();
        
        try {   
            // Display the dialog, and wait for return value.  If they cancel
            // out of the selection, throw an error -- no file to read
            if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
                throw new Error("Input file not selected");
            
            // Grab the selected File info
            File inFile = chooser.getSelectedFile();
            
            // Create a scanner, and attach it to the file.  Loop through
            // line at a time and print the contents to the screen.
            Scanner fileScanner = new Scanner(inFile);
            while(fileScanner.hasNext()) {
               int arrivalTime = fileScanner.nextInt();
               int transactionTime = fileScanner.nextInt();
               boolean isWalkIn = fileScanner.nextBoolean();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Data file not found.");
        } catch (Exception e) {
            System.err.println("A mysterious error occurred.");
            e.printStackTrace(System.err);
        }
    }
}
