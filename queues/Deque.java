/* *****************************************************************************
 *  Name: Yifan
 *  Date: 2022-Nov-28
 *  Description: Deque with circular array Implementation plus auto-resize
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 4;
    private Item[] elements;
    private int head = 0, tail = 0, size = 0;

    // construct an empty deque
    public Deque() {
        // ugly cast :)

        elements = (Item[]) new Object[INIT_CAPACITY];
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == elements.length;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            resize(elements.length * 2);
        }
        if (isEmpty()) {
            elements[head] = item;
        }
        else {
            head = dec(head);
            elements[head] = item;
        }

        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            resize(elements.length * 2);
        }
        if (isEmpty()) {
            elements[tail] = item;
        }
        else {
            tail = inc(tail);
            elements[tail] = item;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item e = elements[head];
        elements[head] = null;
        head = inc(head);

        size--;
        if (isEmpty()) {
            head = tail;
        }
        if (size > 0 && size == elements.length / 4) {
            resize(elements.length / 2);
        }

        return e;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item e = elements[tail];
        elements[tail] = null;
        tail = dec(tail);

        size--;
        if (isEmpty()) {
            tail = head;
        }
        if (!isEmpty() && size == elements.length / 4) {
            resize(elements.length / 2);
        }

        return e;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        public boolean hasNext() {
            return size > 0;
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item e = elements[tail--];
            size--;
            return e;

        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void resize(int newCapacity) {
        Item[] copy = (Item[]) new Object[newCapacity];
        for (int i = 0; i < size; i++)
            copy[i] = elements[(head + i) % elements.length];
        elements = copy;
        head = 0;
        tail = size - 1;
    }

    private int dec(int i) {
        return Math.floorMod(i - 1, elements.length);
    }

    private int inc(int i) {
        return (i + 1) % elements.length;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        for (int i = 1; i < 10; i++) {
            deque.addLast(i);
        }
        for (int i = 1; i < 10; i++) {
            StdOut.println("removing :" + deque.removeLast());
        }

        for (int i = 1; i < 10; i++) {
            deque.addFirst(i);
        }
        for (int i = 1; i < 5; i++) {
            StdOut.println("removing :" + deque.removeFirst());
        }

        for (Integer integer : deque) {
            System.out.println(integer);
        }

    }

}

