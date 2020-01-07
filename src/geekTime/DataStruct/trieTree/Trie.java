package geekTime.DataStruct.trieTree;

/**
 * Trie��
 */
public class Trie {
    private TrieNode root = new TrieNode('/');//�洢��������ַ�

    //���뷽�����Բ�����������ݽ�����ѯ��
    public void insert(char[] text){
        //��λ��ǰ�ڵ�
        TrieNode p = root;
        for(int i=0;i<text.length;i++){
            //ASCII���������Ǳ�
            int index = text[i]-'a';
            //�����ǰ�ڵ�Ϊ��
            if(p.children[index]==null){
                //�ѵ�ǰ����ĸֵ��Ϊһ����ֵ���ɽڵ�
                TrieNode newNode = new TrieNode(text[i]);
                //�������ɵĽڵ���뵽��ǰλ����
                p.children[index]=newNode;
            }
            //�����ǰλ����ֵ������ѵ����i��λ�ã��ڵ�ǰ�ڵ���ӽڵ����β��������ɵ�TrieNode
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    //��ѯ��������ѯƥ�䷽����
    public boolean find (char[] pattern){
        //��λ��ǰ�ڵ�
        TrieNode p = root;
        for(int i = 0;i<pattern.length;i++){
            //ASCII���������Ǳ�
            int index = pattern[i]-'a';
            //�����û��ѯ�꣬���Ѿ���ȡ���յĽڵ㣬����false
            if(p.children[index]==null){
                return false;
            }
            //���򣬼�������ִ��
            p = p.children[index];
        }
        //�������Ҫ�����ų�pattern�ǿյ�����µ����⡣����û��ִ��forѭ��������£�
        if(p.isEndingChar==false)return  false;
        else return  true;
    }
}
