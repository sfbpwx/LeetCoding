package geekTime.DataStruct.binarySearch;

public class BinarySearch {

    /**
     * 寻找第一个给定值元素
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearchForFirstValue(int[] a, int n, int value) {
        //设置低指针
        int low = 0;
        //设置高指针
        int high = n - 1;
        //当且仅当低指针<=高指针位置时
        while (low <= high) {
            //获取二分查找的中位数指针的值(括号内的意思是，高位减去低位除以2，如果不是整数，选取右边第一位)
            int mid =  low + ((high - low) >> 1);
            //当中位数指针大于给定值时，说明在小区间，则大指针挪到中位数前一位
            if (a[mid] > value) {
                high = mid - 1;
                //当小于时，说明在大区间，则小指针挪到中位数的后一位
            } else if (a[mid] < value) {
                low = mid + 1;
                //当相等时，就判断
            } else {
                //如果mid指针等于第一个或者mid指针的前一位不等于给定值，则返回当前指针数据
                if ((mid == 0) || (a[mid - 1] != value)) {
                    return mid;
                }
                //否则，高指针范围缩小
                else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 寻找最后一个给定值元素
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearchForLastValue(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == n - 1) || (a[mid + 1] != value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 寻找第一个小于等于给定值元素
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearchForOrLessThanFirstValue(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if ((mid == 0) || (a[mid - 1] < value)) return mid;
                else high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 寻找最后一个小于等于给定值元素
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearchForOrLessThanLastValue(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == n - 1) || (a[mid + 1] > value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }



    public static void main(String[]args){
        BinarySearch binarySearch = new BinarySearch();
        int[] input = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
        binarySearch.bsearchForFirstValue(input,input.length,6);
    }
}
