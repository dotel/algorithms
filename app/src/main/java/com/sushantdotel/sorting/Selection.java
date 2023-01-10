package com.sushantdotel.sorting;

public class Selection {
    public static void sort(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++)
        {
            int min = i;
            for (int j = i+1; j < N; j++)
                if (less(a[j], a[min]))
                    min = j;
            exchange(a, i, min);
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
