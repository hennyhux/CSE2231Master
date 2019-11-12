import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;

/**
 * Layered implementation of secondary method {@code prettyPrint} for
 * {@code Program}.
 */
public final class Program1PrettyPrint1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Constructs into the given {@code Program} the program read from the given
     * input file.
     *
     * @param fileName
     *            the name of the file containing the program
     * @param p
     *            the constructed program
     * @replaces p
     * @requires [fileName is the name of a file containing a valid BL program]
     * @ensures p = [program from file fileName]
     */
    private static void loadProgram(String fileName, Program p) {
        SimpleReader in = new SimpleReader1L(fileName);
        p.parse(in);
        in.close();
    }

    /**
     * Prints the given number of spaces to the given output stream.
     *
     * @param out
     *            the output stream
     * @param numSpaces
     *            the number of spaces to print
     * @updates out.content
     * @requires out.is_open and spaces >= 0
     * @ensures out.content = #out.content * [numSpaces spaces]
     */
    private static void printSpaces(SimpleWriter out, int numSpaces) {
        for (int i = 0; i < numSpaces; i++) {
            out.print(' ');
        }
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1PrettyPrint1() {
        super();
    }

    /*
     * Secondary methods ------------------------------------------------------
     */

    @Override
    public void prettyPrint(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("PROGRAM " + this.name() + " IS");
        out.println();

        Map<String, Statement> newContext = this.newContext();
        Map<String, Statement> restoredContext = this.newContext();

        this.swapContext(newContext);
        int newContextSize = newContext.size();
        for (int i = 0; i < newContextSize; i++) {
            Map.Pair<String, Statement> removedPair = newContext.removeAny();
            printSpaces(out, 4);
            out.println("INSTRUCTION " + removedPair.key() + " IS");
            removedPair.value().prettyPrint(out, Program.INDENT_SIZE + 4);
            restoredContext.add(removedPair.key(), removedPair.value());
            printSpaces(out, 4);
            out.println("END " + removedPair.key());
            out.println();
        }

        this.swapContext(restoredContext);

        Statement newBody = this.newBody();
        this.swapBody(newBody);
        out.println("BEGIN");
        newBody.prettyPrint(out, Program.INDENT_SIZE); //overloading
        this.swapBody(newBody);
        out.println("END " + this.name());

    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Generate expected output in file "data/expected-output.txt"
         */
        out.println("*** Generating expected output ***");
        Program p1 = new Program1();
        loadProgram(fileName, p1);
        SimpleWriter ppOut = new SimpleWriter1L("data/expected-output.txt");
        p1.prettyPrint(ppOut);
        ppOut.close();
        /*
         * Generate actual output in file "data/actual-output.txt"
         */
        out.println("*** Generating actual output ***");
        Program p2 = new Program1PrettyPrint1();
        loadProgram(fileName, p2);
        ppOut = new SimpleWriter1L("data/actual-output.txt");
        p2.prettyPrint(ppOut);
        ppOut.close();
        /*
         * Check that prettyPrint restored the value of the program
         */
        if (p2.equals(p1)) {
            out.println("Program value restored correctly.");
        } else {
            out.println("Error: program value was not restored.");
        }

        in.close();
        out.close();
    }

    /*
     * EXTRA ACTIVITIES AND HOMEWORK
     *
     * Think about how you could automate testing of prettyPrint with JUnit.
     * Write a JUnit test fixture to test Program prettyPrint.
     */

    /**
     * Refactors the given {@code Program} by renaming instruction
     * {@code oldName}, and every call to it, to {@code newName}. Everything
     * else is left unmodified.
     *
     * @param p
     *            the {@code Program}
     * @param oldName
     *            the name of the instruction to be renamed
     * @param newName
     *            the new name of the renamed instruction
     * @updates p
     * @requires <pre>
     * oldName is in DOMAIN(p.context)  and
     * [newName is a valid IDENTIFIER]  and
     * newName is not in DOMAIN(p.context)
     * </pre>
     * @ensures <pre>
     * p = [#p refactored so that instruction oldName and every call
     *   to it are replaced by newName]
     * </pre>
     */
    public static void renameInstruction(Program p, String oldName,
            String newName) {

        Map<String, Statement> contextCopy = p.newContext();
        Map<String, Statement> restoredContext = p.newContext();
        p.swapContext(contextCopy);

        while (contextCopy.size() > 0) {
            Map.Pair<String, Statement> instr = contextCopy.removeAny();
            String key = instr.key();
            if (key.equals(oldName)) {
                key = newName;
            }
            renameInstruction(instr.value(), oldName, newName);
            restoredContext.add(key, instr.value());
        }

        p.swapContext(restoredContext);

        Statement body = p.newBody();
        p.swapBody(body);
        renameInstruction(body, oldName, newName);
        p.swapBody(body);

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
     * @ensures
     *
     *          <pre>
     * s = [#s refactored so that every occurrence of instruction oldName
     *   is replaced by newName]
     *          </pre>
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
                Statement subTree = s.newInstance();
                Statement.Condition ifCondition = s.disassembleIf(subTree);
                renameInstruction(subTree, oldName, newName);
                s.assembleIf(ifCondition, subTree);
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

                Statement subTree = s.newInstance();
                Statement.Condition whileCondition = s
                        .disassembleWhile(subTree);
                renameInstruction(subTree, oldName, newName);
                s.assembleWhile(whileCondition, subTree);
                break;

            }
            case CALL: {
                String call = s.disassembleCall();
                if (call.equals(oldName)) {
                    s.assembleCall(newName);
                } else {
                    s.assembleCall(call);
                }
                break;
            }
            default:
                break;
        }

    }

}
