
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int size;
    private double[] openSites;

    // perform independent t on an n-by-n grid
    public PercolationStats(final int n, final int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Doesn't work that way");
        openSites = new double[trials];
        int randomSite;
        int x, y;
        size = n;
        for (int i = 0; i < trials; i++) {
            Percolation pc = new Percolation(n);
            int openedSites = 0;
            while (!pc.percolates()) {
                x = StdRandom.uniform(1, n + 1);
                y = StdRandom.uniform(1, n + 1);
                if (!pc.isOpen(x, y)) {
                    pc.open(x, y);
                    openedSites++;
                }
            }
            openSites[i] = (double) openedSites / (n * n);
        }
    }


    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(openSites);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(openSites);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(size)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(size)));
    }


    // test client (see below)
    public static void main(final String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt((args[1]));
        PercolationStats st = new PercolationStats(n, t);
        System.out.println("mean = " + st.mean());
        System.out.println("stdev = " + st.stddev());
        System.out.println(
                "95% confidence interval = [" + st.confidenceLo() + ", " + st.confidenceHi() + "]");
    }
}
