// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package com.example.afs.jamming.color.base;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.example.afs.jamming.image.Item;
import com.example.afs.jamming.sound.Composable;
import com.example.afs.jamming.utility.Node;

public class ClusteringColorMap extends BaseColorMap {

  private Comparator<Color> colorComparator;
  private Composable[] composables;
  private ColorMapEntry flyweightMapEntry = new ColorMapEntry();
  private Map<Color, Composable> map = new HashMap<>();

  public ClusteringColorMap(Composable[] composables, Comparator<Color> colorComparator) {
    this.composables = composables;
    this.colorComparator = colorComparator;
  }

  @Override
  public void calibrate(Node<Item> items) {
    Item[] itemArray = getItemArray(items);
    Arrays.sort(itemArray, 0, items.getChildNodeCount(), new ItemColorComparator(colorComparator));
    if (itemArray.length > composables.length) {
      // Round up divisor to ensure quotient in loop remains in range
      int itemsPerComposable = (itemArray.length + composables.length - 1) / composables.length;
      for (int i = 0; i < itemArray.length; i++) {
        Item item = itemArray[i];
        Composable composable = composables[i / itemsPerComposable];
        map.put(item.getColor(), composable);
      }
    } else {
      int composablesPerItem = composables.length / itemArray.length;
      for (int i = 0; i < itemArray.length; i++) {
        Item item = itemArray[i];
        Composable composable = composables[i * composablesPerItem];
        map.put(item.getColor(), composable);
      }
    }
  }

  @Override
  public Entry<? extends Color, Composable> findClosestEntry(Color color) {
    Composable composable = map.get(color);
    flyweightMapEntry.setKey(color);
    flyweightMapEntry.setValue(composable);
    return flyweightMapEntry;
  }

  private Item[] getItemArray(Node<Item> items) {
    Item[] itemArray = new Item[items.getChildNodeCount()];
    int i = 0;
    for (Item item : items.getValues()) {
      itemArray[i++] = item;
    }
    return itemArray;
  }
}
