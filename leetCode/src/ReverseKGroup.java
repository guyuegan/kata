import java.util.ArrayList;
import java.util.Stack;

public class ReverseKGroup {
    private static class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }


   }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (null == head || null == head.next)
            return head;

        Stack<ListNode> stack = new Stack<>();
        ListNode movePointer = head;
        int i = 1;
        while (i <= k) {
            stack.push(movePointer);
            movePointer = movePointer.next;
            if (null == movePointer)
                break;
            i++;
        }

        if (k != stack.size())
            return head;

        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (!stack.isEmpty()) {
            tail.next = stack.pop();
            tail = tail.next;
        }

        // 每次递归，都会返回翻转好的链表，直接接到head尾即可（head现在是翻转链表的尾）
        tail.next = reverseKGroup(movePointer, k);

        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1) {{
            next = new ListNode(2) {{
                next = new ListNode(3) {{
                    next = new ListNode(4);
                }};
            }};
        }};

        ListNode listNode = reverseKGroup(head, 3);
        System.out.println(listNode);
    }
}
