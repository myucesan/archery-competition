package nl.hva.ict.se.sands;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Efficiency {

    static Comparator<Archer> comparator;
    public static void createComparator() {
        comparator = new ScoreComparator();
    }
    public static double selSortEfficiency(List<Archer> archerList) {
        createComparator();
        Stopwatch s1 = new Stopwatch();
        ChampionSelector.selInsSort(archerList, comparator);
        double timeElapsed = s1.elapsedTime();
        double timeTaken = timeElapsed;

        return timeTaken;
    }

    public static double quickSortEfficiency(List<Archer> archerList2) {
        createComparator();
        Stopwatch s2 = new Stopwatch();
        ChampionSelector.quickSort(archerList2, comparator);
        double timeElapsed = s2.elapsedTime();

        double timeTaken = timeElapsed;

        return timeTaken;
    }

    public static double colSortEfficiency(List<Archer> archerList3) {
        createComparator();
        Stopwatch s3 = new Stopwatch();
        ChampionSelector.collectionSort(archerList3, comparator);
        double timeElapsed = s3.elapsedTime();
        double timeTaken = timeElapsed;

        return timeTaken;
    }
}
