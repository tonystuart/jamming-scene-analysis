// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package sequencemapper;

import com.example.afs.jamming.command.Options;
import com.example.afs.jamming.image.Block;
import com.example.afs.jamming.utility.Node;
import com.example.afs.jamming.utility.NodeArray;

public class RowMapper implements SequenceMapper {

  private Options options;

  public RowMapper(Options options) {
    this.options = options;
  }

  @Override
  public BlockSequence mapBlocks(Node<Block> blocks, int width, int height) {
    int begin = 0;
    float maximumBrightness = 0;
    NodeArray<Block> childNodes = blocks.getChildNodes();
    Node<SequencedBlock> sequencedBlocks = new Node<>();
    int columnCount = options.getColumnCount();
    int rowCount = options.getRowCount();
    for (int row = 0; row < rowCount; row++) {
      for (int column = 0; column < columnCount; column++) {
        int index = row * columnCount + column; // items are always stored in row major order
        Block block = childNodes.getValue(index);
        maximumBrightness = Math.max(maximumBrightness, block.getItem().getColor().getBrightness());
        int end = begin + block.getItem().getWidth();
        sequencedBlocks.addChild(new SequencedBlock(block, row, column, begin, end));
        begin = end;
      }
    }
    return new BlockSequence(sequencedBlocks, begin, maximumBrightness);
  }

}