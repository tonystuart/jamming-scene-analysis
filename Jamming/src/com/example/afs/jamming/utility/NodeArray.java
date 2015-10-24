// Copyright 2015 Anthony F. Stuart - All rights reserved.
//
// This program and the accompanying materials are made available
// under the terms of the GNU General Public License. For other license
// options please contact the copyright owner.
//
// This program is made available on an "as is" basis, without
// warranties or conditions of any kind, either express or implied.

package com.example.afs.jamming.utility;

import java.util.Iterator;

public class NodeArray<T> implements Iterable<Node<T>> {

  public class NodeIterator implements Iterator<Node<T>> {

    private int offset;

    @Override
    public boolean hasNext() {
      return offset < nodeList.length;
    }

    @Override
    public Node<T> next() {
      return nodeList[offset++];
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

  }

  private Node<T>[] nodeList;

  @SuppressWarnings("unchecked")
  public NodeArray(Node<T> node) {
    int childNodeCount = node.getChildNodeCount();
    nodeList = new Node[childNodeCount];
    Node<T> thisNode = node.getFirstChild();
    for (int i = 0; i < childNodeCount; i++) {
      nodeList[i] = thisNode;
      thisNode = thisNode.getNextSibling();
    }
  }

  public Node<T> getNode(int index) {
    return (Node<T>) nodeList[index];
  }

  public int getNodeCount() {
    return nodeList.length;
  }

  public T getValue(int index) {
    return getNode(index).getValue();
  }

  @Override
  public Iterator<Node<T>> iterator() {
    return new NodeIterator();
  }

  public void setValue(int index, T value) {
    getNode(index).setValue(value);
  }

}
