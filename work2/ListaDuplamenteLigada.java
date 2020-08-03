package Trab2;

import java.util.Iterator;

public class ListaDuplamenteLigada<AnyType> implements Iterable<AnyType> {
    Nodulo<AnyType> firsElement = new Nodulo<>();
    Nodulo<AnyType> lastElement = new Nodulo<>();

    private int size = 0;

    public ListaDuplamenteLigada() {
        firsElement.setNext(lastElement);
        lastElement.setPrevious(firsElement);
    }

    public Iterator iterator() {
        return new ListaDuplamenteLigadaIterator<AnyType>(firsElement);

    }

    public void add(AnyType newnode) {
        Nodulo<AnyType> x = new Nodulo(newnode);
        Nodulo<AnyType> last = lastElement.getPrevious();
        last.setNext(x);
        x.setPrevious(last);
        x.setNext(lastElement);
        lastElement.setPrevious(x);
        size++;

    }

    public void add(int i, AnyType x) {
        Nodulo<AnyType> newNode = new Nodulo<>(x);

        if (i > size) {
            return;

        } else if (size == 0) {
            //System.out.println("SIZE = 0");
            newNode.setPrevious(firsElement);
            newNode.setNext(lastElement);
            firsElement.setNext(newNode);
            lastElement.setPrevious(newNode);

        } else if (i == 0) {
            //System.out.println("I = 0");
            newNode.setNext(firsElement.getNext());
            firsElement.getNext().setPrevious(newNode);
            newNode.setPrevious(firsElement);
            firsElement.setNext(newNode);

        } else if (i == size) {
            //System.out.println("I = SIZE" + size);
            newNode.setNext(lastElement);
            newNode.setPrevious(lastElement.getPrevious());
            lastElement.getPrevious().setNext(newNode);
            lastElement.setPrevious(newNode);

        } else {
            Nodulo<AnyType> myNode = getNode(i);
            Nodulo<AnyType> toAdd = new Nodulo<>(myNode.getPrevious(), x, myNode);
            myNode.getPrevious().setNext(toAdd);
            myNode.setPrevious(toAdd);

        }
        size++;
    }

    public void set(int i, AnyType x) {
        Nodulo<AnyType> toeEdit = getNode(i);
        toeEdit.setElement(x);
    }

    public AnyType remove(int i) {
        size--;
        Nodulo<AnyType> toShow = getNode(i);
        toShow.getPrevious().setNext(toShow.getNext());
        toShow.getNext().setPrevious(toShow.getPrevious());

        return null;
    }

    public void remove(AnyType x) {
        Nodulo<AnyType> toRemove = firsElement;

        while (toRemove.getElement() != x) {
            toRemove = toRemove.getNext();
        }

        toRemove.getNext().setPrevious(toRemove.getPrevious());
        toRemove.getPrevious().setNext(toRemove.getNext());
        size--;
    }

    public void clear() {
        size = 0;
        firsElement.setNext(lastElement);
        lastElement.setPrevious(firsElement);
    }

    public AnyType get(int i) {
        int number = 0;
        Nodulo<AnyType> toIterate = firsElement;
        while (number != i) {
            number++;
            toIterate = toIterate.getNext();

        }
        return toIterate.getElement();
    }

    private Nodulo<AnyType> getNode(int i) {
        int number = 0;
        Nodulo<AnyType> toIterate = firsElement;
        while (number != i) {
            number++;
            toIterate = toIterate.getNext();

        }
        return toIterate;
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (AnyType type : this) {
            if (type != null)
                sb.append(type + "\n");
        }
        return sb.toString();
    }

    /*
    //Iterator class
    */
    private class ListaDuplamenteLigadaIterator<AnyType> implements Iterator<AnyType> {

        Nodulo<AnyType> element;

        private ListaDuplamenteLigadaIterator(Nodulo<AnyType> x) {
            element = x;
        }

        public boolean hasNext() {
            return element.getNext() != null;
        }

        public boolean hasPrevious() {
            return element.getPrevious() != null;
        }

        public AnyType next() {
            if (hasNext()) {
                AnyType toReturn = element.getElement();
                element = element.getNext();
                return toReturn;
            }
            return null;
        }

        public AnyType previous() {
            if (hasPrevious()) {
                AnyType toReturn = element.getElement();
                element = element.getPrevious();
                return toReturn;
            }
            return null;
        }
    }

    /*
    //Nodulo calss
     */
    public static class Nodulo<AnyType> {

        private Nodulo<AnyType> previous;
        private AnyType element;
        private Nodulo<AnyType> next;

        public Nodulo(Nodulo previous, AnyType element, Nodulo next) {
            this.previous = previous;
            this.element = element;
            this.next = next;
        }

        public Nodulo() {
            this.previous = null;
            this.element = null;
            this.next = null;
        }

        public Nodulo(AnyType element) {
            this.previous = null;
            this.element = element;
            this.next = null;
        }

        public Nodulo(AnyType element, Nodulo next) {
            this.previous = null;
            this.element = element;
            this.next = next;
        }

        public Nodulo(Nodulo previous, AnyType element) {
            this.previous = previous;
            this.element = element;
            this.next = null;
        }

        public boolean hasNext() {
            return this.next != null;
        }

        public boolean hasPrevious() {
            return this.previous != null;
        }

        public void setNext(Nodulo next) {
            this.next = next;
        }

        public void setPrevious(Nodulo previous) {
            this.previous = previous;
        }

        public void setElement(AnyType element) {
            this.element = element;
        }

        public Nodulo getNext() {
            return next;
        }

        public AnyType getElement() {
            return element;
        }

        public Nodulo getPrevious() {
            return previous;
        }
    }
}
