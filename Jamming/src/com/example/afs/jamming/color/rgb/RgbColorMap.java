// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package com.example.afs.jamming.color.rgb;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.example.afs.jamming.color.base.BaseColorMap;
import com.example.afs.jamming.color.base.Color;
import com.example.afs.jamming.image.Item;
import com.example.afs.jamming.sound.Composable;
import com.example.afs.jamming.utility.Node;

public abstract class RgbColorMap extends BaseColorMap {

  private Map<Color, Composable> colorMap = new HashMap<>();

  public void add(Color color, Composable composable) {
    colorMap.put(color, composable);
  }

  @Override
  public void calibrate(Node<Item> items) {
  }

  @Override
  public Entry<? extends Color, Composable> findClosestEntry(Color color) {
    // There is a truly remarkable O(log n) algorithm but hanc marginis exiguitas non caperet
    // See https://en.wikipedia.org/wiki/Nearest_neighbor_search
    int r1 = color.getRed();
    int g1 = color.getGreen();
    int b1 = color.getBlue();
    int closestDistance = 0;
    Entry<Color, Composable> closestEntry = null;
    for (Entry<Color, Composable> mapEntry : colorMap.entrySet()) {
      Color mapColor = mapEntry.getKey();
      int r2 = mapColor.getRed();
      int g2 = mapColor.getGreen();
      int b2 = mapColor.getBlue();
      int distance = (int) Math.sqrt(Math.pow(r2 - r1, 2) + Math.pow(g2 - g1, 2) + Math.pow(b2 - b1, 2));
      if (closestEntry == null || distance < closestDistance) {
        closestEntry = mapEntry;
        closestDistance = distance;
      }
    }
    return closestEntry;
  }
}
