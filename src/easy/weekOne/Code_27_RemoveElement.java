package easy.weekOne;

public class Code_27_RemoveElement {
	public int removeElement(int[] nums, int val) {
		if (nums == null || nums.length == 0)
			return 0;

		int left = 0; // slow point
		for (int i = 0; i < nums.length; i++) { // fast point
			if (nums[i] != val) {
				nums[left]=nums[i];//快慢指针的问题，当前数值与val相符时，复制快指针数据到慢指针，当前数值与val不相符时，慢指针不动，快指针继续推进。最后仅截取慢指针的运行长度
				left++;
			}else{
			}
		}
		return left;
    }
	public static void main(String args[]){
		Code_27_RemoveElement a = new Code_27_RemoveElement();
		int[] input = new int[]{0,1,2,2,3,0,4,2};
		System.out.println(a.removeElement(input, 2));
	}
}
