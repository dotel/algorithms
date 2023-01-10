import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (size == 0) {
            head = new Node();
            head.item = item;
            head.next = null;
            head.prev = null;
            tail = head;
        }
        else {
            Node oldHead = head;
            head = new Node();
            head.item = item;
            head.next = oldHead;
            head.prev = null;
            oldHead.prev = head;
        }
        size++;

    }

    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (size == 0) {
            tail = new Node();
            tail.item = item;
            tail.prev = null;
            tail.next = null;
            head = tail;
        }
        else {
            Node oldTail = tail;
            tail = new Node();
            tail.item = item;
            tail.prev = oldTail;
            tail.next = null;
            oldTail.next = tail;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Deque underflow");
        Item i = head.item;
        if (size == 1) {
            head = null;
            tail = null;
        }
        else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return i;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Deque underflow");
        Item i = tail.item;
        if (size == 1) {
            head = null;
            tail = null;
        }
        else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return i;
    }

    public Iterator<Item> iterator() {
        return new DequeueIterator();
    }

    private class DequeueIterator implements Iterator<Item> {
        Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<String> q = new Deque<>();
        q.addFirst("Hello");
        q.addLast("World");
        q.addFirst("Wow");

        System.out.println(q.size());
        System.out.println(q.removeFirst());

        System.out.println(q.removeLast());
        for (String s : q) {
            System.out.println(s);
        }
		
    }
}
