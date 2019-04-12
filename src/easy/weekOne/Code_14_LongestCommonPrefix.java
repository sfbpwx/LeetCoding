package easy.weekOne;

import java.util.HashMap;
import java.util.Map;

public class Code_14_LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0) {
			return "";
		}
		if (strs.length == 1) {
			return strs[0];
		}
		Map<String, String> map = new HashMap<String, String>();
		int num = 10000;
		for (String str : strs) {
			map.put(str, str);
			num = (str.length() < num ? str.length() : num);
		}
		String compare = "";
		if (num > 0) {
			for (int i = 1; i < num + 1; i++) {
				for (String key : map.keySet()) {
					compare = (compare.length() == i ? compare : key.substring(0, i));
					if (!key.substring(0, i).equals(compare)) {
						return compare.substring(0, i - 1);
					}
				}
			}
		}
		return compare;
	}

	public static void main(String arg[]) {
		Code_14_LongestCommonPrefix a = new Code_14_LongestCommonPrefix();
		String[] strs = new String[] { "flower", "flow", "flight" };
		System.out.println(a.longestCommonPrefix(strs));
	}
}
