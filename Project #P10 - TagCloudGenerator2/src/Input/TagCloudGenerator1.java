package Input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

import Output.OutputGenerator;
import Output.WordPairSorter;

/**
 * This creates a Map<String, Integer> of wordAndCount (dealing with input when
 * created), then uses OutputGenerator to handle the output (sorting the data
 * and writing Tag Cloud formatted html/CSS file)
 */
public class TagCloudGenerator1 {

    //Input:
    Integer numOfWords;
    private int highestCount;
    private Map<String, Integer> wordMap;

    //Sorted version
    SortedMap<String, Integer> sortedMap;

    //Output:
    OutputGenerator htmlWriter;

    TagCloudGenerator1(BufferedReader inFromFile, PrintWriter outToFile,
            Integer numOfWords) throws IOException {
        this.numOfWords = numOfWords;

        //1.) Setup Map, later sorted in OutputGenerator
        this.wordMap = new HashMap<String, Integer>();

        //2.) Input: Read file and store counted words
        this.countWords(inFromFile);

        //3.) Sort: by count (top n), then by alpha
        this.sort();

        //4.) Output: Setup to output to file
        this.htmlWriter = new OutputGenerator(this.sortedMap, outToFile,
                this.highestCount);
    }

    /*
     * Public
     */
    public void generateFile() {
        //5.) Use the writer class to generate the html + CSS file
        try {
            this.htmlWriter.generateFile();
        } catch (FileNotFoundException e) {
            System.err.println("HTML File could not be generated!");
            e.printStackTrace();
        }
    }

    /*
     * Private (helpers)
     */

    /**
     * Uses WordPairSorter to sort map of word/count
     */
    private void sort() {
        //Sort by count
        List<Entry<String, Integer>> wordList = WordPairSorter
                .sortBycount(this.wordMap);

        int last = wordList.size();
        this.highestCount = wordList.get(last - 1).getValue();

        //Sort by alpha
        this.sortedMap = WordPairSorter.sortByAlpha(wordList, this.numOfWords);
    }

    /**
     * Reads words into the map, counting them
     *
     * @param inFromFile
     * @throws IOException
     */
    private void countWords(BufferedReader inFromFile) {

        //Add count/word Entries
        NextWordOrSeparator wordOrSep = new NextWordOrSeparator();
        try {
            while (inFromFile.readLine() != null) {
                //Get each line
                String currLine = inFromFile.readLine();

                //Filter line, adding words to the map or adjusting count
                int currPos = 0;
                while (currPos < currLine.length()) {

                    //Get next word/sep
                    String token = wordOrSep.nextWordOrSeparator(currLine,
                            currPos);

                    //Iterative: break off word/sep
                    currPos += token.length();

                    //Add to map if a word
                    if (!wordOrSep.isSeparator(token) && token != null) {

                        /*
                         * Convert to lower/upper-case before storing data in
                         * map to avoid case sensitivity, and allow
                         * case-formatting to be applied during output
                         */
                        String word = token.toLowerCase();
                        this.updateWordCount(word);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Cannot read from input file!");
            e.printStackTrace();
        }
    }

    /**
     * Adds the word into the map or updates its count
     *
     * @param word
     */
    private void updateWordCount(String word) {

        if (!this.wordMap.containsKey(word)) {
            //Add new word
            this.wordMap.put(word, 1);
        } else {
            //Update count of word
            int count = this.wordMap.get(word);
            count++;
            this.wordMap.replace(word, count);
        }
    }
}
