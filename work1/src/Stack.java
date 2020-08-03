public interface Stack<AnyType> {
    void push(AnyType o);
    AnyType top();
    AnyType pop();
    int size();
    boolean empty();
}