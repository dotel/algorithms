

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> points;

    public PointSET() {
        points = new TreeSet<>();
    }

    public boolean isEmpty(){
        return points.isEmpty();
    }
    public int size(){
        return points.size();
    }
    public void insert(Point2D p){
        if(p == null) throw new IllegalArgumentException("Points can't be null");
        points.add(p);
    }
    public boolean contains(Point2D p){
        if(p == null) throw new IllegalArgumentException("Points can't be null");
        return points.contains(p);
    }
    public void draw(){
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        for(Point2D p: points){
            p.draw();
        }
    }
    public Iterable<Point2D> range(RectHV rect){
        if(rect == null) throw new IllegalArgumentException("rectangle can't be null");
        ArrayList<Point2D> iter = new ArrayList<>();
        for(Point2D p: points){
            if(rect.contains(p))
                iter.add(p);
        }
        return iter;
    }
    public Point2D nearest(Point2D p){
        if(p == null) throw new IllegalArgumentException("Points can't be null");
        if(size() == 0) return null;
        double minDistance = p.distanceSquaredTo(points.first());
        Point2D nearestPoint = points.first();
        Point2D first = points.first();
        points.remove(first);
        for(Point2D x: points){
            double distanceTo = x.distanceSquaredTo(p);
            if(distanceTo < minDistance){
                minDistance = distanceTo;
                nearestPoint = x;
            }
        }
        points.add(first);
        return nearestPoint;
    }
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        PointSET brute = new PointSET();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            brute.insert(p);
        }
        System.out.println(brute.nearest(new Point2D(0.81, 0.30)));
        System.out.println(brute.size());
        brute.draw();
    }
}
