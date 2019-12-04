package components.waitingline;

import components.queue.Queue;
import components.standard.Standard;

/**
 * First-in-first-out (FIFO) waiting line kernel component with primary methods.
 * (Note: by package-wide convention, all references are non-null.)
 *
 * @param <T>
 *            type of {@code WaitingLineKernel} entries
 * @mathmodel type QueueKernel is modeled by string of T; all entries in T are
 *            unique
 * @initially <pre>
 * ():
 *  ensures
 *   this = <>
 * </pre>
 * @iterator ~this.seen * ~this.unseen = this
 *
 *
 * @author Rohan Binoy, Logan Conner, Nathan Weltle, Henry Zhang, Shyamal Shah,
 *         Nyigel Spann
 * @author Group 5, 12, 17
 */
public interface WaitingLineKernel<T> extends Standard<Queue<T>>, Iterable<T> {

    /**
     * Adds {@code x} to the end of {@code this}.
     *
     * @param x
     *            the entry to be added
     * @aliases reference {@code x}
     * @updates this
     * @requires this does not contain x
     * @ensures this = #this * <x>
     */
    void addToLine(T x);

    /**
     * Removes and returns the entry at index pos of {@code this}.
     *
     * @param pos
     *            the index of the element to be removed
     * @return the entry removed
     * @updates this
     * @requires this /= <>
     * @requires 0 <= pos < this.length
     * @ensures #this = this[0, pos] * <remove> * this[pos + 1, this.length]
     */
    T removeFromLine(int pos);

    /**
     * Reports the index of x within {@code this} if present and -1 otherwise.
     *
     * @param x
     *            element to check for
     * @return index of x within {@code this} if present and -1 otherwise.
     */
    int indexOf(T x);

    /**
     * Reports length of {@code this}.
     *
     * @return the length of {@code this}
     * @ensures length = |this|
     */
    int length();

}