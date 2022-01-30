package com.shzhangji.javasandbox.algs4.wordsearch;

public class SearchResult {

    public static final String NOT_FOUND = "NOT FOUND";

    private Point startPoint;
    private Point stopPoint;

    public SearchResult(Point startPoint, Point stopPoint) {
        this.startPoint = startPoint;
        this.stopPoint = stopPoint;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d) (%d,%d)",
                startPoint.getRow(), startPoint.getCol(),
                stopPoint.getRow(), stopPoint.getCol());
    }

}
