package com.shzhangji.misc;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class Ed2k {
  public static final int CHUNK_SIZE = 9500 * 1024;

  public Object[] hash(InputStream in) throws Exception {
    MessageDigest md4 = MessageDigest.getInstance("MD4", new BouncyCastleProvider());
    byte[] buffer = new byte[8192];
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int len = 0, chunk = 0, total = 0;
    while ((len = in.read(buffer)) > 0) {
      if (chunk + len >= CHUNK_SIZE) {
        int offset = CHUNK_SIZE - chunk;
        md4.update(buffer, 0, offset);
        out.write(md4.digest());
        md4.reset();
        chunk = len - offset;
        md4.update(buffer, offset, chunk);
      } else {
        md4.update(buffer, 0, len);
        chunk += len;
      }
      total += len;
    }

    out.write(md4.digest());
    md4.reset();

    if (out.size() > md4.getDigestLength()) {
      md4.update(out.toByteArray());
      out.reset();
      out.write(md4.digest());
    }

    return new Object[] {
        total,
        Hex.toHexString(out.toByteArray()).toUpperCase()
    };
  }
}
