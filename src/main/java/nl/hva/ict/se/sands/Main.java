package nl.hva.ict.se.sands;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    // FIXME: One list of nr has to be deep cloned so the same list is sorted thrice by three algorithms
    // FIXME: heap space error if i creating three list of archers multiple times, so one list should be created that is deep cloned twice
    final static int MAX_ARCHERS = 5000000;
    public static void main(String[] args) {
        int nrOfArchers =  100;
        double totalTimeSelSort = 0;
        double totalTimeQS = 0;
        double totalTimeCS = 0;
        double totalTimeAll = 0;
        while(nrOfArchers <= MAX_ARCHERS) {
            System.out.printf("Nr of archers: %d \n", nrOfArchers);
            List<Archer> archerList1 = Arrays.asList(new Archer[nrOfArchers]);;
            List<Archer> archerList2 = Archer.generateArchers(nrOfArchers);
            List<Archer> archerList3 = Arrays.asList(new Archer[nrOfArchers]);
            Collections.copy(archerList1, archerList2);
            Collections.copy(archerList3, archerList2);

            double timeTakenselSort = Efficiency.selSortEfficiency(archerList1, nrOfArchers);
            double timeTakenQS = Efficiency.quickSortEfficiency(archerList2, nrOfArchers);
            double timeTakenCS = Efficiency.colSortEfficiency(archerList3, nrOfArchers);
            System.out.printf("Time taken with selection sort with %d archers: %.2f milliseconds \n", nrOfArchers , timeTakenselSort);
            System.out.printf("Time taken with quick sort with %d archers: %.2f milliseconds \n", nrOfArchers , timeTakenQS);
            System.out.printf("Time taken with collection sort with %d archers: %.2f milliseconds \n", nrOfArchers , timeTakenCS);
            nrOfArchers = nrOfArchers * 2;
//            totalTimeSelSort += timeTakenselSort;
            totalTimeQS += timeTakenQS;
            totalTimeCS += timeTakenCS;
            totalTimeAll = totalTimeAll + totalTimeSelSort + totalTimeQS + totalTimeCS;
        }
        System.out.println();
        System.out.printf("Total time taken to selection sort: %.2f \n", totalTimeSelSort);
        System.out.printf("Total time taken to quick sort: %.2f \n", totalTimeQS);
        System.out.printf("Total time taken to collection sort: %.2f \n", totalTimeCS);
        System.out.printf("Total time taken to sort: %.2f \n", totalTimeAll);

//        List<Archer> archersList = Archer.generateArchers(100);
//        List<Archer> archersListQS = Archer.generateArchers(1000);
//        List<Archer> archersListCS = Archer.generateArchers(1000);
//        ScoreComparator sc = new ScoreComparator();
//
//        long startTimeSelectionSort = System.nanoTime();
//        long startTimeQuick = System.nanoTime();
//        long startTimeCS = System.nanoTime();
//
//        archersList = ChampionSelector.selInsSort(archersList, sc);
//        archersListQS = ChampionSelector.quickSort(archersList, sc);
//        archersListCS = ChampionSelector.collectionSort(archersList, sc);
//
//        for (Archer a : archersList)
//            System.out.println(a.toString());
//
//        long stopTimeSelectionsort = System.nanoTime();
//        System.out.println("Time in nano seconds: " + (stopTimeSelectionsort - startTimeSelectionSort));
//
//        System.out.println("--------------------------------------------------------------------------------------");
//
//        for (Archer a : archersListQS)
//            System.out.println(a.toString());
//
//        long stopTimeQuick = System.nanoTime();
//        System.out.println("Time in nano seconds: " + (stopTimeQuick - startTimeQuick));
//
//        System.out.println("--------------------------------------------------------------------------------------");
//
//        for (Archer a : archersListCS)
//            System.out.println(a.toString());
//
//        long stopTimeCS = System.nanoTime();
//        System.out.println("Time in nano seconds: " + (stopTimeCS - startTimeCS));

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
