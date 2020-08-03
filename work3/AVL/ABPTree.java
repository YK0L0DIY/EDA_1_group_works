package AVL;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ABPTree<AnyType extends Comparable<? super AnyType>> implements Iterable<AnyType> {

    ABPNode<AnyType> root;

    public ABPTree(AnyType x) {
        root = new ABPNode(x);
    }

    public ABPTree(ABPNode node) {
        root = node;
    }

    public ABPTree() {
        root = null;
    }

    public ABPTree(AnyType node, ABPNode esq, ABPNode dir) {
        root = new ABPNode(node, esq, dir);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void print() {
        print(root, 0);
    }

    private void print(ABPNode node, int tab) {
        if (node != null) {
            System.out.println(ntabs(tab) + node);
            print(node.esq, tab + 1);
            print(node.dir, tab + 1);
        }
    }

    private String ntabs(int tab) {
        String result = "";
        for (int i = 0; i < tab; i++)
            result += "\t";
        return result;
    }

    public AnyType findMin() {
        if (isEmpty())
            return null;
        return findMin(root);
    }

    private AnyType findMin(ABPNode<AnyType> node) {
        if (node.esq == null)
            return node.element;
        else
            return findMin(node.esq);
    }

    public boolean contains(AnyType x, ABPNode<AnyType> node) {
        if (node == null)
            return false;
        else {
            if (node.element.compareTo(x) < 0)
                return contains(x, node.dir);
            else if (node.element.compareTo(x) > 0)
                return contains(x, node.esq);
            else
                return true;
        }
    }

    public void insert(AnyType x) {
        root = insert(x, root);
    }

    private ABPNode<AnyType> insert(AnyType x, ABPNode<AnyType> node) {
        if (node == null)
            node = new ABPNode<AnyType>(x, null, null);
        else if ((node.element).compareTo(x) > 0)
            node.esq = insert(x, node.esq);
        else if ((node.element).compareTo(x) < 0)
            node.dir = insert(x, node.dir);

        return node;
    }

    public void remove(AnyType x) {
        root = remove(x, root);
    }

    private ABPNode<AnyType> remove(AnyType x, ABPNode<AnyType> node) {
        if (node == null)
            return node;

        if (node.element.compareTo(x) < 0)
            node.dir = remove(x, node.dir);

        else if (node.element.compareTo(x) > 0)
            node.esq = remove(x, node.esq);

        else if (node.esq != null && node.dir != null) {
            AnyType min = findMin(node.dir);
            node.element = min;
            node.dir = remove(min, node.dir);
        } else if (node.esq == null)
            node = node.dir;
        else
            node = node.esq;

        return node;
    }

    public Iterator<AnyType> iterator() {
        return new ABPIterator<>(root);
    }

    //INNER CLASSES
    public static class ABPNode<AnyType> {
        AnyType element;
        ABPNode<AnyType> esq;
        ABPNode<AnyType> dir;

        public ABPNode(AnyType x) {
            element = x;
            esq = null;
            dir = null;
        }

        public ABPNode(AnyType x, ABPNode esq, ABPNode dir) {
            element = x;
            this.esq = esq;
            this.dir = dir;
        }

        public String toString() {
            return element.toString();
        }
    }

    private class ArrayStack<AnyType> {

        private AnyType[] anyTypes;
        private int topPos = 0;
        private int size;

        public ArrayStack() {
            anyTypes = (AnyType[]) new Object[10];
        }

        public ArrayStack(int size) {
            anyTypes = (AnyType[]) new Object[size];
            this.size = size;
        }

        public void push(AnyType o) {
            if (topPos == size)
                throw new RuntimeException("above the size");
            anyTypes[topPos] = o;
            topPos++;
        }

        public AnyType top() {
            if (topPos == 0)
                return anyTypes[topPos];
            else
                return anyTypes[topPos - 1];
        }

        public AnyType pop() {
            if (size() == 0)
                throw new RuntimeException("nothing to remove");
            topPos--;
            return anyTypes[topPos];
        }

        public int size() {
            return topPos;
        }

        public boolean empty() {
            return topPos == 0;
        }

        public String toString() {
            String finalText = "";

            for (int i = 0; i < topPos; i++) {
                finalText += anyTypes[i].toString() + " ";
            }

            return finalText;
        }
    }

    //Pre-ordem
    public class ABPIterator<AnyType> implements Iterator<AnyType> {

        ABPNode<AnyType> currentNode;
        ArrayStack<ABPNode<AnyType>> choiceNodes;

        public ABPIterator(ABPNode<AnyType> node) {
            currentNode = node;
            choiceNodes = new ArrayStack<>();
        }

        public boolean hasNext() {
            return currentNode == null;
        }

        public AnyType next() {
            if (!hasNext())
                throw new NoSuchElementException();

            AnyType toReturn = currentNode.element;

            if (currentNode.dir != null)
                currentNode = currentNode.esq;

            else if (!choiceNodes.empty())
                currentNode = choiceNodes.pop();
            else
                currentNode = null;

            return toReturn;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
