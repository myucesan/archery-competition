package nl.hva.ict.se.sands;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Efficiency {

    static Comparator<Archer> comparator;
    public static void createComparator() {
        comparator = new ScoreComparator();
    }

    /**
     * Sorts the given list using selection sort
     *
     * @param archerList the list with archers
     */
    public static double selSortEfficiency(List<Archer> archerList) {
        createComparator();
        Stopwatch s1 = new Stopwatch();
        ChampionSelector.selInsSort(archerList, comparator);
        double timeElapsed = s1.elapsedTime();
        double timeTaken = timeElapsed;

        return timeTaken;
    }

    /**
     * Sorts the given list using quickSort
     *
     * @param archerList the list with archers
     */
    public static double quickSortEfficiency(List<Archer> archerList) {
        createComparator();
        Stopwatch s2 = new Stopwatch();
        ChampionSelector.quickSort(archerList, comparator);
        double timeElapsed = s2.elapsedTime();

        double timeTaken = timeElapsed;

        return timeTaken;
    }

    /**
     * Sorts the given list using Collection.sort()
     *
     * @param archerList the list with archers
     */
    public static double colSortEfficiency(List<Archer> archerList) {
        createComparator();
        Stopwatch s3 = new Stopwatch();
        ChampionSelector.collectionSort(archerList, comparator);
        double timeElapsed = s3.elapsedTime();
        double timeTaken = timeElapsed;

        return timeTaken;
    }
}
