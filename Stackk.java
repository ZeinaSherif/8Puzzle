
package puzzleproject;

import java.util.Iterator;
import java.util.NoSuchElementException;


//This are movement types that we do with a tile

public class Stackk <Item> implements Iterable<Item> {
    
    private Node<Item> first;    // beginning of stack
    private Node<Item> last;     // end of stack
    private int N;               // number of elements on stack
 private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
 public Stackk() {
        first = null;
        last = null;
        N = 0;
    }

    public void clear() {
        first = null;
        last = null;
         N= 0;
    }

    /**
     * Returns true if this stack is empty.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this stack.
     */
    public int size() {
        return N;
    }

    /**
     * Returns the item least recently added to this stack.
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }

    /**
     * Adds the item to this stack.
     */
    public void push(Item item) {

        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    /**
     * Removes and returns the item on this stack that was least recently added.
     */
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;   
        return item;
    }


    /**
     * Returns an iterator that iterates over the items in this stack in FILO order.
     */
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }


    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }



    /**
     * add a stack to the beginning of the current stack
     */
    public void addStack(Stackk<Item> stack) {
        if (!stack.isEmpty()) {


            Node<Item> oldFirst = first;

            if (isEmpty()) {
                first = stack.first;
                last = stack.last;
            } else {
                first = stack.first;
                stack.last.next = oldFirst;
            }

            N = N + stack.size();
        }

    }}
    
    
    
    
    

