package easy.weekOne;

public class Code_53_MaxSubArray {
	/**
	 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

		示例:

		输入: [-2,1,-3,4,-1,2,1,-5,4],
		输出: 6
		解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
		进阶:

		如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
		
		双指针解法
	 * @param nums
	 * @return
	 */
	public int maxSubArray(int[] nums) {
//		int maxSoFar = nums[0];
//		int maxEndHere = nums[0];
//		System.out.println("maxEndHere:"+maxEndHere+"  maxSoFar:"+maxSoFar);
//		for(int i=1;i<nums.length;i++){
//			maxEndHere = Math.max(nums[i], maxEndHere+nums[i]);
//			maxSoFar = Math.max(maxEndHere, maxSoFar);
//			System.out.println("maxEndHere:"+maxEndHere+"  maxSoFar:"+maxSoFar);
//		}
//        return maxSoFar;
		
		int res = nums[0];
        int sum = 0;
        System.out.println("res:"+res+"  sum:"+sum);
        for (int num : nums) {
            if (sum > 0)
                sum += num;
            else
                sum = num;
            res = Math.max(res, sum);
            System.out.println("res:"+res+"  sum:"+sum);
        }
        return res;
    }
	
	public static void main(String args[]){
		Code_53_MaxSubArray a = new Code_53_MaxSubArray();
		int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
		System.out.println(a.maxSubArray(nums));
	}
}
