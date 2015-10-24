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

public class Node<T> implements Iterable<Node<T>> {

  public class NodeIterator implements Iterator<Node<T>> {

    private Node<T> next;

    public NodeIterator(Node<T> head) {
      next = head;
    }

    @Override
    public boolean hasNext() {
      return next != null;
    }

    @Override
    public Node<T> next() {
      Node<T> currentNext = next;
      next = next.nextNode;
      return currentNext;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

  }

  public class ValueIterator implements Iterator<T> {

    private Iterator<Node<T>> nodeIterator;

    public ValueIterator(Iterator<Node<T>> iterator) {
      this.nodeIterator = iterator;
    }

    @Override
    public boolean hasNext() {
      return nodeIterator.hasNext();
    }

    @Override
    public T next() {
      return nodeIterator.next().getValue();
    }

  }

  protected int childNodeCount;
  protected Node<T> headNode;
  protected Node<T> nextNode;
  protected Node<T> parentNode;
  protected Node<T> previousNode;
  protected Node<T> tailNode;
  private transient NodeArray<T> nodeArray;
  private T value;

  public Node() {
  }

  public Node(T value) {
    this.value = value;
    childNodeCount = 1;
  }

  public void addChild(Node<T> node) {
    if (node.parentNode != null) {
      node.parentNode.removeChild(node);
    }
    node.parentNode = (Node<T>) this;
    node.nextNode = null;
    node.previousNode = tailNode;
    if (tailNode == null) {
      headNode = node;
    } else {
      tailNode.nextNode = node;
    }
    tailNode = node;
    childNodeCount++;
    nodeArray = null;
  }

  public void addChild(T value) {
    addChild(new Node<T>(value));
  }

  public boolean contains(Node<T> node) {
    for (Node<T> current = headNode; current != null; current = current.nextNode) {
      if (current == node) {
        return true;
      }
    }
    return false;
  }

  public int getChildNodeCount() {
    return childNodeCount;
  }

  public NodeArray<T> getChildNodes() {
    if (nodeArray == null) {
      nodeArray = new NodeArray<T>(this);
    }
    return nodeArray;
  }

  public Node<T> getFirstChild() {
    return headNode;
  }

  public Node<T> getLastChild() {
    return tailNode;
  }

  public Node<T> getNextSibling() {
    return nextNode;
  }

  public Node<T> getParentNode() {
    return parentNode;
  }

  public Node<T> getPreviousSibling() {
    return previousNode;
  }

  public T getValue() {
    return value;
  }

  public Iterable<T> getValues() {
    return new Iterable<T>() {
      @Override
      public Iterator<T> iterator() {
        return new ValueIterator(new NodeIterator(headNode));
      }
    };
  }

  @Override
  public Iterator<Node<T>> iterator() {
    return new NodeIterator(headNode);
  }

  public void removeChild(Node<T> node) {
    if (headNode == node) {
      headNode = node.nextNode;
    }
    if (tailNode == node) {
      tailNode = node.previousNode;
    }
    childNodeCount--;
    if (node.previousNode != null) {
      node.previousNode.nextNode = node.nextNode;
    }
    if (node.nextNode != null) {
      node.nextNode.previousNode = node.previousNode;
    }
  }

  public boolean removeChildIfPresent(Node<T> node) {
    boolean isContained = contains(node);
    if (isContained) {
      removeChild(node);
    }
    return isContained;
  }

  public void setValue(T value) {
    this.value = value;
  }
}
