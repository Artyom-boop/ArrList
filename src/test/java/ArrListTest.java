import org.example.ArrList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrListTest {
    private static final Random random = new Random();
    private static final int QUANTITY_ITERATIONS = 100;

    private static final int MAX_SIZE_LIST = 10000;

    private static List<Integer> referenceList;

    private static ArrList<Integer> testList;

    @BeforeEach
    public void updateLists() {
        testList = new ArrList<>();
        referenceList = new ArrayList<>();
        randomDataToLists(referenceList, testList);
    }

    @Test
    public void addByElementTest() {
        int element;
        int size = referenceList.size();
        for (int i = size; i < QUANTITY_ITERATIONS + size; i++) {
            element = random.nextInt();
            assertTrue(testList.add(element));
            referenceList.add(element);
            assertEquals(referenceList.size(), testList.size());
            assertEquals(referenceList.get(i), testList.get(i));
        }
    }

    @Test
    public void addByIndexTest() {
        int element;
        for (int i = 0; i < QUANTITY_ITERATIONS; i++) {
            element = random.nextInt();
            int indexForAdd = getIndex(referenceList);
            referenceList.add(indexForAdd, element);
            testList.add(indexForAdd, element);
            assertEquals(referenceList.size(), testList.size());
            assertEquals(referenceList.get(indexForAdd), testList.get(indexForAdd));
        }
    }

    @Test
    public void addByIndexThrowTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> testList.add(testList.size(), random.nextInt()));
    }

    @Test
    public void getTest() {
        for (int j = 0; j < QUANTITY_ITERATIONS; j++) {
            updateLists();
            assertEquals(referenceList.size(), testList.size());
            for (int i = 0; i < referenceList.size(); i++) {
                assertEquals(referenceList.get(i), testList.get(i));
            }
        }
    }

    @Test
    public void getThrowTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> testList.get(testList.size()));
    }

    @Test
    public void setTest() {
        for (int j = 0; j < QUANTITY_ITERATIONS; j++) {
            updateLists();
            assertEquals(referenceList.size(), testList.size());
            for (int i = 0; i < referenceList.size(); i++) {
                int element = random.nextInt();
                assertEquals(referenceList.set(i, element), testList.set(i, element));
                assertEquals(referenceList.get(i), testList.get(i));
            }
        }
    }

    @Test
    public void setThrowTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> testList.set(testList.size(), random.nextInt()));
    }

    @Test
    public void removeByIndexTest() {
        for (int j = 0; j < QUANTITY_ITERATIONS; j++) {
            updateLists();
            for (int i = 0; i < referenceList.size(); i++) {
                int indexForRemove = getIndex(referenceList);
                Integer referenceRemovedElement = referenceList.remove(indexForRemove);
                Integer testRemovedElement = testList.remove(indexForRemove);
                assertEquals(referenceRemovedElement, testRemovedElement);
                assertEquals(referenceList.contains(referenceRemovedElement), testList.contains(referenceRemovedElement));
                assertEquals(referenceList.size(), testList.size());
            }
        }
    }

    @Test
    public void removeByIndexThrowTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> testList.remove(testList.size()));
    }

    @Test
    public void removeByElementTest() {
        for (int j = 0; j < QUANTITY_ITERATIONS; j++) {
            updateLists();
            for (int i = 0; i < referenceList.size(); i++) {
                Integer element = random.nextInt();
                boolean referenceRemovedElement = referenceList.remove(element);
                boolean testRemovedElement = testList.remove(element);
                assertEquals(referenceRemovedElement, testRemovedElement);
                assertEquals(referenceList.contains(element), testList.contains(element));
                assertEquals(referenceList.size(), testList.size());
            }
        }
    }

    @Test
    public void removeByElementNullTest() {
        assertEquals(referenceList.remove(null), testList.remove(null));
    }

    @Test
    public void clearTest() {
        testList.clear();
        assertEquals(testList.size(), 0);
        assertTrue(testList.isEmpty());
    }

    @Test
    public void indexOfTest() {
        for (int j = 0; j < QUANTITY_ITERATIONS; j++) {
            int indexForIndexOf = getIndex(referenceList);
            Integer element = referenceList.get(indexForIndexOf);
            assertEquals(referenceList.indexOf(element), testList.indexOf(element));
            assertEquals(referenceList.indexOf(random.nextInt()), testList.indexOf(random.nextInt()));
            assertEquals(referenceList.indexOf(null), testList.indexOf(null));
        }
    }

    @Test
    public void indexOfNullTest() {
        assertEquals(referenceList.indexOf(null), testList.indexOf(null));
    }

    @Test
    public void containsTest() {
        for (int j = 0; j < QUANTITY_ITERATIONS; j++) {
            int indexForContains = getIndex(referenceList);
            assertTrue(testList.contains(referenceList.get(indexForContains)));
            Integer removedElement = referenceList.remove(indexForContains);
            testList.remove(indexForContains);
            assertEquals(referenceList.contains(removedElement), testList.contains(removedElement));
        }
    }

    @Test
    public void toArrayTest() {
        Object[] testArr = testList.toArray();
        Object[] referenceArr = referenceList.toArray();
        assertEquals(referenceArr.length, testArr.length);
        for (int i = 0; i < referenceArr.length; i++) {
            assertEquals(referenceArr[i], testArr[i]);
        }
    }

    private static void randomDataToLists(List<Integer> arrayList, ArrList<Integer> arrList) {
        int size = random.nextInt(MAX_SIZE_LIST) + 1;
        int element;
        for (int i = 0; i < size; i++) {
            element = random.nextInt();
            arrayList.add(element);
            arrList.add(element);
        }
        for (int i = 0; i < size / 10; i++) {
            int indexForNull = getIndex(arrayList);
            arrayList.set(indexForNull, null);
            arrList.set(indexForNull, null);
        }
    }

    private static int getIndex(List<Integer> list) {
        return random.nextInt(list.size());
    }
}
