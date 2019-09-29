import java.util.EmptyStackException;
import java.util.Stack;

public class MyStack<E> extends Stack<E> {
    private MyArrayList<E> arrayList;

    MyStack() {
        arrayList = new MyArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    @Override
    public E push(E element) {
        arrayList.add(element);
        return element;
    }

    @Override
    public E pop() {
        E element = this.peek();
        arrayList.remove(arrayList.size() - 1);
        return element;
    }

    @Override
    public E peek() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return arrayList.get(arrayList.size() - 1);
    }
}
