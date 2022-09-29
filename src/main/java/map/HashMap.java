package map;

import java.util.*;
import java.util.stream.Collectors;

public class HashMap<K, V> {
    class Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private Object[] map;
    private static int size = 10;

    public HashMap() {
        this.map = new Object[size];
    }

    private int getIndex(K key) {
        return key.hashCode() % this.size;
    }

    public void put(K key, V value) {
        int i = getIndex(key);

        if (this.map[i] == null)
            this.map[i] = new Entry<>(key, value);
        else {
            Entry<K, V> entry = (Entry<K, V>) this.map[i];
            while (entry != null) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                    return;
                }
                entry = entry.next;
            }
            this.map[i] = new Entry<>(key, value, (Entry<K, V>) this.map[i]);
        }
    }

    public V get(K key) {
        int i = getIndex(key);
        if (this.map[i] == null)
            return null;
        Entry<K, V> entry = (Entry<K, V>) this.map[i];
        while (entry != null) {
            if (entry.key.equals(key))
                return entry.value;
            entry = entry.next;
        }
        return null;
    }

    public V remove(K key) {
        int i = getIndex(key);
        if (this.map[i] == null)
            return null;
        Entry<K, V> entry = (Entry) this.map[i];
        if(entry.key.equals(key)) {
            this.map[i] = entry.next;
            return entry.value;
        }
        while (entry.next != null) {
            Entry<K, V> prev = entry;
            entry = entry.next;
            if (entry.key.equals(key)){
                prev.next = entry.next;
                return entry.value;
            }
        }
        return null;
    }

    public Set<K> keySet() {
        return Arrays.stream(this.map).filter(Objects::nonNull).map(s -> ((Entry<K, V>) s).key).collect(Collectors.toSet());
    }

    public Collection<V> values() {
        return null;
    }

    public boolean contain(K key) {
        return Arrays.stream(this.map).filter(Objects::nonNull).anyMatch(s -> ((Entry<K, V>) s).key.equals(key));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        sb.append("{");
        for (int i = 0; i < this.map.length; i++) {
            if (this.map[i] != null) {
                Entry entry = (Entry) this.map[i];
                while (entry != null) {
                    if(isFirst){
                        isFirst = false;
                        sb.append(entry);
                    }
                    sb.append(", ").append(entry);
                    entry = entry.next;
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
