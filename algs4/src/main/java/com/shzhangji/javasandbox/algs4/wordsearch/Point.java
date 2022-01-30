package com.shzhangji.javasandbox.algs4.wordsearch;

public class Point {

    public static final Point NULL = new Point(-1, -1, '\0');

    private int row;
    private int col;
    private char chr;

    public Point(int row, int col, char chr) {
        this.row = row;
        this.col = col;
        this.chr = chr;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public char getChr() {
        return chr;
    }

}
