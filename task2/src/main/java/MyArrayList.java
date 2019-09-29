import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class MyArrayList<E> implements List<E> {
    private Object[] array;
    private int currentMaxSize;
    private int size;
    private static final int maxArraySize = Integer.MAX_VALUE - 1;

    MyArrayList() {
        currentMaxSize = 10;
        size = 0;
        array = new Object[0];
    }

    MyArrayList(Object[] array) {
        size = array.length;
        currentMaxSize = size * 3;
        this.array = Arrays.copyOf(array, currentMaxSize);
    }

    private void increaseArraySize() {
        if (size == 0) {
            array = Arrays.copyOf(array, currentMaxSize);
            return;
        }
        if (size * 2 >= currentMaxSize) {
            if (size * 2 < maxArraySize) {
                currentMaxSize *= 3;
            } else {
                currentMaxSize *= maxArraySize;
            }
            array = Arrays.copyOf(array, currentMaxSize);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object element : array) {
            if (element.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public boolean add(E e) {
        increaseArraySize();
        array[size] = e;
        ++size;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public void sort(Comparator<? super E> c) {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public void clear() {
        currentMaxSize = 10;
        size = 0;
        array = new Object[0];
    }

    @Override
    public E get(int i) {
        if (i > size)
            throw new IndexOutOfBoundsException("Out of bounds");
        return (E) array[i];
    }

    @Override
    public E set(int i, E e) {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public void add(int i, E e) {
        if (i > size)
            throw new IndexOutOfBoundsException("Out of bounds");
        increaseArraySize();
        for (int j = size - 1; j >= i; --j) {
            array[j + 1] = array[j];
        }
        array[i] = e;
        ++size;
    }

    @Override
    public E remove(int i) {
        if (i >= size)
            throw new IndexOutOfBoundsException("Out of bounds");
        for (int j = i; j < size; ++j) {
            array[j] = array[j + 1];
        }
        --size;
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (int i = 0; i < size(); ++i) {
            stringBuilder.append(array[i]);
            stringBuilder.append(',');
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public List<E> subList(int i, int i1) {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public Spliterator<E> spliterator() {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public Stream<E> stream() {
        throw new UnsupportedMethod("Unsupported operation with array");
    }

    @Override
    public Stream<E> parallelStream() {
        throw new UnsupportedMethod("Unsupported operation with array");
    }
}
