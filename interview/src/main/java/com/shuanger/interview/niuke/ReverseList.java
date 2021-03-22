package com.shuanger.interview.niuke;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-03-22 09:38
 * @description:
 */
public class ReverseList {

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode curr = head;
        ListNode temp = null;
        while (curr != null) {
            temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }

        return pre;
    }
}
