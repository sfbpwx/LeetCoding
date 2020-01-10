package easy.weekOne;

import java.util.HashMap;
import java.util.Map;

public class Code_1_TwoSum {
	public int[] twoSum_solution1(int[] nums, int target) {
        int[] result = null;
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            for(Integer key : map.keySet()){ 
                if(target==nums[i]+key){
                    result = new int[]{map.get(key),i};
                    System.out.println(result);
                }
            }
            map.put(nums[i],i);   
        }
        return result;
    }
	public int[] twoSum_solution2(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            if(map.get(target-nums[i])!=null){
                return new int[]{map.get(target-nums[i]),i};
            }
            map.put(nums[i],i);   
        }
        //Iterator�ı�������
//        Iterator<Map.Entry<Integer,Integer>> mapA = map.entrySet().iterator();
//        while(mapA.hasNext()){
//        	Map.Entry<Integer, Integer> entry = mapA.next(); 
//        	entry.getKey();
//        	entry.getValue();
//        }
        return null;
        
        
       
    }
}

