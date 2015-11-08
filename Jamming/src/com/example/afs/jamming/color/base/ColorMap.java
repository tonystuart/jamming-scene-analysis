// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package com.example.afs.jamming.color.base;

import java.util.Map.Entry;

import com.example.afs.jamming.image.Item;
import com.example.afs.jamming.sound.Composable;
import com.example.afs.jamming.utility.Node;

public interface ColorMap {

  void calibrate(Node<Item> items);

  Entry<? extends Color, ? extends Composable> findClosestEntry(Color color);

  String getName();
}
