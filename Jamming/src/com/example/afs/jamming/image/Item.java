// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package com.example.afs.jamming.image;

import com.example.afs.jamming.color.base.Color;

public class Item {

  private Color color;
  private int height;
  private int left;
  private int top;
  private int width;

  public Item(int top, int left, int itemWidth, int itemHeight, Color color) {
    this.top = top;
    this.left = left;
    this.width = itemWidth;
    this.height = itemHeight;
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  public int getHeight() {
    return height;
  }

  public int getLeft() {
    return left;
  }

  public int getTop() {
    return top;
  }

  public int getWidth() {
    return width;
  }

  @Override
  public String toString() {
    return "Item [row=" + top + ", column=" + left + ", itemWidth=" + width + ", itemHeight=" + height + ", color=" + color + "]";
  }

}
