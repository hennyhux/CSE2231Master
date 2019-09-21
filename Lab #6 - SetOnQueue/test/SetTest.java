import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
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
    public void testEmptyConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.constructorTest();
        Set<String> qExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testNonEmptyConstructor() {
        Set<String> s = this.constructorTest();
        s.add("a");
        Set<String> sExpected = this.constructorRef();
        sExpected.add("a");
        assertEquals(s, sExpected);
    }

    @Test
    public final void testAddNonEmpty() {
        Set<String> s = this.createFromArgsTest("B", "C");
        Set<String> sExpected = this.createFromArgsRef("A", "B", "C");
        s.add("A");
        assertEquals(s, sExpected);
    }

    @Test
    public final void testAddEmpty() {
        Set<String> s = this.createFromArgsTest("");
        Set<String> sExpected = this.createFromArgsRef("A");
        s.add("A");
        assertEquals(s, sExpected);
    }

    @Test
    public final void testRemoveNonEmpty() {
        Set<String> s = this.createFromArgsTest("A", "B", "C");
        Set<String> sExpected = this.createFromArgsRef("A", "B");
        s.remove("C");
        assertEquals(s, sExpected);
    }

    @Test
    public final void testRemoveEmpty() {
        /*
         * ,* Set up variables ,
         */
        Set<String> s = this.createFromArgsTest("green", "red", "blue");
        Set<String> sExpected = this.createFromArgsRef("red", "blue");
        /*
         * ,* Call method under test ,
         */
        s.remove("green");
        /*
         * ,* Assert that values of variables match expectations ,
         */
        assertEquals(s, sExpected);
    }

    @Test
    public final void testRemoveAny() {

        Set<String> s = this.createFromArgsTest("A", "B", "C");
        Set<String> sExpected = this.createFromArgsRef("A", "B", "C");
        String entry = s.removeAny();
        assertTrue(
                sExpected.contains(entry) && s.size() == sExpected.size() - 1);

    }

    @Test
    public final void testContains() {
        Set<String> s = this.createFromArgsTest("A", "B", "C");
        Set<String> sExpected = this.createFromArgsRef("A", "B", "C");
        String entry = "A";
        assertTrue(sExpected.contains(entry) && s.contains(entry));

    }

    @Test
    public final void testSize() {
        Set<String> s = this.createFromArgsTest("A", "B", "C");
        int size = s.size();
        assertEquals(size, 3);
    }

}
