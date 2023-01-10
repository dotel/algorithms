package com.sushantdotel.searching;

public class SequentialSearchST<Key, Value> {
    private Node[] items;
    private int n;
    private Node first = null;
    private class Node{
        Key key;
        Value value;
        Node next;
        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    public void put(Key k, Value v){
        if(v == null) {
            delete(k);
            return;
        }
        for(Node temp = first; temp!=null; temp = temp.next){
            if(temp.key.equals(k)){
                temp.value = v;
                return;
            }
        }
        first = new Node(k, v, first);
        n++;
    }
    public Value get(Key k){
        for (Node x = first; x != null; x = x.next) {
            if (k.equals(x.key))
                return x.value;
        }
        return null;

    }
    public boolean contains(Key k){
        return get(k) != null;
    }
    public int size(){
        return n;
    }

    // finding rank is O(n) too but I'm bored.
    public void delete(Key k){
        if (k == null) throw new IllegalArgumentException("argument to delete() is null");
        first = delete(first, k);
    }
    private Node delete(Node x, Key k){
        if (x == null) return null;
        if (k.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, k);
        return x;

    }
    public boolean isEmpty(){
        return size() ==0;
    }
    public static void main(String[] args) {
        SequentialSearchST<Integer, String> test = new SequentialSearchST<>();
        test.put(1, "Hello.");
        test.put(2, "Hi.");
        test.put(1, " I don't know");
        test.put(3, " you.");
        System.out.println(test.get(2) + test.get(1)  + test.get(3));
        System.out.println(test.contains(2) + " Empty or not : " + test.isEmpty());
        test.delete(2);
        System.out.println(test.get(1)  + test.get(3));
    }
}
