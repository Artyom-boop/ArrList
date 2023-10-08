import org.example.ArrList;
import org.example.QuickSort;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Random;

public class QuickSortTest {

    private static final Random random = new Random();
    private static final int QUANTITY_ITERATIONS = 100;
    private static final int MAX_SIZE_LIST = 10000;

    @Test
    public void QuickSortIntegerTest() {
        for (int j = 0; j < QUANTITY_ITERATIONS; j++) {
            ArrList<Integer> arrList = new ArrList<>();
            ArrayList<Integer> arrayList = new ArrayList<>();
            int size = random.nextInt(MAX_SIZE_LIST) + 1;
            int element;
            for (int i = 0; i < size; i++) {
                element = random.nextInt();
                arrayList.add(element);
                arrList.add(element);
            }

            arrayList.sort(Integer::compareTo);
            QuickSort.sort(arrList, Integer::compareTo);

            assertEquals(arrayList.size(), arrList.size());
            for (int i = 0; i < arrayList.size(); i++) {
                assertEquals(arrayList.get(i), arrList.get(i));
            }
        }
    }

    @Test
    public void QuickSortDoubleTest() {
        for (int j = 0; j < QUANTITY_ITERATIONS; j++) {
            ArrList<Double> arrList = new ArrList<>();
            ArrayList<Double> arrayList = new ArrayList<>();
            int size = random.nextInt(MAX_SIZE_LIST) + 1;
            double element;
            for (int i = 0; i < size; i++) {
                element = random.nextDouble();
                arrayList.add(element);
                arrList.add(element);
            }

            arrayList.sort(Double::compareTo);
            QuickSort.sort(arrList, Double::compareTo);

            assertEquals(arrayList.size(), arrList.size());
            for (int i = 0; i < arrayList.size(); i++) {
                assertEquals(arrayList.get(i), arrList.get(i));
            }
        }
    }
}
