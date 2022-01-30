package com.shzhangji.javasandbox.algs4.basic;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListTest {

    private void reverseInternal(LinkedList<Integer> list, String method) {
        switch (method) {
        case "reverse":
            list.reverse();
            break;
        case "reverse1":
            list.reverse1();
            break;
        default:
            assert false;
        }
    }

    private void testReverseInternal(String method) {

        LinkedList<Integer> list = new LinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);

        reverseInternal(list, method);

        Assert.assertTrue(1 == list.pop());
        Assert.assertTrue(2 == list.pop());
        Assert.assertTrue(3 == list.pop());

        list = new LinkedList<>();
        reverseInternal(list, method); // no exception
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testReverse() {
        testReverseInternal("reverse");
    }

    @Test
    public void testReverse1() {
        testReverseInternal("reverse1");
    }

}
