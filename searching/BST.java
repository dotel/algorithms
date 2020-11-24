package np.com.sushantdotel.searching;
import np.com.sushantdotel.fundamentals.LinkedQueue;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value>{
    private Node root;
    private class Node{
        private Key key;
        private Node left, right;
        private Value value;
        private int count;          // number of nodes in the subtree - useful for various helper functions

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
    public void put(Key k, Value v){
        root = put(root, k, v);
    }
    private Node put(Node temp, Key k, Value v){
        if (temp == null) return new Node(k, v);
        int c = temp.key.compareTo(k);
        if(c < 0)
            temp.left = put(temp.left, k, v);
        else if(c > 0)
            temp.right = put(temp.right, k, v);
        else
            temp.value = v;
        temp.count = 1 + size(temp.left) + size(temp.right);
        return temp;
    }

    public int rank(Key key){
        return rank(key, root);
    }
    private int rank(Key k, Node root){
        if (root == null) return 0;
        int c = root.key.compareTo(k);
        if(c < 0)
            return rank(k, root.left);
        else if( c > 0)
            return 1 + size(root.left) + rank(k, root.right);
        else
            return size(root.left);
    }
    public Value get(Key k){
        Node temp = root;
        while(temp != null){
            int com = temp.key.compareTo(k);
            if (com < 0) temp = temp.left;
            else if (com > 0) temp = temp.right;
            else return temp.value;
        }
        return null;
    }

    public Iterable<Key> iterator(){
        LinkedQueue<Key> queue = new LinkedQueue();
        inorder(root, queue);
        return queue;
    }
    private void inorder(Node x, LinkedQueue<Key> queue){
        if(x == null) return;
        inorder(x.left, queue);
        queue.enqueue(x.key);
        inorder(x.right, queue);
    }
    public int size(Node n){
        if(n == null) return 0;
        return n.count;
    }
    public void deleteMin(){
        root = deleteMin(root);
    }
    private Node deleteMin(Node temp){
        if(temp.left == null) return temp.right;
        temp.left = deleteMin(temp.left);
        temp.count = 1 + size(temp.left) + size(temp.right);
        return temp;
    }
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    public boolean isEmpty() {
        return size() == 0;
    }
    public int size(){
        return size(root);
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else                return min(x.left);
    }

    public static void main(String[] args) {
        BST<Integer, String> test = new BST<>();
        test.put(1, "Hello.");
        test.put(2, "Hi.");
        test.put(1, " I don't know");
        test.put(3, " you.");
        System.out.println(test.get(2) + test.get(1)  + test.get(3));
        System.out.println(test.get(1)  + test.get(3));
        for(Integer i: test.iterator())
            System.out.println(i);
    }
}
