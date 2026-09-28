# Football — two classes that talk to each other

**CSCI-121 · Week 6 · Recitation · Mon Sep 28 (R3) / Wed Sep 30 (R4)**

We build two classes together, live: a `Receiver` and a `Quarterback`. By the end,
the quarterback throws the ball by calling a method **on the receiver** — one object
changing another.

No tests. You run it, you read what it prints.

```bash
./mvnw compile exec:java          # Windows:  mvnw.cmd compile exec:java
```

## The rule

> **Write down what you think it will print. Then run it.**

## Setup — do this once

> ## ⚠️ Fork it. Do not clone this repository.
>
> **You cannot push to this repo.** A fork is *your own copy on GitHub*, which you can
> push to and which I can read. **No fork, no way to hand anything in.**

1. Click **Fork** at the top right of this page. The name at the top should now read
   `your-username / Football`, *forked from DSU-CSCI-121-F26/Football*. If it still says
   `DSU-CSCI-121-F26`, the fork did not happen — do it again.
2. Clone **your** fork:

   ```bash
   git clone https://github.com/YOUR-USERNAME/Football.git
   cd Football
   git remote -v        # must show YOUR username, not DSU-CSCI-121-F26
   ```

3. Run it:

   ```bash
   ./mvnw compile exec:java          # Windows:  mvnw.cmd compile exec:java
   ```

   You should see exactly this, and nothing else:

   ```
   === Football ===
   ```

   That is correct — every step is switched off. The first run downloads Maven and takes a
   minute or two.

**In IntelliJ:** File → Open → pick the `Football` folder → Trust Project. If there is no
green ▶ next to `main`, right-click `pom.xml` → Maven → Reload project.

---

## How to work it

1. Type the code for the step into `Receiver.java` or `Quarterback.java`.
2. In `Main.java`, uncomment that step: its call in `main()` and its body.
3. Predict. Run. Compare with the output below.

---

## Step 1 · A class, fields, a constructor

`Receiver.java`:

```java
public class Receiver {

    private final String name;
    private final int number;
    private int catches;
    private int yards;

    public Receiver(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName()  { return name; }
    public int getNumber()   { return number; }
    public int getCatches()  { return catches; }
    public int getYards()    { return yards; }
}
```

**Predict:** the constructor never sets `catches`. What does `getCatches()` give?

```
=== Step 1 ===
Davis wears #11
catches: 0
```

`catches` is a **field**, so it starts at `0` by itself. A local variable would not.

---

## Step 2 · A method that changes the object

Add to `Receiver`:

```java
    public void catchBall(int gain) {
        catches = catches + 1;
        yards = yards + gain;
    }

    @Override
    public String toString() {
        return "#" + number + " " + name + ": " + catches + " catches, " + yards + " yards";
    }
```

**Predict:** `catchBall(12)` then `catchBall(8)`.

```
=== Step 2 ===
#11 Davis: 2 catches, 20 yards
```

`println(davis)` calls `toString()` for you.

---

## Step 3 · The second class

`Quarterback.java`:

```java
public class Quarterback {

    private final String name;
    private final int armStrength;   // longest throw he can make, in yards
    private int attempts;
    private int completions;
    private int passingYards;

    public Quarterback(String name, int armStrength) {
        this.name = name;
        this.armStrength = armStrength;
    }

    public String getName()       { return name; }
    public int getAttempts()      { return attempts; }
    public int getCompletions()   { return completions; }
    public int getPassingYards()  { return passingYards; }
}
```

```
=== Step 3 ===
Carter has thrown 0 passes
```

---

## Step 4 · One object calls a method on another

This is the point of today. Add to `Quarterback`:

```java
    public boolean throwTo(Receiver target, int yards) {
        attempts = attempts + 1;

        if (yards > armStrength) {
            System.out.println(name + " -> " + target.getName() + ", " + yards + " yards: INCOMPLETE");
            return false;
        }

        completions = completions + 1;
        passingYards = passingYards + yards;
        target.catchBall(yards);
        System.out.println(name + " -> " + target.getName() + ", " + yards + " yards: CAUGHT");
        return true;
    }
```

Carter's arm is 40 yards. **Predict all five lines:**

```java
carter.throwTo(davis, 15);
carter.throwTo(brooks, 25);
carter.throwTo(davis, 50);
System.out.println(davis);
System.out.println(brooks);
```

```
=== Step 4 ===
Carter -> Davis, 15 yards: CAUGHT
Carter -> Brooks, 25 yards: CAUGHT
Carter -> Davis, 50 yards: INCOMPLETE
#11 Davis: 1 catches, 15 yards
#84 Brooks: 1 catches, 25 yards
```

`Main` never called `catchBall` — but Davis has a catch. The **quarterback** called it,
through `target`. `target` is not a copy of Davis; it *is* Davis.

---

## Step 5 · Completion percentage

Add to `Quarterback` — **type it exactly like this first:**

```java
    public int completionPercentage() {
        if (attempts == 0) {
            return 0;
        }
        return completions / attempts * 100;
    }

    @Override
    public String toString() {
        return name + ": " + completions + "/" + attempts + ", " + passingYards + " yards, "
                + completionPercentage() + "%";
    }
```

**Predict:** 2 completions out of 3 attempts.

```
Carter: 2/3, 40 yards, 0%
```

**0%.** `2 / 3` is integer division — it is `0`, and `0 * 100` is `0`. Change one line:

```java
        return completions * 100 / attempts;
```

```
=== Step 5 ===
Carter -> Davis, 15 yards: CAUGHT
Carter -> Davis, 25 yards: CAUGHT
Carter -> Davis, 50 yards: INCOMPLETE
Carter: 2/3, 40 yards, 66%
```

`200 / 3` is `66`, not `67` — integer division chops, it does not round.

---

## Step 6 · Overloading

Most passes are short. Add a second `throwTo` with **the same name** and fewer parameters:

```java
    public boolean throwTo(Receiver target) {
        return throwTo(target, 10);
    }
```

**Predict:**

```java
carter.throwTo(brooks);
carter.throwTo(brooks, 30);
```

```
=== Step 6 ===
Carter -> Brooks, 10 yards: CAUGHT
Carter -> Brooks, 30 yards: CAUGHT
#84 Brooks: 2 catches, 40 yards
```

Java picks the method by the arguments. The short one does not repeat the arm-strength
check — it hands off to the version that already has it.

---

## Stretch · if there is time

Add to `Receiver`:

```java
    public double yardsPerCatch() {
        if (catches == 0) {
            return 0.0;
        }
        return (double) yards / catches;
    }
```

**Predict** — note `slot` has no `new`:

```java
Receiver davis = new Receiver("Davis", 11);
Receiver slot = davis;

carter.throwTo(davis, 15);
carter.throwTo(slot, 10);

System.out.println(davis);
System.out.println(davis.yardsPerCatch());
```

```
=== Stretch ===
Carter -> Davis, 15 yards: CAUGHT
Carter -> Davis, 10 yards: CAUGHT
#11 Davis: 2 catches, 25 yards
12.5
```

One receiver, two names. And `(double)` is why it is `12.5` and not `12`.

**On your own:** `1 catches` is bad English. Fix `toString()` so it says `1 catch`.
(Hint: you need an `if` — which is tomorrow's lecture.)

---

## Handing it in

Started in section, finished at home. It counts when there is an **open pull request in
your own fork**.

```bash
git switch -c assignment
git add -A
git commit -m "Football recitation"
git push -u origin assignment
```

Then on GitHub: **Compare & pull request** → make sure the base repo is **your own fork**,
not `DSU-CSCI-121-F26` → **your username on both sides of the arrow** → **Create pull
request** → **post the link in Canvas.**

> The check on your pull request only answers **"does it compile?"** A green check does
> not mean your output is right — compare it against this page.
