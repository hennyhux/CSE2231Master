import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Program to copy a text file into another file.
 *
 * @author Henry Zhang
 *
 */
public final class CopyFileStdJava {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CopyFileStdJava() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments: input-file-name output-file-name
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) {

        BufferedReader input;
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter input file lcoation: ");
        String location = in.nextLine();

        //Code to open a input file
        try {
            input = new BufferedReader(new FileReader(location));

        } catch (IOException e) {
            System.err.println("Error opening the file!");
            return;
        }

        //Code to open an output file
        PrintWriter output;
        System.out.println("Please enter output file location:");
        String outLocation = in.nextLine();

        try {
            output = new PrintWriter(
                    new BufferedWriter(new FileWriter(outLocation)));

        } catch (IOException e) {
            System.err.println("Error creating the file!");
            return;
        }

        //Code to read and print from file
        try {
            String s = "";
            while (s != null) {
                s = input.readLine(); //Next line can be null!

                if (s != null) { // Second check
                    output.println(s);
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading the file!");
            return;

        }

        //Code to close the file
        try {
            input.close();
            output.close();
            in.close();
        } catch (IOException e) {
            System.err.println("Error closing!");
        }

    }

}
