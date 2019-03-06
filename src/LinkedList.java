import java.util.*;

public class LinkedList<E> {
    public Node<E> first;

    public LinkedList() {
        first = null;
    }

    public LinkedList(Node<E> first) {
        this.first = first;
    }

    void add(Node<E> node) {
        if (first == null) {
            first = new Node<E>(node.data);
        } else {
            Node<E> current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<E>(node.data);
        }
    }

    void add(int index, Node<E> node) {
        if (this.size() < index || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            first = new Node<E>(node.data, first);
        } else {
            Node<E> current = first;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = new Node<E>(node.data, current.next);
        }
    }

    // removes the 1st occurrence (if it exists) of a Node that is equal to node
    public void remove(Node<E> node) {
        // if 1st occur at the first.
        if (first != null && first.equals(node)) {
            // if only one node v.s more than one node
            if (first.next == null) {
                first = new Node<>();
            } else {
                first = first.next;
            }
        } else {
            Node<E> current = first;
            while (current.next != null) {
                if (current.next.equals(node)) {
                    current.next = current.next.next;
                    break;
                } else {
                    current = current.next;
                }
            }
        }
    }

    int size() {
        int size = 1;
        if (first == null) {
            return 0;
        }
        Node current = first;
        while (current.next != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    boolean exists(Node<E> node) {
        Node<E> current = first;
        while (current.next != null) {
            if (current.equals(node)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    Node<E> getParent(Node<E> node) {
        if (!exists(node)) {
            throw new NoSuchElementException();
        }
        if (first.next == null || first.equals(node)) {
            return null;
        }
        Node<E> firstSpot = first;
        Node<E> secondSpot = first.next;
        while (secondSpot.next != null) {
            if (firstSpot.next.equals(node)) {
                return firstSpot;
            }
            firstSpot = firstSpot.next;
            secondSpot = secondSpot.next;
        }
        return null;
    }

    void truncateList(E value) {
        if (first == null) {
            return;
        } else if (first.data.equals(value)) {
            first = null;
            return;
        }
        Node<E> curr = first;
        while (curr.next != null) {
            if (curr.next.data.equals(value)) {
                curr.next = null;
                return;
            }
            curr = curr.next;
        }
    }

    public boolean equals(Object o) {
        if (o instanceof LinkedList) {
            LinkedList other = (LinkedList) o;
            return toString().equals(other.toString());
        } else {
            return false;
        }
    }

    public String toString() {
        Node current = first;
        if (current == null) {
            return "[]";
        }
        String result = "[";
        while (current != null) {
            result += current.data + "]";
            if (current.next != null) {
                result += " -> [";
            }
            current = current.next;
        }
        return result;
    }

}
