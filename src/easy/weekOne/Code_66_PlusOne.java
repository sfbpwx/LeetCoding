package easy.weekOne;

public class Code_66_PlusOne {
	/**
	 * ����һ����������ɵķǿ���������ʾ�ķǸ��������ڸ����Ļ����ϼ�һ��

		���λ���ִ�����������λ�� ������ÿ��Ԫ��ֻ�洢һ�����֡�

		����Լ���������� 0 ֮�⣬��������������㿪ͷ��

		ʾ�� 1:

		����: [1,2,3]
		���: [1,2,4]
		����: ���������ʾ���� 123��
		ʾ�� 2:

		����: [4,3,2,1]
		���: [4,3,2,2]
		����: ���������ʾ���� 4321��
		
		
		��Ŀ��������һ��
	 * @param digits
	 * @return
	 */
	public int[] plusOne(int[] digits) {
		
		 try {
	            int i = digits.length - 1;
	            while (digits[i] == 9){
	                digits[i] = 0;
	                i --;
	            }
	            digits[i] += 1;
	            return digits;
	        } catch (Exception e) {
	            e.printStackTrace();
	            int[] i = new int[digits.length + 1];
	            i[0] = 1;
	            return i;
	        }
    }
}
