package com.sushantdotel.sorting;

public class Shell {
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        int h = 1;
        while (h < N/3)
            h = 3*h + 1; // 1, 4, 13, 40, 121, 364, ...
        while (h >= 1)
        { // h-sort the array.
            for (int i = h; i < N; i++)
            {
                for (int j = i; j >= h; j -= h)
                    if(less(a[j], a[j-h]))
                        exchange(a, j, j-h);
            }
            h = h/3;
        }
    }
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    private static void exchange(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static void main(String[] args) {
        Integer[] xs = new Integer[] {10, 11, 2, 19, 33, 6, 7, 9, 12, 18};
        Selection.sort(xs);
        String[] ys = new String[] {"Hello", "World", "This", "Is", "A", "Sorting", "Example"};
        for(int x: xs)
            System.out.print(x + " ");
        Selection.sort(ys);
        for(String s: ys)
            System.out.print(s + " ");
    }
}
