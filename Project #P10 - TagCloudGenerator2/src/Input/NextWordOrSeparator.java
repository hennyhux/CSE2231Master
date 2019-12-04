package Input;
import components.set.Set;
import components.set.Set1L;

/**
 * Program to test static methods {@code generateElements} and
 * {@code nextWordOrSeparator}.
 *
 * @author Nathan Weltle and Henry Zhang
 *
 */
public final class NextWordOrSeparator {

	static final String separatorStr = " \t\n\r,-.!?[]';:/()\"";
    final Set<Character> sepSet;
	
    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    public NextWordOrSeparator() {
    	sepSet = new Set1L<Character>();
    	buildStrSet(separatorStr);
    }

    /**
     * Generates and returns the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @replaces strSet
     * @ensures strSet = entries(str)
     */
    private void buildStrSet(String str) {
        assert str != null : "Violation of: str is not null";

        if (str.length() > 1) {
            //#Iterative: Chop off Char
            String choppedStr = str.substring(1);
            Character currChar = str.charAt(0);

            //#Recursive:
            buildStrSet(choppedStr);
            //#Use:
            if (!sepSet.contains(currChar)) {
            	sepSet.add(currChar);
            }
            
        } else { //#Base: length == 1
            Character currChar = str.charAt(0);

            sepSet.add(currChar);
        }
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
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
    public String nextWordOrSeparator(String text, int position) {
    	//Check/add front
    	Character front = text.charAt(position);
    	String wordOrSep = "" + front;
    	
    	boolean isWord = !sepSet.contains(front);
    	
    	//Peak into next and add if chain isn't broken
    	position++;
    	if (position < text.length()) {
    		//Check next char
    		Character next = text.charAt(position);
    		boolean nextIsWord = !sepSet.contains(next);
    		
    		//chain didn't break
    		if (isWord && nextIsWord) {
    			wordOrSep += nextWordOrSeparator(text, position);
    		}
    	}
    	
    	return wordOrSep;
    }
    
    /**
     * Returns whether or not the token is a separator
     * @param token
     * @return
     */
    public boolean isSeparator(String token) {
    	//Separator if string has a separator in front
    	//(No way for token to be any longer if there's a separator)
    	return sepSet.contains(token.charAt(0));
    }
}