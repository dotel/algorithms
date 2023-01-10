package com.sushantdotel.types;

import java.util.Iterator;

public class LinkedBag<T> implements Iterable<T>{
    private Node first = null;
    private int size = 0;
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node temp = first;
            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public T next() {
                T item = temp.item;
                temp = temp.next;
                return item;
            }
        };
    }

    class Node{
        T item;
        Node next;
    }

    public void add(T i){
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
