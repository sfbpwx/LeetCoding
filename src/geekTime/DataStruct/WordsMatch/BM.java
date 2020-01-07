package geekTime.DataStruct.WordsMatch;

import java.util.regex.Matcher;

public class BM {
    //ȫ�ֱ������Ա����
    private static final int SIZE = 256;
    //BM�㷨��ɢ�б��ʼ����ܣ����б���b��ģʽ����m��ģʽ���ĳ��ȣ�bc�Ǹո�˵��ɢ�б�
    //�˷�������ʵ�ֻ��ַ��Ĺ���ASCII�����������
    private void generateBC(char[] b,int m,int[]bc){
        for(int i=0;i<SIZE;i++){
            //��ʼ��BC
            bc[i]=-1;
        }
        for(int i=0;i<m;i++){
            //����b[i]��ASCII��
            int ascii = (int)b[i];
            bc[ascii] = i;
        }
    }

    public int bm(char[] a,int n,char[] b,int m){
        //��¼ģʽ����ÿ���ַ������ֵ�λ��
        int[] bc = new int[SIZE];
        //�������ַ��Ĺ�ϣ��ִ����˲��Ժ�bc���Ѿ������������ˣ��������ֱ�������ã�
        generateBC(b,m,bc);

        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        //������ǰ׺�Ĺ�ϣ��
        generateGS(b,m,suffix,prefix);
        //i��ʾ������ģʽ������ĵ�һ���ַ�
        int i = 0;
        //�����ҽ���iС��n��m�Ĳ�ֵʱ��ƥ�乤���Ծ��ڽ����У�ģʽ��ĩβδ��������
        while(i<=n-m){
            int j;
            //ģʽ���Ӻ���ǰƥ��
            for(j = m-1;j>=0;j--){
                //���ַ���Ӧģʽ���е��±���j�����û�ж�Ӧ��jλֱ�ӷ��أ�������forѭ����
                //����ע�⣬j��С����ִ�е�-1��λ�ã���j==0ʱ��forѭ��������Ч
                if(a[i+j]!=b[j]) break;
            }
            if(j<0){
                //ƥ��ɹ�������������ģʽ����һ��ƥ���ַ���λ��
                return i;
            }
            //δƥ��ɹ�����ģʽ����󻬶�j-bc[(int)a[i+j]]��λ��
            //��һ���������
            //ͨ��ASCII������ƶ�λ��������ĸ������������ƥ��
            int x = i+(j-bc[(int)a[i+j]]);
            //ƥ���滵�ַ��Ժ󣬿�ʼ��ǰ׺��ƥ�䷽��
            int y = 0;
            if(j<m-1){//����к�ǰ׺�Ļ�
                y = moveByGS(j,m,suffix,prefix);
            }
            i = i+ Math.max(x,y);

        }
        return -1;
    }

    //j��ʾ���ַ���Ӧģʽ���е��ַ��±꣬m��ʾģʽ������
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
    //��ǰ׺�ַ����Ĺ�������
    private void generateGS(char[] b,int m,int[] suffix,boolean[] prefix){
        //��ʼ������
        for(int i=0;i<m;i++){
            suffix[i]=-i;
            prefix[i]=false;
        }
        //b[0,i]
        for(int i=0;i<m-1;i++){
            int j=i;
            int k=0;//������׺�ַ�����
            while(j>=0 &&b[j]==b[m-i-k]){//��b[0,m-1]�󹫹���׺�Ӵ�
                --j;
                ++k;
                suffix[k]=j+1;//j+1 ��ʾ������׺�Ӵ���b[0,i]�е���ʼ�±�
            }
            if(j==-1)prefix[k]=true;//���������׺�Ӵ�Ҳ��ģʽ����ǰ׺�Ӵ�
        }
    }

    public static void  main(String[]args){
        char[] a = new char[]{'a','b','c','a','c','a','b','d','c'};
        char[] b = new char[]{'a','b','d'};
        BM bm = new BM();
        bm.bm(a,a.length,b,b.length);
    }
}
