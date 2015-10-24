// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package sequencemapper;

import com.example.afs.jamming.image.Block;

public class SequencedBlock {
  private int begin;
  private Block block;
  private int column;
  private int end;
  private int row;

  public SequencedBlock(Block block, int row, int column, int begin, int end) {
    this.block = block;
    this.row = row;
    this.column = column;
    this.begin = begin;
    this.end = end;
  }

  public int getBegin() {
    return begin;
  }

  public Block getBlock() {
    return block;
  }

  public int getColumn() {
    return column;
  }

  public int getEnd() {
    return end;
  }

  public int getRow() {
    return row;
  }

  @Override
  public String toString() {
    return "SequencedBlock [block=" + block + ", row=" + row + ", column=" + column + ", begin=" + begin + ", end=" + end + "]";
  }

}