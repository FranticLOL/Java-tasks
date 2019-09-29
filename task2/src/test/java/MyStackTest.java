import org.junit.Test;

import static org.junit.Assert.*;

public class MyStackTest {
    @Test
    public void isEmpty() {
        MyStack<Integer> stack = new MyStack<>();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void pushAndPeek() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(4);
        int expected = stack.peek();
        assertEquals(expected, 4);
    }

    @Test
    public void pop() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(4);
        int expected = stack.pop();
        assertEquals(expected, 4);
        assertTrue(stack.isEmpty());
    }
}
