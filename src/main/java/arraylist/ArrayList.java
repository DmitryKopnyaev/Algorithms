package arraylist;

import java.lang.reflect.Array;
import java.util.Iterator;

public class ArrayList<T> implements Iterable<T> {

    private Object[] list;
    private int index;

    public ArrayList() {
        this.list = new Object[10];
    }

    public ArrayList(int i) {
        this.list = new Object[i];
    }

    private void riseList() {
        int newSize = (int) (this.list.length * 1.5);
        Object[] newList = new Object[newSize];
        System.arraycopy(list, 0, newList, 0, this.list.length);
        this.list = newList;
    }

    public void add(T t) {
        this.list[index] = t;
        this.index++;
        if (this.index == this.list.length - 1)
            riseList();
    }

    public void add(int i, T t) {
        if (i < 0 || i > this.index) throw new IllegalArgumentException("index out of bound");
        System.arraycopy(this.list, i, this.list, i + 1, this.index - i);
        this.list[i] = t;
        this.index++;
        if (this.index == this.list.length - 1)
            riseList();
    }

    public T get(int i) {
        return (T) this.list[i];
    }

    public T remove(int i) {
        if (i < 0 || i >= this.index) throw new IllegalArgumentException("index out of bound");
        T t = (T) this.list[i];
        System.arraycopy(this.list, i + 1, this.list, i, this.index - i);
        this.index--;
        return t;
    }

    public int indexOf(T t) {
        for (int i = 0; i < this.index; i++)
            if (this.list[i].equals(t)) return i;
        return -1;
    }

    public ArrayList<T> sublist(int begin, int end) {
        if (begin >= end || end > this.index || begin < 0) throw new IllegalArgumentException("begin >= end");
        Object[] newArray = new Object[end - begin + 1];//?
        System.arraycopy(this.list, 0, newArray, 0, end - begin);
        ArrayList<T> newList = new ArrayList<>();
        newList.list = newArray;
        newList.index = end - begin;
        return newList;
    }

    public T[] toArray(T[] arr) {
        T[] ts = (T[]) Array.newInstance(arr.getClass().getComponentType(), this.index);
        System.arraycopy(this.list,0,ts,0,this.index);
        return ts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.index; i++) {
            sb.append(this.list[i]);
            if (i != this.index - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    class ListIterator implements Iterator<T>{

        private int i;
        private int index;
        private T[] current;

        public ListIterator(T[] current, int index) {
            this.current = current;
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return i < index;
        }

        @Override
        public T next() {
            T t = current[i];
            i++;
            return t;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator((T[]) this.list, this.index);
    }
}
