package easy.listnode;

import utils.ListNode;

public class middleNode {
    /**
     * 双指针滑动方法，快指针速度是慢指针的两倍。
     * 奇数位的时候快指针前进2位慢指针前进1位
     * 偶数位的时候，快指针在最后无法前进2位的时候，前进1位，慢指针始终前进1位
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy.next;
        ListNode slow = dummy.next;

        while (fast!=null&&fast.next!=null){
            if(fast.next.next!=null){
                fast = fast.next.next;
            }else{
                fast = fast.next;
            }
            slow = slow.next;
        }
        slow.next =  null;
        return slow;
    }

    public static void main(String[]args){
        int[] i = new int[]{1,2,3,4,5,6};
        ListNode head =  ListNode.buildListNode(i);
        middleNode reverseList = new middleNode();
        reverseList.middleNode(head);
    }
}
