import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    // TODO - add test cases for constructor, add, remove, and length

    @Test
    public void testLength() {
        /*
         * Set up variables and call method under test if applicable
         */
        Sequence<String> s = this.createFromArgsTest("A", "B", "C", "D");
        int sLength = s.length();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sLength, 4);

    }

    @Test
    public void testEmptyConstructor() {
        /*
         * Set up variables and call method under test if applicable
         */
        Sequence<String> s = this.constructorTest();
        Sequence<String> sExpected = this.constructorRef();

        /*
         * Assert that values of variables match expectations
         */

        assertEquals(s, sExpected);

    }

    @Test
    public void testNonEmptyConstructor() {
        /*
         * Set up variables and call method under test if applicable
         */
        Sequence<String> s = this.createFromArgsTest("A", "B", "C", "D");
        Sequence<String> sExpected = this.createFromArgsRef("A", "B", "C", "D");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, sExpected);

    }

    @Test
    public void testAdd() {
        /*
         * Set up variables and call method under test if applicable
         */
        Sequence<String> s = this.createFromArgsTest("A", "B", "C");
        Sequence<String> sExpected = this.createFromArgsRef("A", "B", "C", "D");
        s.add(3, "D");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, sExpected);

    }

}
