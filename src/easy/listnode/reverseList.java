package easy.listnode;

import utils.ListNode;

public class reverseList {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr!=null){
            //将prev链表进行反转拼接，并拼接到curr后面，然后用prev把curr的值获取出来，等待下一次拼接
            ListNode temp =  curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    public static void main(String[]args){
        int[] i = new int[]{1,2,3,4,5,6};
        ListNode head =  ListNode.buildListNode(i);
        reverseList reverseList = new reverseList();
        reverseList.reverseList(head);
    }
}
