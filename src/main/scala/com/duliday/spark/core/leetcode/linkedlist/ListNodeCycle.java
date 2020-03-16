package com.duliday.spark.core.leetcode.linkedlist;

public class ListNodeCycle {

    private ListNode head;
    private ListNode tail;

    /**
     * 构建有环链表:5->3->2->7->1->6->8->9->4->1
     * 环的入口节点为1
     */ {
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
        cycle.iterator(reversed);*/

    }

    private void iterator(ListNode reverseListNode) {
        while (reverseListNode != null) {
            System.out.println(reverseListNode.getValue());
            reverseListNode = reverseListNode.getNext();
        }
    }

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

    /**
     * @param head
     * @param toBeDeleted 删除链表中给点待节点
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


}
