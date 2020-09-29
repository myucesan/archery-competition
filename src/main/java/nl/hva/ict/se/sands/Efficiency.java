package nl.hva.ict.se.sands;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Efficiency {

    static Comparator<Archer> comparator;


    public static void createComparator() {
        comparator = new ScoreComparator();
    }
    public static double selSortEfficiency(List<Archer> archerList, int nrOfArchers) {
        createComparator();
        Stopwatch stopwatch = new Stopwatch();
        archerList = ChampionSelector.selInsSort(archerList, comparator);
        double timeTaken = stopwatch.elapsedTime();

        return timeTaken;
    }

    public static double quickSortEfficiency(List<Archer> archerList, int nrOfArchers) {
        createComparator();
        Stopwatch stopwatch = new Stopwatch();
        archerList = ChampionSelector.quickSort(archerList, comparator);
        double timeTaken = stopwatch.elapsedTime();

        return timeTaken;
    }

    public static double colSortEfficiency(List<Archer> archerList, int nrOfArchers) {
        createComparator();
        Stopwatch stopwatch = new Stopwatch();
        archerList = ChampionSelector.collectionSort(archerList, comparator);
        double timeTaken = stopwatch.elapsedTime();

        return timeTaken;
    }
}
