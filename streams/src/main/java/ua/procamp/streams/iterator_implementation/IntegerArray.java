package ua.procamp.streams.iterator_implementation;

import ua.procamp.streams.iterator.IntegerIterator;

public class IntegerArray implements IntegerIterator {

    private int[] array;
    private int index;

    public IntegerArray(int[] array) {
        this.array = array;
        index = -1;
    }

    @Override
    public boolean hasNext() {
        return index < array.length-1;
    }

    @Override
    public Integer next() {
        return array[++index];
    }
}

