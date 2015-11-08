// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package com.example.afs.jamming.color.base;

import java.util.Comparator;

import com.example.afs.jamming.image.Item;

public class ItemColorComparator implements Comparator<Item> {

  private Comparator<Color> colorComparator;

  public ItemColorComparator(Comparator<Color> colorComparator) {
    this.colorComparator = colorComparator;
  }

  @Override
  public int compare(Item o1, Item o2) {
    return colorComparator.compare(o1.getColor(), o2.getColor());
  }
}