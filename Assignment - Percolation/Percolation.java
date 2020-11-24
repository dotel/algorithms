
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF wf;
    private int[][] open;
    private final int size;
    private final int top;
    private final int bottom;

    public Percolation(final int n) {
        if (n <= 0) 
            throw new IllegalArgumentException("Doesn't seems right");        
        wf = new WeightedQuickUnionUF(n * n + 2);
        top = 0;
        bottom = n * n + 1;
        size = n;
        for (int i = 1; i <= n; i++) 
            wf.union(top, xyTo1D(1, i));       
        for (int i = 1; i <= n; i++) 
            wf.union(bottom, xyTo1D(n, i));        
        open = new int[n][n];
    }
    
    public void open(final int row, final int col) {
        validateIndices(row, col);
        if (!isOpen(row, col)) {
            open[row - 1][col - 1] = 1;
            if (col < size && isOpen(row, col + 1)) 
                wf.union(xyTo1D(row, col + 1), xyTo1D(row, col));            
            if (col > 1 && isOpen(row, col - 1)) 
                wf.union(xyTo1D(row, col), xyTo1D(row, col - 1));            
            if (row < size && isOpen(row + 1, col)) 
                wf.union(xyTo1D(row, col), xyTo1D(row + 1, col));            
            if (row > 1 && isOpen(row - 1, col)) 
                wf.union(xyTo1D(row, col), xyTo1D(row - 1, col));            
        }
    }

    private int xyTo1D(final int x, final int y) {
        return (x - 1) * size + y;
    }
    
    public boolean isOpen(final int row, final int col) {
        validateIndices(row, col);
        return open[row - 1][col - 1] == 1;
    }

    public boolean isFull(final int row, final int col) {
        validateIndices(row, col);
        return wf.find(xyTo1D(row, col)) == wf.find(top) && isOpen(row, col);
    }

    private void validateIndices(int x, int y) {
        if (x < 1 || x > size || y < 1 || y > size)
			throw new IllegalArgumentException("row index i out of bounds");    
    }
    
    public int numberOfOpenSites() {
        int openSites = 0;
        for (int i = 1; i < size * size + 1; i++) {
            if (isOpen(getX(i), getY(i))) 
                openSites++;            
        }
        return openSites;
    }

    private int getX(final int id) {
        if (id % size == 0) 
            return id / size;        
        return (id / size) + 1;
    }

    private int getY(final int id) {
        return id - size * (getX(id) - 1);
    }
    
    public boolean percolates() {
        if (size == 1 || size == 0) 
            return false;        
        return wf.find(top) == wf.find(bottom);
    }    
}
