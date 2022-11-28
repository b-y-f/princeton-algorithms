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

    public boolean isFull() {
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
        if (size > 0 && size == elements.length / 4) resize(elements.length / 2);
        if (isEmpty()) {
            tail = head;
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
        if (size > 0 && size == elements.length / 4) resize(elements.length / 2);
        if (isEmpty()) {
            head = tail;
        }

        return e;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return null;
    }

    private void resize(int newCapacity) {
        // if (newCapacity < elements.length) System.out.println("size shrinked" + newCapacity);
        Item[] copy = (Item[]) new Object[newCapacity];
        for (int i = 0; i < size; i++)
            copy[i] = elements[(head + i) % size];
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
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);
        deque.addFirst(7);
        deque.addFirst(8);
        deque.addFirst(9);
        StdOut.println("Size:" + deque.size());
        StdOut.println("removing removeLast:" + deque.removeLast());
        StdOut.println("removing removeLast:" + deque.removeLast());
        StdOut.println("removing removeLast:" + deque.removeLast());
        StdOut.println("removing removeLast:" + deque.removeLast());
        StdOut.println("removing removeLast:" + deque.removeLast());
        StdOut.println("removing removeLast:" + deque.removeLast());
        StdOut.println("removing removeLast:" + deque.removeLast());
    }

}

