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

import com.example.afs.jamming.color.base.Color;
import com.example.afs.jamming.sound.Composable;
import com.example.afs.jamming.sound.Note;

public class Note4OctaveDynamicHsbColorMap extends HsbColorMap {

  private class ColorMapEntry implements Entry<Color, Composable> {
    private Color color;
    private Note note;

    public ColorMapEntry(Color color, Note note) {
      this.color = color;
      this.note = note;
    }

    @Override
    public Color getKey() {
      return color;
    }

    @Override
    public Composable getValue() {
      return note;
    }

    @Override
    public Composable setValue(Composable value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
      return "ColorMapEntry [color=" + color + ", note=" + note + "]";
    }
  }

  private static final int BASE = 36;
  private static final int OCTAVES = 4;
  private static final int SEMITONES = 12;

  @Override
  public Entry<? extends Color, Composable> findClosestEntry(Color color) {
    float saturation = color.getSaturation();
    saturation = Math.min(saturation, 0.99f);
    int octave = (int) (saturation * OCTAVES);
    int baseNote = BASE + (octave * SEMITONES);
    float hue = color.getHue();
    hue = Math.min(hue, 0.99f);
    int noteOffset = (int) (hue * SEMITONES);
    Note note = new Note(baseNote + noteOffset);
    ColorMapEntry entry = new ColorMapEntry(color, note);
    return entry;
  }

}
