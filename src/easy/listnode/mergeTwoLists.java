package easy.listnode;

import utils.ListNode;

import java.util.List;

/**
 * 链表合并
 */
public class mergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode listNode = dummyHead;
        while (l1!=null&&l2!=null){
            if(l1.val>l2.val){
                listNode.next=l2;
                l2 = l2.next;
            }else {
                listNode.next=l1;
                l1 = l1.next;
            }
            listNode = listNode.next;
        }
        if(l1==null){
            listNode.next=l2;
        }else if(l2==null){
            listNode.next=l1;
        }
        return dummyHead.next;

    }

    public static void main(String[] args){
        ListNode l1 = ListNode.buildListNode(new int[]{1,2,4});
        ListNode l2 = ListNode.buildListNode(new int[]{1,3,4});
        mergeTwoLists mergeTwoLists = new mergeTwoLists();
        mergeTwoLists.mergeTwoLists(l1,l2);

    }
}
