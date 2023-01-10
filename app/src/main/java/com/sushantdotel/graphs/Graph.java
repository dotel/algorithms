package com.sushantdotel.graphs;

import com.sushantdotel.types.LinkedBag;

// adjecency bag implementation
//2 -> 3, 4, 5
//1 -> 2, 3
public class Graph {
    private final int V;
    private LinkedBag<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        adj = new LinkedBag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedBag<Integer>();
        }
    }

    public void addEdge(int v, int w) {
        if(v > V || w > V)
            throw new UnsupportedOperationException("Vertex doesn't exist");
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    
    public int V() {
        return V;
    }

    public static void main(String[] args) {
    }



}
