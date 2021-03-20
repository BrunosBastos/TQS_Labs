package stack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class StackTQSTest {

    private StackTQS<Integer> stackTQS;

    @BeforeEach
    void setUp() {
        stackTQS = new StackTQS<Integer>();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testPush_nTimes() {

        int n = 10;

        for(int i=0;i<n;i++){
            stackTQS.push(i);
        }

        assertEquals(n, stackTQS.size(), "Stack size does not match the number of pushes");
        assertFalse(stackTQS.isEmpty(), "Stack with inserted elements cannot be empty");
    }

    @Test
    void testPop_LastValue() {

        int x = 10;
        stackTQS.push(x);

        assertEquals(x, stackTQS.pop(), "Pop does not return last inserted value");
        assertEquals(0, stackTQS.size(), "Pop is not removing inserted values" );
    }

    @Test
    void testPop_nTimes(){

        stackTQS.push(1);
        stackTQS.push(2);
        stackTQS.push(3);

        stackTQS.pop();
        stackTQS.pop();
        stackTQS.pop();

        assertTrue(stackTQS.isEmpty());
        assertEquals(0,stackTQS.size());
    }

    @Test
    void testPop_EmptyStack(){

        assertThrows( NoSuchElementException.class, () -> stackTQS.pop(),
                "Expected NoSuchElementException when using pop on empty stack" );
    }

    @Test
    void testPeek_LastValue() {

        int x = 10;
        stackTQS.push(x);

        assertEquals(x, stackTQS.peek(), "Peek return value does not match the last inserted value");
        assertEquals(1, stackTQS.size(), "Peek does not keep the same stack size");

    }

    @Test
    void testPeek_EmptyStack(){

        assertThrows( NoSuchElementException.class, () -> stackTQS.peek(),
                "Expected NoSuchElementException when using peek on empty stack" );

    }

    @Test
    void testSize() {

        assertEquals(0, stackTQS.size(), "Stack on construction cannot have size 0");
    }

    @Test
    void testIsEmpty() {

        assertTrue(stackTQS.isEmpty());

    }
    @Test
    void testBound(){

        stackTQS.setBound(0);

        assertThrows( IllegalStateException.class, () -> stackTQS.push(1),
                "Expected IllegalStateException when inserting more values than the bound" );

    }

}