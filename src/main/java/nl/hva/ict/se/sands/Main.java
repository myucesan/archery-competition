package nl.hva.ict.se.sands;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    final static int MAX_ARCHERS = 5000000;
    final static int MAX_MILLISECONDS = 20000; // 20 seconds

    public static void main(String[] args) throws IOException {
        int nrOfArchers = 100;

        double selSortTime = 0;
        double quickSortTime = 0;
        double colSortTime = 0;

        Stopwatch selSortTracker;
        Stopwatch quickSortTracker;
        Stopwatch colSortTracker;
        // these booleans are used to stop execution of a sorting algorithm when it starts to take 20 seconds
        boolean selSortRunning= true;
        boolean quickSortRunning = true;
        boolean colSortRunning = true;

        while (nrOfArchers < MAX_ARCHERS) {

            // generate list and make 3 copies of it.
            List<Archer> generatedList = Archer.generateArchers(nrOfArchers);
            List<Archer> selSortList = Arrays.asList(new Archer[nrOfArchers]);
            List<Archer> quickSortList = Arrays.asList(new Archer[nrOfArchers]);
            List<Archer> colSortList = Arrays.asList(new Archer[nrOfArchers]);
            Collections.copy(selSortList, generatedList);
            Collections.copy(quickSortList, generatedList);
            Collections.copy(colSortList, generatedList);

            System.out.printf("Number of archers: %d \n", nrOfArchers);
            if (selSortRunning) {
                selSortTracker = new Stopwatch();  // stopwatch created to keep track of how long this sorting is running
                selSortTime = Efficiency.selSortEfficiency(selSortList);
                System.out.printf("Selection sort: %.2f \n", selSortTime);
                if (selSortTracker.elapsedTime() >= MAX_MILLISECONDS) {
                    selSortRunning = false;  // if the sorting took more than 20 seconds this is set to false so this sorting is not executed anymore
                }
            }

            if (quickSortRunning) {
                quickSortTracker = new Stopwatch();  // stopwatch created to keep track of how long this sorting is running
                quickSortTime = Efficiency.quickSortEfficiency(quickSortList);
                if (quickSortTracker.elapsedTime() >= MAX_MILLISECONDS) {
                    quickSortRunning = false;  // if the sorting took more than 20 seconds this is set to false so this sorting is not executed anymore
                }
                System.out.printf("QuickSort sort: %.2f \n", quickSortTime);
            }
            if (colSortRunning) {
                colSortTracker = new Stopwatch();  // stopwatch created to keep track of how long this sorting is running
                colSortTime = Efficiency.colSortEfficiency(colSortList);
                if (colSortTracker.elapsedTime() >= MAX_MILLISECONDS) {
                    colSortRunning = false;  // if the sorting took more than 20 seconds this is set to false so this sorting is not executed anymore
                }
                System.out.printf("Collection sort: %.2f \n", colSortTime);
            }
            System.out.println();
            measurementsToCsv(nrOfArchers, selSortTime, quickSortTime, colSortTime);  // writes measures to csv file
            nrOfArchers *= 2;

        }

    }

// Code below is used for storing the scores in csv files for reporting convenience. Can be ignored.

    static FileWriter out;
    static CSVPrinter csvPrinter;

    static {
        try {
            out = new FileWriter("measurements.csv");
            csvPrinter = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("Number of Archers", "Selection Sort times", "Quick Sort times", "Collection sort times"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes record to csv file
     *
     * @param nrOfArchers - number of archers sorted
     * @param selSortTime - time it took to selection sort
     * @param quickSortTime - time it took to selection sort
     * @param colSortTime - time it took to selection sort
     */
    static void measurementsToCsv(int nrOfArchers, double selSortTime, double quickSortTime, double colSortTime) throws IOException {

        csvPrinter.printRecord(nrOfArchers, selSortTime, quickSortTime, colSortTime);
        csvPrinter.flush();
    }

    /**
     * Prints the list values (used to check values)
     *
     * @param archersList - list with archers
     */
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
