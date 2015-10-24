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
import java.util.concurrent.Callable;

import com.example.afs.jamming.utility.Node;

public class ItemFinderTask implements Callable<Node<Item>> {

  private int bottom;
  private int columnCount;
  private BufferedImage image;
  private Node<Item> items;
  private int left;
  private int right;
  private int rowCount;
  private int top;

  public ItemFinderTask(BufferedImage image, int top, int bottom, int left, int right, int rowCount, int columnCount) {
    this.image = image;
    this.top = top;
    this.bottom = bottom;
    this.left = left;
    this.right = right;
    this.rowCount = rowCount;
    this.columnCount = columnCount;
  }

  @Override
  public Node<Item> call() throws Exception {
    try {
      items = new ItemFinder().findItems(image, top, bottom, left, right, rowCount, columnCount);
      return items;
    } catch (RuntimeException e) {
      e.printStackTrace();
      throw e;
    }
  }

  public Node<Item> getItems() {
    return items;
  }

  @Override
  public String toString() {
    return "Task [top=" + top + ", bottom=" + bottom + ", items=" + items + "]";
  }

}