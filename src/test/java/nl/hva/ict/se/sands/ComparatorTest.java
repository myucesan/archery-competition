package nl.hva.ict.se.sands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the comparator
 */
public class ComparatorTest {

    protected Comparator<Archer> comparator;

    @BeforeEach
    public void createComparator() {
        comparator = new ScoreComparator();
    }

    @Test
    public void testGreaterThan() {
        Archer archerOne = new Archer("Test", "archer1", 1);
        Archer archerTwo = new Archer("Test", "archer2", 10);

        int result = comparator.compare(archerOne, archerTwo);
        assertEquals(1, result);
    }

    @Test
    public void testLessThan() {
        Archer archerOne = new Archer("Test", "archer1", 100);
        Archer archerTwo = new Archer("Test", "archer2", 10);

        int result = comparator.compare(archerOne, archerTwo);
        assertEquals(-1, result);
    }
}
