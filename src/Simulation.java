import java.util.Random;

public class Simulation {
    public static void main(String[] args) {

        RandomGenerator randGen = new RandomGenerator();
        double[] array = randGen.getCumulativeProbability();
        randGen.calcRanges(array);
        System.out.println(randGen.setRandomNumList(17,37,83,8));
    }
}
