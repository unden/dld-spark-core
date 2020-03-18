package com.duliday.spark.core.leetcode.linkedlist;

import java.util.Stack;

public class ListNodeCycle<begi> {

    private ListNode head;
    private ListNode tail;

    /**
     * 构建有环链表:5->3->2->7->1->6->8->9->4->1
     * 环的入口节点为1
     */
    {
        ListNode four = new ListNode(4, null);
        ListNode nine = new ListNode(9, four);
        ListNode eight = new ListNode(8, nine);
        ListNode six = new ListNode(6, eight);
        ListNode one = new ListNode(1, six);
        ListNode seven = new ListNode(7, one);
        ListNode two = new ListNode(2, seven);
        ListNode three = new ListNode(3, two);
        head = new ListNode(5, three);
        tail = four;
//        four.setNext(one);
    }

    public static void main(String[] args) {
        ListNodeCycle cycle = new ListNodeCycle();
        /*ListNode entrance = cycle.detectCycle(head);
        if (entrance == null) {
            System.out.println("no cycle");
        } else {
            System.out.println(entrance.getValue());
        }

        System.out.println(cycle.hasCycle(head));
        cycle.remove(head, tail);
        ListNode reversed = cycle.reverse(cycle.getHead());
        cycle.iterator(reversed);
        cycle.printListReversingWithRecursion(cycle.head);
        System.out.println();
        cycle.printListReversingWithStack(cycle.head);
        cycle.iterator(cycle.removeNthFromEnd(cycle.head, 11));*/


        ListNode middle = cycle.middleNode(cycle.head);
        System.out.println(middle);
    }

    private void iterator(ListNode reverseListNode) {
        while (reverseListNode != null) {
            System.out.println(reverseListNode.getValue());
            reverseListNode = reverseListNode.getNext();
        }
    }

    /****************************** <begin> ***********************************/
    /**
     * @param head
     * @return 当链表有环时，假设环外长度为a,环长度为b
     * 第一个while里的逻辑：相遇时，fast指针走过的路程为f,slow指针走过的路程为s,则f=2s
     * 同时,f=s+nb,推断出s=nb
     * 第二个while里的逻辑：让fast重新指回head,当一个节点从head走到环入口时，走过的路程为a+nb
     * 当fast从head走a步时,slow又走了a+nb,这个时候,slow和fast在环入口相遇
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;


        while (true) {
            if (fast == null || fast.getNext() == null) {
                return null;
            }

            fast = fast.getNext().getNext();
            slow = slow.getNext();
            if (fast == slow) {
                break;
            }
        }

        fast = head;
        while (fast != slow) {
            fast = fast.getNext();
            slow = slow.getNext();
        }
        return fast;
    }
    /****************************** <end> ***********************************/

    /****************************** <begin> ***********************************/
    /**
     * @param head
     * @return 判断链表是否有环，快慢指针法
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (true) {
            if (fast == null || fast.getNext() == null) {
                return false;
            }

            fast = fast.getNext().getNext();
            slow = slow.getNext();
            if (fast == slow) {
                return true;
            }
        }
    }
    /****************************** <end> ***********************************/

    /****************************** <begin> ***********************************/
    /**
     * @param head
     * @param toBeDeleted 删除链表中给点的节点
     */
    public void remove(ListNode head, ListNode toBeDeleted) {
        if (toBeDeleted == null || head == null) {
            return;
        }

        // 待删除节点不是尾节点
        if (toBeDeleted.getNext() != null) {
            toBeDeleted.setValue(toBeDeleted.getNext().getValue());
            // 这样写，会造成toBeDeleted的next节点内存泄漏
            toBeDeleted.setNext(toBeDeleted.getNext().getNext());
            return;
        }

        // 待删除节点是头节点，而且next节点为null
        if (toBeDeleted == head) {
            toBeDeleted = null;
            head = null;
            return;
        }

        ListNode pHead = head;
        while (true) {
            if (pHead == null || pHead.getNext() == toBeDeleted) {
                break;
            }
            pHead = pHead.getNext();
        }

        if (pHead == null) {
            throw new RuntimeException("toBeDeleted not exist");
        }

        pHead.setNext(null);
        toBeDeleted = null;
    }
    /****************************** <end> ***********************************/

    /****************************** <begin> ***********************************/
    /**
     * @param head
     * @return
     * 双指针，反转链表
     */
    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode current = head;

        while (current != null) {
            ListNode next = current.getNext();
            current.setNext(pre);
            pre = current;
            current = next;
        }

        return pre;
    }
    /****************************** <end> ***********************************/


    /****************************** <begin> ***********************************/
    /**
     * 删除链表倒数第N个节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n < 1) {
            return head;
        }

        // 使用傀儡节点的目的，是当要删除当节点就是head时，保证代码逻辑正确
        ListNode puppet = new ListNode(0, head);
        ListNode detector = puppet;
        ListNode front = puppet;
        ListNode back = puppet;

        for (int i = 0; i < n; i++) {
            if (detector.getNext() == null) {
                throw new RuntimeException("invalid input n: " + n);
            }
            detector = detector.getNext();
        }

        for (int i = 0; i <= n; i++) {
            front = front.getNext();
        }

        while (front != null) {
            front = front.getNext();
            back = back.getNext();
        }

        back.setNext(back.getNext().getNext());
        return puppet.getNext();
    }

    /****************************** <begin> ***********************************/
    /**
     * 链表的中间节点
     */
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode front = head;
        ListNode back = head;
        while (true) {
            if (front == null || front.next == null) {
                return back;
            }

            front = front.next.next;
            back = back.next;
        }
    }
    /****************************** <end> ***********************************/

    /****************************** <begin> ***********************************/
    /**
     * 题目表述：倒序打印链表
     * @param head
     */
    // 采用栈
    public void printListReversingWithStack(ListNode head) {
        Stack<ListNode> stack = new Stack<ListNode>();
        while (head != null) {
            stack.push(head);
            head = head.getNext();
        }

        while (!stack.empty()) {
            ListNode top = stack.pop();
            System.out.print(top.getValue());
        }
    }

    // 采用递归
    public void printListReversingWithRecursion(ListNode head) {
        if (head == null) {
            return;
        }
        printListReversingWithRecursion(head.getNext());
        System.out.print(head.getValue());
    }
    /****************************** <end> ***********************************/


    /****************************** <begin> ***********************************/
    /**
     * 删除有序链表中重复的节点
     */
    public ListNode deleteDuplication(ListNode head) {
        ListNode pre = null;
        ListNode current = head;
        while (current != null) {
            if (current.getNext() != null && current.getNext().getValue() == current.getValue()) {
                while (current.getNext() != null && current.getNext().getValue() == current.getValue()) {
                    current = current.getNext();
                }
                current = current.getNext();
                if (pre == null) {
                    head = current;
                } else {
                    pre.setNext(current);
                }
            } else {
                pre = current;
                current = current.getNext();
            }

        }

        return head;
    }
    /****************************** <end> ***********************************/

    /****************************** <begin> ***********************************/
    /**
     * 找到两个单链表相交的起始节点
     * @param headA
     * @param headB
     * @return
     *         ListNode three = new ListNode(3, null);
     *         ListNode two = new ListNode(2, three);
     *         ListNode one = new ListNode(1, two);
     *
     *         ListNode six = new ListNode(6, null);
     *         ListNode five = new ListNode(5, six);
     *         ListNode four = new ListNode(4, five);
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p = headA, q = headB;
        while (p != q) {
            p = p == null ? headB : p.getNext();
            q = q == null ? headA : q.getNext();
        }
        return p;
    }
    /****************************** <end> ***********************************/


    /****************************** <begin> ***********************************/
    /**
     * 合并两个有序链表
     */
    public ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        if (l1.value < l2.value) {
            l1.next = mergeTwoSortedLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoSortedLists(l1, l2.next);
            return l2;
        }
    }
}
