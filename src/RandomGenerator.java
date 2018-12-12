import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class RandomGenerator {
    Range[] randRanges = new Range[5];
    ArrayList interArrivalTimesList = new ArrayList<Double>();
    ArrayList intervalList = new ArrayList<Double>();
    ArrayList randomNumList = new ArrayList<Double>();

    public double[] getCumulativeProbability() {
        out.println("Enter the time between arrivals and the frequency of occurrence for the simulation (Press 0,0 to cancel): ");
        ArrayList frequencyList = new ArrayList<Double>();
        ArrayList probabilityList = new ArrayList<Double>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            String[] string = input.trim().split(",");
            double interval = Double.parseDouble(string[0].trim());
            double freq = Double.parseDouble(string[1].trim());

            if (freq != 0.0) {
                intervalList.add(interval);
                frequencyList.add(freq);
            } else {
                break;
            }
        }

        double totalFreq = 0.0;
        for (Object aFrequencyList : frequencyList) {
            totalFreq += (double) aFrequencyList;
        }
        for (int i = 0; i < frequencyList.size(); i++) {
            double prob = (double) frequencyList.get(i) / totalFreq;
            prob = (double) Math.round(prob * 100) / 100;
            probabilityList.add(prob);
        }

        double sum = 0.0;
        double[] cumulativeProbList = new double[probabilityList.size()];
        for (int i = 0; i < cumulativeProbList.length; i++) {
            sum += (double) probabilityList.get(i);
            sum = (double) Math.round(sum * 1000) / 1000;
            cumulativeProbList[i] = sum;
        }
        return cumulativeProbList;
    }

    void calcRanges(double[] cumProbList) {
        double previousLower = 0.0;
        for (int i = 1; i < cumProbList.length + 1; i++) {
            Range range = new Range();
            range.lowerlimit = previousLower;
            range.upperlimit = Math.round((cumProbList[i - 1] - 0.01) * 100) / 100.0;
            range.interval = (Double) intervalList.get(i - 1);
            randRanges[i-1] = range;
            previousLower = cumProbList[i - 1];
        }
        for (Range i : randRanges) {
            System.out.println(i);
        }
    }

    void interArrivalTimes() {
        for (int i = 0; i < randomNumList.size(); i++) {
            double random = (Double) randomNumList.get(i);

            for (Range range : randRanges){

                if(random >= range.lowerlimit && random <= range.upperlimit){
                    interArrivalTimesList.add(range.interval);
                }
            }
        }
    }

    public void randomNumberGenerator(int constant, int randomNum, int modulo, int num) {
        double randomNumbers[] = new double[num];
        for (int i = 0; i < num; i++) {
            randomNumbers[i] = randomNum / (double) modulo;
            randomNumbers[i] = round(randomNumbers[i], 2);
            randomNum = (constant * randomNum) % modulo;

            randomNumList.add(randomNumbers[i]);
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    void display(){
        out.println("ith\t\t\tRandom Number\t\t\tInterArrival Time");
        for(int i = 0; i < randomNumList.size(); i++){
            out.println("\n" + (i + 1) + "\t\t\t\t" + randomNumList.get(i) + "\t\t\t\t\t" + interArrivalTimesList.get(i));
        }
    }

}
