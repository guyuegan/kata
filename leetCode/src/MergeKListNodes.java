import java.util.Arrays;
import java.util.stream.Stream;

public class MergeKListNodes {
    public static void main(String[] args) {
//[[1,4,5],[1,3,4],[2,6]]

        /*ListNode listNode01 = new ListNode(1) {{next = new ListNode(4) {{next = new ListNode(5);}}; }};
        ListNode listNode02 = new ListNode(1) {{next = new ListNode(3) {{next = new ListNode(4);}}; }};
        ListNode listNode03 = new ListNode(2) {{next = new ListNode(6);}};

        ListNode listNode = new MergeKListNodes().mergeKLists(new ListNode[]{listNode01, listNode02, listNode03});
        System.out.println(listNode);*/
    }

    public ListNode mergeKLists(ListNode[] lists) {
        mergeKLists(lists, lists.length/2);

        if(lists.length%2 != 0)
            return mergeTwoListNode(lists[0], lists[lists.length-1]);

        return lists[lists.length-1];
    }

    public void mergeKLists(ListNode[] lists, int midLen) {
        if (0 == midLen) return;

        for (int i = 0; i < midLen; i++)
            lists[i] = mergeTwoListNode(lists[i], lists[i+midLen]);

        mergeKLists(lists, midLen/2);
    }

    public ListNode mergeTwoListNode(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode movePointer = dummyHead;
        while (null != l1 && null != l2) {
            if (l1.val < l2.val) {
                movePointer.next = l1;
                l1 = l1.next;
            } else {
                movePointer.next = l2;
                l2 = l2.next;
            }
            movePointer = movePointer.next;
        }

        if (null != l1)
            movePointer.next = l1;

        if (null != l2)
            movePointer.next = l2;

        return dummyHead.next;
    }

    static class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }

    }
}

