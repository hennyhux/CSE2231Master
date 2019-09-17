import static org.junit.Assert.assertEquals;

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

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size
    @Test
    public void testConstrDefault() {
        //setup
        Map<String, String> testMap = this.constructorTest();
        Map<String, String> refMap = this.constructorRef();

        //check
        assertEquals(testMap, refMap);
    }

    /*
     * createNewRep()
     */
    @Test
    public void testCreateNewRepAfterAdding1() {
        //setup
        Map<String, String> testMap = this.createFromArgsTest("key", "val");
        Map<String, String> refMap = this.createFromArgsRef("key", "val");

        //check
        assertEquals(testMap, refMap);
    }

    @Test
    public void testCreateNewRepAfterAdding2() {
        //setup
        Map<String, String> testMap = this.createFromArgsTest("key", "val",
                "color", "maroon");
        Map<String, String> refMap = this.createFromArgsRef("key", "val",
                "color", "maroon");

        //check
        assertEquals(testMap, refMap);
    }

    /*
     * add() tests
     */
    @Test
    public void testAddOne() {
        //setup
        Map<String, String> testMap = this.constructorTest();
        Map<String, String> refMap = this.createFromArgsRef("donkey kong",
                "OP");

        //test methods:
        testMap.add("donkey kong", "OP");

        //check
        assertEquals(testMap, refMap);
    }

    @Test
    public void testAddMultiple1() {
        //setup
        Map<String, String> testMap = this.constructorTest();
        Map<String, String> refMap = this.createFromArgsRef("donkey kong", "OP",
                "kirby", "poyo");

        //test methods:
        testMap.add("donkey kong", "OP");
        testMap.add("kirby", "poyo");

        //check
        assertEquals(testMap, refMap);
    }

    @Test
    public void testAddMultiple2() {
        //setup
        Map<String, String> testMap = this.constructorTest();
        Map<String, String> refMap = this.createFromArgsRef("hello", "there",
                "hey", "here");

        //test methods:
        testMap.add("hello", "there");
        testMap.add("hey", "here");

        //check
        assertEquals(testMap, refMap);
    }

    @Test
    public void testAddOutOfOrder() {
        //setup
        Map<String, String> testMap = this.constructorTest();
        Map<String, String> refMap = this.createFromArgsRef("ganon", "doriyah",
                "banjo", "guh huh", "link", "hai heyaaahhh");

        //test methods:
        testMap.add("link", "hai heyaaahhh");
        testMap.add("ganon", "doriyah");
        testMap.add("banjo", "guh huh");

        //check
        assertEquals(testMap, refMap);
    }

    /*
     * remove() tests
     */
    @Test
    public void testRemoveOne() {
        //setup
        Map<String, String> testMap = this.createFromArgsTest("ganon",
                "doriyah", "banjo", "guh huh", "link", "hai heyaaahhh");
        Map<String, String> refMap = this.createFromArgsRef("banjo", "guh huh",
                "link", "hai heyaaahhh");

        //test methods:
        testMap.remove("ganon");

        //check
        assertEquals(testMap, refMap);
    }

    @Test
    public void testRemoveAll() {
        //setup
        Map<String, String> testMap = this.createFromArgsTest("ganon",
                "doriyah", "banjo", "guh huh", "link", "hai heyaaahhh");
        Map<String, String> refMap = this.constructorRef();

        //test methods:
        testMap.remove("ganon");
        testMap.remove("banjo");
        testMap.remove("link");

        //check
        assertEquals(testMap, refMap);
    }

    //add and remove

    //add/remove strings of same hashcode value (bucket)

    /*
     * removeAny() tests
     */
    @Test
    public void testremoveAnyOne() {
        //setup
        Map<String, String> testMap = this.createFromArgsTest("ganon",
                "doriyah", "banjo", "guh huh", "link", "hai heyaaahhh");
        Map<String, String> refMap = this.createFromArgsRef("ganon", "doriyah",
                "banjo", "guh huh", "link", "hai heyaaahhh");

        //test methods:
        Pair<String, String> removedPair = testMap.removeAny();
        String removedKey = removedPair.key();

        //Remove same pair from ref
        refMap.remove(removedKey);

        //check
        assertEquals(testMap, refMap);
    }

    @Test
    public void testRemoveAnyMultiple1() {
        //setup
        Map<String, String> testMap = this.createFromArgsTest("ganon",
                "doriyah", "banjo", "guh huh", "link", "hai heyaaahhh");
        Map<String, String> refMap = this.createFromArgsRef("ganon", "doriyah",
                "banjo", "guh huh", "link", "hai heyaaahhh");

        //test methods:
        Pair<String, String> removedPair1 = testMap.removeAny();
        String removedKey1 = removedPair1.key();

        Pair<String, String> removedPair2 = testMap.removeAny();
        String removedKey2 = removedPair2.key();

        //Remove same pair from ref
        refMap.remove(removedKey1);
        refMap.remove(removedKey2);

        //check
        assertEquals(testMap, refMap);
    }

    @Test
    public void testRemoveAnyAll() {
        //setup
        Map<String, String> testMap = this.createFromArgsTest("ganon",
                "doriyah", "banjo", "guh huh", "link", "hai heyaaahhh");
        Map<String, String> refMap = this.createFromArgsRef("ganon", "doriyah",
                "banjo", "guh huh", "link", "hai heyaaahhh");

        //test methods:
        Pair<String, String> removedPair1 = testMap.removeAny();
        String removedKey1 = removedPair1.key();

        Pair<String, String> removedPair2 = testMap.removeAny();
        String removedKey2 = removedPair2.key();

        Pair<String, String> removedPair3 = testMap.removeAny();
        String removedKey3 = removedPair3.key();

        //Remove same pair from ref
        refMap.remove(removedKey1);
        refMap.remove(removedKey2);
        refMap.remove(removedKey3);

        //check
        assertEquals(testMap, refMap);
    }

    /*
     * value() tests
     */
    @Test
    public void testValueOne() {
        //setup
        Map<String, String> testMap = this.createFromArgsTest("ganon",
                "doriyah", "banjo", "guh huh", "link", "hai heyaaahhh");

        //test methods:
        String val = testMap.value("ganon");

        //check
        assertEquals(val, "doriyah");
    }

    @Test
    public void testValueMultiple() {
        //setup
        Map<String, String> testMap = this.createFromArgsTest("ganon",
                "doriyah", "banjo", "guh huh", "link", "hai heyaaahhh");

        //test methods:
        String val = testMap.value("ganon");
        String val2 = testMap.value("banjo");
        String val3 = testMap.value("link");

        //check
        assertEquals(val, "doriyah");
        assertEquals(val2, "guh huh");
        assertEquals(val3, "hai heyaaahhh");
    }

    /*
     * hasKey() tests
     */
    @Test
    public void testHasKeyMultiple() {
        //setup
        Map<String, String> testMap = this.createFromArgsTest("ganon",
                "doriyah", "banjo", "guh huh", "link", "hai heyaaahhh");

        //test methods:
        boolean hasKey1 = testMap.hasKey("ganon");
        boolean hasKey2 = testMap.hasKey("kirby");
        boolean hasKey3 = testMap.hasKey("link");

        //check
        assertEquals(hasKey1, true);
        assertEquals(hasKey2, false);
        assertEquals(hasKey3, true);
    }

    @Test
    public void testHasKeyEmpty() {
        //setup
        Map<String, String> testMap = this.createFromArgsTest();

        //test methods:
        boolean hasKey1 = testMap.hasKey("ganon");

        //check
        assertEquals(hasKey1, false);
    }

    @Test
    public void hasKeyTrue() {
        //setup
        Map<String, String> testMap = this.createFromArgsTest("game and watch",
                "beep");

        //test methods:
        boolean hasKey1 = testMap.hasKey("game and watch");

        //check
        assertEquals(hasKey1, true);
    }

    @Test
    public void hasKey() {
        //setup
        Map<String, String> testMap = this.createFromArgsTest("game and watch",
                "beep", "ganon", "doriyah", "banjo", "guh huh", "link",
                "hai heyaaahhh");

        //test methods:
        boolean hasKey1 = testMap.hasKey("game and watch");

        //check
        assertEquals(hasKey1, true);
    }

    /*
     * size()
     */
    @Test
    public void testSizeEmpty() {
        //setup
        Map<String, String> testMap = this.createFromArgsTest();

        //test methods:
        int size = testMap.size();

        //check
        assertEquals(0, size);
    }

    @Test
    public void testSizeOne() {
        //setup
        Map<String, String> testMap = this.createFromArgsTest("game and watch",
                "beep");

        //test methods:
        int size = testMap.size();

        //check
        assertEquals(1, size);
    }

    @Test
    public void testSizeAfterRemove() {
        //setup
        Map<String, String> testMap = this.createFromArgsTest("game and watch",
                "beep", "ganon", "doriyah", "banjo", "guh huh", "link",
                "hai heyaaahhh");

        //test method
        testMap.remove("game and watch");
        testMap.remove("link");

        //test methods:
        int size = testMap.size();

        //check
        assertEquals(2, size);
    }

    @Test
    public void testSizeAfterRemoveAny() {
        //setup
        Map<String, String> testMap = this.createFromArgsTest("game and watch",
                "beep", "ganon", "doriyah", "banjo", "guh huh", "link",
                "hai heyaaahhh");

        //test method
        testMap.removeAny();
        testMap.removeAny();
        testMap.removeAny();

        //test methods:
        int size = testMap.size();

        //check
        assertEquals(1, size);
    }

}
