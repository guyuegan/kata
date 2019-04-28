public class ListNodeTest {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(4);
        ListNode listNode02 = new ListNode(7);
        l1.next = listNode02;

        ListNode l2 = new ListNode(5);
        ListNode listNode03 = new ListNode(3);
        l2.next = listNode03;

        ListNode listNode = wrapAddTwoNumbers(l1, l2);
        do {
            System.out.println(listNode.val);
            listNode = listNode.next;
        } while (listNode != null);
    }

    public static void main03(String[] args) {
        ListNode l1 = new ListNode(5);
        ListNode listNode02 = new ListNode(6);
        l1.next = listNode02;

        ListNode l2 = new ListNode(5);

        ListNode listNode = wrapAddTwoNumbers(l1, l2);
        do {
            System.out.println(listNode.val);
            listNode = listNode.next;
        } while (listNode != null);
    }

    public static void main02(String[] args) {
        ListNode l1 = new ListNode(5);

        ListNode l2 = new ListNode(5);

        ListNode listNode = wrapAddTwoNumbers(l1, l2);
        do {
            System.out.println(listNode.val);
            listNode = listNode.next;
        } while (listNode != null);
    }

    public static void main01(String[] args) {
        ListNode l1 = new ListNode(3);
        ListNode listNode02 = new ListNode(4);
        ListNode listNode03 = new ListNode(2);
        l1.next = listNode02;
        listNode02.next = listNode03;

        ListNode l2 = new ListNode(4);
        ListNode listNode05 = new ListNode(6);
        ListNode listNode06 = new ListNode(5);
        l2.next = listNode05;
        listNode05.next = listNode06;

        ListNode listNode = wrapAddTwoNumbers(l1, l2);
        do {
            System.out.println(listNode.val);
            listNode = listNode.next;
        } while (listNode != null);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode wrapAddTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tmp1 = null;
        while (l1 != null) {
            ListNode curNode = new ListNode(l1.val);
            curNode.next = tmp1;
            tmp1 = curNode;

            l1 = l1.next;
        }

        ListNode tmp2 = null;
        while (l2 != null) {
            ListNode curNode = new ListNode(l2.val);
            curNode.next = tmp2;
            tmp2 = curNode;

            l2 = l2.next;
        }

        ListNode listNode = addTwoNumbers(tmp1, tmp2);
        ListNode tmp3 = null;
        while (listNode != null) {
            ListNode curNode = new ListNode(listNode.val);
            curNode.next = tmp3;
            tmp3 = curNode;

            listNode = listNode.next;
        }

        return tmp3;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        //从头结点到尾结点构造链表（和是807，则链表是7-0-8）
        ListNode headNode = new ListNode(0);//先弄临时的链表头
        ListNode prevNode = headNode;//记录最后一个结点
        int overTen = 0;

        //l1, l2任何一个不为空
        while (l1 != null || l2 != null) {
            int num1 = l1 != null ? l1.val : 0;
            int num2 = l2 != null ? l2.val : 0;

            //从低位开始，计算两个链表每一位的和
            int sum = num1 + num2 + overTen;
            ListNode curNode = new ListNode(sum % 10);
            //将当前结点加到前一个结点
            prevNode.next = curNode;
            prevNode = curNode;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            overTen = sum / 10;
        }

        //如果overTen是1，说明高位是1，即链表尾是1，否则[5] [5]这种情况会出错
        if (overTen > 0) {
            prevNode.next = new ListNode(overTen);
        }

        //临时表头的下一个结点才是真正的表头
        return headNode.next;

    }
/**
 如果链表中的数字不是按逆序存储的呢？例如：
 (3 - 4 -2) + (4 - 6 - 5) = 8 - 0 - 7 (3→4→2)+(4→6→5)=8→0→7
 */
}
