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
        Stopwatch s1 = new Stopwatch();
        archerList = ChampionSelector.selInsSort(archerList, comparator);
        double timeElapsed = s1.elapsedTime();
        double timeTaken = timeElapsed;

        return timeTaken;
    }

    public static double quickSortEfficiency(List<Archer> archerList, int nrOfArchers) {
        createComparator();
        Stopwatch s2 = new Stopwatch();
        archerList = ChampionSelector.quickSort(archerList, comparator);
        double timeElapsed = s2.elapsedTime();

        double timeTaken = timeElapsed;

        return timeTaken;
    }

    public static double colSortEfficiency(List<Archer> archerList, int nrOfArchers) {
        createComparator();
        Stopwatch s3 = new Stopwatch();
        archerList = ChampionSelector.collectionSort(archerList, comparator);
        double timeElapsed = s3.elapsedTime();
        double timeTaken = timeElapsed;

        return timeTaken;
    }
}
