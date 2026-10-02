package football;

/**
 * CSCI-121 · Week 6 · Recitation · Football
 *
 * No tests. You run it and read what it prints:
 *
 *     ./mvnw compile exec:java          (Windows:  mvnw.cmd compile exec:java)
 *
 * HOW TO WORK THIS
 *
 *   1. Open the README at Step 1.
 *   2. Type the code into Receiver.java / Quarterback.java.
 *   3. Write down what you think it will print. THEN run it.
 *   4. Uncomment the next step: its call in main(), and its body below.
 *
 * One step at a time.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Football ===");
            step1();
            step2();
            step3();
            step4();
            step5();
            step6();
            stretch();
    }

    static void header(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }

    // Step 1 - a class, a constructor, getters
    static void step1() {
        header("Step 1");
        // Receiver davis = new Receiver("Davis", 11);
        // System.out.println(davis.getName() + " wears #" + davis.getNumber());
        // System.out.println("catches: " + davis.getCatches());
    }

    // Step 2 - a method that changes the object's state
    static void step2() {
        header("Step 2");
        // Receiver davis = new Receiver("Davis", 11);
        // davis.catchBall(12);
        // davis.catchBall(8);
        // System.out.println(davis);
    }

    // Step 3 - the second class
    static void step3() {
        header("Step 3");
        // Quarterback carter = new Quarterback("Carter", 40);
        // System.out.println(carter.getName() + " has thrown " + carter.getAttempts() + " passes");
    }

    // Step 4 - one object calling a method on another
    static void step4() {
        header("Step 4");
        // Quarterback carter = new Quarterback("Carter", 40);
        // Receiver davis = new Receiver("Davis", 11);
        // Receiver brooks = new Receiver("Brooks", 84);

        // carter.throwTo(davis, 15);
        // carter.throwTo(brooks, 25);
        // carter.throwTo(davis, 50);

        // System.out.println(davis);
        // System.out.println(brooks);
    }

    // Step 5 - completion percentage, and the integer-division trap
    static void step5() {
        header("Step 5");
        // Quarterback carter = new Quarterback("Carter", 40);
        // Receiver davis = new Receiver("Davis", 11);

        // carter.throwTo(davis, 15);
        // carter.throwTo(davis, 25);
        // carter.throwTo(davis, 50);

        // System.out.println(carter);
    }

    // Step 6 - overloading
    static void step6() {
        header("Step 6");
        // Quarterback carter = new Quarterback("Carter", 40);
        // Receiver brooks = new Receiver("Brooks", 84);

        // carter.throwTo(brooks);
        // carter.throwTo(brooks, 30);

        // System.out.println(brooks);
    }

    // Stretch - a double method, and two names for one receiver
    static void stretch() {
        header("Stretch");
        // Quarterback carter = new Quarterback("Carter", 40);
        // Receiver davis = new Receiver("Davis", 11);
        // Receiver slot = davis;

        // carter.throwTo(davis, 15);
        // carter.throwTo(slot, 10);

        // System.out.println(davis);
        // System.out.println(davis.yardsPerCatch());
    }
}
