import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStackTest {
    //push() tests

    @Test
    public void pushAddOneElement() {
        ArrayStack<Integer> stack = new ArrayStack<>(5);

        stack.push(10);

        assertEquals(1, stack.size());
        assertEquals(10, stack.peek());
    }

    @Test
    public void pushAddsMultipleElements() {
        ArrayStack<String> stack = new ArrayStack<>(5);

        stack.push("Dog");
        stack.push("Cat");
        stack.push("Bird");

        assertEquals(3, stack.size());
        assertEquals("Bird", stack.peek());
    }

    @Test
    public void pushDoublesCapacityWhenFull() {
        ArrayStack<Integer> stack = new ArrayStack<>(2);

        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(3, stack.size());
        assertEquals(30, stack.peek());
    }

    @Test
    public void pushWorksWithZeroInitialCapacity() {
        ArrayStack<Integer> stack = new ArrayStack<>(0);

        stack.push(10);

        assertEquals(1, stack.size());
        assertEquals(10, stack.peek());
    }

    //pop() tests

    @Test
    public void popReturnsTopElement() {
        ArrayStack<String> stack = new ArrayStack<>(5);
        stack.push("Dog");
        stack.push("Cat");

        String result = stack.pop();

        assertEquals("Cat", result);
    }

    @Test
    public void popRemovesTopElement() {
        ArrayStack<Integer> stack = new ArrayStack<>(5);
        stack.push(10);
        stack.push(20);

        stack.pop();

        assertEquals(1, stack.size());
        assertEquals(10, stack.peek());
    }

    @Test
    public void popOnEmptyStackThrowsException() {
        ArrayStack<Integer> stack = new ArrayStack<>(5);

        assertThrows(NoSuchElementException.class, () -> stack.pop());
    }

    //peek() tests

    @Test
    public void peekReturnsTopElement() {
        ArrayStack<String> stack = new ArrayStack<>(5);
        stack.push("Dog");
        stack.push("Cat");

        assertEquals("Cat", stack.peek());
    }

    @Test
    public void peekDoesNotRemoveTopElement() {
        ArrayStack<Integer> stack = new ArrayStack<>(5);
        stack.push(10);
        stack.push(20);

        int result = stack.peek();

        assertEquals(20, result);
        assertEquals(2, stack.size());
    }

    @Test
    public void peekCanBeCalledMultipleTimes() {
        ArrayStack<Integer> stack = new ArrayStack<>(5);
        stack.push(10);

        assertEquals(10, stack.peek());
        assertEquals(10, stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    public void peekOnEmptyStackThrowsException() {
        ArrayStack<Integer> stack = new ArrayStack<>(5);

        assertThrows(
                NoSuchElementException.class,
                () -> stack.peek()
        );
    }

    //size() tests
    @Test
    public void newStackHasSizeZero() {
        ArrayStack<Integer> stack = new ArrayStack<>(5);

        assertEquals(0, stack.size());
    }

    @Test
    public void sizeIncreasesAfterPushes() {
        ArrayStack<Integer> stack = new ArrayStack<>(5);

        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(3, stack.size());
    }

    @Test
    public void sizeDecreasesAfterPop() {
        ArrayStack<Integer> stack = new ArrayStack<>(5);
        stack.push(10);
        stack.push(20);

        stack.pop();

        assertEquals(1, stack.size());
    }

}
