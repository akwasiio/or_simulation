import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

public class RandomGenerator {
    private Range[] randRanges;
    private ArrayList interArrivalTimesList = new ArrayList<Double>();
    private ArrayList intervalList = new ArrayList<Double>();
    private ArrayList randomNumList = new ArrayList<Double>();
    ArrayList probabilityList = new ArrayList<Double>();


    private double arrivalTime[];
    private double startTime[];
    private double checkTime = 30.0;
    private double waitTime[];
    private double departureTime[];
    private double timeSpent[];
    private double queue[];
    private Scanner scanner;

    private int randomToBeGenerated;
    private int numOfProbs;

    RandomGenerator(int numberOfProbs, int numToBeGenerated) {
        randRanges = new Range[numberOfProbs];
        arrivalTime = new double[numToBeGenerated];
        startTime = new double[numToBeGenerated];
        waitTime = new double[numToBeGenerated];
        departureTime = new double[numToBeGenerated];
        timeSpent = new double[numToBeGenerated];
        queue = new double[numToBeGenerated];
        scanner = new Scanner(System.in);

        randomToBeGenerated = numToBeGenerated;
        numOfProbs = numberOfProbs;
    }

    private void convertFrequencyToProbability() {
        out.println("Enter the time between arrivals and the frequency of occurrence for the simulation: ");
        ArrayList frequencyList = new ArrayList<Double>();

        int i = 1;
        while (i <= numOfProbs) {
            String input = scanner.nextLine();
            String[] string = input.trim().split(",");
            double interval = Double.parseDouble(string[0].trim());
            double freq = Double.parseDouble(string[1].trim());

            intervalList.add(interval);
            frequencyList.add(freq);
            i++;
        }

        double totalFreq = 0.0;
        for (Object aFrequencyList : frequencyList) {
            totalFreq += (double) aFrequencyList;
        }
        for (Object aFrequencyList : frequencyList) {
            double prob = (double) aFrequencyList / totalFreq;
            prob = round(prob);
            probabilityList.add(prob);
        }
    }

    private double[] computeCumulativeProbability() {
        double sum = 0.0;
        double[] cumulativeProbList = new double[probabilityList.size()];
        for (int i = 0; i < cumulativeProbList.length; i++) {
            sum += (double) probabilityList.get(i);
            sum = round(sum);
            cumulativeProbList[i] = sum;
        }

//        if(cumulativeProbList[cumulativeProbList.length - 1] != 1){
//            System.err.println("Cumulative probability does not sum up to 1. Try again.");
//            convertFrequencyToProbability();
//
//        }

        return cumulativeProbList;
    }


    private void calcRanges(double[] cumProbList) {
        double previousLower = 0.0;
        for (int i = 1; i < cumProbList.length + 1; i++) {
            Range range = new Range();
            range.lowerlimit = previousLower;
            range.upperlimit = round(cumProbList[i - 1] - 0.01);
            range.interval = (Double) intervalList.get(i - 1);
            randRanges[i - 1] = range;
            previousLower = cumProbList[i - 1];
        }
        for (Range i : randRanges) {
            System.out.println(i);
        }
    }

    private void interArrivalTimes() {
        for (Object aRandomNumList : randomNumList) {
            double random = (Double) aRandomNumList;

            for (Range range : randRanges) {

                if (random >= range.lowerlimit && random <= range.upperlimit) {
                    interArrivalTimesList.add(range.interval);
                    break;
                }
            }
        }
    }

    private void randomNumberGenerator(int constant, int randomNum, int modulo, int num) {
        double randomNumbers[] = new double[num];
        for (int i = 0; i < num; i++) {
            randomNumbers[i] = randomNum / (double) modulo;
            randomNumbers[i] = round(randomNumbers[i]);
            randomNum = (constant * randomNum) % modulo;

            randomNumList.add(randomNumbers[i]);
        }
    }

    private double round(double value) {
        return Math.round(value * 100) / 100.0;
    }

    private void display() {
        out.println("ith\t\t\tRandom Number\t\t\tInterArrival Time\t\t\tArrival Time\t\t\tStart Time\t\t\tWait Time" +
                "\t\t\tQueue Length\t\t\tCheck Time" + "\t\t\tDeparture Time\t\t\tTime Spent");

        for (int i = 0; i < randomNumList.size(); i++) {
            double rand = (double) randomNumList.get(i);
            double inter = (double) interArrivalTimesList.get(i);

            out.printf("%n%1d\t\t\t\t%.2f\t\t\t\t\t%.2f\t\t\t\t\t%.2f\t\t\t\t    %.2f\t\t\t\t    %.2f\t\t\t\t  %.2f" +
                            "\t\t\t\t  %.2f" + "\t\t\t\t\t%.2f\t\t\t\t  %.2f%n",
                    i + 1, rand, inter, arrivalTime[i], startTime[i], waitTime[i], queue[i],
                    checkTime, departureTime[i], timeSpent[i]);
        }
    }

    private void computeColumns() {
        for (int i = 0; i < randomNumList.size(); i++) {
            if (i == 0) {
                arrivalTime[i] = 0;
                startTime[i] = 0;
            } else {
                arrivalTime[i] = arrivalTime[i - 1] + (double) interArrivalTimesList.get(i);
                startTime[i] = departureTime[i - 1];
            }

            waitTime[i] = startTime[i] - arrivalTime[i];
            departureTime[i] = arrivalTime[i] + waitTime[i] + checkTime;
            timeSpent[i] = waitTime[i] + checkTime;
        }
    }

    private void findQueueLength() {

        for (int i = 0; i < randomNumList.size(); i++) {
            int count = 0;
            double current = arrivalTime[i];

            if (i == 0 || i == 1) {
                queue[i] = count;
                continue;
            }

            for (int j = i - 1; j >= 0; j--) {
                if (current < startTime[j])
                    count++;
            }
            queue[i] = count;
        }
    }

    private void averages() {
        int sumWaitTime = 0;
        double maxQueueLength = 0;
        int sumTimeSpent = 0;

        for (int i = 0; i < waitTime.length; i++) {
            sumWaitTime += waitTime[i];
            sumTimeSpent += timeSpent[i];

            if (queue[i] >= maxQueueLength)
                maxQueueLength = queue[i];
        }

        double meanWait = sumWaitTime / (double) waitTime.length;
        double meanTimeSpent = sumTimeSpent / (double) timeSpent.length;

        out.println("Mean Wait Time: " + meanWait);
        out.println("Maximum Queue Length: " + maxQueueLength);
        out.println("Mean Time Spent: " + meanTimeSpent);
    }

    public void runEntireProgram() {
        Scanner scan = new Scanner(System.in);
        out.print("\nEnter the seed value: ");
        int seedValue = scan.nextInt();
        out.print("Enter constant multiplier: ");
        int constantMultiplier = scan.nextInt();
        out.print("Enter modulo: ");
        int modulo = scan.nextInt();

        convertFrequencyToProbability();
        double array[] = computeCumulativeProbability();
        calcRanges(array);

        randomNumberGenerator(constantMultiplier, seedValue, modulo, randomToBeGenerated);
        interArrivalTimes();
        computeColumns();
        findQueueLength();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        display();
        averages();
    }

}
