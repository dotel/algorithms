package np.com.sushantdotel.sorting;
public class Merge{
    private final static int CUTOFF = 10;

    private static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high){
        assert isSorted(a, low, mid);
        assert isSorted(a, mid + 1, high);
        for(int i = low; i <= high; i++)
            aux[i] = a[i];
        int i = low, j = mid + 1;
        // main merge operation
        for(int k = low; k <= high; k++){
            if(i > mid) a[k] = aux[j++];
            else if (j > high) a[k] = aux[i++];
            else if (less(a[j], a[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
        assert isSorted(a, low, high);
    }

    public static void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
        assert isSorted(a);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int low, int high){
        // For arrays smaller than size 7, do insertion sort instead of merge sort
        if(high <= low + CUTOFF -1){
            Insertion.sort(a, low, high);
        }
        if (high <= low) return;
        int mid = low + (high - low) / 2;
        sort(a, aux, low, mid);
        sort(a, aux, mid + 1, high);
        if(less(a[mid], a[mid + 1])) return;
        merge(a, aux, low, mid, high);
        assert isSorted(a, low, high);
    }
    //Helper functions
    private static boolean isSorted(Comparable[] a){
        return isSorted(a, 0, a.length - 1);
    }
    private static boolean isSorted(Comparable[] a, int low, int high){
        for(int i = low + 1; i <= high; i++)
            if(less(a[i], a[i - 1]))   return false;
        return true;
    }
    private static boolean less(Comparable i, Comparable j){
        return i.compareTo(j) < 0;
    }
    public static void main(String[] args){
        Integer[] xs = new Integer[]{2, 3, 23, 22, 21, 19, 17};
        Merge.sort(xs);
        for(int i : xs)
            System.out.print(i + " " );
        System.out.println("\n");
    }
}
