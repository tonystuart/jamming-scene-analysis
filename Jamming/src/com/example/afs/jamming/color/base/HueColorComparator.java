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

public final class HueColorComparator implements Comparator<Color> {

  public static final HueColorComparator INSTANCE = new HueColorComparator();

  private HueColorComparator() {
  }

  @Override
  public int compare(Color o1, Color o2) {
    return Float.compare(o1.getHue(), o2.getHue());
  }
}