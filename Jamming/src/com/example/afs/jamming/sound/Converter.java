// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package com.example.afs.jamming.sound;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;

import sequencemapper.BlockSequence;
import sequencemapper.SequencedBlock;

import com.example.afs.jamming.command.Options;
import com.example.afs.jamming.command.Trace.TraceOption;
import com.example.afs.jamming.utility.Node;
import com.sun.media.sound.MidiUtils;

public class Converter {

  public enum TickOrigin {
    LEFT, MIDPOINT
  }

  public static final int DRUM_CHANNEL = 9;
  public static final int MAXIMUM_VELOCITY = 127;
  public static final int STANDARD_DRUM_KIT = 1;
  public static final int TICKS_PER_BEAT = 250;

  private int midiChannel;
  private int midiProgram;
  private Options options;

  public Converter(Options options, int midiChannel, int midiProgram) {
    this.options = options;
    this.midiChannel = midiChannel;
    this.midiProgram = midiProgram;
    if (options.getTrace().isSet(TraceOption.CONVERSION)) {
      System.out.println("midiChannel=" + midiChannel + ", midiProgram=" + midiProgram);
    }
  }

  public Sequence convert(BlockSequence blockSequence) {
    try {
      int blockIndex = 0;
      Sequence sequence = new Sequence(Sequence.PPQ, TICKS_PER_BEAT);
      TrackBuilder trackBuilder = new TrackBuilder(sequence.createTrack());
      trackBuilder.addShortMessage(0, midiChannel, ShortMessage.PROGRAM_CHANGE, midiProgram, 0);
      Node<SequencedBlock> sequencedBlocks = blockSequence.getSequencedBlocks();
      for (SequencedBlock sequencedBlock : sequencedBlocks.getValues()) {
        int begin = sequencedBlock.getBegin();
        int end = sequencedBlock.getEnd();
        int duration;
        long tick;
        switch (options.getMidiTickOrigin()) {
          case LEFT:
            duration = end - begin;
            tick = begin;
            break;
          case MIDPOINT:
            duration = (end - begin) / 2;
            tick = begin + duration;
            break;
          default:
            throw new UnsupportedOperationException();
        }
        float brightness = sequencedBlock.getBlock().getItem().getColor().getBrightness();
        int velocity = scaleVelocity(blockSequence.getMaximumBrightness(), brightness);
        if (options.getTrace().isSet(TraceOption.MARKER)) {
          sequencedBlock.getBlock().getComposable().addToTrack(trackBuilder, tick, midiChannel, velocity, duration, blockIndex++);
        } else {
          sequencedBlock.getBlock().getComposable().addToTrack(trackBuilder, tick, midiChannel, velocity, duration);
        }
      }
      int lastTick = blockSequence.getTotalDuration();
      trackBuilder.addMetaMessage(lastTick, midiChannel, MidiUtils.META_END_OF_TRACK_TYPE, null, 0);
      return sequence;
    } catch (InvalidMidiDataException e) {
      throw new RuntimeException(e);
    }
  }

  public int getMidiProgram() {
    return midiProgram;
  }

  private int scaleVelocity(float maximumItemBrightness, float itemBrightness) {
    int baseMidiVelocity = options.getMidiBaseVelocity();
    int dynamicVelocityRange = MAXIMUM_VELOCITY - baseMidiVelocity;
    int scaledVelocity = (int) (baseMidiVelocity + ((dynamicVelocityRange * itemBrightness) / maximumItemBrightness));
    return scaledVelocity;
  }

}
