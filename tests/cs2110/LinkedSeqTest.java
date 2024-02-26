package cs2110;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedSeqTest {

    // Helper functions for creating lists used by multiple tests.  By constructing strings with
    // `new`, more likely to catch inadvertent use of `==` instead of `.equals()`.

    /**
     * Creates [].
     */
    static Seq<String> makeList0() {
        return new LinkedSeq<>();
    }

    /**
     * Creates ["A"].  Only uses prepend.
     */
    static Seq<String> makeList1() {
        Seq<String> ans = new LinkedSeq<>();
        ans.prepend(new String("A"));
        return ans;
    }

    /**
     * Creates ["A", "B"].  Only uses prepend.
     */
    static Seq<String> makeList2() {
        Seq<String> ans = new LinkedSeq<>();
        ans.prepend(new String("B"));
        ans.prepend(new String("A"));
        return ans;
    }

    /**
     * Creates ["A", "B", "C"].  Only uses prepend.
     */
    static Seq<String> makeList3() {
        Seq<String> ans = new LinkedSeq<>();
        ans.prepend(new String("C"));
        ans.prepend(new String("B"));
        ans.prepend(new String("A"));
        return ans;
    }

    /**
     * Creates a list containing the same elements (in the same order) as array `elements`.  Only
     * uses prepend.
     */
    static <T> Seq<T> makeList(T[] elements) {
        Seq<T> ans = new LinkedSeq<>();
        for (int i = elements.length; i > 0; i--) {
            ans.prepend(elements[i - 1]);
        }
        return ans;
    }

    @DisplayName("WHEN a LinkedSeq is first constructed, THEN it should be empty.")
    @Test
    void testConstructorSize() {
        Seq<String> list = new LinkedSeq<>();
        assertEquals(0, list.size());
    }

    @DisplayName("GIVEN a LinkedSeq, WHEN an element is prepended, " +
            "THEN its size should increase by 1 each time.")
    @Test
    void testPrependSize() {
        // Note: List creation helper functions use prepend.
        Seq<String> list;

        // WHEN an element is prepended to an empty list
        list = makeList1();
        assertEquals(1, list.size());

        // WHEN an element is prepended to a list whose head and tail are the same
        list = makeList2();
        assertEquals(2, list.size());

        // WHEN an element is prepended to a list with no nodes between its head and tail
        list = makeList3();
        assertEquals(3, list.size());
    }

    @DisplayName("GIVEN a LinkedSeq containing a sequence of values, " +
            "THEN its string representation should include the string representations of its " +
            "values, in order, separated by a comma and space, all enclosed in square brackets.")
    @Test
    void testToString() {
        Seq<String> list;

        // WHEN empty
        list = makeList0();
        assertEquals("[]", list.toString());

        // WHEN head and tail are the same
        list = makeList1();
        assertEquals("[A]", list.toString());

        // WHEN there are no nodes between head and tail
        list = makeList2();
        assertEquals("[A, B]", list.toString());

        // WHEN there are at least 3 nodes
        list = makeList3();
        assertEquals("[A, B, C]", list.toString());

        // WHEN values are not strings
        Seq<Integer> intList = makeList(new Integer[]{1, 2, 3, 4});
        assertEquals("[1, 2, 3, 4]", intList.toString());
    }

    // TODO: Add new test cases here as you implement each method in `LinkedSeq`.  To save typing,
    // you may combine multiple tests for the _same_ method in the same @Test procedure, but be sure
    // that each test case is visibly distinct (comments are good for this, as demonstrated above).
    // You are welcome to compare against an expected `toString()` output in order to check multiple
    // aspects of the state at once (in general, later tests may make use of methods that have
    // previously been tested).  Each test procedure must describe its scenario using @DisplayName.
    @DisplayName("Given a linked list check for `elem` in the list that does not contain "
            + "`elem`, in a list that contains it once, and in a list that contains it more than "
            + "once.")
    @Test
    void testContains() {
        Seq<String> list;
        // WHEN empty and does not contain
        list = makeList0();
        assertFalse(list.contains("B"));

        // WHEN nonempty and does not contain
        list = makeList1();
        assertFalse(list.contains("B"));

        // WHEN nonempty and does contain
        list = makeList1();
        assertTrue(list.contains("A"));

        // WHEN nonempty and contains element twice or more
        String[] stringArray = {"Apple", "Banana", "Cherry", "Apple"};
        list = makeList(stringArray);
        assertTrue(list.contains("Apple"));
    }


    /*
     * There is no need to read the remainder of this file for the purpose of completing the
     * assignment.  We have not yet covered `hashCode()` or `assertThrows()` in class.
     */

    @DisplayName("GIVEN two distinct LinkedSeqs containing equivalent values in the same order, " +
            "THEN their hash codes should be the same.")
    @Test
    void testHashCode() {
        // WHEN empty
        assertEquals(makeList0().hashCode(), makeList0().hashCode());

        // WHEN head and tail are the same
        assertEquals(makeList1().hashCode(), makeList1().hashCode());

        // WHEN there are no nodes between head and tail
        assertEquals(makeList2().hashCode(), makeList2().hashCode());

        // WHEN there are at least 3 nodes
        assertEquals(makeList3().hashCode(), makeList3().hashCode());
    }

    @DisplayName("GIVEN a LinkedSeq, THEN its iterator should yield its values in order " +
            "AND it should stop yielding after the last value.")
    @Test
    void testIterator() {
        Seq<String> list;
        Iterator<String> it;

        // WHEN empty
        list = makeList0();
        it = list.iterator();
        assertFalse(it.hasNext());
        Iterator<String> itAlias = it;
        assertThrows(NoSuchElementException.class, () -> itAlias.next());

        // WHEN head and tail are the same
        list = makeList1();
        it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("A", it.next());
        assertFalse(it.hasNext());

        // WHEN there are no nodes between head and tail
        list = makeList2();
        it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("A", it.next());
        assertTrue(it.hasNext());
        assertEquals("B", it.next());
        assertFalse(it.hasNext());
    }
}
