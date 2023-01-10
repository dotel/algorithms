import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;
    private int N = 0;

    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int x) {
        Item[] copy = (Item[]) new Object[x];
        for (int i = 0; i < N; i++)
            copy[i] = queue[i];
        queue = copy;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (size == queue.length / 2) resize(2 * queue.length);
        queue[N++] = item;
        StdRandom.shuffle(queue, 0, N);
        size++;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        int index = StdRandom.uniform(N);
        Item item = queue[index];
        for (int i = index + 1; i < N; i++) {
            queue[i - 1] = queue[i];
        }
        N--;
        queue[N] = null;
        if (N > 0 && N == queue.length / 4) resize(queue.length / 2);
        return item;
    }

    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int index = StdRandom.uniform(N);
        Item item = queue[index];
        return item;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int top = 0;
        private int size = N;
        private Item[] que;

        public RandomizedQueueIterator() {
            que = queue.clone();
        }

        public boolean hasNext() {
            return top != size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            int index = StdRandom.uniform(size - top);
            Item item = que[index];
            for (int i = index + 1; i < N; i++) {
                que[i - 1] = que[i];
            }
            top++;
            return item;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(2);
        rq.enqueue(4);
        rq.enqueue(8);
        for (int i : rq)
            System.out.println(i);
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
    }
}
