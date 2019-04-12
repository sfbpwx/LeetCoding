package easy.weekOne;

import java.util.HashMap;
import java.util.Map;

public class Code_13_RomanToInt {
	public int romanToInt(String s) {
	    Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("I", 1);
		map.put("V", 5);
		map.put("X", 10);
		map.put("L", 50);
		map.put("C", 100);
		map.put("D", 500);
		map.put("M", 1000);
		String[] sb = s.split("");
		int resultInt =0;
		for(int i=sb.length-1;i>-1;i--){
			resultInt+=(i==sb.length-1?map.get(sb[i]):(map.get(sb[i])>=map.get(sb[i+1])?map.get(sb[i]):-map.get(sb[i])));
		}
        return resultInt;
    }
	//�������У�forѭ�������αȽϣ�ǰһλ���ں�һλ��������ӣ�ǰһλС�ں�һλ��������ǰ��
	//����һ�仰����Ϊ����ǰΪi==sb.length-1λ��ʱ��ֱ����ӣ���ǰλ��i<sb.length-1ʱ���ڽ��бȽ��жϡ�
	
	public static void main(String args[]){
		Code_13_RomanToInt a = new Code_13_RomanToInt();
		System.out.println(a.romanToInt("MCMXCIV"));
	}
}
