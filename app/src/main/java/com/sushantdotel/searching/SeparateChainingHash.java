package com.sushantdotel.searching;

public class SeparateChainingHash<Key, Value> {
    private int M = 97;
    private Node[] st = new Node[M];
    private static class Node{
        private Object key;
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff % M);
    }
    public Value get(Key key){
        int i = hash(key);
        for(Node x = st[i]; x!=null; x = x.next){
            if(key.equals(x.key)) return (Value) x.val;
        }
        return null;
    }
    public void put(Key key, Value value){
        int i = hash(key);
        for(Node x = st[i]; x!=null; x = x.next){
            if(key.equals(x.key)) {x.val = value; return;}
        }
        st[i] = new Node(key, value, st[i]);
    }

}
