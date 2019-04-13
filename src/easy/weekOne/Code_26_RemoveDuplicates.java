package easy.weekOne;

public class Code_26_RemoveDuplicates {
	/**
	 * 
	 * @param nums
	 * ������ 27. �Ƴ�Ԫ�� ���ƣ��������˿���˫ָ���������
	 * ͬ�������ǿ�������ָ����ָ����һ�����ظ�Ԫ�صĲ���λ�ã���ָ�����ڵ����������ָ��ָ���Ԫ���Ƿ�͵�ǰ��������Ԫ���ظ�������ظ�����뵽left��ָ��λ�ü��ɣ�
	 * �����ָ���ֵ + 1 ��Ԫ��ʣ�಻�ظ�Ԫ�ص�ֵ��
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
