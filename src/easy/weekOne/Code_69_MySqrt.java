package easy.weekOne;

public class Code_69_MySqrt {
	/**
	 * ʵ�� int sqrt(int x) ������

		���㲢���� x ��ƽ���������� x �ǷǸ�������

		���ڷ������������������ֻ���������Ĳ��֣�С�����ֽ�����ȥ��

		ʾ�� 1:

		����: 4
		���: 2
		ʾ�� 2:

		����: 8
		���: 2
		˵��: 8 ��ƽ������ 2.82842..., 
     	���ڷ���������������С�����ֽ�����ȥ��
     	
     	˫ָ�� ��С�жϣ���ı�X��С�ı�XС��ȡС
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
