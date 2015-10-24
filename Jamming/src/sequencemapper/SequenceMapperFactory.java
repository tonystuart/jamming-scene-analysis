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

public class SequenceMapperFactory {

  public enum SequenceMapping {
    COLUMN, ROW
  }

  public static SequenceMapper getSequenceMapper(Options options) {
    SequenceMapper sequenceMapper;
    switch (options.getSequenceMapping()) {
      case COLUMN:
        sequenceMapper = new ColumnMapper(options);
        break;
      case ROW:
        sequenceMapper = new RowMapper(options);
        break;
      default:
        throw new UnsupportedOperationException(options.getSequenceMapping().toString());
    }
    return sequenceMapper;
  }
}