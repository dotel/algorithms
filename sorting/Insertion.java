package np.com.sushantdotel.sorting;

import java.util.Comparator;

public class Insertion {
    public static void sort(Comparable[] a){
        sort(a, 0, a.length);
    }

    public static void sort(Comparable[] a, int low, int high)
    {
        for(int i = low; i < high; i++){
            for(int j = i; j > 0; j--){
                if(less(a[j], a[j - 1]))
                    exchange(a, j, j - 1);
            }
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
        Integer[] nums = new Integer[]{2, 32, 42, 43, 44, 21, 38, 99, 41, 7};
        Insertion.sort(nums);
        for(int x: nums)
            System.out.println(x);
    }
}
