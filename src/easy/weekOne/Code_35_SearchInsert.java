package easy.weekOne;

public class Code_35_SearchInsert {
	public int searchInsert(int[] nums, int target) {
		if(nums.length==0)return 0;
		if(nums[0]>target)return 0;
		if(nums[nums.length-1]<target)return nums.length;
		for(int i=0;i<nums.length;i++){
            if(nums[i]==target) return i;
			if(nums[i]<target&&nums[i+1]>target)return i+1;
		}
		return 0;
    }
	/**
	 * 二分查找
	 * @param nums
	 * @param target
	 * @return
	 */
	public int searchInsert2(int[] nums, int target) {
        // 二分查找
        if(nums.length == 0)
            return -1;
        int left = 0;
        int right = nums.length-1;
        while (left <= right){
            int mid = (left+right)/2;
            if(target == nums[(left+right)/2])
                return (left+right)/2;
            if(target<nums[mid]){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return left;
    }
}
