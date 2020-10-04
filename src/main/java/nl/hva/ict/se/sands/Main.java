package nl.hva.ict.se.sands;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Main {

    final static int MAX_ARCHERS = 5000000;
    final static int MAX_MILLISECONDS = 20000; // 20 seconds
    static int measurementRound;
    public static void main(String[] args) throws IOException {
        int nrOfArchers = 100;


        double selSortTime = 0;
        double quickSortTime = 0;
        double colSortTime = 0;




        Stopwatch selSortTracker = null;
        Stopwatch quickSortTracker = new Stopwatch();
        Stopwatch colSortTracker = new Stopwatch();
        boolean selSortRunning= true;

        while (nrOfArchers < MAX_ARCHERS) {

            // generate list and make 3 copies of it.
            List<Archer> generatedList = Archer.generateArchers(nrOfArchers);
            List<Archer> selSortList = Arrays.asList(new Archer[nrOfArchers]);
            List<Archer> quickSortList = Arrays.asList(new Archer[nrOfArchers]);
            List<Archer> colSortList = Arrays.asList(new Archer[nrOfArchers]);
            Collections.copy(selSortList, generatedList);
            Collections.copy(quickSortList, generatedList);
            Collections.copy(colSortList, generatedList);

            if (selSortRunning) {
                System.out.println(nrOfArchers);
                selSortTracker = new Stopwatch();
                selSortTime = Efficiency.selSortEfficiency(selSortList);
                System.out.printf("Selection sort: %.2f \n", selSortTime);
                if (selSortTracker.elapsedTime() >= MAX_MILLISECONDS) {
                    selSortRunning = false;
                }
            }

            while(nrOfArchers < MAX_ARCHERS & quickSortTracker.elapsedTime() < MAX_MILLISECONDS) {
                System.out.println(nrOfArchers);
                quickSortTime = Efficiency.quickSortEfficiency(quickSortList);
                System.out.printf("QuickSort sort: %.2f \n", quickSortTime);
            }
            while(nrOfArchers < MAX_ARCHERS & colSortTracker.elapsedTime() < MAX_MILLISECONDS) {
                System.out.println(nrOfArchers);
                colSortTime = Efficiency.colSortEfficiency(colSortList);
                System.out.printf("Collection sort: %.2f \n", colSortTime);
            }

            nrOfArchers *= 2;

        }

    }


    static FileWriter out;
    static CSVPrinter csvPrinter;

    static {
        try {
            out = new FileWriter("measurements.csv", true);
            csvPrinter = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("Number of Archers", "Selection Sort times", "Quick Sort times", "Collection sort times"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void measurementsToCsv(int nrOfArchers, double selSortTime, double quickSortTime, double colSortTime) throws IOException {

        csvPrinter.printRecord(nrOfArchers, selSortTime, quickSortTime, colSortTime);
        csvPrinter.flush();
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
