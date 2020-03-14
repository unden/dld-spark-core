package com.duliday.spark.core.leetcode.linkedlist;

public class TwoWayLinkedList {

    private String value;

    private TwoWayLinkedList pre;

    private TwoWayLinkedList next;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TwoWayLinkedList getPre() {
        return pre;
    }

    public void setPre(TwoWayLinkedList pre) {
        this.pre = pre;
    }

    public TwoWayLinkedList getNext() {
        return next;
    }

    public void setNext(TwoWayLinkedList next) {
        this.next = next;
    }
}
