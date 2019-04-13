package easy.weekOne;

public class Code_27_RemoveElement {
	public int removeElement(int[] nums, int val) {
		if (nums == null || nums.length == 0)
			return 0;

		int left = 0; // slow point
		for (int i = 0; i < nums.length; i++) { // fast point
			if (nums[i] != val) {
				nums[left]=nums[i];//����ָ������⣬��ǰ��ֵ��val���ʱ�����ƿ�ָ�����ݵ���ָ�룬��ǰ��ֵ��val�����ʱ����ָ�벻������ָ������ƽ���������ȡ��ָ������г���
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
