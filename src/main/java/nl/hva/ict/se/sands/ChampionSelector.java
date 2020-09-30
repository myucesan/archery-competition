package nl.hva.ict.se.sands;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Given a list of Archer's this class can be used to sort the list using one of three sorting algorithms.
 * Note that you are NOT allowed to change the signature of these methods! Adding method is perfectly fine.
 *
 * @author Costa van Elsas, 500782594
 * @author Mustafa Yücesan, 500769574
 */
public class ChampionSelector {

    /**
     * This method uses either selection sort or insertion sort for sorting the archers.
     * Selection sort
     */
    public static List<Archer> selInsSort(List<Archer> archers, Comparator<Archer> scoringScheme) {
        int N = archers.size();
        for (int i = 0; i < N; i++)
        {
            int min = i;
            for (int j = i+1; j < N; j++)
                if (less(archers.get(j), archers.get(min), scoringScheme)) min = j;
            exch(archers, i, min);
        }
        return archers;
    }

    /**
     * This method uses quick sort for sorting the archers.
     */
    public static List<Archer> quickSort(List<Archer> archers, Comparator<Archer> scoringScheme) {
        Collections.shuffle(archers);  // Eliminate dependence on input.
        sort(archers, 0, archers.size() - 1, scoringScheme);
        return archers;
    }

    private static void sort(List<Archer> a, int lo, int hi, Comparator<Archer> scoringScheme) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi, scoringScheme); // Partition (see page 291).
        sort(a, lo, j-1, scoringScheme); // Sort left part a[lo .. j-1].
        sort(a, j+1, hi, scoringScheme); // Sort right part a[j+1 .. hi].
    }

    /**
     * This method uses the Java collections sort algorithm for sorting the archers.
     */
    public static List<Archer> collectionSort(List<Archer> archers, Comparator<Archer> scoringScheme) {
        Collections.sort(archers, scoringScheme);
        return archers;
    }

    /**
     * Compare the archers
     * @param v Archer
     * @param w Archer
     * @param scoringScheme the scores
     * @return the compared items
     */
    private static boolean less(Archer v, Archer w, Comparator<Archer> scoringScheme) {
        return scoringScheme.compare(v, w) < 0;
    }

    /**
     * exchange method
     * @param a archer list
     * @param i int
     * @param j int
     */
    private static void exch(List<Archer> a, int i, int j) {
        Archer t = a.get(i); a.set(i, a.get(j)); a.set(j, t);
    }

    /**
     * Partition so that you can sort the left and the right side
     * @param a archer list
     * @param lo int
     * @param hi int
     * @param scoringScheme the scores
     * @return j with a[lo..j-1] <= a[j] <= a[j+1..hi].
     */
    private static int partition(List<Archer> a, int lo, int hi, Comparator<Archer> scoringScheme) {
        int i = lo, j = hi+1; // left and right scan indices
        Archer v = a.get(lo); // partitioning item
        while (true)
        { // Scan right, scan left, check for scan complete, and exchange.
            while (less(a.get(++i), v, scoringScheme)) if (i == hi) break;
            while (less(v, a.get(--j), scoringScheme)) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
}
