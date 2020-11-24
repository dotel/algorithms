/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private int[][] board;
    private int n;


    public Board(int[][] tiles) {
        n = tiles[1].length;
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int hams = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != i * n + j + 1)
                    hams++;
            }
        }
        return hams - 1;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int man = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0)
                    continue;
                int goalRow = -1;
                int sum = 0;
                while (sum < board[i][j]) {
                    goalRow++;
                    sum += n;
                }
                int goalCol = Math.abs(board[i][j] - 1 - goalRow * n);
                if (board[i][j] != i * n + j + 1)
                    man += Math.abs(goalCol - j) + Math.abs(goalRow - i);
            }
        }
        return man;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null || y instanceof String)
            return false;
        Board that = (Board) y;
        return Arrays.deepEquals(board, that.board);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighs = new ArrayList<>();
        String[] s = this.toString().split("\n");
        int dim = Integer.parseInt(s[0]);
        int[][] t = new int[dim][dim];
        int zrow = 0, zcol = 0;
        for (int i = 0; i < dim; i++) {
            String[] singleBox = s[i + 1].trim().split("\\s+");
            for (int j = 0; j < dim; j++) {
                t[i][j] = Integer.parseInt(singleBox[j]);
                if (t[i][j] == 0) {
                    zrow = i;
                    zcol = j;
                }
            }
        }

        int[][] t1 = new int[n][n];
        int[][] t2 = new int[n][n];
        int[][] t3 = new int[n][n];
        int[][] t4 = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                t1[i][j] = t[i][j];
                t2[i][j] = t[i][j];
                t3[i][j] = t[i][j];
                t4[i][j] = t[i][j];
            }
        }
        if (zrow + 1 < n) {
            int temp = t1[zrow][zcol];
            t1[zrow][zcol] = t1[zrow + 1][zcol];
            t1[zrow + 1][zcol] = temp;
            neighs.add(new Board(t1));
        }
        if (zcol - 1 >= 0) {
            int temp = t4[zrow][zcol];
            t4[zrow][zcol] = t4[zrow][zcol - 1];
            t4[zrow][zcol - 1] = temp;
            neighs.add(new Board(t4));
        }
        if (zrow - 1 >= 0) {
            int temp = t2[zrow][zcol];
            t2[zrow][zcol] = t2[zrow - 1][zcol];
            t2[zrow - 1][zcol] = temp;
            neighs.add(new Board(t2));
        }
        if (zcol + 1 < n) {
            int temp = t3[zrow][zcol];
            t3[zrow][zcol] = t3[zrow][zcol + 1];
            t3[zrow][zcol + 1] = temp;
            neighs.add(new Board(t3));
        }


        return neighs;

    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        String[] s = this.toString().split("\n");
        int dim = Integer.parseInt(s[0]);
        int[][] t = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            String[] singleBox = s[i + 1].trim().split("\\s+");
            for (int j = 0; j < dim; j++) {
                t[i][j] = Integer.parseInt(singleBox[j]);
            }
        }
        int k = 0, m = 0, x = 1;
        while (true) {
            if (t[k][m] != 0 && t[k][x] != 0 && k < n && m < n && x < n) {
                int temp = t[k][x];
                t[k][x] = t[k][m];
                t[k][m] = temp;
                return new Board(t);
            }
            else {
                k++;
            }

        }
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);
        // System.out.println(initial);
        for (Board b : initial.neighbors()) {
            System.out.println(b);
            System.out.println("manhattan is " + b.manhattan() + " hamming is " + b.hamming());
        }
    }
}
