package easy.weekOne;

public class Code_28_StrStr {
	public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }	
	/**
	 * @author Administrator
	 * @author KMP算法
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public int strStr1(String haystack, String needle) {
		if (needle.isEmpty())
			return 0;
		int len = needle.length();
		int[] next = getNextArr(needle);
		int hlen=haystack.length();
		for(int i=0;i<hlen;){
			for(int j=1;j<next.length;){
                if(i>=hlen)return -1;
				char hc=haystack.charAt(i);
				char nc=needle.charAt(j-1);
				if(hc==nc){
					if(j==next.length-1){
						return i-len+1;
					}
					i++;j++;
					continue;
				}else{
					if(next[j]==0){
						i++;
						break;
					}else{
						j=next[j];
						continue;
					}
				}
			}
		}
		return -1;
	}

	public int[] getNextArr(String str) {
		int len = str.length();
		int[] ans = new int[len + 1];
		ans[1] = 0;
		for (int i = 2; i < len + 1; i++) {
			if (i > 2) {
				int max = i - 2;// 最大[1,max] 和[i-max,i-1]匹配 注意:string from 0
				String prefix = str.substring(0, max);
				String last = str.substring(i - max-1, i-1);
				while (max > 0 && !prefix.equals(last)){
					max--;
					prefix = str.substring(0, max);
					last = str.substring(i - max-1, i-1);
				}
				if (max == 0)
					ans[i] = 1;
				else
					ans[i] = max + 1;

			} else
				ans[i] = 1;
		}
		return ans;
	}
}
