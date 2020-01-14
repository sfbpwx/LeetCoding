package geekTime.DataStruct.binaryTree;

import javafx.scene.Node;

public class BinaryTree {
    /**
     * 前序遍历的递推公式：
     * preOrder(r) = print r->preOrder(r->left)->preOrder(r->right)
     *
     * 中序遍历的递推公式：
     * inOrder(r) = inOrder(r->left)->print r->inOrder(r->right)
     *
     * 后序遍历的递推公式：
     * postOrder(r) = postOrder(r->left)->postOrder(r->right)->print r
     */


    /**
     * 前序遍历
     * @param root
     */
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root); // 此处为伪代码，表示打印root节点
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 中序遍历
     * @param root
     */
    void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root); // 此处为伪代码，表示打印root节点
        inOrder(root.right);
    }

    /**
     * 后序遍历
     * @param root
     */
    void postOrder(TreeNode root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root); // 此处为伪代码，表示打印root节点
    }
}
