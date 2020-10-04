package nl.hva.ict.se.sands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChampionSelectorTest {
    protected Comparator<Archer> comparator;

    @BeforeEach
    public void createComparator() {
        comparator = new ScoreComparator();
    }

    @Test
    public void selInsSortAndCollectionSortResultInSameOrder() {
        List<Archer> unsortedArchersForSelIns = Archer.generateArchers(23);
        List<Archer> unsortedArchersForCollection = new ArrayList<>(unsortedArchersForSelIns);

        List<Archer> sortedArchersSelIns = ChampionSelector.selInsSort(unsortedArchersForSelIns, comparator);
        List<Archer> sortedArchersCollection = ChampionSelector.collectionSort(unsortedArchersForCollection, comparator);

        assertEquals(sortedArchersCollection, sortedArchersSelIns);
    }

    /**
     * test to see if the quicksort is working
     */
    @Test
    public void testListIsSorted() {
        Archer costa = new Archer("Costa", "van Elsas", 100);
        Archer mustafa = new Archer("mustafa", "Yücesan", 99);
        Archer testman1 = new Archer("testman1 ", "ja", 98);
        Archer testman2 = new Archer("testman1 ", "nee", 97);

        List<Archer> players = Arrays.asList(mustafa, testman1, testman2, costa);
        Archer[] sorted = ChampionSelector.quickSort(players, comparator).toArray(new Archer[0]);

        assertEquals(sorted[0], costa);
        assertEquals(sorted[1], mustafa);
        assertEquals(sorted[2], testman1);
        assertEquals(sorted[3], testman2);
    }

    /**
     * Test for the quick sort to see if it is the same result as the collections sort method
     */
    @Test
    public void quickSortResultInSameOrder() {
        List<Archer> unsortedArchersForQuickSort = Archer.generateArchers(50);
        List<Archer> unsortedArchersForCollection = new ArrayList<>(unsortedArchersForQuickSort);

        List<Archer> sortedArchersQuick = ChampionSelector.quickSort(unsortedArchersForQuickSort, comparator);
        List<Archer> sortedArchersCollection = ChampionSelector.collectionSort(unsortedArchersForCollection, comparator);

        assertEquals(sortedArchersCollection, sortedArchersQuick);
    }
}
