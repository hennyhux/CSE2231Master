package Output;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

/*
 * 1.) Sorts the Map<Word, Count> pairs by alphabetical order (Comparator)
 * 2.) Generates html + CSS file of the top n pairs
 * (When removing)
 * -Font size determined by the count (either have a minimum/maximum, scale based on ratio count:n)
 */

public class OutputGenerator {

    private PrintWriter outToFile;
    private int highestCount;

    SortedMap<String, Integer> sortedMap; //top n words sorted alphabetically

    private final int FONT_MIN = 11;
    private final int FONT_MAX = 48;

    public OutputGenerator(SortedMap<String, Integer> sortedMap,
            PrintWriter outToFile2, int highestCount) {
        this.sortedMap = sortedMap;
        this.outToFile = outToFile2;
        this.highestCount = highestCount;
    }

    /*
     * Public:
     */
    public void generateFile() throws FileNotFoundException {
        this.generateHTML();
        this.generateFooter();
        this.generateCSS();

        //Close:
        this.outToFile.close();
    }

    /*
     * HTML Generation
     */

    private void generateHTML() {
        this.generateHeader();
        this.generateBody();
    }

    //Helper 1:
    private void generateHeader() {
        this.outToFile.println("<html>");
        this.outToFile.println("<head>");
        this.outToFile.println("    <title>TagCloud</title>");
        this.outToFile.println(
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></meta>");
        this.outToFile.println(
                "    <link href=\"tagcloud.css\" rel=\"stylesheet\" type=\"text/css\"></link>"); //link CSS file
        this.outToFile.println("</head>");
    }

    private void generateBody() {
        this.outToFile.println();
        this.outToFile.println("<body>");
        this.outToFile.println("    <div class = \"wordDiv\">");
        this.outToFile.println("    <p class = \"cbox\" >");

        this.generateWords();

        this.outToFile.println("    </p>");
        this.outToFile.println("    </div>");
        this.outToFile.println("</body>");
    }

    private void generateWords() {
        //Iterate through, remove each pair from map to write to file
        Set<Entry<String, Integer>> entrySet = this.sortedMap.entrySet();
        Iterator<Entry<String, Integer>> it = entrySet.iterator();

        while (it.hasNext()) {
            //Remove each word-pair
            Entry<String, Integer> pair = it.next();
            it.remove();

            String word = pair.getKey();
            Integer count = pair.getValue();

            //Scale font-size
            int fontSize = this.fontSize(count);

            //Write word-element using class defined by fontSize
            this.outToFile.println(
                    "            <span style=\"cursor:default\" class=\"f"
                            + fontSize + "\" title=\"count: " + count + "\">"
                            + word + "</span>");
        }
    }

    private void generateFooter() {
        this.outToFile.println("</html>");
    }

    /**
     * Creates the CSS file (with element-classes for each font-size)
     *
     * @throws FileNotFoundException
     */
    private void generateCSS() throws FileNotFoundException {
        PrintWriter cssOut = new PrintWriter("tagcloud.css");

        //Stylize Body:
        cssOut.println("body { padding: 10px; margin: 10px;");
        cssOut.println("       background: #fff; color: #05e;");
        cssOut.println(
                "       font-family:\"Arial\", Arial, Helvetica, sans-serif; }");
        cssOut.println();

        //Stylize Dividers:
        cssOut.println(
                ".cbox { padding: 12px; background: #d5d5d5; width: 700px; }");
        cssOut.println(
                ".wordDiv { margin-top: 0; padding-left: 7px; padding-right: 7px; }");
        cssOut.println(
                ".wordDiv span { text-direction: none; padding: 0px; margin: 3px; }");
        cssOut.println(
                ".wordDiv span:hover { color: #fff; background: #05e; }");
        cssOut.println();

        //Stylize font-classes
        for (int i = this.FONT_MIN; i < this.FONT_MAX; i++) {
            cssOut.println(".f" + i + " { font-size: " + i + "px; line-height: "
                    + i + "px; }");
        }

        cssOut.close();
    }

    /**
     * Scales font-size using: count:totalMapCount and FONT_MIN:FONT_MAX
     *
     * @param count
     *            occurences of the word
     * @return the scaled font-size
     */
    private int fontSize(int count) {
        /*
         * Want: -value between MIN, MAX -based on (scaled by) the (possible)
         * part: whole count
         *
         * (whole - part)/(max - min) * (part - min) + min
         */

        //Based on occurences, scale occordingly
        //Domain: [count, totalMapCount]
        //Range: [FONT_MIN, FONT_MAX]

        //int fontSize = FONT_MIN + (int)((FONT_MAX - FONT_MIN) * (count / (totalMapCount - 0)));
        double ratio = (double) count / (this.highestCount);
        int fontSize = (int) (ratio * (this.FONT_MAX - this.FONT_MIN))
                + this.FONT_MIN;

        //Cap off the size
        if (fontSize > this.FONT_MAX) {
            fontSize = this.FONT_MAX;
        }

        return fontSize;
    }
}
