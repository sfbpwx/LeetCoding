package easy.weekOne;

import java.util.ArrayList;
import java.util.List;

public class Code_7_ReverseInteger {
	    public int std(long x) {
	        return (int) (x > Integer.MAX_VALUE? 0: (x < Integer.MIN_VALUE? 0: x));
	    }
	    //������ת���⣬˼·������10������Ȼ�����±��ţ�����Ҫ�ж��Ƿ񳬳�integer�ķ�Χ
	    public int reverse(int x) {
	        boolean f = x > 0;
	       long sx = Long.valueOf(x);
//	       long sx1 = Math.abs(x);  ����ֵ������ʹ�ù�����������
	        sx=f?sx:-sx;//��תʹ��֤һ��Ϊ����
	        List<Long> cache = new ArrayList<Long>();
	        do {
	            cache.add(sx % 10);
	        } while((sx /= 10) > 0);
	        for (int i=0, len = cache.size(); i < len; i++) {
	            sx += Math.pow(10, len - i - 1) * cache.get(i);//math.pow ��һ�������ļ��η��ķ���
	        }
	        return std(f? sx: -sx);
	    }
}
