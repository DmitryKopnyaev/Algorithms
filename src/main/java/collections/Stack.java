package collections;

public class Stack<T> {
    class Entry {
        private T value;
        private Entry prev;

        public Entry(T value, Entry prev) {
            this.value = value;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return String.valueOf(this.value);
        }
    }

    private Entry top; //вершина стека
    private int count;

    public Stack() {
    }

    public void add(T val) {
        this.top = new Entry(val, this.top);
        this.count++;
    }

    public T peek() {
        return this.top.value;
    }

    public T pop() {
        Entry removed = this.top;
        this.top = removed.prev;
        this.count--;
        return removed.value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Count: " + this.count + "\n");
        Entry e = this.top;
        while (e != null) {
            sb.append(e.value).append(" ");
            e = e.prev;
        }
        return sb.toString();
    }

    public T peek(int i) {
        Entry entry = this.top;
        for (int j = 0; j < i; j++)
            entry = entry.prev;
        return entry.value;
    }

    public T pop(int i) {
        if (i == 0) return pop();
        Entry beforeEntry = null;
        Entry removedEntry = this.top;
        for (int j = 0; j < i; j++) {
            beforeEntry = removedEntry;
            removedEntry = removedEntry.prev;
        }
        beforeEntry.prev = removedEntry.prev;
        this.count--;
        return removedEntry.value;
    }
}
