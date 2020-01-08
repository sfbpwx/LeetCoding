package geekTime.DataStruct.WordsMatch;

/**
 * kmp算法
 */
public class KMP {
    /**
     *
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     * @return  匹配完成的位置（返回-1则表示未匹配到）
     */
    public static int kmp(char[] a, int n, char[] b, int m) {
        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; ++i) {
            // 一直找到a[i]和b[j]
            while (j > 0 && a[i] != b[j]) {
                 j = next[j - 1] + 1;
            }
            if (a[i] == b[j]) {
                ++j;
            }
            // 找到匹配模式串的了
            if (j == m) {
                 return i - m + 1;
            }
        }
        return -1;
    }

    /**
     *
     * @param b 模式串
     * @param m 模式串长度
     * @return
     */
    private static int[] getNexts(char[] b, int m) {
        //构建next数组
        int[] next = new int[m];
        //头位置定义为-1，即不可匹配，（-1为不可匹配状态）
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < m; ++i) {
            while (k != -1 && b[k + 1] != b[i]) {
                k = next[k];
            }
            if (b[k + 1] == b[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }

    public static void  main(String[]args){
        char[] a = new char[]{'a','b','c','a','c','a','b','d','c'};
        char[] b = new char[]{'a','b','d'};
        System.out.println(KMP.kmp(a,a.length,b,b.length));
    }
}
