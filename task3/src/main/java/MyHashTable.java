import java.util.Arrays;

class MyHashTable<K, V> {
    private MyHashTable.Entry[] table;
    private int size;
    private int currentMaxSize;
    private static final int MAX_ARRAY_SIZE = (int) Math.pow(2, 16);
    private static final int STARTED_MAX_SIZE = 8;
    private static final int INCREASES_ARRAY_SIZE_VALUE = 2;

    MyHashTable() {
        size = 0;
        currentMaxSize = STARTED_MAX_SIZE;
        table = new MyHashTable.Entry[0];
    }

    private static int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ h >>> 16;
    }

    V put(K key, V value) {

        if (tryIncreaseTableSize()) {
            ++size;
        }
        int hash = hash(key);
        int index = (currentMaxSize - 1) & hash;

        if (table[index] == null) {
            table[index] = new MyHashTable.Entry(hash, key, value, null);
            return value;
        }

        for (Entry e = table[index]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.getKey()) == key || key.equals(k))) {
                V oldValue = (V) e.value;
                e.value = value;
                return oldValue;
            }
        }

        for (Entry e = table[index]; e != null; e = e.next) {
            if (e.next == null) {
                e.next = new MyHashTable.Entry<K, V>(hash, key, value, null);
                break;
            }
        }

        return value;
    }

    public V get(Object key) {
        int hash = hash(key);
        int index = (currentMaxSize - 1) & hash;

        for (Entry e = table[index]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.getKey()) == key || key.equals(k))) {
                return (V) e.getValue();
            }
        }
        return null;
    }

    boolean isEmpty() {
        return table.length == 0;
    }

    boolean containsKey(Object key) {
        int hash = hash(key);
        int index = (currentMaxSize - 1) & hash;

        for (Entry e = table[index]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.getKey()) == key || key.equals(k))) {
                return true;
            }
        }
        return false;
    }

    boolean containsValue(Object value) {
        for (Entry entry : table) {
            for (Entry e = entry; e != null; e = e.next) {
                if (e.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean tryIncreaseTableSize() {
        if (size == 0) {
            table = Arrays.copyOf(table, currentMaxSize);
            return true;
        }
        if (size >= MAX_ARRAY_SIZE) {
            return false;
        }
        if (size * INCREASES_ARRAY_SIZE_VALUE >= currentMaxSize) {
            if (size * INCREASES_ARRAY_SIZE_VALUE < MAX_ARRAY_SIZE) {
                currentMaxSize *= INCREASES_ARRAY_SIZE_VALUE;
            } else {
                currentMaxSize = MAX_ARRAY_SIZE;
            }
            table = Arrays.copyOf(table, currentMaxSize);
            return true;
        }
        return false;
    }

    private static class Entry<K, V> implements java.util.Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        MyHashTable.Entry<K, V> next;

        protected Entry(int hash, K key, V value, MyHashTable.Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(V value) {
            if (value == null) {
                throw new NullPointerException();
            } else {
                V oldValue = this.value;
                this.value = value;
                return oldValue;
            }
        }
    }
}
