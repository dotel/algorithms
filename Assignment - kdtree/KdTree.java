/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {
    private Node root;
    private int size;
    public KdTree() {
        size = 0;
        root = null;
    }

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node left;        // the left/bottom subtree
        private Node right;        // the right/top subtree
        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        throwIllegalException(p);
        if (p == null) throw new IllegalArgumentException("Points can't be null");
        if(!contains(p)) {
            root = insert(root, p, true);
            size++;
        }
    }
    private Node insert(Node x, Point2D p, boolean isVerticle){
        if (x == null)  {
            Node n = new Node(p, new RectHV(0, 0, 1, 1));
            return n;
        }
        int cmp = isVerticle?Point2D.X_ORDER.compare(p, x.p) : Point2D.Y_ORDER.compare(p, x.p);
        if(cmp < 0){
            x.left = insert(x.left, p, !isVerticle);
            if(isVerticle)
                x.left.rect = new RectHV(x.rect.xmin(), x.rect.ymin(), x.p.x(), x.rect.ymax()); // left partition
            else
                x.left.rect = new RectHV(x.rect.xmin(), x.rect.ymin(), x.rect.xmax(), x.p.y()); // down partition
        }
        else if (cmp >= 0) {
            x.right = insert(x.right, p, !isVerticle);
            if(isVerticle)
                x.right.rect = new RectHV(x.p.x(), x.rect.ymin(), x.rect.xmax() , x.rect.ymax());  // right partition
            else
                x.right.rect = new RectHV(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.rect.ymax());    // top partition
        }
        return x;
    }

    public boolean contains(Point2D p) {
        throwIllegalException(p);
        return contains(root, p, true);
    }
    private boolean contains(Node x, Point2D p, boolean isVertical){
        if(x == null)
            return false;
        if(x.p.equals(p))
            return true;
        int cmp;
        cmp = isVertical? Point2D.X_ORDER.compare(p, x.p): Point2D.Y_ORDER.compare(p, x.p);
        if(x.p.equals(p) == true)
            return true;
        if(cmp >= 0)
            return contains(x.right, p, !isVertical);
        else
            return contains(x.left, p, !isVertical);
    }

    public void draw() {
        draw(root, true);
    }
    private void draw(Node temp, boolean isVertical){
        throwIllegalException(temp);
        if(temp == null) return;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.02);
        temp.p.draw();
        StdDraw.setPenRadius(0.005);
        if(isVertical){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(root.rect.xmax(), root.rect.ymin(), root.rect.xmax(), root.rect.ymax());
        }else{
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(root.rect.xmin(), root.rect.ymax(), root.rect.xmax(), root.rect.ymax());
        }
        draw(temp.left, !isVertical);
        draw(temp.right, !isVertical);
    }

    public Iterable<Point2D> range(RectHV rect) {
        throwIllegalException(rect);
        if (rect == null) { throw new NullPointerException("rect can't be null"); }
        ArrayList<Point2D> iter = new ArrayList<>();
        return range(root, rect, iter);
    }
    private ArrayList<Point2D> range(Node root, RectHV rect, ArrayList<Point2D> iter){
        if(root == null || !rect.intersects(root.rect))
            return iter;
        if(rect.contains(root.p))
            iter.add(root.p);
        iter = range(root.left, rect, iter);
        return range(root.right, rect, iter);
    }

    public Point2D nearest(Point2D p) {
        throwIllegalException(p);
        if(size() == 0) return null;
        return nearest(root, p, root.p);
    }
    private Point2D nearest(Node x, Point2D p, Point2D nearestPoint){
        double min = nearestPoint.distanceSquaredTo(p);
        if(x == null || min <= x.rect.distanceSquaredTo(p))
            return nearestPoint;
        if (x.p.equals(p)) return x.p;
            if(min > p.distanceSquaredTo(x.p))
                nearestPoint = x.p;
        nearestPoint = nearest(x.left, p, nearestPoint);
        nearestPoint = nearest(x.right, p, nearestPoint);
        return nearestPoint;
    }
    private void throwIllegalException(Object o){
        if(o == null) throw new IllegalArgumentException("Argument can't be null");
    }
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        // PointSET brute = new PointSET();
        KdTree test= new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            test.insert(p);
            // brute.insert(p);
        }
        // KdTree test = new KdTree();
        // Point2D[] points = new Point2D[6];
        // points[0] = new Point2D(.2, .3);
        // points[1] = new Point2D(.2, 0);
        // points[2] = new Point2D(.3, .2);
        // points[3] = new Point2D(.4, .4);
        // points[4] = new Point2D(.2, .6);
        // points[5] = new Point2D(0, .3);
        // for(int i = 0; i < points.length; i++)
        //     test.insert(points[i]);
        // System.out.println(test.size());
        // System.out.println(test.contains(new Point2D(.5, 0)));
        // test.draw();
        int count = 0;
        for(Point2D p: test.range(new RectHV(0, 0, 1, 1))) {
            System.out.println("Points are : " + p.x() + " " + p.y());
            count++;
        }
        System.out.println("Number of items "  + count);
        // int x= 0;
        Point2D nearest = test.nearest(new Point2D(0.042, 0.899));
        // System.out.println("Nearest point to " + points[x].x() + ", " + points[x].y() + " is " + nearest.x() + " , " + nearest.y());
        System.out.println(nearest.x() + ", " + nearest.y());
        System.out.println(test.contains(new Point2D( 0.9 ,0.6)));
    }
}