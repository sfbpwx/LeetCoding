package easy.listnode;

import utils.ListNode;

public class removeNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        //firt承载 dummy并将来可以拼接，first在往后扫的过程中作为指针
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }

    /**
     * 双指针方法，前后指针一起扫，当前指针扫到末尾报空时，后指针就可以把指向
     * 下一个节点的方法指向下一个节点的下一个节点，就可以删除目标节点了
     * @param head
     * @param n
     * @return
     */
    public ListNode oneSingleScan(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy.next;
        ListNode slow = dummy.next;
        int i = 0;
        while(fast!=null){
            if(i>n){
                slow = slow.next;
            }
            fast = fast.next;
            i++;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public static void main(String[]args){
        int[] i = new int[]{1,2,3,4,5,6};
        ListNode head =  ListNode.buildListNode(i);
        removeNthFromEnd reverseList = new removeNthFromEnd();
        reverseList.oneSingleScan(head,3);
    }
}
