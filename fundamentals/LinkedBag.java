package np.com.sushantdotel.fundamentals;

import java.util.Iterator;

public class LinkedBag<Item> implements Iterable<Item>{
    private Node first = null;
    private int size = 0;
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node temp = first;
            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public Item next() {
                Item item = temp.item;
                temp = temp.next;
                return item;
            }
        };
    }

    class Node{
        Item item;
        Node next;
    }

    public void add(Item i){
        Node oldFirst = first;
        first = new Node();
        first.next = oldFirst;
        first.item = i;
        size += 1;
    }

    public int size(){
        return size;
    }
    public static void main(String[] args) {
        LinkedBag<Integer> test = new LinkedBag<>();
        for(int i = 0; i < 30; i++)
            test.add(i);
        for(int i: test)
            System.out.println(i);
    }
}
