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
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.afs.jamming.command.Options;
import com.example.afs.jamming.utility.Node;

public class ImageProcessor {

  private int columnCount;
  private BufferedImage image;
  private int imageHeight;
  private int imageWidth;
  private Options options;
  private int rowCount;

  public ImageProcessor(BufferedImage image, Options options) {
    this.image = image;
    this.options = options;
    imageWidth = image.getWidth();
    imageHeight = image.getHeight();
    columnCount = options.getColumnCount();
    rowCount = options.getRowCount();
    if (rowCount > imageHeight) {
      throw new IllegalArgumentException("Expected more rowCount to be less than imageHeight");
    }
  }

  public Node<Item> extractItems() {
    try {
      int threadCount = options.getThreads();
      Node<Item> items;
      if (threadCount == 0) {
        threadCount = Runtime.getRuntime().availableProcessors();
      }
      TimeKeeper timeKeeper = new TimeKeeper(image, threadCount, options);
      if (threadCount == 1) {
        items = extractSerial();
      } else {
        items = extractParallel(threadCount);
      }
      timeKeeper.report();
      return items;
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private Node<Item> extractParallel(int threadCount) throws InterruptedException {
    List<ItemFinderTask> itemFinderTasks = map(threadCount);
    ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);
    threadPool.invokeAll(itemFinderTasks);
    threadPool.shutdown();
    Node<Item> items = reduce(itemFinderTasks);
    return items;
  }

  private Node<Item> extractSerial() {
    Node<Item> items = new ItemFinder().findItems(image, 0, imageHeight, 0, imageWidth, rowCount, columnCount);
    return items;
  }

  private List<ItemFinderTask> map(int threadCount) {
    List<ItemFinderTask> itemFinderTasks = new LinkedList<>();
    if (threadCount > rowCount) {
      threadCount = rowCount;
    }
    int rowCountRemaining = rowCount;
    int imageHeightRemaining = imageHeight;
    int finalThread = threadCount - 1;
    int imageRowsPerItemRow = imageHeight / rowCount;
    int rowCountPerTask = rowCountRemaining / threadCount;
    int imageHeightPerTask = rowCountPerTask * imageRowsPerItemRow;
    int taskRowCount = rowCountPerTask;
    int taskImageHeight = imageHeightPerTask;
    int taskImageTop = 0;

    for (int i = 0; i < threadCount; i++) {
      if (i == finalThread) {
        taskRowCount = rowCountRemaining;
        taskImageHeight = imageHeightRemaining;
      }
      int taskImageBottom = taskImageTop + taskImageHeight;
      itemFinderTasks.add(new ItemFinderTask(image, taskImageTop, taskImageBottom, 0, imageWidth, taskRowCount, columnCount));
      rowCountRemaining -= taskRowCount;
      imageHeightRemaining -= taskImageHeight;
      taskImageTop = taskImageBottom;
    }

    return itemFinderTasks;
  }

  private Node<Item> reduce(List<ItemFinderTask> itemFinderTasks) {
    Node<Item> items = new Node<>();
    for (ItemFinderTask itemFinderTask : itemFinderTasks) {
      Node<Item> taskItems = itemFinderTask.getItems();
      for (Item item : taskItems.getValues()) {
        items.addChild(item);
      }
    }
    return items;
  }

}
