import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Henry Zhang and Nathan Weltle
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    // TODO - add test cases for four constructors, multiplyBy10, divideBy10, isZero

    @Test
    public final void testNoArguementConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber num = this.constructorTest();
        NaturalNumber numExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(num, numExpected);

    }

    @Test
    public final void testIntConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber num = this.constructorTest(12);
        NaturalNumber numExpected = this.constructorRef(12);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(num, numExpected);
    }

    @Test
    public final void testZeroIntConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber num = this.constructorTest(0);
        NaturalNumber numExpected = this.constructorRef(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(num, numExpected);
    }

    @Test
    public final void testIntegerMaxConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber num = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber numExpected = this.constructorRef(Integer.MAX_VALUE);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(num, numExpected);
    }

    @Test
    public final void testStringConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber num = this.constructorTest("42");
        NaturalNumber numExpected = this.constructorRef("42");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(num, numExpected);
    }

    @Test
    public final void testEmptyStringConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber num = this.constructorTest("0");
        NaturalNumber numExpected = this.constructorRef("0");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(num, numExpected);
    }

    @Test
    public final void testVeryLongStringConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber num = this.constructorTest("876545682521582581215852");
        NaturalNumber numExpected = this
                .constructorRef("876545682521582581215852");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(num, numExpected);
    }

    @Test
    public final void testNNConstructorEmpty() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nn = new NaturalNumber1L();
        NaturalNumber num = this.constructorTest(nn);
        NaturalNumber numExpected = this.constructorRef(nn);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(num, numExpected);
    }

    @Test
    public final void testNNConstructorNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nn = new NaturalNumber1L(42);
        NaturalNumber num = this.constructorTest(nn);
        NaturalNumber numExpected = this.constructorRef(nn);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(num, numExpected);
    }

    @Test
    public final void testLargeNNConstructorNonEmptyString() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nn = new NaturalNumber1L(
                "42513461532657451324791545128562");
        NaturalNumber num = this.constructorTest(nn);
        NaturalNumber numExpected = this.constructorRef(nn);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(num, numExpected);
    }

    @Test
    public final void testLargeNNConstructorNonEmptyWithIntegerMax() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nn = new NaturalNumber1L(Integer.MAX_VALUE);
        NaturalNumber num = this.constructorTest(nn);
        NaturalNumber numExpected = this.constructorRef(nn);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(num, numExpected);
    }

    @Test
    public final void testIsZero() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber num = this.constructorTest();
        boolean numExpected = true;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(num.isZero(), numExpected);
    }

    @Test
    public final void testIsZeroButItsNotNNConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber number = new NaturalNumber1L("12");
        NaturalNumber num = this.constructorTest(number);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(num.isZero(), false);
    }

    @Test
    public final void testIsZeroWithStringConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber num = this.constructorTest("42");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(num.isZero(), false);
    }

    @Test
    public final void testIsZeroWithIntegerConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber num = this.constructorTest(42);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(num.isZero(), false);
    }

    ////////Kernel Method Tests///////////

    @Test
    public void multiplyBy10Zero() {
        //Setup:
        NaturalNumber testNum = this.constructorTest(139);
        NaturalNumber refNum = this.constructorRef(1390);

        //Method:
        testNum.multiplyBy10(0);

        //Check:
        assertEquals(testNum, refNum);
    }

    @Test
    public void multiplyBy10Max() {
        //Setup:
        NaturalNumber testNum = this.constructorTest(Integer.MAX_VALUE / 10);
        NaturalNumber refNum = this.constructorRef(Integer.MAX_VALUE);

        //Method:
        testNum.multiplyBy10(7);

        //Check:
        assertTrue(testNum.compareTo(refNum) == 0);

    }

    @Test
    public void multiplyToZeroByZero() {
        //Setup:
        NaturalNumber testNum = this.constructorTest(0);
        NaturalNumber refNum = this.constructorRef(0);

        //Method:
        testNum.multiplyBy10(0);

        //Check:
        assertTrue(testNum.compareTo(refNum) == 0);
    }

    @Test
    public void multiplyByReg1() {
        //Setup:
        NaturalNumber testNum = this.constructorTest(167084);
        NaturalNumber refNum = this.constructorRef(1670849);

        //Method:
        testNum.multiplyBy10(9);

        //Check:
        assertTrue(testNum.compareTo(refNum) == 0);
    }

    @Test
    public void multiplyByReg2() {
        //Setup:
        NaturalNumber testNum = this.constructorTest(10);
        NaturalNumber refNum = this.constructorRef(105);

        //Method:
        testNum.multiplyBy10(5);

        //Check:
        assertTrue(testNum.compareTo(refNum) == 0);
    }

    @Test
    public void multiplyByReg3() {
        //Setup:
        NaturalNumber testNum = this.constructorTest(1);
        NaturalNumber refNum = this.constructorRef(10);

        //Method:
        testNum.multiplyBy10(0);

        //Check:
        assertTrue(testNum.compareTo(refNum) == 0);
    }

    @Test
    public void multiplyByReg4() {
        //Setup:
        NaturalNumber testNum = this.constructorTest(999999);
        NaturalNumber refNum = this.constructorRef(9999999);

        //Method:
        testNum.multiplyBy10(9);

        //Check:
        assertTrue(testNum.compareTo(refNum) == 0);
    }

    @Test
    public void divideAZero() {
        //Setup:
        NaturalNumber testNum = this.constructorTest(0);
        NaturalNumber refNum = this.constructorRef(0);

        //Method:
        Integer remainder = testNum.divideBy10();

        //Check:
        assertTrue(testNum.compareTo(refNum) == 0);
        assertEquals(remainder.intValue(), 0);
    }

    @Test
    public void divideAMax() {
        //Setup:
        NaturalNumber testNum = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber refNum = this.constructorRef(Integer.MAX_VALUE / 10);

        //Method:
        Integer remainder = testNum.divideBy10();

        //Check:
        assertTrue(testNum.compareTo(refNum) == 0);
        assertEquals(remainder.intValue(), 7);
    }

    @Test
    public void divideAMiddle1() {
        //Setup:
        NaturalNumber testNum = this.constructorTest(1099);
        NaturalNumber refNum = this.constructorRef(109);

        //Method:
        Integer remainder = testNum.divideBy10();

        //Check:
        assertTrue(testNum.compareTo(refNum) == 0);
        assertEquals(remainder.intValue(), 9);
    }

    @Test
    public void divideASmall1() {
        //Setup:
        NaturalNumber testNum = this.constructorTest(8);
        NaturalNumber refNum = this.constructorRef(0);

        //Method:
        Integer remainder = testNum.divideBy10();

        //Check:
        assertTrue(testNum.compareTo(refNum) == 0);
        assertEquals(remainder.intValue(), 8);
    }

    @Test
    public void divideBy10Reg1() {
        //Setup:
        NaturalNumber testNum = this.constructorTest(100);
        NaturalNumber refNum = this.constructorRef(10);

        //Method:
        Integer remainder = testNum.divideBy10();

        //Check:
        assertTrue(testNum.compareTo(refNum) == 0);
        assertEquals(remainder.intValue(), 0);
    }

    @Test
    public void divideBy10Reg2() {
        //Setup:
        NaturalNumber testNum = this.constructorTest(1);
        NaturalNumber refNum = this.constructorRef(0);

        //Method:
        Integer remainder = testNum.divideBy10();

        //Check:
        assertTrue(testNum.compareTo(refNum) == 0);
        assertEquals(remainder.intValue(), 1);
    }

    @Test
    public void isZeroTrue() {
        //Setup:
        NaturalNumber testNum = this.constructorTest(0);

        //Method:
        boolean isZero = testNum.isZero();

        //Check:
        assertTrue(isZero);
    }

    @Test
    public void isZeroFalse() {
        //Setup:
        NaturalNumber testNum = this.constructorTest(909);

        //Method:
        boolean isZero = testNum.isZero();

        //Check:
        assertTrue(!isZero);
    }

    @Test
    public void isZeroEmpty() {
        //Setup:
        NaturalNumber testNum = this.constructorTest();

        //Method:
        boolean isZero = testNum.isZero();

        //Check:
        assertTrue(isZero);
    }

}
