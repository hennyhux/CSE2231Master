import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value, hasKey, and size
    @Test
    public void addToEmptyTest() {
        //Setup
        Map<String, String> testMap = this.constructorTest();

        Map<String, String> refMap = this.constructorTest();
        refMap.add("heyo", "heyo there");

        //Method: add
        testMap.add("heyo", "heyo there");

        //Check
        assertEquals(testMap, refMap);

    }

    @Test
    public void addMultipleTest() {
        //Setup
        Map<String, String> testMap = this.constructorTest();

        Map<String, String> refMap = this.constructorTest();
        refMap.add("heyo", "heyo there");
        refMap.add("hello", "hello there");

        //Method: remove
        testMap.add("heyo", "heyo there");
        testMap.add("hello", "hello there");

        //Check:
        assertEquals(testMap, refMap);
    }

    @Test
    public void addReg1Test() {
        //Setup
        Map<String, String> testMap = this.constructorTest();

        Map<String, String> refMap = this.constructorTest();
        refMap.add("hi", "hi there");
        refMap.add("heyo", "heyo there");
        refMap.add("hello", "hello there");

        //Method: remove
        testMap.add("hi", "hi there");
        testMap.add("heyo", "heyo there");
        testMap.add("hello", "hello there");

        //Check:
        assertEquals(testMap, refMap);
    }

    /*
     * remove() tests
     */
    @Test
    public void removeFromOneTest() {
        //Setup
        Map<String, String> testMap = this.constructorTest();
        testMap.add("hi", "hi there");

        Map<String, String> refMap = this.constructorTest();

        //Method
        testMap.remove("hi");

        //Check

        assertEquals(testMap, refMap);
    }

    @Test
    public void removeMultipleTest() {
        //Setup
        Map<String, String> testMap = this.constructorTest();
        testMap.add("hi", "hi there");
        testMap.add("heyo", "heyo there");
        testMap.add("hello", "hello there");

        Map<String, String> refMap = this.constructorTest();
        refMap.add("heyo", "heyo there");
        refMap.add("hello", "hello there");

        //Method: remove
        testMap.remove("hi");

        //Check:
        assertEquals(testMap, refMap);
    }

    /*
     * removeAny tests
     */
    @Test
    public void removeAnyFromOneTest() {
        //Setup
        Map<String, String> testMap = this.constructorTest();
        testMap.add("hi", "hi there");

        Map<String, String> refMap = this.constructorTest();
        refMap.add("hi", "hi there");

        //Method
        Pair<String, String> pair = testMap.removeAny();

        //Check:
        //Remove the same Pair from refMap
        refMap.remove(pair.key());

        assertEquals(testMap, refMap);
    }

    @Test
    public void removeAnyReg1Test() {
        //Setup
        Map<String, String> testMap = this.constructorTest();
        testMap.add("hi", "hi there");
        testMap.add("heyo", "heyo there");
        testMap.add("hello", "hello there");

        Map<String, String> refMap = this.constructorTest();
        refMap.add("hi", "hi there");
        refMap.add("heyo", "heyo there");
        refMap.add("hello", "hello there");

        //Method: remove
        Pair<String, String> removedPair = testMap.removeAny();

        //Remove same pair from ref
        refMap.remove(removedPair.key());

        //Check:
        assertEquals(testMap, refMap);
    }

    /*
     * value()
     */
    @Test
    public void valueFromOneTest() {
        //Setup
        Map<String, String> testMap = this.constructorTest();
        testMap.add("hi", "hi there");
        testMap.add("heyo", "heyo there");
        testMap.add("hello", "hello there");

        //Method
        String val = testMap.value("heyo");

        //Check:
        assertTrue(val.equals("heyo there"));
    }

    @Test
    public void valueFromManyTest() {
        //Setup
        Map<String, String> testMap = this.constructorTest();
        testMap.add("hi", "hi there");

        Map<String, String> refMap = this.constructorTest();
        refMap.add("hi", "hi there");

        //Method
        String val = testMap.value("hi");

        //Check:
        assertTrue(val.equals("hi there"));
    }

    /*
     * hasKey() tests
     */
    @Test
    public void hasKeyTrueTest() {
        //Setup
        Map<String, String> testMap = this.constructorTest();
        testMap.add("hi", "hi there");
        testMap.add("heyo", "heyo there");
        testMap.add("hello", "hello there");

        //Method
        boolean hasKey = testMap.hasKey("heyo");

        //Check:
        assertTrue(hasKey);
    }

    @Test
    public void hasKeyFalseTest() {
        //Setup
        Map<String, String> testMap = this.constructorTest();
        testMap.add("hi", "hi there");
        testMap.add("heyo", "heyo there");
        testMap.add("hello", "hello there");

        //Method
        boolean hasKey = testMap.hasKey("General Kenobi");

        //Check:
        assertTrue(!hasKey);
    }

    @Test
    public void hasKeyEmpty() {
        //Setup
        Map<String, String> testMap = this.constructorTest();

        //Method
        boolean hasKey = testMap.hasKey("heyo");

        //Check:
        assertTrue(!hasKey);
    }

    /*
     * size() tests
     */
    @Test
    public void sizeEmptyTest() {
        //Setup
        Map<String, String> testMap = this.constructorTest();

        //Method
        int size = testMap.size();

        //Check:
        assertEquals(size, 0);
    }

    @Test
    public void sizeRegTest() {
        //Setup
        Map<String, String> testMap = this.constructorTest();
        testMap.add("hi", "hi there");
        testMap.add("heyo", "heyo there");
        testMap.add("hello", "hello there");

        //Method
        int size = testMap.size();

        //Check:
        assertEquals(size, 3);
    }

}
