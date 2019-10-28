import components.statement.Statement;

/**
 * Utility class with method to count the number of calls to primitive
 * instructions (move, turnleft, turnright, infect, skip) in a given
 * {@code Statement}.
 *
 * @author That nigga
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

                int blockLength = s.lengthOfBlock();
                for (int i = 0; i < blockLength; i++) {
                    Statement call = s.removeFromBlock(i);
                    count += countOfPrimitiveCalls(call);
                    s.addToBlock(i, call);
                }

                break;
            }
            case IF: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the IF.
                 */

                Statement subLabel = s.newInstance();
                Statement.Condition c = s.disassembleIf(subLabel);
                count += countOfPrimitiveCalls(subLabel);
                s.assembleIf(c, subLabel);

                break;
            }
            case IF_ELSE: {
                /*
                 * Add up the number of calls to primitive instructions in the
                 * "then" and "else" bodies of the IF_ELSE.
                 */

                Statement subLabelIF = s.newInstance();
                Statement subLabelELSE = s.newInstance();
                Statement.Condition c = s.disassembleIfElse(subLabelIF,
                        subLabelELSE);
                count += countOfPrimitiveCalls(subLabelIF)
                        + countOfPrimitiveCalls(subLabelELSE);
                s.assembleIfElse(c, subLabelIF, subLabelELSE);

                break;
            }
            case WHILE: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the WHILE.
                 */

                Statement subLabelWHILE = s.newInstance();
                Statement.Condition c = s.disassembleWhile(subLabelWHILE);
                count += countOfPrimitiveCalls(subLabelWHILE);
                s.assembleWhile(c, subLabelWHILE);

                break;
            }
            case CALL: {
                /*
                 * This is a leaf: the count can only be 1 or 0. Determine
                 * whether this is a call to a primitive instruction or not.
                 */

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
     * Reports the number of calls to a given instruction, {@code instr}, in a
     * given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @param instr
     *            the instruction name
     * @return the number of calls to {@code instr} in {@code s}
     * @ensures countOfInstructionCalls = [number of calls to instr in s]
     */
    public static int countOfInstructionCalls(Statement s, String instr) {

        return 0;
    }

    /**
     * Refactors the given {@code Statement} by renaming every occurrence of
     * instruction {@code oldName} to {@code newName}. Every other statement is
     * left unmodified.
     *
     * @param s
     *            the {@code Statement}
     * @param oldName
     *            the name of the instruction to be renamed
     * @param newName
     *            the new name of the renamed instruction
     * @updates s
     * @requires [newName is a valid IDENTIFIER]
     * @ensures <pre>
     * s = [#s refactored so that every occurrence of oldName is
     *   replaced by newName]
     * </pre>
     */
    public static void renameInstruction(Statement s, String oldName,
            String newName) {

    }
}
