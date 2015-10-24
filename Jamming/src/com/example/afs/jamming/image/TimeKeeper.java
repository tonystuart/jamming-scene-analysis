// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package com.example.afs.jamming.image;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import com.example.afs.jamming.command.Options;
import com.example.afs.jamming.command.Trace.TraceOption;

public class TimeKeeper {
  private BufferedImage image;
  private Options options;
  private long startTimeNs;
  private int threadCount;

  public TimeKeeper(BufferedImage image, int threadCount, Options options) {
    this.image = image;
    this.threadCount = threadCount;
    this.options = options;
    if (options.getTrace().isSet(TraceOption.PERFORMANCE)) {
      startTimeNs = System.nanoTime();
    }
  }

  public void report() {
    if (options.getTrace().isSet(TraceOption.PERFORMANCE)) {
      long finishTimeNs = System.nanoTime();
      long elapsedTimeNs = finishTimeNs - startTimeNs;
      long elapsedTimeMs = TimeUnit.NANOSECONDS.toMillis(elapsedTimeNs);
      long pixelCount = (long) image.getWidth() * (long) image.getHeight();
      System.out.println("Processed " + pixelCount + " pixels in " + elapsedTimeMs + " milliseconds using " + threadCount + " thread(s)");
    }
  }
}