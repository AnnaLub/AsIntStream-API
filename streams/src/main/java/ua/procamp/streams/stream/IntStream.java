package ua.procamp.streams.stream;

import ua.procamp.streams.function.*;
import ua.procamp.streams.iterator.IntegerIterator;

public interface IntStream {

    Double average();

    Integer max();

    Integer min();

    IntegerIterator iterator();
    
    IntStream flatMap(IntToIntStreamFunction func);

    long count();

    IntStream filter(IntPredicate predicate);

    void forEach(IntConsumer action);

    IntStream map(IntUnaryOperator mapper);

    int reduce(int identity, IntBinaryOperator op);

    Integer sum();

    int[] toArray();
}
