import java.util.Comparator;
import java.io.Serializable;

/**
 * Compares cars
 *
 * @author a55617 Elisio Fernandes, a73175 Daniel Martins, a78879 Nuno Silva
 * @version (a version number or a date)
 */
public class ComparatorKmsTotal implements Comparator <Vehicle>, Serializable {
    public int compare(Vehicle v1, Vehicle v2) {
        double aux1 = v1.getKmsTotal();
        double aux2 = v2.getKmsTotal();

        if (aux1 == aux2) return 0;
        if (aux1 < aux2) return -1;
        else return 1;
    }
}
