import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    // TODO - add test cases for constructor, push, pop, and length

    @Test
    public final void testConstructor() {
        Stack<String> s = this.constructorTest();
        Stack<String> sExpected = this.constructorRef();

        assertEquals(sExpected, s);
    }

    public final void testPushEmpty() {
        Stack<String> s = this.createFromArgsTest();
        Stack<String> sExpected = this.createFromArgsRef("Aye");

        s.push("Aye");

        assertEquals(sExpected, s);
    }

    @Test
    public final void testPushNonEmpty() {
        Stack<String> s = this.createFromArgsTest("Aye");
        Stack<String> sExpected = this.createFromArgsRef("Hello", "Aye");

        s.push("Hello");

        assertEquals(sExpected, s);
    }

    @Test
    public final void testPopEmpty() {
        Stack<String> s = this.createFromArgsTest("Aye");
        Stack<String> sExpected = this.createFromArgsRef();

        String entry = s.pop();

        assertEquals(sExpected, s);
        assertEquals("Aye", entry);
    }

    @Test
    public final void testPopToNonEmpty() {
        Stack<String> s = this.createFromArgsTest("Aye", "What");
        Stack<String> sExpected = this.createFromArgsRef("What");

        String entry = s.pop();

        assertEquals(sExpected, s);
        assertEquals("Aye", entry);
    }

    @Test
    public final void testLengthNonEmpty() {
        Stack<String> s = this.createFromArgsTest("Hi");

        int length = s.length();

        assertEquals(1, length);
    }

    @Test
    public final void testLengthEmpty() {
        Stack<String> s = this.createFromArgsTest();

        int length = s.length();

        assertEquals(0, length);
    }

}
