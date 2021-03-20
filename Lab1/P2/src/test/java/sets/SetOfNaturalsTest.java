/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

/**
 * @author ico0
 */
public class SetOfNaturalsTest {
    private SetOfNaturals setA;
    private SetOfNaturals setB;
    private SetOfNaturals setC;
    private SetOfNaturals setD;

    @BeforeEach
    public void setUp() {
        setA = new SetOfNaturals();
        setB = SetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});

        setC = new SetOfNaturals();
        for (int i = 5; i < 50; i++) {
            setC.add(i * 10);
        }
        setD = SetOfNaturals.fromArray(new int[]{30, 40, 50, 60, 10, 20});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = setD = null;
    }

    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        setB.add(11);
        assertTrue(setB.contains(11), "add: added element not found in set.");
        assertEquals(7, setB.size(), "add: elements count not as expected.");
    }

    @Test
    public void testAddBadArray() {
        int[] elems = new int[]{10, 20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }


    @Test
    public void testIntersectForNoIntersection() {
        assertFalse(setA.intersects(setB), "no intersection but was reported as existing");

    }

    @Test
    public void testAddDuplicate(){

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                setB.add(20);
            }
        }, "Expecting IllegalArgumentException when inserting duplicated value");
    }

    @Test
    public void testAddNonNaturalNumber(){

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                setB.add(-1);
            }
        }, "Expecting IllegalArgumentException when inserting a non natural number");

    }

    @Test
    public void testFromArrayNonNaturalArray(){

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                int[] array = {-1,10,3};
                SetOfNaturals.fromArray(array);
            }
        },"Expecting IllegalArgumentException when converting a non natural array to SetOfNaturals");

    }

    @Test
    public void testIntersect(){

        assertTrue(setD.intersects(setB));
        assertFalse(setD.intersects(setA));

    }

    @Test
    public void testContains(){

        assertTrue(setD.contains(30));

    }

    @Test
    public void testSize_EmptySet(){

        assertEquals(0, setA.size());

    }

}
