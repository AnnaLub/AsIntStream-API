package ua.procamp.streams.iterator_implementation;

import ua.procamp.streams.function.IntPredicate;
import ua.procamp.streams.iterator.IntegerIterator;

public class IntegerFilter implements IntegerIterator {

    private IntPredicate predicate;
    private IntegerIterator iterator;
    private int value;
    private boolean hasNext, hasNextEvaluated;

    public IntegerFilter(IntPredicate predicate, IntegerIterator iterator) {
        this.predicate = predicate;
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        if(!hasNextEvaluated) {
            nextIteration();
            hasNextEvaluated = true;
        }
        return hasNext;
    }

    @Override
    public Integer next() {
        if(!hasNextEvaluated) {
            hasNext = hasNext();
        }
        hasNextEvaluated = false;
        return value;
    }

    private void nextIteration() {
        while(iterator.hasNext()) {
            value = iterator.next();
            if(predicate.test(value)) {
                hasNext = true;
                return;
            }
        }
        hasNext = false;
    }





}
