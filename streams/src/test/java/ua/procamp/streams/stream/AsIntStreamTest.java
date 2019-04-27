package ua.procamp.streams.stream;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AsIntStreamTest {

    private IntStream intStream;
    private IntStream intStreamMap;
    private IntStream intStreamFlatMap;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
        int[] map = {1, 2, 3};
        intStreamMap = AsIntStream.of(map);
        int[] flatMap = {1, 4, 9};
        intStreamFlatMap = AsIntStream.of(flatMap);
    }

    @Test
    public void testAverage() {
        System.out.println("average");
        double expResult = 1.0;
        double result = intStream.average();
        assertEquals(expResult, result, 0.01);
    }

    @Test
    public void max() {
        System.out.println("max");
        int expResult = 3;
        int result = intStream.max();
        assertEquals(expResult, result);
    }

    @Test
    public void min() {
        System.out.println("min");
        int expResult = -1;
        int result = intStream.min();
        assertEquals(expResult, result);
    }

    @Test
    public void count() {
        System.out.println("count");
        long expResult = 5;
        long result = intStream.count();
        assertEquals(expResult, result);
    }

    @Test
    public void sum() {
        System.out.println("sum");
        int expResult = 5;
        int result = intStream.sum();
        assertEquals(expResult, result);
    }

    @Test
    public void filter() {
        System.out.println("filter");
        int[] expResult = {1, 2, 3};
        int[] result = intStream.filter(x -> x > 0).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void forEach() {
        System.out.println("forEach");
        String expResult = "-10123";
        StringBuilder str = new StringBuilder();
        intStream.forEach(x -> str.append(x));
        String result = str.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void map() {
        System.out.println("map");
        int[] expResult = {1, 4, 9};
        int[] result = intStreamMap.map(x -> x * x).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void flatMap() {
        System.out.println("flatMap");
        int[] expResult = {0, 1, 2, 3, 4, 5, 8, 9, 10};
        int[] result = intStreamFlatMap.flatMap(x -> AsIntStream.of(x - 1, x, x + 1)).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void reduce() {
        System.out.println("reduce");
        int expResult = 26;
        int result = intStream.reduce(21,(a, b) -> a + b);
        assertEquals(expResult, result);
    }

    @Test
    public void toArray() {
        System.out.println("toArray");
        int [] expResult = {-1, 0, 1, 2, 3};
        int [] result = intStream.toArray();
        assertArrayEquals(expResult, result);
    }
}