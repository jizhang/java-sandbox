package com.shzhangji.pattern.proxy;

import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

@RequiredArgsConstructor
public class ImageProxy implements Icon {
  final URL imageURL;
  ImageIcon imageIcon;
  Thread retrievalThread;
  boolean retrieving = false;

  @Override
  public int getIconWidth() {
    return imageIcon != null ? imageIcon.getIconWidth() : 800;
  }

  @Override
  public int getIconHeight() {
    return imageIcon != null ? imageIcon.getIconHeight() : 600;
  }

  @Override
  public void paintIcon(Component c, Graphics g, int x, int y) {
    if (imageIcon != null) {
      imageIcon.paintIcon(c, g, x, y);
      return;
    }

    g.drawString("Loading CD cover, please wait...", x + 300, y + 190);
    if (retrieving) return;

    retrieving = true;
    retrievalThread = new Thread(() -> {
      imageIcon = new ImageIcon(imageURL, "CD Cover");
      c.repaint();
    });
    retrievalThread.start();
  }
}
