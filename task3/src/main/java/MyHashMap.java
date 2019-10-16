import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyHashMap<K, V> implements Map<K, V> {
    private MyHashTable hashTable;

    MyHashMap() {
        hashTable = new MyHashTable();
    }

    @Override
    public boolean isEmpty() {
        return hashTable.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return hashTable.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return hashTable.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return (V) hashTable.get(key);
    }

    @Override
    public V put(K key, V value) {
        return (V) hashTable.put(key, value);
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Unsupported operation with HashMap");
    }

    @Override
    public V remove(Object o) {
        throw new UnsupportedOperationException("Unsupported operation with HashMap");
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("Unsupported operation with HashMap");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Unsupported operation with HashMap");
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Unsupported operation with HashMap");
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("Unsupported operation with HashMap");
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("Unsupported operation with HashMap");
    }
}
