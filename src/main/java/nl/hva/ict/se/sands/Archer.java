package nl.hva.ict.se.sands;

import java.util.*;

/**
 * Holds the name, archer-id and the points scored for 30 arrows.
 *
 * Archers MUST be created by using one of the generator methods. That is way the constructor is private and should stay
 * private. You are also not allowed to add any constructor with an access modifier other then private unless it is for
 * testing purposes in which case the reason why you need that constructor must be contained in a very clear manner
 * in your report.
 */
public class Archer {
    public final static int MAX_ARROWS = 3;
    public final static int MAX_ROUNDS = 10;
    private static Random randomizer = new Random();
    private final int id; // Once assigned a value this attribute is not allowed to change.
    private static int idNumbering = 135787;
    private String firstName;
    private String lastName;
    private int totalScore;
    private int numberOfNines;
    private int numberOfTens;
    private Map<Integer, int[]> scores = new HashMap<Integer, int[]>();

    /**
     * Constructs a new instance of Archer and assigns a unique ID to the instance. The ID is not allowed to ever
     * change during the lifetime of the instance! For this you need to use the correct Java keyword. Each new instance
     * is a assigned a number that is 1 higher than the last one assigned. The first instance created should have
     * ID 135788;
     *
     * @param firstName the archers first name.
     * @param lastName the archers surname.
     */
    protected Archer(String firstName, String lastName) {
        this.id = idNumbering + 1; // sets the id to idNumbering + 1, so it starts at the preferred number (135788)
        idNumbering++;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     *  Added an extra constructor for the test class to test with points
     * @param firstName the archers first name.
     * @param lastName the archers surname.
     * @param pts the archers points
     */
    protected Archer(String firstName, String lastName, int pts) {
        this.id = idNumbering + 1; // sets the id to idNumbering + 1, so it starts at the preferred number (135788)
        idNumbering++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalScore = pts;
    }

    /**
     * Registers the point for each of the three arrows that have been shot during a round. The <code>points</code>
     * parameter should hold the three points, one per arrow.
     *
     * @param round the round for which to register the points, zero based.
     * @param points the points shot during the round.
     */
    public void registerScoreForRound(int round, int[] points) {
        scores.put(round + 1, points);
        for (int point : points) {
            totalScore += point;

            if (point == 9) {numberOfNines++;} // increments numberOfNines by 1 so it has track over nines
            if (point == 10) {numberOfTens++;} // increments numberOfNines by 1 so it has track over tens

        }
    }
    /**
     * Returns the score
     * @return the Map with the scores
     */
    public Map<Integer, int[]> getScores() {
        return scores;
    }

    public int getTotalScore() {
        return this.totalScore;
    }

    /**
     * Returns the number of 10's scored by this archer.
     * @return the number of 10's for this archer.
     */
    public int getTens() {
        return this.numberOfTens;
    }

    /**
     * Returns the number of 9's scored by this archer.
     * @return the number of 9's for this archer.
     */
    public int getNines() {
        return this.numberOfNines;
    }

    /**
     * Returns the id of this archer.
     * @return the id of this archer..
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the the full name of the archer
     * @return the full name of the archer
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }


    /*
    The code below is their for your own convenience. You don't have include it in your report.
     */

    /**
     * This methods creates a List of archers. This method takes care of assigning each arhcher
     * a first name, surname and lets them should 30 arrows.
     *
     * @param nrOfArchers the number of archers in the list.
     * @return archers
     */
    public static List<Archer> generateArchers(int nrOfArchers) {
        List<Archer> archers = new ArrayList<>(nrOfArchers);
        for (int i = 0; i < nrOfArchers; i++) {
            Archer archer = new Archer(Names.nextFirstName(), Names.nextSurname());
            letArcherShoot(archer);
            archers.add(archer);
        }
        return archers;

    }

    private static void letArcherShoot(Archer archer) {
        for (int round = 0; round < MAX_ROUNDS; round++) {
            archer.registerScoreForRound(round, shootOneRound());
        }
    }

    private static int[] shootOneRound() {
        int[] points = new int[MAX_ARROWS];
        for (int arrow = 0; arrow < MAX_ARROWS; arrow++) {
            points[arrow] = shootArrow();
        }
        return points;
    }

    private static int shootArrow() {
        return 1 + randomizer.nextInt(10);
    }

    @Override
    public String toString() {
        return this.id + " (" + this.totalScore + ") " + this.firstName + " " + this.lastName;
    }




}


