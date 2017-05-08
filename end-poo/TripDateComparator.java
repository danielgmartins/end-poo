import java.util.Comparator;
import java.io.Serializable;
import java.time.LocalDateTime;


public class TripDateComparator implements Comparator<Trip> , Serializable {

	public int compare (Trip t1,Trip t2){
		return t1.getDate().compareTo(t2.getDate());
	}
}
