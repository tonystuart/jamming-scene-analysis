// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package com.example.afs.jamming.image;

import com.example.afs.jamming.color.hsb.HsbColor;
import com.example.afs.jamming.color.rgb.Color;
import com.example.afs.jamming.sound.Composable;

public class Block {
  private int averageRgb;
  private Color color;
  private Composable composable;
  private Item item;

  public Block(Item item, Color color, Composable composable, int averageRgb) {
    this.item = item;
    this.color = color;
    this.composable = composable;
    this.averageRgb = averageRgb;
  }

  public Color getAverageColor() {
    Color averageColor;
    if (color instanceof HsbColor) {
      averageColor = new HsbColor(averageRgb);
    } else {
      averageColor = new Color(averageRgb);
    }
    return averageColor;
  }

  public int getAverageRgb() {
    return averageRgb;
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
    if (color instanceof HsbColor) {
      return item + ", averageHsb=" + new HsbColor(averageRgb) + ", matchingHsb=" + color + ", composable=" + composable;
    } else {
      return item + ", averageRgb=" + Integer.toHexString(averageRgb) + ", distance=" + Color.getDistance(averageRgb, color) + ", color=" + color + ", composable=" + composable;
    }
  }

}
