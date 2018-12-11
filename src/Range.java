import java.util.ArrayList;

public class Range {
    RandomGenerator rnd = new RandomGenerator();
    double upperlimit;
    double lowerlimit;

    Range(){
        this.lowerlimit= 0.0;
        this.upperlimit= 0.0;
    }

    @Override
    public String toString() {

        return lowerlimit + " -- " + upperlimit;
    }
}
