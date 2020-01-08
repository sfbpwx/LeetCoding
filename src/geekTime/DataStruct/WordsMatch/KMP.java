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
            char one = a[i];
            char two = b[j];
            //当主串与模式串未匹配到时，j移动到上次获取的公共前缀的头位置，为接下来的继续匹配做准备
            while (j > 0 && a[i] != b[j]) {
                 j = next[j - 1] + 1;
            }
            char oneB = a[i];
            char twoB = b[j];
            //此处意思是两个串匹配上了，模式串长度递增，为下一步继续匹配做准备
            if (a[i] == b[j]) {
                ++j;
            }
            // 找到匹配模式串的了
            //当模式串和匹配到的串长度一致时，意味着已经匹配到
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
        //初始化K值，
        int k = -1;
        for (int i = 1; i < m; ++i) {
            char one = b[k+1];
            char two = b[i];
            //这里拆分成两部分理解
            // 首先是k为-1时，无论如何都不能执行
            //其次，当b[k+1]!=b[i]的时候，k获取当前的偏移量，将k定位到上次公共前缀的头位置，然后重新执行while函数
            while (k != -1 && b[k + 1] != b[i]) {
                k = next[k];
            }
            char oneB = b[k+1];
            char twoB = b[i];
            //当b[k+1]==b[i]的时候，意味着，匹配到了。k值增加，为接下来的另外一个数值匹配做准备
            if (b[k + 1] == b[i]) {
                ++k;
            }
            //计算当前匹配到的串长度，写入到next数组中
            next[i] = k;
        }
        return next;
    }

    public static void  main(String[]args){
//        char[] a = new char[]{'a','b','c','a','c','a','b','d','c'};
//        char[] b = new char[]{'a','b','d'};
        String main = "ABABABAABABAAABABAA";
        String model = "ABABAAABABAA";
        char[] a = main.toCharArray();
        char[] b = model.toCharArray();
        int[] next = getNexts(b,b.length);
        int j=0;
        for(int i:next){
            System.out.println(j+":"+i);
            j++;
        }
        System.out.println(KMP.kmp(a,a.length,b,b.length));
    }
}
