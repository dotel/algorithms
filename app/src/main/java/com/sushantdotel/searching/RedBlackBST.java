package com.sushantdotel.searching;

import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;
    class Node{
        Key key;
        Value value;
        int size;
        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        Node left, right;
        boolean color;
    }
    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }


    // delete the key-value pair with the given key rooted at h
    private Node delete(Node h, Key key) {
        // assert get(h, key) != null;

        if (key.compareTo(h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.value = x.value;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }
    private Node moveRedLeft(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        colorFlip(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            colorFlip(h);
        }
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        colorFlip(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            colorFlip(h);
        }
        return h;
    }


    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the minimum key rooted at h
    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }


    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) {
        // assert x != null;
        if (x.left == null) return x;
        else                return min(x.left);
    }

    private Node balance(Node h) {
        // assert (h != null);

        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     colorFlip(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }
    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }


    public int size() {
        return size(root);
    }


    private boolean isRed(Node x){
        if(x == null) return false;
        return x.color;
    }
    private Node rotateLeft(Node x){
        assert isRed(x.right);
        Node temp = x.right;
        x.right = temp.left;
        temp.left = x;
        temp.color = x.color;
        x.color = RED;
        return temp;
    }
    private Node rotateRight(Node x){
        assert isRed(x.left);
        Node temp = x.left;
        x.left= temp.right;
        temp.right = x;
        temp.color = x.color;
        x.color = RED;
        return temp;
    }
    private void colorFlip(Node x){
        assert !isRed(x);
        assert isRed(x.left);
        assert isRed(x.right);
        x.color = RED;
        x.left.color = BLACK;
        x.right.color = BLACK;
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
        temp.size = 1 + size(temp.left) + size(temp.right);

        return balance(temp);
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

    public static void main(String[] args) {
        RedBlackBST<Integer, String> test = new RedBlackBST<>();
        test.put(1, "Hello.");
        test.put(2, "Hi.");
        test.put(1, " I don't know");
        test.put(3, " you.");
        System.out.println(test.get(2) + test.get(1)  + test.get(3));
        System.out.println(test.get(1)  + test.get(3));

    }

}
