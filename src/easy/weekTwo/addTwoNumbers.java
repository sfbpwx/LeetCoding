package easy.weekTwo;
import utils.ListNode;

public class addTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //首先定义一个单链表，
        ListNode dummyHead = new ListNode(0);
        //定义curr承接dummyHead
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        //当且仅当p或q都不为空时，
        while (p != null || q != null) {
            //获取p的下一个节点，否则返回0
            int x = (p != null) ? p.val : 0;
            //获取q的下一个节点，否则返回0
            int y = (q != null) ? q.val : 0;
            //得到x+y的结果，
            int sum = carry + x + y;
            //检测结果是否涉及进位，并把结果进行保存，下一位相加的结果要加上此处的进位
            carry = sum / 10;
            //把当前数据的除余结果返回到新的链表结构中
            curr.next = new ListNode(sum % 10);
            //指针指向下一位
            curr = curr.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        //如果最后的结果仍旧大于0，则新增一位把最后运算结果的进位进行保存
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        //依次打出结果
        return dummyHead.next;
    }

    public static void main(String args[]){
        addTwoNumbers leetCodeTest = new addTwoNumbers();
        System.out.println(leetCodeTest.addTwoNumbers(ListNode.buildListNode(new int[]{2,4,3}),ListNode.buildListNode
                (new int[]{5,6,4})));
    }




}
