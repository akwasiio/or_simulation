import java.util.Scanner;

import static java.lang.System.out;

public class Simulation {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        out.print("Enter the number of frequencies to be entered: ");
        int numberToBeEntered = scanner.nextInt();

        out.print("\nEnter number of random numbers to be generated: ");
        int randomToBeGenerated = scanner.nextInt();

        RandomGenerator simulation = new RandomGenerator(numberToBeEntered, randomToBeGenerated);
        simulation.runEntireProgram();

    }
}
