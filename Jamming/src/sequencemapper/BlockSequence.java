// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package sequencemapper;

import com.example.afs.jamming.utility.Node;

public class BlockSequence {

  private float maximumBrightness;
  private Node<SequencedBlock> sequencedBlocks;
  private int totalDuration;

  public BlockSequence(Node<SequencedBlock> sequencedBlocks, int totalDuration, float maximumBrightness) {
    this.sequencedBlocks = sequencedBlocks;
    this.totalDuration = totalDuration;
    this.maximumBrightness = maximumBrightness;
  }

  public float getMaximumBrightness() {
    return maximumBrightness;
  }

  public Node<SequencedBlock> getSequencedBlocks() {
    return sequencedBlocks;
  }

  public int getTotalDuration() {
    return totalDuration;
  }

  @Override
  public String toString() {
    return "BlockSequence [totalDuration=" + totalDuration + ", maximumBrightness=" + maximumBrightness + ", sequencedBlocks=" + sequencedBlocks + "]";
  }

}