package diplomski.autoceste.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;

@Component
public class NumberComparator implements Comparator<Number> {

    @Override
    public int compare(Number a, Number b) {
        if (a.toString().equals("Infinity")) {
            if (b.toString().equals("Infinity")) {
                return 0;
            }
            return 1;
        } else if (b.toString().equals("Infinity")) {
            return -1;
        }

        if (a.toString().equals("-Infinity")) {
            if (b.toString().equals("-Infinity")) {
                return 0;
            }
            return -1;
        } else if (b.toString().equals("-Infinity")) {
            return 1;
        }
        return new BigDecimal(a.toString()).compareTo(new BigDecimal(b.toString()));
    }
}
