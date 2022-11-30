/* *****************************************************************************
 *  Name: Yifan
 *  Date: 2022-Nov-29
 *  Description: Random queue with array
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 4;
    private Item[] elements;
    private int size = 0;
    private int randIdx;


    // construct an empty randomized queue
    public RandomizedQueue() {
        elements = (Item[]) new Object[INIT_CAPACITY];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == elements.length;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            resize(elements.length * 2);
        }
        elements[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();

        Item ele = sample();
        elements[randIdx] = elements[size - 1];
        elements[size - 1] = null;
        size--;
        if (size > 0 && size == elements.length / 4) resize(elements.length / 2);
        return ele;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        randIdx = StdRandom.uniformInt(size);
        return elements[randIdx];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            copy[i] = elements[i];
        elements = copy;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        private int index;
        private Item[] cp;

        public ReverseArrayIterator() {
            cp = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                cp[i] = elements[i];
            }
            StdRandom.shuffle(cp);
        }

        public boolean hasNext() {
            return index < size;
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return cp[index++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}

