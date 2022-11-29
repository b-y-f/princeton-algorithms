/* *****************************************************************************
 *  Name: Yifan
 *  Date: 2022-Nov-29
 *  Description: Random queue with array
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 4;
    private Item[] elements;
    private int size = 0;


    // construct an empty randomized queue
    public RandomizedQueue() {
        elements = (Item[]) new Object[INIT_CAPACITY];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == elements.length;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (isFull()) {
            resize(elements.length * 2);
        }
        elements[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();

        int randIdx = StdRandom.uniformInt(size);
        Item ele = sample(randIdx);
        elements[randIdx] = elements[size - 1];
        size--;
        if (size > 0 && size == elements.length / 4) resize(elements.length / 2);
        return ele;
    }

    // return a random item (but do not remove it)
    public Item sample(int idx) {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        return elements[idx];
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

        public boolean hasNext() {
            return size > 0;
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item ele = dequeue();
            return ele;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rqueue = new RandomizedQueue<Integer>();
        for (int i = 1; i < 20; i++) {
            rqueue.enqueue(i);
        }
        
        for (int elt : rqueue) {
            StdOut.print(elt + " ");
        }
    }

}

