package easy.weekOne;

import java.util.ArrayList;
import java.util.List;

public class Code_7_ReverseInteger {
	    public int std(long x) {
	        return (int) (x > Integer.MAX_VALUE? 0: (x < Integer.MIN_VALUE? 0: x));
	    }
	    //整数反转问题，思路：除以10的余数然后重新编排，并且要判断是否超出integer的范围
	    public int reverse(int x) {
	        boolean f = x > 0;
	       long sx = Long.valueOf(x);
//	       long sx1 = Math.abs(x);  绝对值方法，使用过程中有问题
	        sx=f?sx:-sx;//反转使保证一定为正数
	        List<Long> cache = new ArrayList<Long>();
	        do {
	            cache.add(sx % 10);
	        } while((sx /= 10) > 0);
	        for (int i=0, len = cache.size(); i < len; i++) {
	            sx += Math.pow(10, len - i - 1) * cache.get(i);//math.pow 第一个参数的几次方的方法
	        }
	        return std(f? sx: -sx);
	    }
}
