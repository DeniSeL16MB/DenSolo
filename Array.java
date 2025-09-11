import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Array {
    public static <T> Set<T> getUniqueElements(ArrayList<T> list) {
        return new HashSet<>(list);
    }
}