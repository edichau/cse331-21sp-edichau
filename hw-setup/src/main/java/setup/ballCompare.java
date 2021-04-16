package setup;

import java.util.Comparator;

public class ballCompare implements Comparator<Ball> {
        public int compare(Ball b1, Ball b2) {
            return Double.compare(b1.getVolume(), b2.getVolume());
        }
    }
