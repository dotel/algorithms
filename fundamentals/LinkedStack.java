package np.com.sushantdotel.fundamentals;

import java.util.Iterator;

public class LinkedStack<Item> implements Iterable<Item>{
    private Node first = null;

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

    public void push(Item i){
        Node oldFirst = first;
        first = new Node();
        first.next = oldFirst;
        first.item = i;
    }

    public Item pop(){
        Item item = first.item;
        first = first.next;
        return item;
    }
    public static void main(String[] args) {
        LinkedStack<Integer> test = new LinkedStack<>();
        for(int i = 0; i < 30; i++)
            test.push(i);
        for(int i: test)
            System.out.println(i);
        for(int i = 0; i < 30; i++)
            System.out.println("Item number " + i + " is " + test.pop());
    }
}
