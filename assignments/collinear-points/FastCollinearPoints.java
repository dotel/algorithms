/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private Point[] points;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new java.lang.IllegalArgumentException();
                }
            }
        }
        this.points = points.clone();
    }

    public int numberOfSegments()        // the number of line segments
    {
        return segments().length;
    }


    public LineSegment[] segments()                // the line segments
    {
        ArrayList<LineSegment> ls = new ArrayList<>();
        Point[] x = points.clone();
        for (int i = 0; i < points.length; i++) {
            Point origin = x[i];
            Arrays.sort(points, 0, points.length);
            Arrays.sort(points, 0, points.length, origin.slopeOrder());
            int count = 2;
            int end = 1;
            for (int start = 1; start < points.length - 1; start++) {
                if (origin.slopeTo(points[start]) == origin
                        .slopeTo(points[start + 1])) {
                    end = start + 1;
                    count++;
                    if (start != points.length - 2 && origin.slopeTo(points[start + 1]) == origin
                            .slopeTo(points[start + 2]))
                        continue;
                }
                else {
                    count = 2;
                }
                if (count >= 4) {
                    LineSegment newSegment = new LineSegment(origin, points[end]);
                    if (origin.compareTo(points[end - count + 2]) < 0) {
                        ls.add(newSegment);
                    }
                }

            }
        }
        return ls.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
