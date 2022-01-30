package com.shzhangji.javasandbox.algs4.basic;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Evaluate {

    public static void main(String[] args) {

        Stack<String> ops = new LinkedList<>();
        Stack<Double> vals = new LinkedList<>();

        while (!StdIn.isEmpty()) {

            String s = StdIn.readString();
            switch (s) {
            case "(":
                break;
            case "+":
            case "-":
            case "*":
            case "/":
            case "sqrt":
                ops.push(s);
                break;
            case ")":

                String op = ops.pop();
                double v = vals.pop();

                switch (op) {
                case "+":
                    v = vals.pop() + v;
                    break;
                case "-":
                    v = vals.pop() - v;
                    break;
                case "*":
                    v = vals.pop() * v;
                    break;
                case "/":
                    v = vals.pop() / v;
                    break;
                case "sqrt":
                    v = Math.sqrt(v);
                default:
                    assert false;
                }

                vals.push(v);
                break;

            default:
                vals.push(Double.parseDouble(s));
            }

        }

        StdOut.println(vals.pop());
    }

}
