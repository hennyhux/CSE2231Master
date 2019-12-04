package components.waitingline;

/**
 * {@code WaitingLineKernel} enhanced with secondary methods.
 *
 * @param <T>
 *            type of {@code Queue} entries
 * @mathdefinitions <pre>
 * IS_TOTAL_PREORDER (
 *   r: binary relation on T
 *  ) : boolean is
 *  for all x, y, z: T
 *   ((r(x, y) or r(y, x))  and
 *    (if (r(x, y) and r(y, z)) then r(x, z)))
 *
 * IS_SORTED (
 *   s: string of T,
 *   r: binary relation on T
 *  ) : boolean is
 *  for all x, y: T where (<x, y> is substring of s) (r(x, y))
 * </pre>
 *
 * @author Rohan Binoy, Logan Conner, Nathan Weltle, Henry Zhang, Shyamal Shah,
 *         Nyigel Spann
 * @author Group 5, 12, 17
 */

public interface WaitingLine<T> extends WaitingLineKernel<T> {

    /**
     * Reports the front of {@code this}.
     *
     * @return the front entry of {@code this}
     * @aliases reference returned by {@code front}
     * @requires this /= <>
     * @ensures <front> is prefix of this
     */
    T front();

    /**
     * Reports the end of {@code this}.
     *
     * @return the last entry of {@code this}
     * @aliases reference returned by {@code end}
     * @requires this /= <>
     * @ensures <end> is suffix of this
     */
    T end();

    /**
     * Concatenates ("appends") {@code q} to the end of {@code this}.
     *
     * @param q
     *            the {@code Queue} to be appended to the end of {@code this}
     * @updates this
     * @clears q
     * @ensures this = #this * #q
     */
    void append(WaitingLine<T> q);

    /**
     * Reverses ("flips") {@code this}.
     *
     * @updates this
     * @ensures this = rev(#this)
     */
    void flip();

    /**
     * Rotates {@code this}.
     *
     * @param distance
     *            distance by which to rotate
     * @updates this
     * @ensures <pre>
     * if #this = <> then
     *  this = #this
     * else
     *  this = #this[distance mod |#this|, |#this|) * #this[0, distance mod |#this|)
     * </pre>
     */
    void rotate(int distance);

    /**
     * Insert x into pos in {@code this}.
     *
     * @param pos
     *            location in {@code this} to insert into
     * @param x
     *            element to insert
     * @requires x is not in {@code this}
     * @ensures x is in location pos in {@code this}
     */
    void insertInLine(int pos, T x);
}