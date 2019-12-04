package Output;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

/**
 * 
 * Sorts by a Map<Word, Count> by count and by alphabetical order.
 * 1.) Sorts the top-numOfWords pairs from a Map<Word, Count> into a SortedSet<Entry<Word,Count>>
 * 2.) Adds these top-n-count words to a second SortingMachine to sort in alphabetical order 
 * (their count is used later when printing output to format the font-size)
 * 
 * @author Nathan Weltle and Henry Zhang
 *
 */
public class WordPairSorter {
	
	/**
	 * Helper: Sorts the wordMap's entries by count into a list
	 */
	public static List<Entry<String, Integer>> sortBycount(Map<String, Integer> wordMap){
		/*
		 * TreeMap only sorts by KEY, not by value
		 * -Can sort a list of map-entries instead
		 */
		List<Entry<String, Integer>> list = new LinkedList<>(wordMap.entrySet());
		Collections.sort(list, new CountComparator());
		
		//Can sort a list of map-entries
		//SortedSet<Map.Entry<String, Integer>> topCountSorter = new SortedSet<Entry<String, Integer>>();
		
		return list;
	}
	
	/**
	 * 
	 * Sorts a list of Entries<String, Integer> into a SortedMap, sorted alpha-numerically
	 */
	public static SortedMap<String, Integer> sortByAlpha(List<Entry<String, Integer>> wordList, int numOfWords) {
		
		//Setup map to be sorted alphanumerically
		SortedMap<String, Integer> sortedMap = new TreeMap<String, Integer>(new AlphaComparator());
		
		//Move the top n from list to map, sorting along the way (insertion sort?)
		int n = numOfWords;

		int size = wordList.size();
		while (n > 0 && size > 0) {
			Entry<String, Integer> pair = wordList.remove(size - 1);
			sortedMap.put(pair.getKey(), pair.getValue());
			
			size--;
			n--;
		}
		
		return sortedMap;
	}
	
	/*
	 * Comparator's for Pair
	 */
	/**
	 * A comparator to sort Integer-Values numerically
	 */
	public static class CountComparator implements Comparator<Entry<String,Integer>>{

		@Override
		public int compare(Entry<String, Integer> pair1, Entry<String, Integer> pair2) {
			Integer val1 = pair1.getValue();
			Integer val2 = pair2.getValue();
			
			return val1.compareTo(val2);
		}	
	}
	
	/**
	 * A comparator to sort String-Keys alpha-numerically
	 */
	public static class AlphaComparator implements Comparator<String>{

		@Override
		public int compare(String str1, String str2) {
			StringBuilder sb1 = new StringBuilder(str1);
			StringBuilder sb2 = new StringBuilder(str2);
			
			return compareStrings(sb1, sb2);
		}
		
		/**
		 * Recursively checks each character to determine whether str1 is greater/less/equal to str2
		 * based on ASCII values
		 * @param str1
		 * @param str2
		 * @return
		 */
		public int compareStrings(StringBuilder str1, StringBuilder str2) {
			int difference = 0;
			
			//Iterative: Strip off firstChar and check until a difference is found (or length reached)
			if (str1.length() > 0 && str2.length() > 0) {
				//Get and delete front character
				char front1 = str1.charAt(0);
				char front2 = str2.charAt(0);
				str1.deleteCharAt(0);
				str2.deleteCharAt(0);
				
				//Recursive: 
				difference = front1 - front2;
				if (difference == 0) {
					difference = compareStrings(str1, str2);
				}
				
				//Restore:
				str1.insert(0, front1);
				str2.insert(0,front2);
			}
			else {
				//One is longer than the other
				difference = (str1.length() > 0) ? 1 : -1;
			}

			return difference;
		}
	}
}
