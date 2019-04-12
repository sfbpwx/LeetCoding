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
	//倒叙排列，for循环，依次比较，前一位大于后一位，两数相加，前一位小于后一位，后数减前数
	//最后的一句话依次为：当前为i==sb.length-1位置时，直接相加，当前位置i<sb.length-1时，在进行比较判断。
	
	public static void main(String args[]){
		Code_13_RomanToInt a = new Code_13_RomanToInt();
		System.out.println(a.romanToInt("MCMXCIV"));
	}
}
