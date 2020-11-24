package np.com.sushantdotel.sorting;

public class MaxPQ<Item extends Comparable<Item>> {
    private Item[] items;
    private int N;
    private int size;
    MaxPQ(int capacity){
        items = (Item[]) new Comparable[capacity];
        N = 0;
        size = 0;
    }
    public void insert(Item v){
        if(size == items.length) resize(2*items.length);
        items[N++] = v;
        size += 1;
    }
    private void resize(int newSize){
        Item[] temp = (Item[])new Comparable[newSize];
        for(int i = 0; i < N; i++)
            temp[i] = items[i];
        items = temp;
    }
    public Item delMax(){
        int max = 0;
        for(int i = 1; i < N; i++){
            if(less(items[max], items[i])) max = i;
        }
        exchange(items, max, N - 1);
        if(size == items.length / 4) resize(items.length / 2);
        size--;
        return items[--N];
    }
    private static void exchange(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    private static boolean less(Comparable i, Comparable j){
        return i.compareTo(j) < 0;
    }
    public boolean isEmpty(){
        return N == 0;
    }
    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ<>(10);
        Integer[] nums = new Integer[]{2, 32, 42, 43, 44, 21, 38, 99, 41, 7};
        for(int i= 0; i< nums.length; i++){
            maxPQ.insert(nums[i]);
        }
        for(int x: nums)
            System.out.println(maxPQ.delMax());
    }
}
