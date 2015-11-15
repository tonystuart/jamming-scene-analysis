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
import com.example.afs.jamming.sound.Composable;

public class Block {
  private Color color;
  private Composable composable;
  private Item item;

  public Block(Item item, Color color, Composable composable) {
    this.item = item;
    this.color = color;
    this.composable = composable;
  }

  public Color getAverageColor() {
    return item.getColor();
  }

  public Color getColor() {
    return color;
  }

  public Composable getComposable() {
    return composable;
  }

  public Item getItem() {
    return item;
  }

  public String toString() {
    return item + ", matchingColor=" + color + ", distance=" + getAverageColor().getDistance(color) + ", composable=" + composable;
  }

}
