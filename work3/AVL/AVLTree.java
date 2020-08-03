package AVL;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AVLTree<AnyType extends Comparable<? super AnyType>> implements Iterable<AnyType> {

    private AvlNode<AnyType> root;

    public AnyType getRoot(){
        return root.element;
    }

    public AVLTree() {
        root = null;
    }

    public void insert(AnyType x) {
        root = insert(x, root);
    }

    public void remove(AnyType x) {
        root = remove(x, root);

    }

    private AvlNode<AnyType> remove(AnyType x, AvlNode<AnyType> node) {
        if (node == null) {
            return node;
        }

        if (x.compareTo(node.element) < 0) {
            node.left = remove(x, node.left); //remove o elemento

            if (height(node.left) - height(node.right) > 1) //verirfica a difereça de alturas
                if (x.compareTo(node.left.element) < 0)
                    node = rotateWithLeftChild(node);
                else
                    node = doubleWithLeftChild(node);

        }else if (x.compareTo(node.element) > 0) {
            node.right = remove(x, node.right); //remove o elemento

            if (height(node.right) - height(node.left) > 1)
                if (x.compareTo(node.right.element) > 0) //verirfica a difereça de alturas
                    node = rotateWithRightChild(node);
                else
                    node = doubleWithRightChild(node);

        }else if (node.left != null && node.right != null){
            AnyType min = findMin(node.right).element;
            node.element = min;
            node.right = remove(min, node.right);

        }
        else if (node.left == null)
            node = node.right;

        else
            node = node.left;

        if (node != null)
            node.height = Math.max(height(node.left), height(node.right)) + 1; //re calcula  atura para cada elemento a dar return
        return node;
    }

    public AnyType findMin() {
        return findMin(root).element;
    }

    public AnyType findMax() {
        return findMax(root).element;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    public void clean() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void printTree() {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
    }

    private AvlNode<AnyType> insert(AnyType x, AvlNode<AnyType> t) {
        if (t == null)
            return new AvlNode<AnyType>(x, null, null);


        if (x.compareTo(t.element) < 0) {
            t.left = insert(x, t.left); //coloca o elemento
            if (height(t.left) - height(t.right) == 2) //verirfica a difereça de alturas
                if (x.compareTo(t.left.element) < 0)
                    t = rotateWithLeftChild(t);
                else
                    t = doubleWithLeftChild(t);
        } else if (x.compareTo(t.element) > 0) {
            t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2) //coloca o elemento
                if (x.compareTo(t.right.element) > 0) //verirfica a difereça de alturas
                    t = rotateWithRightChild(t);
                else
                    t = doubleWithRightChild(t);
        }

        t.height = Math.max(height(t.left), height(t.right)) + 1; //re calcula  atura para cada elemento a dar return
        return t;
    }

    private AvlNode<AnyType> findMin(AvlNode<AnyType> t) {
        if (t == null)
            return t;

        while (t.left != null) //e sempre oque tiver mais a esquerda
            t = t.left;
        return t;
    }

    private AvlNode<AnyType> findMax(AvlNode<AnyType> t) {
        if (t == null)
            return t;

        while (t.right != null) //e sempre oque estiver mais a direita
            t = t.right;
        return t;
    }

    private boolean contains(AnyType x, AvlNode<AnyType> t) {
        while (t != null) {
            if (x.compareTo(t.element) < 0)
                t = t.left;
            else if (x.compareTo(t.element) > 0)
                t = t.right;
            else
                return true;    // iguais
        }

        return false;   // Nao existe
    }

    private void printTree(AvlNode<AnyType> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    public void printPreordem() {
        printPreordem(root);
    }

    private void printPreordem(AvlNode<AnyType> t) {
        if (t != null) {
            System.out.println(t.element);
            printTree(t.left);
            printTree(t.right);
        }
    }

    public void printPosordem() {
        printPosordem(root);
    }

    private void printPosordem(AvlNode<AnyType> t) {
        if (t != null) {
            printTree(t.left);
            printTree(t.right);
            System.out.println(t.element);
        }
    }

    private int height(AvlNode<AnyType> t) {
        if (t == null) {
            return -1;
        }

        return t.height; //devolve a altura do no a contar do fim calculada durante a inserçao
    }

    //roda o elemento introduzido um avez para a esquerda
    private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2) {
        AvlNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    //roda o elemento introduzido um avez para a direita
    private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k1) {
        AvlNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2;
    }

    //dupla rotacao para a esquerda (se for esq esq)
    private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    //dupla rotacao para a direita (se for dir dir)
    private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    public Iterator<AnyType> iterator() {
        return new AVLIterator<>(root);
    }

    public class AVLIterator<AnyType> implements Iterator<AnyType> {

        AvlNode<AnyType> currentNode;
        ArrayStack<AvlNode<AnyType>> choiceNodes;

        public AVLIterator(AvlNode<AnyType> node) {
            currentNode = node;
            choiceNodes = new ArrayStack<>();
        }

        public boolean hasNext() {
            return currentNode != null || choiceNodes.empty();
        }

        public AnyType next() {
            if (!hasNext())
                throw new NoSuchElementException();

            AnyType toReturn = currentNode.element;

            if (currentNode.right != null)
                choiceNodes.push(currentNode.right);

            if (currentNode.left != null)
                currentNode = currentNode.left;

            else{
                if (!choiceNodes.empty())
                    currentNode = choiceNodes.pop();
                else
                    currentNode = null;
            }

            return toReturn;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class ArrayStack<AnyType> {

        private AnyType[] anyTypes;
        private int topPos = 0;
        private int size;

        public ArrayStack() {
            anyTypes = (AnyType[]) new Object[10];
            size = 10;
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

    private static class AvlNode<AnyType> {
        AnyType element;      // sera o contacto
        AvlNode<AnyType> left;         // no esquerdo
        AvlNode<AnyType> right;        // no direito
        int height;       // altura

        // Constructors
        AvlNode(AnyType theElement) {
            this(theElement, null, null);
        }

        AvlNode(AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt) {

            element = theElement;
            left = lt;
            right = rt;
            height = 0;
        }


    }

}
