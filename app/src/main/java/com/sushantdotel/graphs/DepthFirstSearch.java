package com.sushantdotel.graphs;

import java.util.Stack;

public class DepthFirstSearch {
    private final boolean[] marked;
    private final int[] edgeTo;
    private final int searchedItem;

    public DepthFirstSearch(Graph g, int s) {
        searchedItem = s;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        dfs(g, s);
    }

    private void dfs(Graph g, int s) {
        for (int v : g.adj(s)) {
            if (!marked[v]) {
                marked[v] = true;
                edgeTo[v] = s;
                dfs(g, v);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!marked[v]) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != searchedItem; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(searchedItem);
        return path;
    }

    public static void main(String[] args) {
        Graph g = new Graph(7);
        g.addEdge(0, 1);
        g.addEdge(0, 5);
        g.addEdge(5, 4);
//        g.addEdge(1, 3);
        g.addEdge(1, 2);
//        g.addEdge(3, 4);
        int search = 0;
        DepthFirstSearch dfs = new DepthFirstSearch(g, search);
        for (int i = 0; i < 7; i++) {
            System.out.println(dfs.marked(i));
            if (dfs.marked[i]) {
                for (int x : dfs.pathTo(i))
                    System.out.print(x + " ");
                System.out.println();
            }
        }
        System.out.println();

    }
}