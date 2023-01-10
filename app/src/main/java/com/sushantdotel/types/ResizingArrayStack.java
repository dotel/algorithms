package com.sushantdotel.types;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item>{
    private Item[] items;
    private int top;
    private int size;

    public ResizingArrayStack() {
        items = (Item[]) new Object[2];
        top = 0;
        int size = 0;
    }

    public int size(){
        return size;
    }

    public void push(Item x){
        if(size == items.length)
            resize(2 * items.length);
        items[top++] = x;
        size++;
    }
    public void resize(int newSize){
        Item[] temp = (Item[]) new Object[newSize];
        for(int i = 0; i < size; i++){
            temp[i] = items[i];
        }
        items = temp;
    }

    // For debugging purpose
    public void printStackSize(){
        System.out.println("Stack size is now: " + top + " and array size is now " + items.length);
    }

    public Item pop(){
        Item i = items[--top];
        if(size == items.length / 4)
            resize(items.length / 2);
        items[top] = null;
        size--;
        return i;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int top = size;
            @Override
            public boolean hasNext() {
                return top != 0;
            }

            @Override
            public Item next() {
                return items[--top];
            }
        };
    }

    public static void main(String[] args) {
        ResizingArrayStack<Integer> test = new ResizingArrayStack<>();
        for(int i = 0; i < 30; i++)
            test.push(i);
        for(Integer i: test){
            System.out.println(i);
        }
        for(int i = 0; i < 30; i++)
            System.out.println("Item number " + i + " is " + test.pop());
    }


}
