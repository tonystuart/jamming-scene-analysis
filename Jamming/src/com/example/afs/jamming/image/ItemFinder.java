// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package com.example.afs.jamming.image;

import java.awt.image.BufferedImage;

import com.example.afs.jamming.color.base.Color;
import com.example.afs.jamming.utility.Node;

public class ItemFinder {

  public Node<Item> findItems(BufferedImage image, int top, int bottom, int left, int right, int rowCount, int columnCount) {
    Node<Item> items = new Node<>();
    processRows(items, image, top, bottom, left, right, rowCount, columnCount);
    return items;
  }

  private Item createItem(BufferedImage image, int top, int left, int itemWidth, int itemHeight) {
    int totalRed = 0;
    int totalGreen = 0;
    int totalBlue = 0;
    for (int x = 0; x < itemWidth; x++) {
      for (int y = 0; y < itemHeight; y++) {
        int rgb = image.getRGB(left + x, top + y);
        totalRed += Color.getRed(rgb);
        totalGreen += Color.getGreen(rgb);
        totalBlue += Color.getBlue(rgb);
      }
    }
    int totalPixels = itemWidth * itemHeight;
    int averageRed = totalRed / totalPixels;
    int averageGreen = totalGreen / totalPixels;
    int averageBlue = totalBlue / totalPixels;
    Color color = new Color(averageRed, averageGreen, averageBlue);
    Item item = new Item(top, left, itemWidth, itemHeight, color);
    return item;
  }

  private void processColumns(Node<Item> items, BufferedImage image, int top, int height, int left, int right, int columnCount) {
    int totalWidth = right - left;
    for (int column = 0; column < columnCount; column++) {
      int itemLeft = (column * totalWidth) / columnCount;
      int nextItemLeft = ((column + 1) * totalWidth) / columnCount;
      int itemWidth = nextItemLeft - itemLeft;
      Item item = createItem(image, top, itemLeft, itemWidth, height);
      items.addChild(item);
      setAverageColor(image, item);
    }
  }

  private void processRows(Node<Item> items, BufferedImage image, int top, int bottom, int left, int right, int rowCount, int columnCount) {
    int totalHeight = bottom - top;
    for (int row = 0; row < rowCount; row++) {
      int itemTop = top + (row * totalHeight) / rowCount;
      int nextItemTop = top + ((row + 1) * totalHeight) / rowCount;
      int itemHeight = nextItemTop - itemTop;
      processColumns(items, image, itemTop, itemHeight, left, right, columnCount);
    }
  }

  private void setAverageColor(BufferedImage image, Item item) {
    int rgb = item.getColor().getRgb();
    int itemHeight = item.getTop() + item.getHeight();
    int itemWidth = item.getLeft() + item.getWidth();
    for (int x = item.getLeft(); x < itemWidth; x++) {
      for (int y = item.getTop(); y < itemHeight; y++) {
        image.setRGB(x, y, rgb);
      }
    }
  }

}
