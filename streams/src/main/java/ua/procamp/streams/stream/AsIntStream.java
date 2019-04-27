package ua.procamp.streams.stream;

import ua.procamp.streams.function.*;
import ua.procamp.streams.iterator_implementation.IntegerArray;
import ua.procamp.streams.iterator.IntegerIterator;
import ua.procamp.streams.iterator_implementation.IntegerFilter;
import ua.procamp.streams.iterator_implementation.IntegerFlatMap;
import ua.procamp.streams.iterator_implementation.IntegerMap;

import java.util.ArrayList;
import java.util.List;

public final class AsIntStream implements IntStream {

    private IntegerIterator iterator;

    private AsIntStream(IntegerIterator iterator) {
        this.iterator = iterator;
    }

    private static final IntStream EMPTY = new AsIntStream(new IntegerIterator() {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Integer next() {
            return null;
        }
    });

    public static IntStream empty() {
        return EMPTY;
    }

    public static IntStream of(int... values) {
        if (values.length == 0) {
            return AsIntStream.empty();
        }
        return new AsIntStream(new IntegerArray(values));
    }

    public IntegerIterator iterator() {
        return iterator;
    }

    @Override
    public Double average() {
        double average = iterator.next();
        int count = 1;
        while (iterator.hasNext()) {
            average += iterator.next();
            count++;
        }
        return average / count;
    }

    @Override
    public Integer max() {
        int max = iterator.next();
        while (iterator.hasNext()) {
            int newMax = iterator.next();
            if (max < newMax) {
                max = newMax;
            }
        }
        return max;
    }

    @Override
    public Integer min() {
        int min = iterator.next();
        while (iterator.hasNext()) {
            int newMin = iterator.next();
            if (min > newMin) {
                min = newMin;
            }
        }
        return min;
    }

    @Override
    public long count() {
        long count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        return count;
    }

    @Override
    public Integer sum() {
        int sum = iterator.next();
        while (iterator.hasNext()) {
            sum += iterator.next();
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new IntegerFilter(predicate, iterator));
    }

    @Override
    public void forEach(IntConsumer action) {
        while (iterator.hasNext()) {
            action.accept(iterator.next());
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new IntegerMap(iterator, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new IntegerFlatMap(iterator, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int reduce = identity;
        while (iterator.hasNext()) {
            int value = iterator.next();
            reduce = op.apply(reduce, value);
        }
        return reduce;
    }

    @Override
    public int[] toArray() {
        List<Integer> converter =  new ArrayList<Integer>();
        while (iterator.hasNext()) {
            converter.add(iterator.next());
        }
        int[] array = new int[converter.size()];
        int index = -1;
        for (Integer value : converter) {
            array[++index] = value;
        }
        return array;
    }
}
