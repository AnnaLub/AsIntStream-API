package ua.procamp.streams.iterator;

import java.util.Iterator;

public interface IntegerIterator extends Iterator<Integer> {

    boolean hasNext();

    Integer next();

    default void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
