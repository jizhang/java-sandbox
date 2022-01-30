package com.shzhangji.javasandbox.algs4.wordsearch;

import org.junit.Assert;
import org.junit.Test;

public class GridTest {

    @Test
    public void testBasic() {

        char[][] data = new char[][] {
            { 'A', 'B', 'C' },
            { 'D', 'E', 'F' },
            { 'G', 'H', 'I' }
        };

        Grid grid = new Grid(data, false);
        Assert.assertEquals("(1,2) (1,0)", grid.search("FED"));
        Assert.assertEquals("NOT FOUND", grid.search("CAB"));
        Assert.assertEquals("NOT FOUND", grid.search("GAD"));
        Assert.assertEquals("NOT FOUND", grid.search("BID"));
        Assert.assertEquals("NOT FOUND", grid.search("HIGH"));

        grid = new Grid(data, true);
        Assert.assertEquals("(1,2) (1,0)", grid.search("FED"));
        Assert.assertEquals("(0,2) (0,1)", grid.search("CAB"));
        Assert.assertEquals("(2,0) (1,0)", grid.search("GAD"));
        Assert.assertEquals("(0,1) (1,0)", grid.search("BID"));
        Assert.assertEquals("NOT FOUND", grid.search("HIGH"));
    }

    @Test
    public void testWide() {

        Grid grid = new Grid (new char[][] {
            { 'A', 'B', 'C', 'D' },
            { 'E', 'F', 'G', 'H' },
            { 'I', 'J', 'K', 'L' }
        }, true);

        Assert.assertEquals("(0,0) (2,3)", grid.search("AFKDEJCHIBGL"));
        Assert.assertEquals("(0,1) (1,1)", grid.search("BELCF"));
        Assert.assertEquals("(1,2) (2,3)", grid.search("GDIFCL"));
        Assert.assertEquals("(1,3) (0,3)", grid.search("HCJED"));
        Assert.assertEquals("NOT FOUND", grid.search("FAK"));
        Assert.assertEquals("NOT FOUND", grid.search("FIDGJB"));
    }

    @Test
    public void testNarrow() {

        Grid grid = new Grid(new char[][] {
            { 'A', 'B' },
            { 'C', 'D' },
            { 'E', 'F' },
            { 'G', 'H' }
        }, true);

        Assert.assertEquals("(0,1) (2,1)", grid.search("BCF"));
        Assert.assertEquals("(3,1) (0,0)", grid.search("HEDA"));
        Assert.assertEquals("NOT FOUND", grid.search("ADEHA"));
        Assert.assertEquals("NOT FOUND", grid.search("HFDBH"));
    }

}
