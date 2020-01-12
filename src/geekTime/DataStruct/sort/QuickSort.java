package geekTime.DataStruct.sort;

public class QuickSort {
    public static int partition(int[] unsorted, int low, int high){
        int pivot = unsorted[low];
        while (low < high){
            while (low < high && unsorted[high] > pivot) {
                high--;
            }
            unsorted[low] = unsorted[high];
            while (low < high && unsorted[low] <= pivot) {
                low++;
            }
            unsorted[high] = unsorted[low];
        }
        unsorted[low] = pivot;
        return low;
    }

    public static void quick_sort(int[] unsorted, int low, int high){
        int loc = 0;
        if (low < high){
            //进行分区
            loc = partition(unsorted, low, high);
            //对低位进行分区
            quick_sort(unsorted, low, loc - 1);
            //对高位进行分区
            quick_sort(unsorted, loc + 1, high);
        }
    }

    public static void main(String[] args)
    {
        int[] x = { 6, 2, 4, 1, 5, 9, 23,51,42,15};
        quick_sort(x, 0, x.length - 1);
        for(int item:x){
            System.out.print(item+",");
        }
    }
}
