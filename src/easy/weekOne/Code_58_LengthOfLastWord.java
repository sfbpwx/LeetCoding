package easy.weekOne;

public class Code_58_LengthOfLastWord {
	/**
	 * 	����һ����������Сд��ĸ�Ϳո� ' ' ���ַ��������������һ�����ʵĳ��ȡ�

		������������һ�����ʣ��뷵�� 0 ��

		˵����һ��������ָ����ĸ��ɣ����������κοո���ַ�����

		ʾ��:

		����: "Hello World"
		���: 5
	 * @param s
	 * @return
	 */
	public int lengthOfLastWord(String s) {
		if(s==null||s.length()==0){
			return 0;
		}
		s = s.trim();
		int n = s.length()-1;
		while(n>=0&&!String.valueOf(s.charAt(n)).equals(" ")){
			n--;
		}
		return s.length()-n-1;
    }
	
	public static void main(String args[]){
		Code_58_LengthOfLastWord a = new Code_58_LengthOfLastWord();
		System.out.println(a.lengthOfLastWord("hello world"));
	}
}
