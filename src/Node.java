public class Node<E> {
    public E data;
    public Node<E> next;

    public Node() {
        this(null, null);
    }

    public Node(E data) {
        this(data, null);
    }

    public Node(E data, Node<E> node) {
        this.data = data;
        this.next = node;
    }

    boolean equals(Node<E> node) {
        if (node.data.equals(data)) {
            return true;
        }
        return false;
    }
}
