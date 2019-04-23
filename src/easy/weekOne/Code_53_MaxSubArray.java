package easy.weekOne;

public class Code_53_MaxSubArray {
	/**
	 * ����һ���������� nums ���ҵ�һ���������͵����������飨���������ٰ���һ��Ԫ�أ������������͡�

		ʾ��:

		����: [-2,1,-3,4,-1,2,1,-5,4],
		���: 6
		����: ���������� [4,-1,2,1] �ĺ����Ϊ 6��
		����:

		������Ѿ�ʵ�ָ��Ӷ�Ϊ O(n) �Ľⷨ������ʹ�ø�Ϊ����ķ��η���⡣
		
		˫ָ��ⷨ
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
