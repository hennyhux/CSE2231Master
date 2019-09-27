import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Nathan Weltle and Henry Zhang
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

    @Test
    public final void testNoArguementConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.constructorTest();
        Set<String> setExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(setExpected, set);

    }

    @Test
    public final void testStringArguementConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest("A", "B", "C", "D");
        Set<String> setExpected = this.createFromArgsRef("A", "B", "C", "D");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(setExpected, set);

    }

    @Test
    public final void testStringIntegerArguementConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest("1", "3", "5", "7");
        Set<String> setExpected = this.createFromArgsRef("1", "3", "5", "7");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(setExpected, set);

    }

    @Test
    public final void testStringWordsArguementConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest("fizzbuzz", "foobar", "jack");
        Set<String> setExpected = this.createFromArgsRef("fizzbuzz", "foobar",
                "jack");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(setExpected, set);

    }

    @Test
    public final void testStringLongWordsArguementConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest(
                "floccinaucinihilipilification", "antidisestablishmentarianism",
                "Pseudopseudohypoparathyroidism");
        Set<String> setExpected = this.createFromArgsRef(
                "floccinaucinihilipilification", "antidisestablishmentarianism",
                "Pseudopseudohypoparathyroidism");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(setExpected, set);

    }

    @Test
    public final void testAddEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest();
        Set<String> setExpected = this.createFromArgsRef("Brave New World");

        set.add("Brave New World");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(setExpected, set);

    }

    @Test
    public final void testAddNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest("Brave New World");
        Set<String> setExpected = this.createFromArgsRef("Brave New World",
                "A world but a stage");

        set.add("A world but a stage");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(setExpected, set);
    }

    @Test
    public final void testAddIntegerNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest("4", "8", "15", "16", "23",
                "42");
        Set<String> setExpected = this.createFromArgsRef("4", "8", "15", "16",
                "23", "42", "858473839487389292");

        set.add("858473839487389292");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(setExpected, set);

    }

    @Test
    public final void testRemoveEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest("Hearts of Darkness");
        Set<String> setExpected = this.createFromArgsRef();

        set.remove("Hearts of Darkness");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(setExpected, set);

    }

    @Test
    public final void testRemoveNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest("Brave New World",
                "Hearts of Darkness");
        Set<String> setExpected = this.createFromArgsRef("Brave New World");

        set.remove("Hearts of Darkness");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(setExpected, set);

    }

    @Test
    public final void testRemoveAny() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest("5", "1", "3");
        Set<String> setExpected = this.createFromArgsRef("5", "3");

        String smallest = set.removeAny();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(setExpected, set);
        assertEquals("1", smallest);

    }

    @Test
    public final void testContainsEmptyFalse() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest();
        Set<String> setExpected = this.createFromArgsRef();

        boolean contains = set.contains("LiL UZI VERT");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, contains);
        assertEquals(setExpected, set);
    }

    @Test
    public final void testContainsNonEmptyFalse() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest("JAGEX IS POWERLESS AGAINST",
                "Chick-Fil-A", "Bethesda");
        Set<String> setExpected = this.createFromArgsRef(
                "JAGEX IS POWERLESS AGAINST", "Chick-Fil-A", "Bethesda");

        boolean contains = set.contains("Yah");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, contains);
        assertEquals(setExpected, set);
    }

    @Test
    public final void testContainsNonEmptyTrue() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest("MODS", "STEAM SUMMER SALE");
        Set<String> setExpected = this.createFromArgsRef("MODS",
                "STEAM SUMMER SALE");

        boolean contains = set.contains("MODS");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, contains);
        assertEquals(setExpected, set);
    }

    @Test
    public final void testSizeEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest();
        Set<String> setExpected = this.createFromArgsRef();

        int size = set.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(setExpected, set);
        assertEquals(0, size);
    }

    @Test
    public final void testSizeNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest("Only", "Human");
        Set<String> setExpected = this.createFromArgsRef("Only", "Human");

        int size = set.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(setExpected, set);
        assertEquals(2, size);
    }

    @Test
    public final void testSizeNonEmptyIntegerString() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest("14", "56", "69", "555");
        Set<String> setExpected = this.createFromArgsRef("14", "56", "69",
                "555");

        int size = set.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(setExpected, set);
        assertEquals(4, size);
    }

}
