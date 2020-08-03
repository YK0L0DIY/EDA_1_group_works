public class ArrayStack<AnyType> implements Stack<AnyType> {

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

    @Override
    public void push(AnyType o) {
        if (topPos == size)
            throw new RuntimeException("above the size");
        anyTypes[topPos] = o;
        topPos++;
    }

    @Override
    public AnyType top() {
        if (topPos == 0)
            return anyTypes[topPos];
        else
            return anyTypes[topPos - 1];
    }

    @Override
    public AnyType pop() {
        if (size() == 0)
            throw new RuntimeException("nothing to remove");
        topPos--;
        return anyTypes[topPos];
    }

    @Override
    public int size() {
        return topPos;
    }

    @Override
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