
public class Simulation {
    public static void main(String[] args) {
        RandomGenerator randGen = new RandomGenerator();
        double[] array = randGen.getCumulativeProbability();
        randGen.calcRanges(array);
        randGen.randomNumberGenerator(17, 37, 83, 5);

        randGen.interArrivalTimes();
        randGen.display();
    }
}
