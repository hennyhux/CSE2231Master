import components.statement.Statement;

/**
 * Utility class with method to count the number of calls to primitive
 * instructions (move, turnleft, turnright, infect, skip) in a given
 * {@code Statement}.
 *
 * @author Put your name here
 *
 */
public final class CountPrimitiveCalls {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CountPrimitiveCalls() {
    }

    /**
     * Reports the number of calls to primitive instructions (move, turnleft,
     * turnright, infect, skip) in a given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @return the number of calls to primitive instructions in {@code s}
     * @ensures <pre>
     * countOfPrimitiveCalls =
     *  [number of calls to primitive instructions in s]
     * </pre>
     */
    public static int countOfPrimitiveCalls(Statement s) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: {
                /*
                 * Add up the number of calls to primitive instructions in each
                 * nested statement in the BLOCK.
                 */

                int length = s.lengthOfBlock();
                for (int i = 0; i < length; i++) {
                    Statement subLabel = s.removeFromBlock(i);
                    count += countOfPrimitiveCalls(subLabel);
                    s.addToBlock(i, subLabel);
                }

                break;
            }
            case IF: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the IF.
                 */

                Statement subLabel = s.newInstance();
                Statement.Condition condition = s.disassembleIf(subLabel);
                count += countOfPrimitiveCalls(subLabel);
                s.assembleIf(condition, subLabel);

                break;
            }
            case IF_ELSE: {
                /*
                 * Add up the number of calls to primitive instructions in the
                 * "then" and "else" bodies of the IF_ELSE.
                 */

                Statement subLabelIf = s.newInstance();
                Statement subLabelElse = s.newInstance();
                Statement.Condition conditionIfElse = s
                        .disassembleIfElse(subLabelIf, subLabelElse);

                count += countOfPrimitiveCalls(subLabelIf)
                        + countOfPrimitiveCalls(subLabelElse);
                s.assembleIfElse(conditionIfElse, subLabelIf, subLabelElse);

                break;
            }
            case WHILE: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the WHILE.
                 */

                Statement subLabel = s.newInstance();
                Statement.Condition condition = s.disassembleWhile(subLabel);
                count += countOfPrimitiveCalls(subLabel);
                s.assembleWhile(condition, subLabel);

                break;
            }
            case CALL: {
                /*
                 * This is a leaf: the count can only be 1 or 0. Determine
                 * whether this is a call to a primitive instruction or not.
                 */

                // TODO - fill in case

                String subLabel = s.disassembleCall();
                if (subLabel.equals("turnright") || subLabel.equals("move")
                        || subLabel.equals("infect")
                        || subLabel.equals("turnleft")
                        || subLabel.equals("skip")) {
                    count++;
                }
                s.assembleCall(subLabel);
                break;

            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
        return count;
    }

    /**
     * HOMEWORK QUESTION #23 Refactors the given {@code Statement} so that every
     * IF_ELSE statement with a negated condition (NEXT_IS_NOT_EMPTY,
     * NEXT_IS_NOT_ENEMY, NEXT_IS_NOT_FRIEND, NEXT_IS_NOT_WALL) is replaced by
     * an equivalent IF_ELSE with the opposite condition and the "then" and
     * "else" BLOCKs switched. Every other statement is left unmodified.
     *
     * @param s
     *            the {@code Statement}
     * @updates s
     * @ensures <pre>
     * s = [#s refactored so that IF_ELSE statements with "not"
     *   conditions are simplified so the "not" is removed]
     * </pre>
     */
    public static void simplifyIfElse(Statement s) {
        switch (s.kind()) {
            case BLOCK: {

                // TODO - fill in case

                break;
            }
            case IF: {

                // TODO - fill in case

                break;
            }
            case IF_ELSE: {

                // TODO - fill in case

                break;
            }
            case WHILE: {

                // TODO - fill in case

                break;
            }
            case CALL: {
                // nothing to do here...can you explain why?
                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
    }

}
