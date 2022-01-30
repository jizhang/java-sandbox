package com.shzhangji.javasandbox.algs4.misc;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BigDecimal {

    public static String add(String a, String b) {

        int length = Math.max(a.length(), b.length()) + 1;
        int[] arrA = stringToArray(a, length);
        int[] arrB = stringToArray(b, length);

        for (int i = 0; i < length; ++i) {
            arrA[i] += arrB[i];
            if (arrA[i] >= 10) {
                arrA[i] -= 10;
                ++arrA[i + 1];
            }
        }

        return arrayToString(arrA);
    }

    public static String multiply(String a, String b) {

        int length = Math.max(a.length(), b.length()) * 2;
        int[] arrA = stringToArray(a, a.length());
        int[] arrB = stringToArray(b, b.length());
        int[] arrC = new int[length];

        for (int i = 0; i < a.length(); ++i) {
            for (int j = 0; j < b.length(); ++j) {
                arrC[i + j] += arrA[i] * arrB[j];
            }
        }

        for (int i = 0; i < length; ++i) {
            if (arrC[i] >= 10) {
                arrC[i + 1] += arrC[i] / 10;
                arrC[i] = arrC[i] % 10;
            }
        }

        return arrayToString(arrC);
    }

    private static int[] stringToArray(String s, int length) {
        int[] arr = new int[length];
        for (int i = 0; i < s.length(); ++i) {
            arr[i] = s.charAt(s.length() - i - 1) - '0';
        }
        return arr;
    }

    private static String arrayToString(int[] arr) {

        int i;
        for (i = arr.length - 1; i >= 0 && arr[i] == 0; --i);
        if (i == -1) {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        for (; i >= 0; --i) {
            result.append(arr[i]);
        }

        return result.toString();
    }

    public static void main(String[] args) {

        String a = StdIn.readString();
        String op = StdIn.readString();
        String b = StdIn.readString();

        String result;
        switch (op) {
        case "+":
            result = add(a, b);
            break;

        case "*":
            result = multiply(a, b);
            break;

        default:
            result = "Invalid operator.";
            break;
        }

        StdOut.println(result);
    }

}
