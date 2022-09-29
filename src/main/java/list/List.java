package list;

import java.util.Iterator;

public class List<T> implements Iterable<T> {
    class Entry {
        private T value;
        private Entry next;
        private Entry prev;

        public Entry(T value, Entry next, Entry prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    private Entry top;
    private Entry tail;
    private int count;

    public List() {
    }

    private Entry getEntry(int i) {
        Entry e = this.top;
        for (int j = 1; j <= i; j++)
            e = e.next;
        return e;
    }

    public int size() {
        return this.count;
    }

    //добавление в начало
    public void addTop(T val) {
        this.add(0, val);
    }

    //добавление в конец
    public void addTail(T val) {
        this.add(this.count, val);
    }

    //добавление по индексу
    public void add(int i, T val) {
        if (i > this.count) throw new IllegalArgumentException("Index out of bound");

        Entry e = this.getEntry(i);
        Entry newEntry = new Entry(val, null, null);

        if (i == 0) this.top = newEntry;
        if (i == this.count) {
            if (this.count > 0) {
                this.tail.next = newEntry;
                newEntry.prev = this.tail;
            }
            this.tail = newEntry;
        } else {
            if (e.prev != null) {
                e.prev.next = newEntry;
                newEntry.prev = e.prev;
            }
            e.prev = newEntry;
            newEntry.next = e;
        }

        this.count++;
    }

    //вставка по индексу
    public void set(int i, T val) {
        if (this.count == 0) throw new IllegalArgumentException("The list is empty");
        if (i >= this.count) throw new IllegalArgumentException("Index out of bound");

        Entry e = this.getEntry(i);
        Entry newEntry = new Entry(val, e.next, e.prev);

        if (e.prev != null)
            e.prev.next = newEntry;
        if (e.next != null)
            e.next.prev = newEntry;

        if (i == 0) this.top = newEntry;
        if (i == this.count - 1) this.tail = newEntry;
    }

    //просмотр начала
    public T peekTop() {
        return this.top.value;
    }

    //просмотр конца
    public T peekTail() {
        return this.tail.value;
    }

    //просмотр по индексу
    public T peek(int i) {
        if (this.count == 0) throw new IllegalArgumentException("The list is empty");
        if (i >= this.count) throw new IllegalArgumentException("Index out of bound");

        Entry e = this.getEntry(i);
        return e.value;
    }

    //забрать по индексу
    public T take(int i) {
        if (this.count == 0) throw new IllegalArgumentException("The list is empty");
        if (i >= this.count) throw new IllegalArgumentException("Index out of bound");

        Entry e = this.getEntry(i);

        if (i == 0) this.top = e.next;
        if (i == count - 1) this.tail = e.prev;

        if (e.prev != null)
            e.prev.next = e.next;
        if (e.next != null)
            e.next.prev = e.prev;
        e.next = null;
        e.prev = null;

        this.count--;
        return e.value;
    }

    //забрать из начала
    public T takeTop() {
        return this.take(0);
    }

    //забрать из хвоста
    public T takeTale() {
        return this.take(this.count - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Count: ").append(count).append("\n");
        Entry entry = this.top;
        while (entry != null) {
            sb.append(entry.value).append(" ");
            entry = entry.next;
        }
        return sb.toString();
    }

    class ListIterator implements Iterator<T> {
        private Entry current;

        public ListIterator(Entry current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public T next() {
            T valBefore = this.current.value;
            this.current = this.current.next;
            return valBefore;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator(this.top);
    }
}
