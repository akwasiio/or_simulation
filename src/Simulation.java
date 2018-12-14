import java.util.Scanner;

import static java.lang.System.out;

public class Simulation {
    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        out.print("Indicate whether you are entering probabilities or frequencies: ");
        String answer = scanner.nextLine();

        out.print("Enter the number of frequencies (probabilities) to be entered: ");
        int numberToBeEntered = scanner.nextInt();

        out.print("\nEnter number of random numbers to be generated: ");
        int randomToBeGenerated = scanner.nextInt();

        RandomGenerator simulation = new RandomGenerator(numberToBeEntered, randomToBeGenerated);
        simulation.runEntireProgram(answer);

    }
}
