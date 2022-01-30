package com.shzhangji.javasandbox.algs4.wordsearch;

public class Grid {

    private int height, width;
    private Point[][] data;
    private boolean wrap;

    public Grid(char[][] data, boolean wrap) {

        this.height = data.length;
        this.width = data[0].length;

        this.data = new Point[height][width];
        for (int row = 0; row < height; ++row) {
            for (int col = 0; col < width; ++col) {
                this.data[row][col] = new Point(row, col, data[row][col]);
            }
        }

        this.wrap = wrap;
    }

    public String search(String word) {

        for (int row = 0; row < height; ++row) {
            for (int col = 0; col < width; ++col) {
                for (Direction direction : Direction.values()) {
                    SearchResult result = search(data[row][col], direction, word);
                    if (result != null) {
                        return result.toString();
                    }
                }

            }
        }

        return SearchResult.NOT_FOUND;
    }

    private SearchResult search(Point point, Direction direction, String word) {

        Point stopPoint = null;
        for (int n = 0; n < word.length(); ++n) {

            stopPoint = nextPoint(point, n, direction);

            if (n > 0 && point.getRow() == stopPoint.getRow() && point.getCol() == stopPoint.getCol()) {
                return null;
            }

            if (word.charAt(n) != stopPoint.getChr()) {
                return null;
            }
        }

        return new SearchResult(point, stopPoint);
    }

    private Point nextPoint(Point point, int n, Direction direction) {

        int row = point.getRow(), col = point.getCol();

        switch (direction) {
        case TOP_BOTTOM:
            row += n;
            break;
        case BOTTOM_TOP:
            row -= n;
            break;
        case LEFT_RIGHT:
            col += n;
            break;
        case RIGHT_LEFT:
            col -= n;
            break;
        case BOTTOMLEFT_TOPRIGHT:
            row -= n;
            col += n;
            break;
        case BOTTOMRIGHT_TOPLEFT:
            row -= n;
            col -= n;
            break;
        case TOPLEFT_BOTTOMRIGHT:
            row += n;
            col += n;
            break;
        case TOPRIGHT_BOTTOMLEFT:
            row += n;
            col -= n;
            break;
        }

        if (row < 0) {
            row = wrap ? (row % height + height) : -1;
        }
        if (row >= height) {
            row = wrap ? (row % height) : -1;
        }
        if (col < 0) {
            col = wrap ? (col % width + width) : -1;
        }
        if (col >= width) {
            col = wrap ? (col % width) : -1;
        }

        if (row == -1 || col == -1) {
            return Point.NULL;
        }

        return data[row][col];
    }

}
