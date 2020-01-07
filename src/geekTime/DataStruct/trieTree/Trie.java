package geekTime.DataStruct.trieTree;

/**
 * Trie树
 */
public class Trie {
    private TrieNode root = new TrieNode('/');//存储无意义的字符

    //插入方法（对插入的数组数据进行轮询）
    public void insert(char[] text){
        //定位当前节点
        TrieNode p = root;
        for(int i=0;i<text.length;i++){
            //ASCII码相减，求角标
            int index = text[i]-'a';
            //如果当前节点为空
            if(p.children[index]==null){
                //把当前的字母值作为一个新值生成节点
                TrieNode newNode = new TrieNode(text[i]);
                //把新生成的节点插入到当前位置中
                p.children[index]=newNode;
            }
            //如果当前位置有值，则轮训到第i个位置，在当前节点的子节点依次插入新生成的TrieNode
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    //查询方法（查询匹配方法）
    public boolean find (char[] pattern){
        //定位当前节点
        TrieNode p = root;
        for(int i = 0;i<pattern.length;i++){
            //ASCII码相减，求角标
            int index = pattern[i]-'a';
            //如果还没轮询完，就已经获取到空的节点，返回false
            if(p.children[index]==null){
                return false;
            }
            //否则，继续向下执行
            p = p.children[index];
        }
        //此情况主要用于排除pattern是空的情况下的问题。（即没有执行for循环的情况下）
        if(p.isEndingChar==false)return  false;
        else return  true;
    }
}
