import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Nathan Weltle and Henry Zhang
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

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size

    /*
     * constructor
     */

    @Test
    public final void testConstructorEmpty() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testConstructorReg1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue", "yellow");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue", "yellow");
        assertEquals(mExpected, m);
    }

    /*
     * add
     */
    //Edge cases
    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddOne() {
        //Setup
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue");
        //Test
        m.add("blue");

        //Check
        assertEquals(mExpected, m);
    }

    //Regular

    @Test
    public final void testAddReg1() {
        //Setup
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue", "yellow", "red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue", "yellow", "red", "violet");
        //Test
        m.add("violet");

        //Check
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddReg2() {
        //Setup
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "1",
                "3", "4", "2");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "1", "2", "3", "4", "5");
        //Test
        m.add("5");

        //Check
        assertEquals(mExpected, m);
    }

    //Test diff tree structures;
    @Test
    public final void testAddLeftLeaf() {
        //Setup
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue", "yellow");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue", "yellow", "red");
        //Test
        m.add("red");

        //Check
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddMidRow() {
        //Setup
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue", "yellow", "red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue", "yellow", "red", "orange");
        //Test
        m.add("orange");

        //Check
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddLastRight() {
        //Setup
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue", "yellow", "red", "orange", "indigo");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue", "yellow", "red", "orange", "indigo", "violet");
        //Test
        m.add("violet");

        //Check
        assertEquals(mExpected, m);
    }

    //Test with same nodes

    @Test
    public final void testAddMatching() {
        //Setup
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue", "yellow", "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue", "yellow", "blue", "yellow");
        //Test
        m.add("yellow");

        //Check
        assertEquals(mExpected, m);
    }

    /*
     * changeToExtractionMode
     */

    @Test
    public final void testChangeModeEmpty() {
        //Setup
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        //Test
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        //Check
        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeModeReg() {
        //Setup
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "2",
                "4", "1");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "2", "4", "1");

        //Test
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        //Check
        assertEquals(mExpected, m);
    }

    //Sift down once
    @Test
    public final void testChangeModeSiftDownOnce() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "heap",
                "insertion", "quick");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "heap", "insertion", "quick");
        //Test
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        //Check
        assertEquals(mExpected, m);
    }

    //SiftDown Multiple times
    @Test
    public final void testChangeModeSiftDownMultiple() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "10",
                "8", "7", "3", "4", "6");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "10", "8", "7", "3", "4", "6");
        //Test
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        //Check
        assertEquals(mExpected, m);
    }

    //SiftDown Multiple times
    @Test
    public final void testChangeModeSiftDownMultiple2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "10",
                "8", "7", "3", "4", "6");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "10", "8", "7", "3", "4", "6");
        //Test
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        //Check
        assertEquals(mExpected, m);
        //assertEquals DOESN'T check if the heaps are the same
    }
    /*
     * removeFirst
     */

    @Test
    public final void testRemoveFirstOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "10",
                "8", "7", "3", "4", "6");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "10", "8", "7", "3", "4", "6");
        //Test
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        String test1 = m.removeFirst();
        String ref1 = mExpected.removeFirst();

        //Check
        assertEquals(mExpected, m);
        assertEquals(ref1, test1);
    }

    //Is this bad practice?? (testing the method multiple times)
    //It showed me errors that I had
    @Test
    public final void testRemoveFirstManyTimes() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "j",
                "h", "g", "c", "d", "f");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "j", "h", "g", "c", "d", "f");
        //Test
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        String test1 = m.removeFirst();
        String ref1 = mExpected.removeFirst();
        String test2 = m.removeFirst();
        String ref2 = mExpected.removeFirst();
        String test3 = m.removeFirst();
        String ref3 = mExpected.removeFirst();
        String test4 = m.removeFirst();
        String ref4 = mExpected.removeFirst();

        //Check
        assertEquals(mExpected, m);

        assertEquals(ref1, test1);
        assertEquals(ref2, test2);
        assertEquals(ref3, test3);
        assertEquals(ref4, test4);
    }

    /*
     * isInInsertionMode
     */

    /*
     * order
     */

    /*
     * size
     */

    //Heapify with top different lengths

    //Check 3 methods callable during/after insertionMode
}
