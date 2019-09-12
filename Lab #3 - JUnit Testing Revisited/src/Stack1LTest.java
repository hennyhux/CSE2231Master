import components.stack.Stack;
import components.stack.Stack1L;
import components.stack.Stack3;

/**
 * Customized JUnit test fixture for {@code Stack1L}.
 */
public class Stack1LTest extends StackTest {

    @Override
    protected final Stack<String> constructorTest() {
    	
    	Stack<String> newStack = new Stack1L<String>();
    	
        return newStack;
    }

    @Override
    protected final Stack<String> constructorRef() {

    	Stack<String> refStack = new Stack1L<String>();
    	
        return refStack;
    }

}