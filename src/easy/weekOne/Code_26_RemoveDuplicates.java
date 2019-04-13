package easy.weekOne;

public class Code_26_RemoveDuplicates {
	/**
	 * 
	 * @param nums
	 * 该题与 27. 移除元素 类似，都运用了快慢双指针来解决。
	 * 同样，我们可以用慢指针来指向下一个不重复元素的插入位置，快指针用于迭代，检测慢指针指向的元素是否和当前遍历到的元素重复。如果重复则插入到left所指的位置即可：
	 * 最后，慢指针的值 + 1 即元素剩余不重复元素的值。
	 * @return
	 */
	public int removeDuplicates(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;

		int left = 0; // slow point
		for (int i = 0; i < nums.length; i++) { // fast point
			if (nums[left] != nums[i]) {
				nums[++left] = nums[i];
			}
		}
		return left + 1;
	}
}
