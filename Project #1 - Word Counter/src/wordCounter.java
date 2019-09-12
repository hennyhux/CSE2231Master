import java.util.Comparator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.*;
import components.simplewriter.*;

/**
 * Program that takes a text file as input and creates an HTML page with a table
 * that lists all the words and their number of occurrences in the text file
 * 
 * @author Henry Zhang
 * 
 */
public class wordCounter {
	
	
	/**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
	public static void main (String args[]) {
		 
		Comparator<String> order = new StringLT();
		Set<Character> seperatorsSet = generateSeperatorSet();
		Queue<String> key = new Queue1L<String>();
		
		SimpleReader in = new SimpleReader1L();
		SimpleWriter out = new SimpleWriter1L(); 
		out.print("Please Enter Your Filename:");
		String filename = in.nextLine();
		Map<String, Integer> wordCount = generateWordMap(filename, seperatorsSet); 
		out.print("Please Enter Your Output Filename:");
		String outputFilename = in.nextLine();
		
		sorter(wordCount, order, key); 
		outputToHTML(filename, outputFilename, wordCount, key); 
		in.close();
		out.close();
	}
	
	/**
	 * Generates a {@code set} of characters which includes the forbidden characters 
	 * 
	 * @return  seperatorsSet: A set of excluded characters		
	 * @ensures <pre>
	 * {@code seperatorsSet = entries(separators)}
	 * </pre>
	 */
	private static Set<Character> generateSeperatorSet() {
	     Set<Character> seperatorsSet = new Set1L<Character>(); 
	     String seperators = " \t\n\r,-.!?[]';:/()\" "; 
	     for (int i = 0; i < seperators.length() - 1; i++) {
	    	 seperatorsSet.add(seperators.charAt(i));
	        }
	     
		return seperatorsSet; 
	}
	
	
	/**
	 * Generates a {@code map} with contains all the words and their respective occurrence value  
	 * 
	 * @return  generateWordMap: A set of all words and counts	
	 * @ensures <pre>
	 * {@code seperatorsSet = entries(words and value)}
	 * </pre>
	 */
	private static Map<String, Integer> generateWordMap(String fileName, Set<Character> seperatorsSet) {
		assert !fileName.isEmpty(): "Violation of: fileName is empty!"; 
		assert seperatorsSet != null: "Violation of: seperatorsSet is not null";
		
		Map<String, Integer> wordCount = new Map1L<String, Integer>();
		SimpleReader file = new SimpleReader1L(fileName); 
		
		while(!file.atEOS()) {
			
			String line = file.nextLine(); 
			int linePos = 0;

			while (linePos < line.length()) {
				String currentLine = nextWordOrSeparator(line, linePos, seperatorsSet); 
				
				if (!seperatorsSet.contains(currentLine.charAt(0))) {
					
					if(!wordCount.hasKey(currentLine)) {
						wordCount.add(currentLine, 1);
					}
					
					else if (wordCount.hasKey(currentLine)) {
						int currentValue = wordCount.value(currentLine);
						currentValue++; 
						wordCount.replaceValue(currentLine, currentValue); 
						
					}
					
				}
				
				linePos = linePos + currentLine.length(); 
						
			}
		}
		
		file.close();
		return wordCount;
	}
	
	/**
     * Sort through the {@code Map} using the {@code StringLT} implementation 
     * 
     * @param words
     *            the {@code Map} containing all the words and the number of 
     *            occurrences
     * @param order
     *            the {@code Comparator} allowing the words inside of queue
     *             to be sorted alphabetically
     * @param key
     *           {@code Queue} containing all the sorted words 
     * @updates key
     * @ensures <pre>
     * {@code Map} of words sorted in a alphabetically fashion
     * </pre>
     * 
     */
	private static void sorter(Map<String, Integer> words,
            Comparator<String> order, Queue<String> key) {
		assert words != null: "Violation of: words is not null";
    	assert key != null: "Violation of: key is not null";
    	assert order != null: "Violation of: order is not null";
    	
		Map<String, Integer> wordCopy = words.newInstance(); 
		Queue<String> keyCopy = key.newInstance(); 
		
		while (words.iterator().hasNext()) {
			Pair<String, Integer> temp = words.removeAny(); 
			wordCopy.add(temp.key(), temp.value());
			key.enqueue(temp.key());
		}
		
		key.sort(order);
		
		 while (key.iterator().hasNext()) {
	            String tempKey = key.dequeue();
	            Pair<String, Integer> pair = wordCopy.remove(tempKey);
	            words.add(pair.key(), pair.value());
	            keyCopy.enqueue(tempKey);
	        }
		 
		 key.transferFrom(keyCopy);
		
		
	}
	
	/**
	 * Returns the first "word" (maximal length string of characters not in
	 * {@code separators}) or "separator string" (maximal length string of
	 * characters in {@code separators}) in the given {@code text} starting at
	 * the given {@code position}.
	 * *REMEMBER* Dont use string concatnation inside a loop, use substrings 
	 * 
	 * @param text
	 *            the {@code String} from which to get the word or separator
	 *            string
	 * @param position
	 *            the starting index
	 * @param separators
	 *            the {@code Set} of separator characters
	 * @return the first word or separator string found in {@code text} starting
	 *         at index {@code position}
	 * @requires 0 <= position < |text|
	 * @ensures <pre>
	 * nextWordOrSeparator =
	 *   text[position, position + |nextWordOrSeparator|)  and
	 * if entries(text[position, position + 1)) intersection separators = {}
	 * then
	 *   entries(nextWordOrSeparator) intersection separators = {}  and
	 *   (position + |nextWordOrSeparator| = |text|  or
	 *    entries(text[position, position + |nextWordOrSeparator| + 1))
	 *      intersection separators /= {})
	 * else
	 *   entries(nextWordOrSeparator) is subset of separators  and
	 *   (position + |nextWordOrSeparator| = |text|  or
	 *    entries(text[position, position + |nextWordOrSeparator| + 1))
	 *      is not subset of separators)
	 * </pre>
	 */
	private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";
        
        int positonCopy = position; 
        String word = "";
        
        if (!separators.contains(text.charAt(position))) { //Found Letter
        	
        	while (positonCopy < text.length() && !separators.contains(text.charAt(positonCopy))) {
        		positonCopy++; 
        	}
        	
        }
        
        if (separators.contains(text.charAt(position))) { //Found Separators 
        	
        	while (positonCopy < text.length() && separators.contains(text.charAt(positonCopy))) {
        		positonCopy++; 
        	}
        	
        }
        
        word = text.substring(position, positonCopy); 
        return word;
    }
	
	
	 /**
     * Prints out an index page in HTML that holds the table which contains all the words
     * 
     * @param fileName
     *            the input file name 
     * @param outputFilename
     *            the output Filename 
     * @param words
     *            the {@code map} which contains all the words
     * @param key
     *            the {@code Queue} that contains all the keys 
     * @ensures <pre>
     * HTML page = table with entries inside of {@code Queue} 
     * </pre>
     * 
     */
    private static void outputToHTML(String fileName, String outputFilename, Map<String, Integer> words, Queue<String> key) {
    	assert fileName != null: "Violation of: fileName is not null";
    	assert outputFilename != null: "Violation of: outputFilename is not null";
    	assert words != null: "Violation of: words is not null";
    	assert key != null: "Violation of: key is not null";
    	assert !fileName.isEmpty(): "Violation of: fileName is empty!";
    	assert !outputFilename.isEmpty(): "Violation of: outputFilename is empty!";
    	
		SimpleWriter indexPage = new SimpleWriter1L(outputFilename);
		indexPage.println("<html>");
		indexPage.println("<head>");
		indexPage.println("<title> Words Counted In " + fileName + "</title>");
		indexPage.println("</head>");
		indexPage.println("<body>");
		indexPage.println("<h2> Words Counted In " + fileName + "</h2>"); 
		indexPage.println("<hr>"); 
		indexPage.println("<table border=\"1\">");
		indexPage.println("<tr>");
		indexPage.println("<th>Words</th>");
		indexPage.println("<th>Counts</th>");
		indexPage.println("</tr>");
		
		while (words.iterator().hasNext()) {
            Pair<String, Integer> temp = words.remove(key.dequeue());
            indexPage.println("<tr>");
            indexPage.println("<td>" + temp.key() + "</td>");
            indexPage.println("<td>" + temp.value() + "</td>");
            indexPage.println("</tr>");
        }
		
		
		indexPage.println("</table>");
		indexPage.println("</body>");
		indexPage.println("</html>"); 
		indexPage.close();
	}


    /**
     * Comparator implementation that sorts {@code key} in alphabetical order
     */
	private static class StringLT implements Comparator<String> {
	    @Override
	    public int compare(String o1, String o2) {
	        return o1.compareTo(o2);
	    }
	}

}
