import java.util.ArrayList;

public class Range {

    public double getLowerlimit() {
        return lowerlimit;
    }

    public double getUpperlimit() {
        return upperlimit;
    }

   public double upperlimit;
   public double lowerlimit;
   public double interval;

    Range(){
        this.lowerlimit= 0.0;
        this.upperlimit= 0.0;
        this.interval = 0.0;
    }

    @Override
    public String toString() {
        return lowerlimit + " -- " + upperlimit;
    }
}
