// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package com.example.afs.jamming.command;

import sequencemapper.SequenceMapperFactory.SequenceMapping;

import com.example.afs.jamming.color.base.ColorMap;
import com.example.afs.jamming.color.base.ColorMaps;
import com.example.afs.jamming.command.Raspistill.WhiteBalance;
import com.example.afs.jamming.command.Trace.TraceOption;
import com.example.afs.jamming.sound.Converter;
import com.example.afs.jamming.sound.Converter.TickOrigin;

public class Options {

  private ColorMap colorMap = ColorMaps.getSingleton().getDefault();
  private int columnCount = 24;
  private String imageBaseFilename = "Jamming";
  private int imageBrightness = 40;
  private String imageCaptureProgram = "raspistill";
  private int imageRotation = 90;
  private int imageSizeHeight = 768;
  private int imageSizeWidth = 1024;
  private WhiteBalance imageWhiteBalance = WhiteBalance.auto;
  private String imageWhiteBalanceGain = "1.0,1.2";
  private boolean isMidiProgramLoop = false;
  private int midiBaseVelocity = Converter.MAXIMUM_VELOCITY / 2;
  private int midiChannel = 0;
  private int midiProgram = 1;
  private float midiTempoFactor = 1.0f;
  private TickOrigin midiTickOrigin = TickOrigin.MIDPOINT;
  private int objectFuzziness = 10;
  private int rowCount = 16;
  private SequenceMapping sequenceMapping = SequenceMapping.COLUMN;
  private int threads = 0;
  private Trace trace = new Trace(TraceOption.MAPPING, TraceOption.MARKER, TraceOption.PERFORMANCE);

  public ColorMap getColorMap() {
    return colorMap;
  }

  public int getColumnCount() {
    return columnCount;
  }

  public String getImageBaseFilename() {
    return imageBaseFilename;
  }

  public int getImageBrightness() {
    return imageBrightness;
  }

  public String getImageCaptureProgram() {
    return imageCaptureProgram;
  }

  public String getImageLatestFilename() {
    return imageBaseFilename + ".jpg";
  }

  public String getImageOutputFilename() {
    return imageBaseFilename + ".tmp";
  }

  public int getImageRotation() {
    return imageRotation;
  }

  public int getImageSizeHeight() {
    return imageSizeHeight;
  }

  public int getImageSizeWidth() {
    return imageSizeWidth;
  }

  public WhiteBalance getImageWhiteBalance() {
    return imageWhiteBalance;
  }

  public String getImageWhiteBalanceGain() {
    return imageWhiteBalanceGain;
  }

  public int getMidiBaseVelocity() {
    return midiBaseVelocity;
  }

  public int getMidiChannel() {
    return midiChannel;
  }

  public int getMidiProgram() {
    return midiProgram;
  }

  public float getMidiTempoFactor() {
    return midiTempoFactor;
  }

  public TickOrigin getMidiTickOrigin() {
    return midiTickOrigin;
  }

  public int getObjectFuzziness() {
    return objectFuzziness;
  }

  public int getRowCount() {
    return rowCount;
  }

  public SequenceMapping getSequenceMapping() {
    return sequenceMapping;
  }

  public int getThreads() {
    return threads;
  }

  public Trace getTrace() {
    return trace;
  }

  public boolean isMidiProgramLoop() {
    return isMidiProgramLoop;
  }

  public void setColorMap(ColorMap colorMap) {
    this.colorMap = colorMap;
  }

  public void setColumnCount(int columnCount) {
    this.columnCount = columnCount;
  }

  public void setImageBaseFilename(String imageBaseFilename) {
    this.imageBaseFilename = imageBaseFilename;
  }

  public void setImageBrightness(int imageBrightness) {
    this.imageBrightness = imageBrightness;
  }

  public void setImageCaptureProgram(String imageCaptureProgram) {
    this.imageCaptureProgram = imageCaptureProgram;
  }

  public void setImageRotation(int imageRotation) {
    this.imageRotation = imageRotation;
  }

  public void setImageSizeHeight(int imageHeight) {
    this.imageSizeHeight = imageHeight;
  }

  public void setImageSizeWidth(int imageWidth) {
    this.imageSizeWidth = imageWidth;
  }

  public void setImageWhiteBalance(WhiteBalance imageWhiteBalance) {
    this.imageWhiteBalance = imageWhiteBalance;
  }

  public void setImageWhiteBalanceGain(String imageWhiteBalanceGain) {
    this.imageWhiteBalanceGain = imageWhiteBalanceGain;
  }

  public void setMidiBaseVelocity(int velocity) {
    this.midiBaseVelocity = velocity;
  }

  public void setMidiChannel(int channel) {
    this.midiChannel = channel;
  }

  public void setMidiProgram(int program) {
    this.midiProgram = program;
  }

  public void setMidiProgramLoop(boolean isMidiProgramLoop) {
    this.isMidiProgramLoop = isMidiProgramLoop;
  }

  public void setMidiTempoFactor(float tempoFactor) {
    this.midiTempoFactor = tempoFactor;
  }

  public void setMidiTickOrigin(TickOrigin tickOrigin) {
    this.midiTickOrigin = tickOrigin;
  }

  public void setObjectFuzziness(int fuzziness) {
    this.objectFuzziness = fuzziness;
  }

  public void setRowCount(int rowCount) {
    this.rowCount = rowCount;
  }

  public void setSequenceMapping(SequenceMapping sequenceMapping) {
    this.sequenceMapping = sequenceMapping;
  }

  public void setThreads(int threads) {
    this.threads = threads;
  }

  public void setTrace(Trace trace) {
    this.trace = trace;
  }

  @Override
  public String toString() {
    return "Options [colorMap=" + colorMap + ", imageBaseFilename=" + imageBaseFilename + ", imageBrightness=" + imageBrightness + ", imageCaptureProgram=" + imageCaptureProgram + ", imageRotation=" + imageRotation + ", imageSizeHeight=" + imageSizeHeight + ", imageSizeWidth=" + imageSizeWidth + ", imageWhiteBalance=" + imageWhiteBalance + ", imageWhiteBalanceGain=" + imageWhiteBalanceGain + ", isMidiProgramLoop=" + isMidiProgramLoop + ", columnCount=" + columnCount + ", rowCount=" + rowCount + ", midiBaseVelocity=" + midiBaseVelocity + ", midiChannel=" + midiChannel + ", midiProgram=" + midiProgram + ", midiTempoFactor=" + midiTempoFactor + ", midiTickOrigin=" + midiTickOrigin + ", objectFuzziness=" + objectFuzziness + ", threads=" + threads + ", trace=" + trace + "]";
  }

}
