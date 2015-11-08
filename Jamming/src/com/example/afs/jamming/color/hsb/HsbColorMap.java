// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package com.example.afs.jamming.color.hsb;

import java.util.Map.Entry;
import java.util.TreeMap;

import com.example.afs.jamming.color.base.BaseColorMap;
import com.example.afs.jamming.color.base.Color;
import com.example.afs.jamming.color.base.HueColorComparator;
import com.example.afs.jamming.image.Item;
import com.example.afs.jamming.sound.Composable;
import com.example.afs.jamming.utility.Node;

public abstract class HsbColorMap extends BaseColorMap {

  private TreeMap<Color, Composable> colorMap = new TreeMap<>(HueColorComparator.INSTANCE);

  public void add(Color color, Composable composable) {
    colorMap.put(color, composable);
  }

  @Override
  public void calibrate(Node<Item> items) {
  }

  @Override
  public Entry<? extends Color, Composable> findClosestEntry(Color color) {
    Entry<Color, Composable> closestEntry = colorMap.ceilingEntry(color);
    if (closestEntry == null) {
      closestEntry = colorMap.firstEntry();
    }
    return closestEntry;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("name=");
    s.append(getName());
    for (Entry<Color, Composable> entry : colorMap.entrySet()) {
      s.append("\n");
      s.append(entry.getKey());
      s.append(" ");
      s.append(entry.getValue());
    }
    return s.toString();
  }
}
