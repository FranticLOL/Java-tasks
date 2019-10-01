import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class MyArrayList<E> implements List<E> {
    private Object[] array;
    private int currentMaxSize;
    private int size;
    private static final int MAX_ARRAY_SIZE = (int) Math.pow(2, 16) - 1;
    private static final int STARTED_MAX_SIZE = 8;
    private static final int INCREASES_ARRAY_SIZE_VALUE = 2;

    MyArrayList() {
        initArrayList();
    }

    MyArrayList(Object[] array) {
        size = array.length;
        currentMaxSize = size * INCREASES_ARRAY_SIZE_VALUE;
        this.array = Arrays.copyOf(array, currentMaxSize);
    }

    private void initArrayList() {
        currentMaxSize = STARTED_MAX_SIZE;
        size = 0;
        array = new Object[0];
    }

    private boolean tryIncreaseArraySize() {
        if (size == 0) {
            array = Arrays.copyOf(array, currentMaxSize);
            return true;
        }
        if (size >= MAX_ARRAY_SIZE) {
            return false;
        }
        if (size < currentMaxSize) {
            return true;
        }
        if (size >= currentMaxSize) {
            if (size * INCREASES_ARRAY_SIZE_VALUE < MAX_ARRAY_SIZE) {
                currentMaxSize *= INCREASES_ARRAY_SIZE_VALUE;
            } else {
                currentMaxSize = MAX_ARRAY_SIZE;
            }
            array = Arrays.copyOf(array, currentMaxSize);
            return true;
        }
        return false;
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
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public boolean add(E e) {
        if (!tryIncreaseArraySize()) {
            throw new IndexOutOfBoundsException("Array overflow");
        }
        array[size] = e;
        ++size;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public void sort(Comparator<? super E> c) {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public void clear() {
        initArrayList();
    }

    @Override
    public E get(int i) {
        if (i > size || i < 0) {
            throw new IllegalArgumentException("Trying get out of bounds element");
        }
        return (E) array[i];
    }

    @Override
    public E set(int i, E e) {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public void add(int i, E e) {
        if (i > size || i < 0) {
            throw new IllegalArgumentException("Trying put element to incorrect position");
        }
        if (!tryIncreaseArraySize()) {
            throw new IndexOutOfBoundsException("Array overflow");
        }
        if (size - i >= 0) {
            System.arraycopy(array, i, array, i + 1, size - i);
        }
        array[i] = e;
        ++size;
    }

    @Override
    public E remove(int i) {
        if(size == 0) {
            throw new NullPointerException("The list is empty");
        }
        if (i >= size || i < 0) {
            throw new IllegalArgumentException("Trying remove element which is out of array's bounds");
        }
        if (size - i >= 0) {
            System.arraycopy(array, i + 1, array, i, size - i);
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
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public List<E> subList(int i, int i1) {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public Spliterator<E> spliterator() {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public Stream<E> stream() {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }

    @Override
    public Stream<E> parallelStream() {
        throw new UnsupportedOperationException("Unsupported operation with array");
    }
}
