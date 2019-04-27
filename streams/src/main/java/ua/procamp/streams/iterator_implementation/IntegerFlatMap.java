package ua.procamp.streams.iterator_implementation;

import ua.procamp.streams.function.IntToIntStreamFunction;
import ua.procamp.streams.iterator.IntegerIterator;
import ua.procamp.streams.stream.IntStream;

public class IntegerFlatMap implements IntegerIterator {

    private IntegerIterator iterator;
    private IntToIntStreamFunction func;
    private IntegerIterator innerIterator;

    public IntegerFlatMap(IntegerIterator iterator, IntToIntStreamFunction func) {
        this.iterator = iterator;
        this.func = func;
    }

    @Override
    public boolean hasNext() {
        if(innerIterator != null && innerIterator.hasNext()) {
            return true;
        }
        while (iterator.hasNext()) {
            int value = iterator.next();
            IntStream intStream = func.applyAsIntStream(value);
            if (intStream == null) {
                continue;
            }
            if (intStream.iterator().hasNext()) {
                innerIterator = intStream.iterator();
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return innerIterator.next();
    }
}
