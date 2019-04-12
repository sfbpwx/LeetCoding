package easy.weekOne;

import java.util.ArrayList;
import java.util.List;

public class Code_9_IsPalindrome {
	public boolean isPalindrome(int x) {
		if(x>Integer.MAX_VALUE||x<Integer.MIN_VALUE){
			return false;
		}
        Long xs = Math.abs(Long.valueOf(x));
        xs = (x>=0?xs:-xs);
        List<Long> list = new ArrayList<>();
        String resultLong = "";
        do{
        	list.add(xs%10);
        }while((xs/=10)>0);
        for(int i=0;i<list.size();i++){
        	resultLong+=list.get(i);
        }
        resultLong = (x>=0?resultLong:resultLong+"-");
        return (String.valueOf(x).equals(resultLong)?true:false);
    }
	
	public static void main(String args[]){
		Code_9_IsPalindrome a = new Code_9_IsPalindrome();
		System.out.println(a.isPalindrome1(1234554321));
	}
	
	public boolean isPalindrome1(int x) {
        StringBuilder str = new StringBuilder(String.valueOf(x));
        return str.toString().equals(str.reverse().toString());
        //Stringbuffer的写法 效率最高
    }
}
