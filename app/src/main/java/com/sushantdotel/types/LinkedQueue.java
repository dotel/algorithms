package com.sushantdotel.types;

import java.util.Iterator;

public class LinkedQueue<Item> implements Iterable<Item>{
    private int size = 0;
    private Node first = null;
    private Node last = null;

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node temp = first;
            private int total = size;
            @Override
            public boolean hasNext() {
                return total != 0;
            }

            @Override
            public Item next() {
                Item i = temp.item;
                temp = temp.next;
                total -= 1;
                return i;
            }
        };
    }

    class Node{
        Item item;
        Node next;
    }
    public void enqueue(Item i){
        Node oldLast = last;
        last = new Node();
        last.item = i;
        last.next = null;
        if(size == 0){
            first = last;
        } else {
            oldLast.next = last;
        }
        size += 1;
    }

    public Item dequeue(){
        Item x = first.item;
        Node temp = first;
        first = first.next;
        temp = null;
        size -= 1;
        return x;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public static void main(String[] args) {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("Hello");
        queue.enqueue("World");
        queue.enqueue("This is a queue");
        for(String s: queue) {
            System.out.println(s);
        }
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
