import java.util.NoSuchElementException;

public class ArrayStack<T> {
    //attributes
    private Object[] stack;
    private int top;

    //constructor
    public ArrayStack(int capacity) {
        this.stack = new Object[capacity];
        this.top = -1; //when the stack has 0 items, the top doesn't exist, which will be represented by -1
    }

    //methods
    //HELPER FUNCTIONALITY
    private void doubleCapacity() {
        /*this method will be called as a helper functionality to double the capacity of the array. this will create
        a temporary array with double the length of the stack, then add all the elements to the new larger stack
        [1,2,3,4,5] -> doubleCapacity() -> [1,2,3,4,5,null,null,null,null,null]
        ["Dog", "Cat"] -> doubleCapacity() -> ["Dog", "Cat", null, null]
        */

        int newCapacity;
        //check if capacity is 0, if it is then newCapacity would be 1, not 0 again
        if (this.stack.length == 0) newCapacity = 1;
        else newCapacity = this.stack.length * 2;
        
        Object[] tempStack = new Object[newCapacity];

        //use a for loop and iterate through both arrays to assign values to the new stack
        for(int i = 0; i < this.stack.length; ++i) {
            tempStack[i] = this.stack[i];
        }
        this.stack = tempStack;
    }

    public void push(T value) {
        /*check if the capacity has been reached, if not then add to the top. If capacity has been reached call
        doubleCapacity() then add to the top of the stack
        [1,2,3,null,null] -> push 4 -> [1,2,3,4,null]
        [1,2] -> push 3 -> [1,2,3,null]
        */

        if(this.size() >= this.stack.length) this.doubleCapacity();

        //add the value to the top
        this.stack[this.size()] = value;
        this.top++; //increment the top
    }

    public T pop() {
        /*check the top to see if its empty. store the value at the top, then remove it by setting it to null, then
        return the stored value and decrement top
        [1,2,3] -> pop -> [1,2,null]
        [null,null] -> pop -> NoSuchElementException
         */

        if(this.isEmpty()) throw new NoSuchElementException(); // top = -1 meaning the stack is empty

        //store the value at top then set to null
        T temp = (T) this.stack[this.top];
        this.stack[this.top] = null;
        this.top--;
        return temp;
    }

    public T peek() {
        /*the exact same as pop() but without removing or decrementing the top value and top
        [1,2,3] -> peek -> 3
         */

        if(this.isEmpty()) throw new NoSuchElementException(); // top = -1 meaning the stack is empty
        return (T) this.stack[this.top];
    }

    public boolean isEmpty() {
        /*check if top = -1, if so then return true, if not then return false
        [1] -> isEmpty -> false
        [null] -> isEmpty -> true
         */

        return this.top == -1;
    }

    public int size() {
        /* returns top + 1 which would equal the size of the stack no matter what
        [1,2,3] -> size -> 3
        [null] -> size -> 0
         */

        return this.top + 1;
    }
}
