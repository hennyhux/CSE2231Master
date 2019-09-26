import components.binarytree.BinaryTree;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Utility class with implementation of {@code BinaryTree} static, generic
 * methods height and isInTree.
 *
 * @author Henry Zhang
 *
 */
public final class BinaryTreeMethods {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private BinaryTreeMethods() {
    }

    /**
     * Returns the height of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose height to return
     * @return the height of the given {@code BinaryTree}
     * @ensures height = ht(t)
     */
    public static <T> int height(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";
        int treeHeight = 1, leftTreeHeight = 0, rightTreeHeight = 0;

        if (t.size() == 0) {
            treeHeight = 0;
        }

        else if (t.size() > 1) {

            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T root = t.disassemble(left, right);
            leftTreeHeight += height(left);
            rightTreeHeight += height(right);
            t.assemble(root, left, right);

            if (leftTreeHeight > rightTreeHeight) {
                treeHeight = treeHeight + leftTreeHeight;
            }

            if (rightTreeHeight > leftTreeHeight) {
                treeHeight = treeHeight + rightTreeHeight;
            }

            else {
                treeHeight = treeHeight + rightTreeHeight;
            }

        }

        return treeHeight;
    }

    /**
     * Returns the size of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose size to return
     * @return the size of the given {@code BinaryTree}
     * @ensures size = |t|
     */
    public static <T> int size(BinaryTree<T> t) {
        int treeSize = 0;

        if (t.height() == 1) {
            treeSize = 1;
        }

        if (t.height() > 1) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T root = t.disassemble(left, right);
            treeSize += size(left) + size(right);
            treeSize++;
            t.assemble(root, left, right);
        }

        return treeSize; //including the root
    }

    /**
     * Returns the size of the given {@code BinaryTree<T>}. iterative
     *
     * @param <T>
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose size to return
     * @return the size of the given {@code BinaryTree}
     * @ensures size = |t|
     */
    public static <T> int sizeIterative(BinaryTree<T> t) {
        int treeSize = 1;

        for (T entry : t) {
            treeSize++;
        }

        return treeSize;

    }

    @Exam
    public static int leafSum(BinaryTree<Integer> t) {
        int sum = 0;

        if (t.size() == 1) {
            sum += t.root();
        }

        else if (t.size() > 1) {
            BinaryTree<Integer> left = t.newInstance();
            BinaryTree<Integer> right = t.newInstance();
            int root = t.disassemble(left, right);
            sum = root + leafSum(left) + leafSum(right);
            t.assemble(root, left, right);
        }

        return sum;
    }

    /**
     * Returns true if the given {@code T} is in the given {@code BinaryTree<T>}
     * or false otherwise.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} to search
     * @param x
     *            the {@code T} to search for
     * @return true if the given {@code T} is in the given {@code BinaryTree},
     *         false otherwise
     * @ensures isInTree = [true if x is in t, false otherwise]
     */
    public static <T> boolean isInTree(BinaryTree<T> t, T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        boolean isInTree = false;

        if (t.size() > 1 && t.root().equals(x)
                || t.size() == 1 && t.root().equals(x)) {
            isInTree = true;
        }

        else if (t.size() > 1) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T root = t.disassemble(left, right);
            isInTree = root.equals(x) || isInTree(left, x)
                    || isInTree(right, x);
            t.assemble(root, left, right);

        }

        return isInTree;
    }

    /**
     * Constructs and returns the {@code BinaryTree<String>} corresponding to
     * the given {@code String}.
     *
     * @param str
     *            the prefix representation of a {@code BinaryTree<String>}
     * @return the {@code BinaryTree<String>} corresponding to {@code str}
     * @requires <pre>
     * [str is the prefix representation of a BinaryTree<String>
     *  where the String representations of the labels of the tree do not
     *  contain the characters '(' and ')']
     * </pre>
     * @ensures treeFromString = [the BinaryTree<String> corresponding to str]
     */
    public static BinaryTree<String> treeFromString(String str) {
        return null;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Input a tree (or just press Enter to terminate): ");
        String str = in.nextLine();
        while (str.length() > 0) {
            BinaryTree<String> t = BinaryTreeUtility.treeFromString(str);
            out.println("Tree = " + BinaryTreeUtility.treeToString(t));
            out.println("Height = " + height(t));
            out.print("  Input a label to search "
                    + "(or just press Enter to input a new tree): ");
            String label = in.nextLine();
            while (label.length() > 0) {
                if (isInTree(t, label)) {
                    out.println("    \"" + label + "\" is in the tree");
                } else {
                    out.println("    \"" + label + "\" is not in the tree");
                }
                out.print("  Input a label to search "
                        + "(or just press Enter to input a new tree): ");
                label = in.nextLine();
            }
            out.println();
            out.print("Input a tree (or just press Enter to terminate): ");
            str = in.nextLine();
        }

        in.close();
        out.close();
    }

}
