package geekTime.DataStruct.WordsMatch;

import java.util.regex.Matcher;

public class BM {
    //全局变量或成员变量
    private static final int SIZE = 256;
    //BM算法的散列表初始化框架，其中变量b是模式串，m是模式串的长度，bc是刚刚说的散列表
    //此方法用于实现坏字符的构建ASCII码数组而产生
    private void generateBC(char[] b,int m,int[]bc){
        for(int i=0;i<SIZE;i++){
            //初始化BC
            bc[i]=-1;
        }
        for(int i=0;i<m;i++){
            //计算b[i]的ASCII码
            int ascii = (int)b[i];
            bc[ascii] = i;
        }
    }

    public int bm(char[] a,int n,char[] b,int m){
        //记录模式串中每个字符最后出现的位置
        int[] bc = new int[SIZE];
        //构建坏字符的哈希表（执行完此步以后，bc就已经被构建出来了，后面可以直接拿来用）
        generateBC(b,m,bc);

        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        //构建好前缀的哈希表
        generateGS(b,m,suffix,prefix);
        //i表示主串与模式串对齐的第一个字符
        int i = 0;
        //（当且仅当i小于n和m的差值时，匹配工作仍旧在进行中，模式串末尾未出主串）
        while(i<=n-m){
            int j;
            //模式串从后往前匹配
            for(j = m-1;j>=0;j--){
                //坏字符对应模式串中的下标是j（如果没有对应，j位直接返回，不继续for循环）
                //这里注意，j最小可以执行到-1的位置，当j==0时，for循环依旧有效
                if(a[i+j]!=b[j]) break;
            }
            if(j<0){
                //匹配成功，返回主串与模式串第一个匹配字符的位置
                return i;
            }
            //未匹配成功，将模式串向后滑动j-bc[(int)a[i+j]]的位数
            //这一步最难理解
            //通过ASCII码计算移动位数，将字母拆解进入数组中匹配
            int x = i+(j-bc[(int)a[i+j]]);
            //匹配玩坏字符以后，开始好前缀的匹配方法
            int y = 0;
            if(j<m-1){//如果有好前缀的话
                y = moveByGS(j,m,suffix,prefix);
            }
            i = i+ Math.max(x,y);

        }
        return -1;
    }

    //j表示坏字符对应模式串中的字符下标，m表示模式串长度
    private int moveByGS(int j,int m,int[] suffix,boolean[] prefix){
        int k=m-1-j;
        if(suffix[k]!=-1){
            return j-suffix[k]+1;
        }
        for(int r=j+2;r<=m-1;r++){
            if(prefix[m-r]==true){
                return r;
            }
        }
        return m;
    }
    //好前缀字符集的构建方法
    private void generateGS(char[] b,int m,int[] suffix,boolean[] prefix){
        //初始化工作
        for(int i=0;i<m;i++){
            suffix[i]=-i;
            prefix[i]=false;
        }
        //b[0,i]
        for(int i=0;i<m-1;i++){
            int j=i;
            int k=0;//公共后缀字符长度
            while(j>=0 &&b[j]==b[m-i-k]){//与b[0,m-1]求公共后缀子串
                --j;
                ++k;
                suffix[k]=j+1;//j+1 表示公共后缀子串在b[0,i]中的起始下标
            }
            if(j==-1)prefix[k]=true;//如果公共后缀子串也是模式串的前缀子串
        }
    }

    public static void  main(String[]args){
        char[] a = new char[]{'a','b','c','a','c','a','b','d','c'};
        char[] b = new char[]{'a','b','d'};
        BM bm = new BM();
        bm.bm(a,a.length,b,b.length);
    }
}
