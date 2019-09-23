import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;

/**
 * JUnit test fixture for {@code BinarySearchTreeMethods}'s static methods
 * isInTree (and removeSmallest).
 */
public final class BinarySearchTreeMethodsTest {

    /**
     * Constructs and return a BST created by inserting the given {@code args}
     * into an empty tree in the order in which they are provided.
     *
     * @param args
     *            the {@code String}s to be inserted in the tree
     * @return the BST with the given {@code String}s
     * @requires [the Strings in args are all distinct]
     * @ensures createBSTFromArgs = [the BST with the given Strings]
     */
    private static BinaryTree<String> createBSTFromArgs(String... args) {
        BinaryTree<String> t = new BinaryTree1<String>();
        for (String s : args) {
            BinaryTreeUtility.insertInTree(t, s);
        }
        return t;
    }

    /**
     * Constructs and return a BST created by inserting the given {@code args}
     * into an empty tree in the order in which they are provided.
     *
     * @param args
     *            the {@code String}s to be inserted in the tree
     * @return the BST with the given {@code String}s
     * @requires [the Strings in args are all distinct]
     * @ensures createBSTFromArgs = [the BST with the given Strings]
     */
    private static BinaryTree<Integer> createBSTFromIntArgs(Integer... args) {
        BinaryTree<Integer> t = new BinaryTree1<Integer>();
        for (Integer s : args) {
            BinaryTreeUtility.insertInTree(t, s);
        }
        return t;
    }

    @Test
    public void sampleTest() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("b", "a", "c");
        BinaryTree<String> t2 = createBSTFromArgs("b", "a", "c");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, inTree);
        assertEquals(t2, t1);
    }

    // TODO: add here other test cases for BinarySearchTreeMethods.isInTree
    // (and for BinarySearchTreeMethods.removeSmallest)

    @Test
    public void testNotInTreeHeight2() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("b", "a", "c");
        BinaryTree<String> t2 = createBSTFromArgs("b", "a", "c");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "e");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, inTree);
        assertEquals(t2, t1);
    }

    @Test
    public void testInTreeHeight3() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("b", "a", "c", "d");
        BinaryTree<String> t2 = createBSTFromArgs("b", "a", "c", "d");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "d");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, inTree);
        assertEquals(t2, t1);
    }

    /*
     * EXAM QUESTION TEST CASES ------------------------------------------------
     */
    @Test
    public void testLeafSumHeight1Size1() {
        /*
         * Set up variables
         */
        BinaryTree<Integer> t1 = createBSTFromIntArgs(5);
        BinaryTree<Integer> t2 = createBSTFromIntArgs(5);
        /*
         * Call method under test
         */
        int sum = BinarySearchTreeMethods.leafSum(t1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(t2, t1);
        assertEquals(sum, 5);

    }

    @Test
    public void testLeafSumHeight2Size2() {
        /*
         * Set up variables
         */
        BinaryTree<Integer> t1 = createBSTFromIntArgs(5, 10);
        BinaryTree<Integer> t2 = createBSTFromIntArgs(5, 10);
        /*
         * Call method under test
         */
        int sum = BinarySearchTreeMethods.leafSum(t1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(t2, t1);
        assertEquals(sum, 15);

    }

    @Test
    public void testLeafSumHeight2Size3() {
        /*
         * Set up variables
         */
        BinaryTree<Integer> t1 = createBSTFromIntArgs(5, 10, 15);
        BinaryTree<Integer> t2 = createBSTFromIntArgs(5, 10, 15);
        /*
         * Call method under test
         */
        int sum = BinarySearchTreeMethods.leafSum(t1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(t2, t1);
        assertEquals(sum, 30);

    }

    @Test
    public void testLeafSumHeight3Size4() {
        /*
         * Set up variables
         */
        BinaryTree<Integer> t1 = createBSTFromIntArgs(5, 10, 15, 20);
        BinaryTree<Integer> t2 = createBSTFromIntArgs(5, 10, 15, 20);
        /*
         * Call method under test
         */
        int sum = BinarySearchTreeMethods.leafSum(t1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(t2, t1);
        assertEquals(sum, 50);

    }

    @Test
    public void testLeafSumHeight3Size5() {
        /*
         * Set up variables
         */
        BinaryTree<Integer> t1 = createBSTFromIntArgs(5, 10, 15, 20, 40);
        BinaryTree<Integer> t2 = createBSTFromIntArgs(5, 10, 15, 20, 40);
        /*
         * Call method under test
         */
        int sum = BinarySearchTreeMethods.leafSum(t1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(t2, t1);
        assertEquals(sum, 90);

    }
}
