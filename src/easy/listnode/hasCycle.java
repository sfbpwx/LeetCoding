package easy.listnode;

import utils.ListNode;

import java.util.HashMap;
import java.util.Map;


public class hasCycle {
    /**
     * HashMap的方法
     */
    public boolean hasCycle1(ListNode head) {
        Map<Object,ListNode> map = new HashMap<>();
        while(head!=null){
            if(map.get(head)!=null){
                return true;
            }
            map.put(head,head);
            head = head.next;
        }
        return false;
    }

    /**
     * 双指针的方法
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow!=fast){
            if(fast==null||fast.next==null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
