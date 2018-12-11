import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;


public class RandomGenerator {
    ArrayList randRanges = new ArrayList<Range>();
    ArrayList interArrivalTimesList = new ArrayList<Double>();
    ArrayList intervalList = new ArrayList<Double>();
    ArrayList randomNumList = new ArrayList<Double>();


    public double [] getCumulativeProbability(){
        out.println("Enter the time between arrivals and the frequency of occurrence for the simulation (Press 0,0 to cancel): ");
        ArrayList frequencyList = new ArrayList<Double>();
        ArrayList probabilityList = new ArrayList<Double>();



        Scanner scanner = new Scanner(System.in);

        while(true){
            String input = scanner.nextLine();
            String[] string = input.trim().split(",");
            double interval = Double.parseDouble(string[0].trim());
            double freq = Double.parseDouble(string[1].trim());


            if (freq != 0.0) {
                intervalList.add(interval);
                frequencyList.add(freq);
            }else{
                break;
            }
        }

        double totalFreq = 0.0;
        for (int i=0;i< frequencyList.size();i++){
            totalFreq += (double)frequencyList.get(i);
        }
        for (int i=0;i<frequencyList.size();i++){
            probabilityList.add((double)frequencyList.get(i)/totalFreq);
        }


        double sum = 0.0;
        double [] cumulativeProbList = new double[probabilityList.size()];
        for (int i = 0; i < cumulativeProbList.length; i++) {
            sum += (double) probabilityList.get(i);
            sum = (double) Math.round(sum * 1000) / 1000;
            cumulativeProbList[i] = sum
            ;
        }
        return cumulativeProbList;
    }

    void calcRanges( double [] cumProbList){
        double previousLower = 0.0;
        for (int i=1; i<cumProbList.length+1 ; i++){
            Range range = new Range();
            range.lowerlimit = previousLower;
            range.upperlimit = cumProbList[i-1] - 0.01;
            randRanges.add(range);
            previousLower = cumProbList[i-1];

        }

        for (Object i : randRanges){
            System.out.println(i);
        }
    }

    ArrayList setRandomNumList (int constant, int randomNum, int modulo, int num){
        double randomNumbers [] = randomNumberGenerator( constant, randomNum, modulo, num);
        randomNumList.add(0);
        for (int i=1; i<num; i++){
            randomNumList.add(randomNumbers[i]/modulo);
        }

        return randomNumList;
    }
//    void interArrivalTimes (){
//        Range range = new Range();
//
//        for (int i=0;i<randomNumList.size();i++){
//            if( ((Double)randomNumList.get(i) >= range.lowerlimit  && ((Double)randomNumList.get(i) <= range.upperlimit)))
//                interArrivalTimesList.add(10);
//        }
//    }

    public double[] randomNumberGenerator(int constant, int randomNum, int modulo, int num){
        double randomNumbers[] = new double[num];
        for (int i = 0; i < num; i++){
            randomNumbers[i] = randomNum / (double) modulo;
            randomNum = (constant * randomNum) % modulo;
        }
        return randomNumbers;
    }


}
