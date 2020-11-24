package np.com.sushantdotel.sorting;

import java.util.Random;

public class Quick {
    private final static int CUTOFF = 7;
    private static int partition(Comparable[] a, int low, int high){
        int i = low, j = high + 1;
        while (true){
            while(less(a[++i], a[low])) // find items on the left to swap
                if(i == high) break;
            while (less(a[low], a[--j])) // find items on the right to swap
                if(j == low) break;
            if (i >= j) break;          // see if the pointer crosses each other
            exchange(a, i, j);
        }
        exchange(a, j, low);
        return j;
    }
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    private static void shuffle(Comparable[] a){
        for(int i = 0 ; i < a.length; i++){
            Random rand = new Random();
            exchange(a, i, rand.nextInt(i + 1));
        }
    }
    private static void sort(Comparable[] a){
        shuffle(a);
        sort(a, 0, a.length - 1);

    }
    private static void sort(Comparable[] a, int low, int high){
        if(low >= high) return;
        if(high <= low + CUTOFF -1){
            Insertion.sort(a, low, high);
        }
        int j = partition(a, low, high);
        sort(a, low, j);
        sort(a, j + 1, high);
    }
    private static void exchange(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] ar = new Integer[1000000];
        for(int i = 0; i < ar.length; i++)
            ar[i] = i;
        Quick.shuffle(ar);
        Merge.sort(ar);
        for(int i : ar)
            System.out.print(i + " ");
    }
}
