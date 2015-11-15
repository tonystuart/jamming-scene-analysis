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

import com.example.afs.jamming.sound.Composable;

public class ColorMapEntry implements Entry<Color, Composable> {
  private Color color;
  private Composable composable;

  public ColorMapEntry() {
  }

  public ColorMapEntry(Color color, Composable composable) {
    this.color = color;
    this.composable = composable;
  }

  @Override
  public Color getKey() {
    return color;
  }

  @Override
  public Composable getValue() {
    return composable;
  }

  public void setKey(Color key) {
    this.color = key;
  }

  @Override
  public Composable setValue(Composable value) {
    Composable oldValue = composable;
    composable = value;
    return oldValue;
  }

  @Override
  public String toString() {
    return "ColorMapEntry [color=" + color + ", composable=" + composable + "]";
  }
}