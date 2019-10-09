import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size

    @Test
    public final void testAddNonEmptyShort() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "holy");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "holy", "moly");
        /*
         * Call method under test
         */
        m.add("moly");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddNonEmptyLong() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "Consanguineous", "Psychotomimetic", "Omphaloskepsis");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "Consanguineous", "Psychotomimetic", "Omphaloskepsis",
                "Xenomorphillia");
        /*
         * Call method under test
         */
        m.add("Xenomorphillia");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddNonEmptyIntegerString() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "1",
                "55", "22");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "1", "55", "22", "6544");
        /*
         * Call method under test
         */
        m.add("6544");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionModeEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        /*
         * Call method under test
         */
        m.changeToExtractionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionModeNonEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "b",
                "f", "g");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "b", "f", "g");
        /*
         * Call method under test
         */
        m.changeToExtractionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionModeNonEmptyWords() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "these",
                "things", "happen");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "these", "things", "happen");
        /*
         * Call method under test
         */
        m.changeToExtractionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionModeLong() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "beseech", "beseechdomineering", "hideous", "immolate",
                "cheerful", "boorish");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "beseech", "beseechdomineering", "hideous", "immolate",
                "cheerful", "boorish");
        /*
         * Call method under test
         */
        m.changeToExtractionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirstSizeOne() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "hey");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "hey");
        /*
         * Call method under test
         */
        String entry = m.removeFirst();
        String entryExpected = mExpected.removeFirst();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(entry, entryExpected);
        assertEquals(m, mExpected);

    }

    @Test
    public final void testRemoveFirstSizeMany() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "ugh",
                "why", "is", "this", "so", "repetative", "i", "need", "cash");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "ugh", "why", "is", "this", "so", "repetative", "i", "need");
        /*
         * Call method under test
         */
        String entry = m.removeFirst();
        String entryExpected = "cash";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(entry, entryExpected);
        assertEquals(m, mExpected);

    }

    @Test
    public final void testRemoveFirstSizeMany1() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a",
                "b", "c", "d", "e", "f", "g", "h", "i", "j", "k");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "b", "c", "d", "e", "f", "g", "h", "i", "j", "k");
        /*
         * Call method under test
         */
        String entry = m.removeFirst();
        String entryExpected = "a";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(entry, entryExpected);
        assertEquals(m, mExpected);

    }

    @Test
    public final void testIsInInsertionModeEmptyTrue() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        /*
         * Call method under test
         */

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, m.isInInsertionMode());
    }

    @Test
    public final void testIsInInsertionModeEmptyFalse() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        /*
         * Call method under test
         */

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(false, m.isInInsertionMode());
    }

    @Test
    public final void testIsInInsertionModeNonEmptyTrue() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "white");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "white");
        /*
         * Call method under test
         */

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, m.isInInsertionMode());
    }

    @Test
    public final void testIsInInsertionModeNonEmptyLongTrue() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b", "c", "d", "e", "f", "g", "h", "i", "j", "k");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k");
        /*
         * Call method under test
         */

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, m.isInInsertionMode());
    }

    @Test
    public final void testIsInInsertionModeNonEmptyFalse() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "white");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "white");
        /*
         * Call method under test
         */

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(false, m.isInInsertionMode());
    }

    @Test
    public final void testSizeEmptyInsertionMode() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        /*
         * Call method to test
         */
        int size = m.size();
        int sizeExpected = 0;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(sizeExpected, size);
    }

    @Test
    public final void testSizeEmptyExtractionMode() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        /*
         * Call method to test
         */
        int size = m.size();
        int sizeExpected = 0;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(sizeExpected, size);
    }

    @Test
    public final void testSizeNonEmptyInsertionMode() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "green", "black", "purple", "white", "teal", "beige", "orange",
                "grey");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "blue", "green", "black", "purple", "white", "teal", "beige",
                "orange", "grey");
        /*
         * Call method to test
         */
        int size = m.size();
        int sizeExpected = 9;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(sizeExpected, size);
    }

    @Test
    public final void testSizeNonEmptyExtractionMode() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "blue",
                "green", "black", "purple", "white", "teal", "beige", "orange",
                "grey");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "blue", "green", "black", "purple", "white", "teal", "beige",
                "orange", "grey");
        /*
         * Call method to test
         */
        int size = m.size();
        int sizeExpected = 9;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(sizeExpected, size);
    }

    @Test
    public final void testOrderEmptyInsertionMode() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        /*
         * Call method to test
         */
        m.order();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testOrderEmptyExtractionMode() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        /*
         * Call method to test
         */
        m.order();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testOrderNonEmptyInsertionMode() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "b",
                "s", "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "s");
        /*
         * Call method to test
         */
        m.order();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testOrderNonEmptyInsertionModeInteger() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "6",
                "3", "2");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "2", "3", "6");
        /*
         * Call method to test
         */
        m.order();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

}
