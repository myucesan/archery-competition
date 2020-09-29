package nl.hva.ict.se.sands;

import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

// TODO: quick sort implementeren
// TODO: Collection sort gewoon
// TODO: Measure runtime and performance (efficiency) of every algorithm
// TODO: make a report
public class Main {


    public static void main(String[] args) {
        List<Archer> archersList = Archer.generateArchers(100);
        List<Archer> archersListQS = Archer.generateArchers(1000);
        List<Archer> archersListCS = Archer.generateArchers(1000);
        ScoreComparator sc = new ScoreComparator();

        long startTimeSelectionSort = System.nanoTime();
        long startTimeQuick = System.nanoTime();
        long startTimeCS = System.nanoTime();

        archersList = ChampionSelector.selInsSort(archersList, sc);
        archersListQS = ChampionSelector.quickSort(archersList, sc);
        archersListCS = ChampionSelector.collectionSort(archersList, sc);

        for (Archer a : archersList)
            System.out.println(a.toString());

        long stopTimeSelectionsort = System.nanoTime();
        System.out.println("Time in nano seconds: " + (stopTimeSelectionsort - startTimeSelectionSort));

        System.out.println("--------------------------------------------------------------------------------------");

        for (Archer a : archersListQS)
            System.out.println(a.toString());

        long stopTimeQuick = System.nanoTime();
        System.out.println("Time in nano seconds: " + (stopTimeQuick - startTimeQuick));

        System.out.println("--------------------------------------------------------------------------------------");

        for (Archer a : archersListCS)
            System.out.println(a.toString());

        long stopTimeCS = System.nanoTime();
        System.out.println("Time in nano seconds: " + (stopTimeCS - startTimeCS));

        }

    public static void listScores(List<Archer> archersList) {
        for (Archer a : archersList) {
            System.out.println(a.getFullName() + " (" + a.getId() + ")");
            for (Map.Entry<Integer, int[]> entry : a.getScores().entrySet()) {

                System.out.print("Round " + entry.getKey() + " scores:");
                for (int point : entry.getValue()) {
                    System.out.print(point + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println("Number of nines:" + a.getNines());
            System.out.println("Number of tens:" + a.getTens());
            System.out.println("Total scores:" + a.getTotalScore());


        }
    }
}
