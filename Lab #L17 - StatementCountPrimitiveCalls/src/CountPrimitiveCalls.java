import components.statement.Statement;
import components.statement.StatementKernel;
import components.statement.StatementKernel.Condition;

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

                int lengthOfBlock = s.lengthOfBlock();

                for (int i = 0; i < lengthOfBlock; i++) {
                    Statement removed = s.removeFromBlock(i);
                    count += countOfPrimitiveCalls(removed);
                    s.addToBlock(i, removed);
                }

                break;
            }
            case IF: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the IF.
                 */

                Statement label = s.newInstance();
                Statement.Condition c = s.disassembleIf(label);
                count += countOfPrimitiveCalls(label);
                s.assembleIf(c, label);

                break;
            }
            case IF_ELSE: {
                /*
                 * Add up the number of calls to primitive instructions in the
                 * "then" and "else" bodies of the IF_ELSE.
                 */

                Statement labelIf = s.newInstance();
                Statement labelElse = s.newInstance();
                Statement.Condition c = s.disassembleIfElse(labelIf, labelElse);
                count += countOfPrimitiveCalls(labelIf)
                        + countOfPrimitiveCalls(labelElse);
                s.assembleIfElse(c, labelIf, labelElse);

                break;
            }
            case WHILE: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the WHILE.
                 */

                Statement label = s.newInstance();
                Statement.Condition c = s.disassembleWhile(label);
                count += countOfPrimitiveCalls(label);
                s.assembleWhile(c, label);

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
     * HOMEWORK QUESTION Refactors the given {@code Statement} so that every
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

                int lengthOfBlock = s.lengthOfBlock();

                for (int i = 0; i < lengthOfBlock; i++) {
                    Statement label = s.removeFromBlock(i);
                    simplifyIfElse(label);
                    s.addToBlock(i, label);
                }

                break;
            }
            case IF: {

                Statement label = s.newInstance();
                Statement.Condition c = s.disassembleIf(label);
                simplifyIfElse(label);
                s.assembleIf(c, label);

                break;
            }
            case IF_ELSE: {

                Statement ifBlock = s.newInstance();
                Statement elseBlock = s.newInstance();
                Statement.Condition c = s.disassembleIfElse(ifBlock, elseBlock);

                switch (c.name()) {

                    case "NEXT_IS_NOT_EMPTY": {
                        Statement.Condition newCondition = Condition.NEXT_IS_EMPTY;
                        simplifyIfElse(ifBlock);
                        simplifyIfElse(elseBlock);
                        s.assembleIfElse(newCondition, elseBlock, ifBlock);
                        break;
                    }

                    case "NEXT_IS_NOT_ENEMY": {
                        Statement.Condition newCondition = Condition.NEXT_IS_ENEMY;
                        simplifyIfElse(ifBlock);
                        simplifyIfElse(elseBlock);
                        s.assembleIfElse(newCondition, elseBlock, ifBlock);
                        break;
                    }

                    case "NEXT_IS_NOT_FRIEND": {
                        Statement.Condition newCondition = Condition.NEXT_IS_FRIEND;
                        simplifyIfElse(ifBlock);
                        simplifyIfElse(elseBlock);
                        s.assembleIfElse(newCondition, elseBlock, ifBlock);
                        break;
                    }

                    case "NEXT_IS_NOT_WALL": {
                        Statement.Condition newCondition = Condition.NEXT_IS_WALL;
                        simplifyIfElse(ifBlock);
                        simplifyIfElse(elseBlock);
                        s.assembleIfElse(newCondition, elseBlock, ifBlock);
                        break;
                    }

                }

                break;
            }
            case WHILE: {

                Statement label = s.newInstance();
                Statement.Condition c = s.disassembleWhile(label);
                simplifyIfElse(label);
                s.assembleWhile(c, label);

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

        int count = 0;
        switch (s.kind()) {
            case BLOCK: {

                int lengthOfBlock = 0;

                for (int i = 0; i < lengthOfBlock; i++) {
                    Statement removed = s.removeFromBlock(i);
                    count += countOfInstructionCalls(removed, instr);
                    s.addToBlock(i, removed);

                }

                break;

            }
            case IF: {

                Statement ifLabel = s.newInstance();
                Statement.Condition c = s.disassembleIf(ifLabel);
                count += countOfInstructionCalls(ifLabel, instr);
                s.assembleIf(c, ifLabel);

                break;

            }
            case IF_ELSE: {

                Statement ifLabel = s.newInstance();
                Statement elseLabel = s.newInstance();
                Statement.Condition c = s.disassembleIfElse(ifLabel, elseLabel);
                count += countOfInstructionCalls(ifLabel, instr);
                count += countOfInstructionCalls(elseLabel, instr);
                s.assembleIfElse(c, ifLabel, elseLabel);

                break;

            }
            case WHILE: {

                Statement whileLabel = s.newInstance();
                Statement.Condition c = s.disassembleWhile(whileLabel);
                count += countOfInstructionCalls(whileLabel, instr);
                s.assembleWhile(c, whileLabel);

                break;

            }
            case CALL: {

                String call = s.disassembleCall();
                if (call.equals(instr)) {
                    count++;
                }

                s.assembleCall(call);

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
        switch (s.kind()) {
            case BLOCK: {

                int length = s.lengthOfBlock();
                for (int i = 0; i < length; i++) {
                    Statement subTree = s.removeFromBlock(i);
                    renameInstruction(subTree, oldName, newName);
                    s.addToBlock(i, subTree);
                }
                break;

            }

            case IF: {

                Statement ifLabel = s.newInstance();
                Statement.Condition c = s.disassembleIf(ifLabel);
                renameInstruction(ifLabel, oldName, newName);
                s.assembleIf(c, ifLabel);
                break;

            }

            case IF_ELSE: {

                Statement subTreeIf = s.newInstance();
                Statement subTreeElse = s.newInstance();
                Statement.Condition ifElseCondition = s
                        .disassembleIfElse(subTreeIf, subTreeElse);
                renameInstruction(subTreeIf, oldName, newName);
                renameInstruction(subTreeElse, oldName, newName);
                s.assembleIfElse(ifElseCondition, subTreeIf, subTreeElse);

                break;
            }

            case WHILE: {

                Statement whileLabel = s.newInstance();
                Statement.Condition c = s.disassembleWhile(whileLabel);
                renameInstruction(whileLabel, oldName, newName);
                s.assembleWhile(c, whileLabel);
                break;

            }

            case CALL: {

                String call = s.disassembleCall();

                if (call.equals(oldName)) {
                    s.assembleCall(newName);
                }

                else {
                    s.assembleCall(call);
                }

                break;
            }

            default:
                break;
        }

    }

    /*
     * HIGHEST IN THE ROOM
     *
     */
    @SuppressWarnings("incomplete-switch")
    public static void refactor(Statement s) {
        switch (s.kind()) {
            case BLOCK: {

                int blockLength = s.lengthOfBlock();

                for (int i = 0; i < blockLength; i++) {
                    Statement temp = s.removeFromBlock(i);
                    refactor(temp);
                    s.addToBlock(i, temp);
                }

            }

            case CALL: {

                String call = s.disassembleCall();
                s.assembleCall(call);
                if (call.equals("move")) {
                    Statement block = s.newInstance();
                    block.addToBlock(0, s);
                    s.assembleIf(StatementKernel.Condition.NEXT_IS_EMPTY,
                            block);
                }

            }
        }
    }

}
