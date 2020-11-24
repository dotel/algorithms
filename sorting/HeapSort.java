package np.com.sushantdotel.sorting;

public class HeapSort<Item extends Comparable<Item>> {

    private static void sort(Comparable items[]){
        int n = items.length;

        // heapify phase
        for (int k = n/2; k >= 1; k--)
            shrink(items, k, n);

        // sortdown phase
        int k = n;
        while (k > 1) {
            exchange(items, 1, k--);
            shrink(items, 1, k);
        }
    }
    private static void shrink(Comparable[] items, int k, int N){
        while(2*k <= N) {
            int j = 2*k;
            if(j < N && less(items, j, j + 1)) j += 1;
            if(!less(items, k, j)) break;
            exchange(items, j, k);
            k = j;
        }

    }
    // Helper functions
    private static void exchange(Comparable[] a, int i, int j){
        Comparable temp = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = temp;
    }

    // indices are off by one to support 1 based indexing
    private static boolean less(Comparable[] items, int i, int j){
        return items[i - 1].compareTo(items[j - 1]) < 0;
    }

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{2, 32, 42, 43, 44, 21, 38, 99, 41, 7};
        HeapSort.sort(nums);
        for(int i: nums)
            System.out.println(i);
    }
}

