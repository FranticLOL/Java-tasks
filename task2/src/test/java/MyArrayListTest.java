import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MyArrayListTest {

    @Test
    public void sizeFirst() {
        List<Integer> arrayList = new MyArrayList<>(new Integer[]{2, 9, 1});
        assertEquals(arrayList.size(), 3);
    }

    @Test
    public void sizeNull() {
        List<Integer> arrayList = new MyArrayList<>();
        assertEquals(arrayList.size(), 0);
    }

    @Test
    public void isEmpty() {
        List<Integer> arrayList = new MyArrayList<>();
        assertTrue(arrayList.isEmpty());
    }

    @Test
    public void isNotEmpty() {
        List<Integer> arrayList = new MyArrayList<>(new Integer[]{1});
        assertFalse(arrayList.isEmpty());
    }

    @Test
    public void contains() {
        List<Integer> arrayList = new MyArrayList<>(new Integer[]{2, 9, 1});
        assertTrue(arrayList.contains(9));
    }

    @Test
    public void clear() {
        List<String> arrayList = new MyArrayList<>(new String[]{"hi", "9", "1"});
        arrayList.clear();
        assertEquals(arrayList.size(), 0);
    }

    @Test
    public void get() {
        List<Integer> arrayList = new MyArrayList<>(new Integer[]{2, 9, 1});
        int expected = arrayList.get(1);
        assertEquals(expected, 9);
    }

    @Test
    public void add() {
        List<Integer> arrayList = new MyArrayList<>(new Integer[]{2, 9, 1});
        arrayList.add(17);
        int expected = arrayList.get(3);
        assertEquals(expected, 17);
    }

    @Test
    public void addToPosition() {
        List<Integer> arrayList = new MyArrayList<>(new Integer[]{2, 9, 1});
        arrayList.add(1, 17);
        int expected = arrayList.get(1);
        assertEquals(expected, 17);
    }

    @Test
    public void remove() {
        List<Integer> arrayList = new MyArrayList<>(new Integer[]{2, 9, 1});
        arrayList.remove(1);
        int expected = arrayList.get(1);
        assertEquals(expected, 1);
        assertEquals(arrayList.size(), 2);
    }
}
