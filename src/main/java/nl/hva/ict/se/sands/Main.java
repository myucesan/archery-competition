package nl.hva.ict.se.sands;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        List<Archer> archersList = Archer.generateArchers(100);

        for (Archer a : archersList) {
            System.out.println(a.toString());
        }

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
