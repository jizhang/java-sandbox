package com.shzhangji.javasandbox.algs4.wordsearch;

import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int rows = sc.nextInt();
        int cols = sc.nextInt();
        sc.nextLine();

        char[][] data = new char[rows][cols];
        for (int row = 0; row < rows; ++row) {
            String line = sc.nextLine();
            for (int col = 0; col < cols; ++col) {
                data[row][col] = line.charAt(col);
            }
        }

        boolean wrap = sc.nextLine().equals("WRAP");

        Grid grid = new Grid(data, wrap);

        int words = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < words; ++i) {
            System.out.println(grid.search(sc.nextLine()));
        }

        sc.close();
    }

}
