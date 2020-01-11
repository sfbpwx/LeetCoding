package geekTime.DataStruct.WordsMatch;

import java.util.FormatFlagsConversionMismatchException;

public class KMPpractice {

    public int compare(char[] a,int n,char[] b,int m){
        int[] next = formNext(b,m);
        int j=0;
        for(int i=0;i<n;i++){
            while(j>0&&a[i]!=b[j]){
                j=next[j-1]+1;
            }
            if(a[i]==b[j]){
                j++;
            }
            if(j==m){
                return i-j+1;
            }
        }
        return -1;
    }

    public int[] formNext(char[] b,int m){
        int[] next = new int[m];
        next[0] = -1;
        int k= -1;
        for(int i=1;i<m;i++){
            while(k!=-1&&b[k+1]!=b[i]){
                k = next[k+1];
            }
            if(b[k+1]==b[i]){
                k++;
            }

            next[i]=k;
        }
        return next;
    }
}
