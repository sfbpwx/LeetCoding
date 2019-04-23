package easy.weekOne;

public class Code_69_MySqrt {
	/**
	 * 实现 int sqrt(int x) 函数。

		计算并返回 x 的平方根，其中 x 是非负整数。

		由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

		示例 1:

		输入: 4
		输出: 2
		示例 2:

		输入: 8
		输出: 2
		说明: 8 的平方根是 2.82842..., 
     	由于返回类型是整数，小数部分将被舍去。
     	
     	双指针 大小判断，大的比X大，小的比X小，取小
	 * @param x
	 * @return
	 */
	public int mySqrt(int x) {
		int before = 0;
        for (int i = 0; i <= x; i++) {
            int current = i * i;
            if (before > current ||  current > x)
                return i - 1;
            System.out.println("before:"+before+"   current:"+current);
            before = current;
        }
        return x;
    }
	
	public static void main(String arg[]){
		Code_69_MySqrt a = new Code_69_MySqrt();
		System.out.println(a.mySqrt(100));
	}
}
