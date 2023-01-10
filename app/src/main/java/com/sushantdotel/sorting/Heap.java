package com.sushantdotel.sorting;

public class Heap<Item extends Comparable<Item>> {
    private Item[] items;
    private int N;
    Heap(int capacity) {
        N = 0;
        items = (Item[]) new Comparable[capacity + 1];
    }

    private void insert(Item item){
        items[++N] = item;
        swim(N);
    }

    private boolean isEmpty() {
        return N == 0;
    }

    private Item deleteMax(){
        Item temp = items[1];
        exchange(items, 1, N--);
        shrink(1);
        items[N + 1] = null;
        return temp;
    }

    private void swim(int k){
        while(k > 1 && less(items[k/2], items[k])) {
            exchange(items, k, k/2);
            k = k / 2;
        }
    }

    private void shrink(int k){
        while(2*k <= N) {
            int j = 2*k;
            if(j < N && less(items[j], items[j + 1])) j += 1;
            if(!less(items[k], items[j])) break;
            exchange(items, j, k);
            k = j;
        }

    }
    // Helper functions
    private static void exchange(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(Comparable i, Comparable j){
        return i.compareTo(j) < 0;
    }

    public static void main(String[] args) {
        Heap<Integer> maxPQ = new Heap<>(10);
        Integer[] nums = new Integer[]{2, 32, 42, 43, 44, 21, 38, 99, 41, 7};
        for(int i= 0; i< nums.length; i++){
            maxPQ.insert(nums[i]);
        }
        for(int x: nums)
            System.out.println(maxPQ.deleteMax());
    }
}

