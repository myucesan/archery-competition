package nl.hva.ict.se.sands;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Archer> {

    @Override
    public int compare(Archer a, Archer b)
    {
        if (a.getTotalScore() > b.getTotalScore() ) return +1;
        if (a.getTotalScore() < b.getTotalScore() ) return -1;
        if (a.getTens() > b.getTens() ) return +1;
        if (a.getTens() < b.getTens() ) return -1;
        if (a.getNines() > b.getNines()) return +1;
        if (a.getNines() < b.getNines()) return -1;
        if (b.getId() < a.getId()) return +1;
        if (b.getId() > a.getId()) return -1;
        return 0;
    }
}
