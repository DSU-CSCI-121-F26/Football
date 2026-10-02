package football;

/**
 * A receiver catches passes and keeps his own stats.
 *
 * Built in Step 1 (fields, constructor), Step 2 (catchBall, toString),
 * and the Stretch (yardsPerCatch).
 */
public class Receiver {

    private final String name;
    private final int number;
    // fields (not local variables) get a default value automatically, so int fields
    // start at 0 and there is no error even though we never initialize them
    private int catches;
    private int yards;

    // the constructor runs once for every object made with "new Receiver(...)"
    // ex. new Receiver("Davis", 11) takes 2 values: a name and a jersey number
    // catches and yards are not passed in, they just start at 0
    public Receiver(String name, int number) {
        // "this.name" is the field, "name" is the parameter; this line copies the
        // parameter into the field for this object
        this.name = name;
        // same idea: store the jersey number in this object's field
        this.number = number;
    }

    // getters just return the current value of a field
    public String getName()  { return name; }
    public int getNumber()   { return number; }
    public int getCatches()  { return catches; }
    public int getYards()    { return yards; }

    // Step 1 prediction: getCatches() gives 0, not null and not an error,
    // because int fields start at 0 by themselves

    // catchBall changes the object: one more catch, and the gain is added to yards
    // it is void, so it prints nothing itself; the change shows up later in toString()
    public void catchBall(int gain) {
        catches = catches + 1;
        yards = yards + gain;
    }

    // @Override is plain Java (not Spring Boot). It tells the compiler this method
    // replaces toString(), which every class inherits from Object. If the name or
    // parameters did not match, the compiler would give an error.
    @Override
    // println(davis) calls toString() automatically, so it returns text like
    // "#11 Davis: 2 catches, 20 yards"
    public String toString() {
        return "#" + number + " " + name + ": " + catches + " catches, " + yards + " yards";
    }
    // Step 2 prediction: catchBall(12) then catchBall(8) gives 2 catches and 20 yards,
    // because each call adds 1 to catches and its gain to yards

    // average yards per catch; returns 0.0 if there are no catches to avoid dividing by zero
    // the (double) cast makes this decimal division instead of integer division
    public double yardsPerCatch() {
        if (catches == 0) {
            return 0.0;
        }
        return (double) yards / catches;
    }
    // Stretch example:
    //Receiver davis = new Receiver("Davis", 11);
    //Receiver slot = davis;   // no "new", so slot is a second name for the same object, not a copy
    //
    //carter.throwTo(davis, 15); = Carter -> Davis, 15 yards: CAUGHT
    //carter.throwTo(slot, 10);  = Carter -> Davis, 10 yards: CAUGHT (prints Davis because slot is Davis)
    //
    //System.out.println(davis); = #11 Davis: 2 catches, 25 yards
    //System.out.println(davis.yardsPerCatch()); = 12.5 (25 yards / 2 catches)
}