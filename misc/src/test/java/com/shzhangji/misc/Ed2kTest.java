package com.shzhangji.misc;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

public class Ed2kTest {
  @Test
  public void testHash() throws Exception {
    Object[] cases = new Object[]{
        new byte[Ed2k.CHUNK_SIZE - 1], "AC44B93FC9AFF773AB0005C911F8396F",
        new byte[Ed2k.CHUNK_SIZE], "FC21D9AF828F92A8DF64BEAC3357425D",
        new byte[Ed2k.CHUNK_SIZE * 2 - 1], "A4AED104A077DE7E4210E7F5B131FE25",
        new byte[Ed2k.CHUNK_SIZE * 2], "114B21C63A74B6CA922291A11177DD5C",
        new byte[Ed2k.CHUNK_SIZE * 2 + 1], "E57F824D28F69FE90864E17673668457"
    };

    Ed2k ed2k = new Ed2k();
    for (int i = 0; i < cases.length; i += 2) {
      InputStream in = new ByteArrayInputStream((byte[]) cases[i]);
      Assert.assertEquals(cases[i + 1], ed2k.hash(in)[1]);
    }
  }
}
