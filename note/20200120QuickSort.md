## 快速排序
    
选取随机数，运用选择排序方法，随机中位数使左边的数字都小于中位数，右边的数字均大于中位数，然后在进行拆分，运用选择排序方法使快速排序成为原地排序算法

### 快速排序要点

#### 1、选取中位数

普通的快速排序是选取中位数，就是简单的选取问题，这个随机选择可能是头，可能是末尾。

运用公式推导进行中位数选择时，会尽量把最靠中间的数字选择出来。运用的是头尾指针向中间靠近的方法得出。

在运算过程中，需要将低位节点的值暂存，最后重新还原到数组中。并且最终选定的值即为暂存值

#### 2、递归排序

运用递归推倒公式得出最后结果。步骤为：

1、推导中位数
2、计算低位排序
3、计算高位排序


#### 快速排序的推导公式

```
递推公式：
quick_sort(p…r) = quick_sort(p…q-1) + quick_sort(q+1… r)

终止条件：
p >= r
```

#### 推导公式生成的伪代码

```

// 快速排序，A是数组，n表示数组的大小
quick_sort(A, n) {
  quick_sort_c(A, 0, n-1)
}
// 快速排序递归函数，p,r为下标
quick_sort_c(A, p, r) {
  if p >= r then return
  
  q = partition(A, p, r) // 获取分区点
  quick_sort_c(A, p, q-1)
  quick_sort_c(A, q+1, r)
}
```

#### 原地分区函数的实现思路

```
partition(A, p, r) {
  pivot := A[r]
  i := p
  for j := p to r-1 do {
    if A[j] < pivot {
      swap A[i] with A[j]
      i := i+1
    }
  }
  swap A[i] with A[r]
  return i

```
### 具体代码如下

#### 1、选取中位数代码

```
package geekTime.DataStruct.sort;

public class QuickSort {
    此处 代码为选取中位数的代码
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
```

#### 2、递归公式

```
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
```
    
#### 3、测试代码
    
```
    public static void main(String[] args)
    {
        int[] x = { 6, 2, 8, 1, 5, 9, 23,51,42,15};
        quick_sort(x, 0, x.length - 1);
        for(int item:x){
            System.out.print(item+",");
        }
    }
}
```