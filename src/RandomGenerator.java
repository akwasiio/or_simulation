import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;


public class RandomGenerator {
    public void getCumulativeProbability(){
        out.println("Enter probabilities for the simulation (Press 0 to cancel): ");
        ArrayList probabilityList = new ArrayList<Double>();

        Scanner scanner = new Scanner(System.in);

        while(true){
            double prob = scanner.nextDouble();
            if (prob != 0.0) {
                probabilityList.add(prob);
            }else{
                break;
            }
        }
        double sum = 0.0;
        double[] cumulativeProbList = new double[probabilityList.size()];
        for (int i = 0; i < cumulativeProbList.length; i++) {
            sum += (double) probabilityList.get(i);
            sum = (double) Math.round(sum * 1000) / 1000;
            cumulativeProbList[i] = sum;
        }

    }

    public double[] randomNumberGenerator(int constant, int randomNum, int modulo, int num){
        double randomNumbers[] = new double[num];
        for (int i = 0; i < num; i++){
            randomNumbers[i] = randomNum / (double) modulo;
            randomNum = (constant * randomNum) % modulo;
        }
        return randomNumbers;
    }
}
