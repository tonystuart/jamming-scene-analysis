// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package com.example.afs.jamming.sound;


public class DynamicRhythmScaleBasedChord extends MarkerComposable {

  private static int count;
  private int fifth;
  private String name;
  private int root;
  private int third;
  private int tonic;

  public DynamicRhythmScaleBasedChord(String name, int tonic, int root, int third, int fifth) {
    this.name = name;
    this.tonic = tonic;
    this.root = root;
    this.third = third;
    this.fifth = fifth;
  }

  @Override
  public void addToTrack(TrackBuilder trackBuilder, long tick, int channel, int velocity, int totalDuration) {
    int duration = totalDuration / 3;
    int modulo = count++ % 4;
    if (modulo == 0) {
      tick = addNote(trackBuilder, tick, channel, tonic + root, velocity, duration);
      tick = addNote(trackBuilder, tick, channel, tonic + third, velocity, duration);
      tick = addNote(trackBuilder, tick, channel, tonic + fifth, velocity, duration / 2);
      tick = addNote(trackBuilder, tick, channel, tonic + fifth, velocity, duration / 2);
    } else if (modulo == 1) {
      tick = addNote(trackBuilder, tick, channel, tonic + root, velocity, duration);
      tick = addNote(trackBuilder, tick, channel, tonic + third, velocity, duration / 2);
      tick = addNote(trackBuilder, tick, channel, tonic + third, velocity, duration / 2);
      tick = addNote(trackBuilder, tick, channel, tonic + fifth, velocity, duration);
    } else if (modulo == 2) {
      tick = addNote(trackBuilder, tick, channel, tonic + root, velocity, duration / 2);
      tick = addNote(trackBuilder, tick, channel, tonic + root, velocity, duration / 2);
      tick = addNote(trackBuilder, tick, channel, tonic + third, velocity, duration);
      tick = addNote(trackBuilder, tick, channel, tonic + fifth, velocity, duration);
    } else if (modulo == 3) {
      tick = addNote(trackBuilder, tick, channel, tonic + root, velocity, duration / 2);
      tick = addNote(trackBuilder, tick, channel, tonic + third, velocity, duration / 2);
      tick = addNote(trackBuilder, tick, channel, tonic + fifth, velocity, duration * 2);
    }
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "[name=" + name + ", tonic=" + tonic + ", root=" + root + ", third=" + third + ", fifth=" + fifth + "]";
  }

  private long addNote(TrackBuilder trackBuilder, long tick, int channel, int note, int velocity, int duration) {
    trackBuilder.addNote(tick, channel, note, velocity, duration);
    tick += duration;
    return tick;
  }

}