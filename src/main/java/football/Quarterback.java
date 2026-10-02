package football;

/**
 * A quarterback throws to a Receiver.
 *
 * Built in Step 3 (fields, constructor), Step 4 (throwTo),
 * Step 5 (completionPercentage, toString), and Step 6 (overloading).
 */
public class Quarterback {

    private final String name;
    private final int armStrength;   // longest throw he can make, in yards
    private int attempts;
    private int completions;
    private int passingYards;

    public Quarterback(String name, int armStrength) {
        // the constructor (not setters) sets the name and arm strength once; they are
        // final, so they can never change afterward
        this.name = name;
        this.armStrength = armStrength;
    }

    public String getName() { return name; }
    // these getters return the current stat values; attempts, completions, and
    // passingYards start at 0 automatically (fields default to 0), so a new QB shows 0, not nothing
    public int getAttempts()      { return attempts; }
    public int getCompletions()   { return completions; }
    public int getPassingYards()  { return passingYards; }

    // throwTo makes one pass: it takes the Receiver being thrown to and how many yards,
    // and returns true if the pass is caught, false if it is incomplete
    public boolean throwTo(Receiver target, int yards) {
        attempts = attempts + 1;
        // every throw counts as an attempt, caught or not

        // if the throw is longer than the QB's arm strength it is incomplete: print it and return false
        // (nothing is added to completions, passingYards, or the receiver's stats)
        if (yards > armStrength) {
            System.out.println(name + " -> " + target.getName() + ", " + yards + " yards: INCOMPLETE");
            return false;
        }

        // otherwise it is a completion: completions goes up by 1 and passingYards
        // grows by only the yards of completed passes

        completions = completions + 1;
        passingYards = passingYards + yards;
        // this line calls catchBall on the receiver. The Receiver object adds the
        // catch and the yards to its own stats; target is the real receiver, not a copy
        target.catchBall(yards);
        // then it prints CAUGHT and returns true
        System.out.println(name + " -> " + target.getName() + ", " + yards + " yards: CAUGHT");
        return true;
    }

    // Step 4 example: Carter's arm strength is 40
    //Carter -> Davis, 15 yards: CAUGHT
    //Carter -> Brooks, 25 yards: CAUGHT
    //Carter -> Davis, 50 yards: INCOMPLETE (50 is more than 40)
    //println(davis)  = #11 Davis: 1 catches, 15 yards
    //println(brooks) = #84 Brooks: 1 catches, 25 yards

    // this is an overload: same name, fewer parameters. Java picks which throwTo to run
    // based on the arguments. This short version always throws 10 yards by handing off to
    // the longer version, so the arm strength check still happens there.
    // carter.throwTo(brooks)     = Carter -> Brooks, 10 yards: CAUGHT
    // carter.throwTo(brooks, 30) = Carter -> Brooks, 30 yards: CAUGHT
    // it prints even though we only called throwTo(brooks) because the call
    // passes through the longer version, which does the printing
    public boolean throwTo(Receiver target) {
        return throwTo(target, 10);
    }


    // calculates the completion percentage as a whole number
    // ("illegal start of an expression" usually means a method was typed inside
    // another method, so check that the braces above are closed)
    public int completionPercentage() {
        // with 0 attempts we return 0 so we never divide by zero
        if (attempts == 0) {
            return 0;
        }
        // multiply completions by 100 FIRST, then divide by attempts. Dividing first
        // (2 / 3) is integer division and gives 0. Integer division chops, it does not round.
        return completions * 100 / attempts;
    }

    @Override
    // @Override replaces toString() inherited from Object, so println(carter) uses this.
    // The format is name: completions/attempts, passingYards yards, percent%
    // ex. Carter: 2/3, 40 yards, 66%
    public String toString() {
        return name + ": " + completions + "/" + attempts + ", " + passingYards + " yards, "
                + completionPercentage() + "%";
        // Step 5 prediction: 2 completions on 3 attempts with 15 + 25 completed yards
        // gives "Carter: 2/3, 40 yards, 66%" (200 / 3 = 66). The 0% only appears
        // with the wrong order: completions / attempts * 100.
    }

}