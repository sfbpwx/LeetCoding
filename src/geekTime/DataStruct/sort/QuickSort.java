package geekTime.DataStruct.sort;

public class QuickSort {
    public static int partition(int[] unsorted, int low, int high){
        //默认中位数为中指针
        int[] old  = unsorted;
        int pivot = unsorted[low];
        while (low < high){
            //当高指针大于中位数时，高指针递减，
            while (low < high && unsorted[high] > pivot) {
                high--;
            }
            //将低指针的值覆盖为高指针的值
            unsorted[low] = unsorted[high];
            //当低指针小于中位数时，低指针递增
            while (low < high && unsorted[low] <= pivot) {
                low++;
            }
            //将高指针的值覆盖为低指针
            unsorted[high] = unsorted[low];
        }
        //将暂存的低指针原有值重新赋给低指针
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
        int[] x = { 6, 2, 8, 1, 5, 9, 23,51,42,15};
        quick_sort(x, 0, x.length - 1);
        for(int item:x){
            System.out.print(item+",");
        }
    }
}
