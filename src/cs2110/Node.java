package cs2110;

/**
 * A node of a singly linked list whose elements have type `T`.
 */
public class Node<T> {

    /**
     * The list element in this node.
     */
    private final T data;

    /**
     * Next node on list (null if this is the last node).
     */
    private Node<T> next;

    /**
     * Create a Node containing element `elem` and pointing to successor node `successor` (may be
     * null).
     */
    Node(T elem, Node<T> successor) {
        data = elem;
        next = successor;
    }

    /**
     * Return the list element contained in this node.
     */
    public T data() {
        return data;
    }

    /**
     * Return the node after this one in the list (null if this is the last node of the list).
     */
    Node<T> next() {
        return next;
    }

    /**
     * Effect: make `successor` the successor to this node (`successor` may be null).
     */
    void setNext(Node<T> successor) {
        next = successor;
    }
}
