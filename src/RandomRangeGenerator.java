import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;
import static java.lang.System.err;

public class RandomRangeGenerator {
    public void getProbability(){
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
            cumulativeProbList[i] = sum;
        }


        for(double i : cumulativeProbList)
            out.println(i);

    }
}
