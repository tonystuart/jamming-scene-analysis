// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package com.example.afs.jamming.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

// raspistill Camera App v1.3.8
// 
// Runs camera for specific time, and take JPG capture at end if requested
// 
// usage: raspistill [options]
// 
// Image parameter commands
// 
// -?, --help  : This help information
// -w, --width : Set image width <size>
// -h, --height  : Set image height <size>
// -q, --quality : Set jpeg quality <0 to 100>
// -r, --raw : Add raw bayer data to jpeg metadata
// -o, --output  : Output filename <filename> (to write to stdout, use '-o -'). If not specified, no file is saved
// -l, --latest  : Link latest complete image to filename <filename>
// -v, --verbose : Output verbose information during run
// -t, --timeout : Time (in ms) before takes picture and shuts down (if not specified, set to 5s)
// -th, --thumb  : Set thumbnail parameters (x:y:quality) or none
// -d, --demo  : Run a demo mode (cycle through range of camera options, no capture)
// -e, --encoding  : Encoding to use for output file (jpg, bmp, gif, png)
// -x, --exif  : EXIF tag to apply to captures (format as 'key=value') or none
// -tl, --timelapse  : Timelapse mode. Takes a picture every <t>ms
// -fp, --fullpreview  : Run the preview using the still capture resolution (may reduce preview fps)
// -k, --keypress  : Wait between captures for a ENTER, X then ENTER to exit
// -s, --signal  : Wait between captures for a SIGUSR1 from another process
// -g, --gl  : Draw preview to texture instead of using video render component
// -gc, --glcapture  : Capture the GL frame-buffer instead of the camera image
// -set, --settings  : Retrieve camera settings and write to stdout
// -cs, --camselect  : Select camera <number>. Default 0
// -bm, --burst  : Enable 'burst capture mode'
// -md, --mode : Force sensor mode. 0=auto. See docs for other modes available
// 
// Preview parameter commands
// 
// -p, --preview : Preview window settings <'x,y,w,h'>
// -f, --fullscreen  : Fullscreen preview mode
// -op, --opacity  : Preview window opacity (0-255)
// -n, --nopreview : Do not display a preview window
// 
// Image parameter commands
// 
// -sh, --sharpness  : Set image sharpness (-100 to 100)
// -co, --contrast : Set image contrast (-100 to 100)
// -br, --brightness : Set image brightness (0 to 100)
// -sa, --saturation : Set image saturation (-100 to 100)
// -ISO, --ISO : Set capture ISO
// -vs, --vstab  : Turn on video stabilisation
// -ev, --ev : Set EV compensation
// -ex, --exposure : Set exposure mode (see Notes)
// -awb, --awb : Set AWB mode (see Notes)
// -ifx, --imxfx : Set image effect (see Notes)
// -cfx, --colfx : Set colour effect (U:V)
// -mm, --metering : Set metering mode (see Notes)
// -rot, --rotation  : Set image rotation (0-359)
// -hf, --hflip  : Set horizontal flip
// -vf, --vflip  : Set vertical flip
// -roi, --roi : Set region of interest (x,y,w,d as normalised coordinates [0.0-1.0])
// -ss, --shutter  : Set shutter speed in microseconds
// -awbg, --awbgains : Set AWB gains - AWB mode must be off
// -drc, --drc : Set DRC Level
// -st, --stats  : Force recomputation of statistics on stills capture pass
// -a, --annotate  : Enable/Set annotate flags or text
// 
// 
// Notes
// 
// Exposure mode options :
// auto,night,nightpreview,backlight,spotlight,sports,snow,beach,verylong,fixedfps,antishake,fireworks
// 
// AWB mode options :
// off,auto,sun,cloud,shade,tungsten,fluorescent,incandescent,flash,horizon
// 
// Image Effect mode options :
// none,negative,solarise,sketch,denoise,emboss,oilpaint,hatch,gpen,pastel,watercolour,film,blur,saturation,colourswap,washedout,posterise,colourpoint,colourbalance,cartoon
// 
// Metering Mode options :
// average,spot,backlit,matrix
// 
// Dynamic Range Compression (DRC) options :
// off,low,med,high
// 
// Preview parameter commands
// 
// -gs, --glscene  : GL scene square,teapot,mirror,yuv,sobel
// -gw, --glwin  : GL window settings <'x,y,w,h'>
//
// raspistill -w 1024 -h 768 -br 30 -tl 1000 -t 21600000 -n -o Jamming.tmp -l Jamming.jpg

public class Raspistill {

  public static class RaspistillBuilder {

    private List<String> command = new LinkedList<String>();
    private boolean isVerbose;

    public RaspistillBuilder(String imageCaptureProgram) {
      command.add(imageCaptureProgram);
    }

    public Raspistill build() {
      try {
        setTimeout(0); // run until terminated
        setKeypress();
        setPreview();
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream();
        Process process = processBuilder.start();
        if (isVerbose) {
          new OutputLogger(process).start();
        }
        Raspistill raspistill = new Raspistill(process);
        return raspistill;
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    public RaspistillBuilder setBrightness(int brightness) {
      command.add("--brightness");
      command.add(Integer.toString(brightness));
      return this;
    }

    public RaspistillBuilder setHeight(int height) {
      command.add("--height");
      command.add(Integer.toString(height));
      return this;
    }

    public RaspistillBuilder setImageWhiteBalance(WhiteBalance whiteBalance) {
      command.add("--awb");
      command.add(whiteBalance.name());
      return this;
    }

    public RaspistillBuilder setImageWhiteBalanceGain(String whiteBalanceGain) {
      command.add("--awbgains");
      command.add(whiteBalanceGain);
      return this;
    }

    public RaspistillBuilder setKeypress() {
      command.add("--keypress");
      return this;
    }

    public RaspistillBuilder setLatestFilename(String latestFilename) {
      command.add("--latest");
      command.add(latestFilename);
      return this;
    }

    public RaspistillBuilder setOutputFilename(String outputFilename) {
      command.add("--output");
      command.add(outputFilename);
      return this;
    }

    public RaspistillBuilder setPreview() {
      command.add("--nopreview");
      return this;
    }

    public RaspistillBuilder setRotation(int rotation) {
      command.add("--rotation");
      command.add(Integer.toString(rotation));
      return this;
    }

    public RaspistillBuilder setTimeout(int timeout) {
      command.add("--timeout");
      command.add(Integer.toString(timeout));
      return this;
    }

    public RaspistillBuilder setVerbose(boolean isVerbose) {
      this.isVerbose = isVerbose;
      return this;
    }

    public RaspistillBuilder setWidth(int width) {
      command.add("--width");
      command.add(Integer.toString(width));
      return this;
    }
  }

  public enum WhiteBalance {
    auto, cloud, flash, fluorescent, horizon, incandescent, off, shade, sun, tungsten
  }

  private static class OutputLogger extends Thread {
    private Process process;

    public OutputLogger(Process process) {
      this.process = process;
    }

    @Override
    public void run() {
      try {
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while ((line = bufferedReader.readLine()) != null) {
          System.err.println(line);
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private Process process;

  protected Raspistill(Process process) {
    this.process = process;
  }

  public void takePhoto() {
    try {
      sendCommand("\n");
    } catch (IOException e) {
      if (!process.isAlive()) {
        throw new IllegalStateException("The image capture program terminated with a status of " + process.exitValue());
      }
      throw new RuntimeException(e);
    }
  }

  public void terminate() {
    try {
      System.out.println("Terminating image capture program");
      sendCommand("X\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void sendCommand(String message) throws IOException {
    process.getOutputStream().write(message.getBytes());
    process.getOutputStream().flush();
  }
}
