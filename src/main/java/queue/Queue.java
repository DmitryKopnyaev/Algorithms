package queue;

public class Queue<T> {
    class Entry {
        private T value;
        private Entry next; //ссылка на следующий элемент очереди

        public Entry(T value, Entry next) {
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(this.value);
        }
    }

    private Entry top; //глава очереди
    private Entry tail; //конец очереди
    private int count;

    public Queue() {
    }

    private Entry getEntry(int i) {
        if (i < 0 || i >= this.count) throw new IllegalArgumentException("index out of bound");
        Entry e = this.top;
        for (int j = 1; j <= i; j++)
            e = e.next;
        return e;
    }

    public void push(T val) {
        this.push(this.count, val);
    }

    public void push(int i, T val) {
        Entry entry;
        if (i == 0) {
            entry = new Entry(val, this.top);
            this.top = entry;
        } else {
            entry = this.getEntry(i - 1);
            Entry newEntry = new Entry(val, entry.next);
            Entry nextEntry = entry.next;
            entry.next = newEntry;
            newEntry.next = nextEntry;
            if (i == this.count)
                this.tail = newEntry;
        }
        count++;
    }

    public T peek() {
        return this.top.value;
    }

    public T peek(int i) {
        Entry entry = this.getEntry(i);
        return entry.value;
    }

    public T take() {
        return this.take(0);
    }

    public T take(int i) {
        if (this.count == 0) throw new IllegalArgumentException("The list is empty");
        Entry returnedEntry;
        if (i == 0) {
            returnedEntry = this.top;
            this.top = returnedEntry.next;
            returnedEntry.next = null;
        } else {
            Entry e = this.getEntry(i - 1);
            returnedEntry = e.next;
            e.next = returnedEntry.next;
            if (i == this.count - 1)
                this.tail = e;
        }

        this.count--;
        return returnedEntry.value;


    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Count: ").append(this.count).append("\n");
        Entry e = this.top;
        while (e != null) {
            sb.append(e).append(" ");
            e = e.next;
        }
        return sb.toString();
    }
}
