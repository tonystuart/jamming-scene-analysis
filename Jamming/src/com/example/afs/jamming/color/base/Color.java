// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package com.example.afs.jamming.color.base;

public class Color {

  public static int getBlue(int rgb) {
    return rgb & 0xff;
  }

  public static int getGreen(int rgb) {
    return (rgb >> 8) & 0xff;
  }

  public static int getRed(int rgb) {
    return (rgb >> 16) & 0xff;
  }

  private int blue;
  private int green;
  private float[] hsbValues = new float[3];
  private String name;
  private int red;
  private int rgb;

  public Color(float hue) {
    this(null, hue, 1f, 1f);
  }

  public Color(int rgb) {
    this(null, rgb);
  }

  public Color(int red, int green, int blue) {
    this((red << 16) | (green << 8) | blue);
  }

  public Color(String name, float hue) {
    this(name, hue, 1f, 1f);
  }

  public Color(String name, float hue, float saturation, float brightness) {
    this(name, java.awt.Color.HSBtoRGB(hue, saturation, brightness) & 0xffffff); // mask off alpha channel
    hsbValues[0] = hue;
    hsbValues[1] = saturation;
    hsbValues[2] = brightness;
  }

  public Color(String name, int rgb) {
    this.name = name;
    this.rgb = rgb;
    this.red = Color.getRed(rgb);
    this.green = Color.getGreen(rgb);
    this.blue = Color.getBlue(rgb);
    java.awt.Color.RGBtoHSB(red, green, blue, hsbValues);
  }

  public int getBlue() {
    return blue;
  }

  public float getBrightness() {
    return hsbValues[2];
  }

  public int getDistance(Color color) {
    int r1 = getRed();
    int g1 = getGreen();
    int b1 = getBlue();
    int r2 = color.getRed();
    int g2 = color.getGreen();
    int b2 = color.getBlue();
    return (int) Math.sqrt(Math.pow(r2 - r1, 2) + Math.pow(g2 - g1, 2) + Math.pow(b2 - b1, 2));
  }

  public int getGreen() {
    return green;
  }

  public float getHue() {
    return hsbValues[0];
  }

  public String getName() {
    return name;
  }

  public int getRed() {
    return red;
  }

  public int getRgb() {
    return rgb;
  }

  public float getSaturation() {
    return hsbValues[1];
  }

  @Override
  public String toString() {
    String s = String.format("%06x %.2f/%.2f/%.2f", getRgb(), hsbValues[0], hsbValues[1], hsbValues[2]);
    String name = getName();
    if (name != null) {
      s += " (" + name + ")";
    }
    return s;
  }

}
