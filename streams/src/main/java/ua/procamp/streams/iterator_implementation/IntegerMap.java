package ua.procamp.streams.iterator_implementation;

import ua.procamp.streams.function.IntUnaryOperator;
import ua.procamp.streams.iterator.IntegerIterator;

public class IntegerMap implements IntegerIterator {

    private IntegerIterator iterator;
    private IntUnaryOperator mapper;

    public IntegerMap(IntegerIterator iterator, IntUnaryOperator mapper) {
        this.iterator = iterator;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Integer next() {
        return mapper.apply(iterator.next());
    }
}
