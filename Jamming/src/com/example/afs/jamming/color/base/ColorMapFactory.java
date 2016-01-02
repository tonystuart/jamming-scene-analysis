// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package com.example.afs.jamming.color.base;

import java.util.Map;
import java.util.TreeMap;

import com.example.afs.jamming.color.hsb.Chord7ColorArpeggiatedHsbColorMap;
import com.example.afs.jamming.color.hsb.Chord7ColorHsbColorMap;
import com.example.afs.jamming.color.hsb.DrumHsbColorMap;
import com.example.afs.jamming.color.hsb.Note1OctaveLowHsbColorMap;
import com.example.afs.jamming.color.hsb.Note2OctaveHsbColorMap;
import com.example.afs.jamming.color.hsb.Note4OctaveDynamicHsbColorMap;
import com.example.afs.jamming.color.hsb.Note4OctaveHsbColorMap;
import com.example.afs.jamming.color.hsb.TechnoMultiColorHsbColorMap;
import com.example.afs.jamming.color.rgb.Chord7ColorRgbColorMap;
import com.example.afs.jamming.color.rgb.DrumRgbColorMap;
import com.example.afs.jamming.sound.ArpeggiatedScaleBasedChord;
import com.example.afs.jamming.sound.Composable;
import com.example.afs.jamming.sound.DynamicRhythmScaleBasedChord;

public final class ColorMapFactory {

  public static class Composables {
    public static final Composable[] argpeggiatedScale = new Composable[] {
        //
        new ArpeggiatedScaleBasedChord("C (I)", 60, 0, 4, 7), //
        new ArpeggiatedScaleBasedChord("Dmin (ii)", 60, 2, 5, 9), //
        new ArpeggiatedScaleBasedChord("Emin (iii)", 60, 4, 7, 11), //
        new ArpeggiatedScaleBasedChord("F (IV)", 60, 5, 9, 12), //
        new ArpeggiatedScaleBasedChord("G (V)", 60, 7, 11, 14), //
        new ArpeggiatedScaleBasedChord("Amin (vi)", 60, 9, 12, 16), //
        new ArpeggiatedScaleBasedChord("Bdim (vii\u00B0)", 60, 11, 14, 17), //
    };
    public static final Composable[] dynamicRhythmScale = new Composable[] {
        //
        new DynamicRhythmScaleBasedChord("C (I)", 60, 0, 4, 7), //
        new DynamicRhythmScaleBasedChord("Dmin (ii)", 60, 2, 5, 9), //
        new DynamicRhythmScaleBasedChord("Emin (iii)", 60, 4, 7, 11), //
        new DynamicRhythmScaleBasedChord("F (IV)", 60, 5, 9, 12), //
        new DynamicRhythmScaleBasedChord("G (V)", 60, 7, 11, 14), //
        new DynamicRhythmScaleBasedChord("Amin (vi)", 60, 9, 12, 16), //
        new DynamicRhythmScaleBasedChord("Bdim (vii\u00B0)", 60, 11, 14, 17), //
    };
  }

  private class ArpeggiatedClusteringHueColorMap extends ClusteringColorMap {
    public ArpeggiatedClusteringHueColorMap() {
      super(Composables.argpeggiatedScale, HueColorComparator.INSTANCE);
    }
  }

  private class DynamicRhythmClusteringHueColorMap extends ClusteringColorMap {
    public DynamicRhythmClusteringHueColorMap() {
      super(Composables.dynamicRhythmScale, HueColorComparator.INSTANCE);
    }
  }

  public static final ColorMapFactory instance = new ColorMapFactory();

  public static ColorMapFactory getSingleton() {
    return instance;
  }

  private Map<String, ColorMap> colorMaps = new TreeMap<>();

  public ColorMapFactory() {
    // http://www.javaworld.com/article/2077477/learn-java/java-tip-113--identify-subclasses-at-runtime.html
    register(new ArpeggiatedClusteringHueColorMap());
    register(new Chord7ColorArpeggiatedHsbColorMap());
    register(new Chord7ColorHsbColorMap());
    register(new Chord7ColorRgbColorMap());
    register(new DrumHsbColorMap());
    register(new DrumRgbColorMap());
    register(new DynamicRhythmClusteringHueColorMap());
    register(new Note1OctaveLowHsbColorMap());
    register(new Note2OctaveHsbColorMap());
    register(new Note4OctaveHsbColorMap());
    register(new Note4OctaveDynamicHsbColorMap());
    register(new TechnoMultiColorHsbColorMap());
  }

  public ColorMap get(String name) {
    return colorMaps.get(name);
  }

  public ColorMap getDefault() {
    return colorMaps.size() == 0 ? null : colorMaps.values().iterator().next();
  }

  public String[] getNames() {
    int nameIndex = 0;
    String[] names = new String[colorMaps.size()];
    for (String name : colorMaps.keySet()) {
      names[nameIndex++] = name;
    }
    return names;
  }

  public void put(String name, ColorMap colorMap) {
    colorMaps.put(name, colorMap);
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (String name : colorMaps.keySet()) {
      if (s.length() > 0) {
        s.append("\n");
      }
      s.append(name);
    }
    return s.toString();
  }

  private void register(ColorMap colorMap) {
    put(colorMap.getName(), colorMap);
  }
}
