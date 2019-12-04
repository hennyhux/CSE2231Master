package Input;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {

        //Initialize Variables
        BufferedReader input = null;
        PrintWriter output = null;
        Integer numOfWords = null;
        TagCloudGenerator1 tgGenerator = null;

        //Code to open a file

        Scanner in = new Scanner(System.in);
        System.out.println(
                "Please enter a complete filepath containing words to read: ");
        String location = in.nextLine();

        try {
            input = new BufferedReader(new FileReader(location));

        } catch (FileNotFoundException e) {
            System.err.println("File is not found! Error: " + e);

        }

        //Code to get how many words to generate
        System.out.println(
                "Please enter the number of words to be in the Tag Cloud: ");

        try {
            numOfWords = (Math.abs(Integer.parseInt(in.nextLine())));

        } catch (NumberFormatException e) {
            System.err.println("Not a number! Error: " + e);

        }

        //Code to open an output file
        System.out.println(
                "Please enter a complete filepath to write the Tag Cloud to: ");
        String outLocation = in.nextLine();

        try {
            output = new PrintWriter(
                    new BufferedWriter(new FileWriter(outLocation)));

        } catch (IOException e) {
            System.err.println("Error creating the file! Error: " + e);
        }

        //Generate the HTML

        try {
            tgGenerator = new TagCloudGenerator1(input, output, numOfWords);

        } catch (NullPointerException e1) {
            System.err.println(
                    "Either Input, output, and numOfWords are null or all of them! Error: "
                            + e1);
        } catch (IOException e2) {
            System.err.println(
                    "General IO exception, check if both input and output files are valid. Error: "
                            + e2);
        }
        tgGenerator.generateFile();

        //Code to close the file
        try {
            input.close();
            output.close();
            in.close();
        } catch (IOException e) {
            System.err.println(
                    "Error closing streams! They might have become Null!");
        }

    }
}
