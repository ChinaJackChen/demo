package cn.ybzy.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ReverseList<T> implements Iterable<T> {

    private List<T> list = new ArrayList<>();

    public void add(T t) {
        list.add(t);
    }

    @Override
    public Iterator<T> iterator() {
        //  return new ReverseIterator(list.size());
        return new Iterator<T>() {
            int index = list.size();

            @Override
            public boolean hasNext() {
                return index > 0;
            }

            @Override
            public T next() {
                index--;
                return ReverseList.this.list.get(index);
            }
        };
    }
}