import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;

public class Solver {
    // find a solution to the initial board (using the A* algorithm)
    private Node solution;
    private boolean solvable;
    private ArrayList<Board> solutionb = new ArrayList<>();
    private int moves;

    private class Node implements Comparable<Node> {
        Board tiles;
        int numberOfMoves;
        Node previous;
        int manhatthan;

        Node(Board b, Node previous) {
            tiles = b;
            numberOfMoves = 0;
            this.previous = previous;
            manhatthan = b.manhattan();
        }


        public int compareTo(Node that) {
            int x = (this.manhatthan + this.numberOfMoves) - (that.manhatthan + that.numberOfMoves);
            return x == 0 ? this.manhatthan - that.manhatthan : x;
        }

    }

    public Solver(Board initial) {
        moves = 0;
        solvable = false;
        if (initial == null)
            throw new IllegalArgumentException();
        MinPQ<Node> queue = new MinPQ<Node>();
        queue.insert(new Node(initial, null));
        queue.insert(new Node(initial.twin(), null));
        while (true) {
            Node min = queue.delMin();
            moves = min.numberOfMoves + 1;
            if (min.tiles.isGoal()) {
                solution = min;
                break;
            }
            for (Board x : min.tiles.neighbors()) {
                Node temp = new Node(x, min);
                temp.numberOfMoves = moves;
                if (moves == 1)
                    queue.insert(temp);
                if (moves > 1 && !(temp.tiles.equals(min.previous.tiles)))
                    queue.insert(temp);
            }
        }
        Node temp = solution;
        while (temp != null) {
            solutionb.add(temp.tiles);
            temp = temp.previous;
        }
        Collections.reverse(solutionb);
        if (solutionb.contains(initial))
            solvable = true;
    }


    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable())
            return -1;
        return solutionb.size() - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable())
            return null;
        return solutionb;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);
        // solve the puzzle
        Solver solver = new Solver(initial);
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());

            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }

    }
}
