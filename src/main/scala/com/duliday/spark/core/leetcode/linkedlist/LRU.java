package com.duliday.spark.core.leetcode.linkedlist;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class LRU {

    private int capacity;

    private int size = 0;

    private Map<String, TwoWayLinkedList> cache = new HashMap<String, TwoWayLinkedList>();

    private TwoWayLinkedList head;

    private TwoWayLinkedList tail;

    public LRU(int capacity) {
        this.capacity = capacity;
    }

    public void put(String value) {
        if (StringUtils.isBlank(value)) {
            return;
        }

        if (cache.containsKey(value)) {
            TwoWayLinkedList old = cache.get(value);
            removeCurrentNode(old);
            moveToHead(old, true);
        } else {
            TwoWayLinkedList newTail = new TwoWayLinkedList();
            newTail.setValue(value);
            if (size < capacity) {
                moveToHead(newTail, false);
            } else {
                removeTail();
                moveToHead(newTail, false);
            }
            size++;
            cache.put(value, newTail);
        }
    }

    private void removeTail() {
        TwoWayLinkedList pre = tail.getPre();
        pre.setNext(null);
        tail = pre;
    }

    private void moveToHead(TwoWayLinkedList old, boolean isContained) {
        if (isContained) {
            if (size == 1) {
                return;
            }
        } else {
            if (size == 0) {
                head = old;
                tail = old;
                return;
            }
        }
        old.setNext(head);
        head.setPre(old);
        head = old;
    }

    private void removeCurrentNode(TwoWayLinkedList old) {
        TwoWayLinkedList pre = old.getPre();
        TwoWayLinkedList next = old.getNext();
        if (pre == null) {
            return;
        }

        pre.setNext(next);
        if (next != null) {
            next.setPre(pre);
        }
    }

}
