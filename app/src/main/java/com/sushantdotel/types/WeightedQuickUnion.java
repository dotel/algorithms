package com.sushantdotel.types;

public class WeightedQuickUnion {
    private int[] parents;
    private int[] size;

    public WeightedQuickUnion(int n) {
        parents = new int[n];
        size = new int[n];
        for(int i = 0; i < n; i++){
            size[i] = 1;
            parents[i] = i;
        }
    }

    public void union(int m, int n){
        if (size(m) <= size(n)) {
            parents[m] = root(n);
            size[m] += 1;
        } else {
            parents[n] = root(m);
            size[n] += 1;
        }
    }

    public int root(int x){
        while(x != parents[x]){
            x = parents[x];
        }
        return x;
    }

    public int size(int x){
        return size[x];
    }

    public boolean connected(int n, int m){
        return root(n) == root(m);
    }

    public static void main(String[] args) {
        WeightedQuickUnion w = new WeightedQuickUnion(10);
        w.union(2, 3);
        w.union(2, 4);
        System.out.println(w.connected(2, 7));
        System.out.println(w.connected(3, 4));
        System.out.println(w.connected(2, 3));
        System.out.println(w.size(3));
    }

}
