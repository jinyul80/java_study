package stydy;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class SorterTest {

    int[] getNumArray() {
        int N = 100000;
        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = N - i;
        }

        return A;
    }

    int[] shuffleNumArray(int[] A) {
        List<Integer> list = Arrays.stream(A)
                                   .boxed()
                                   .collect(Collectors.toList());

        Collections.shuffle(list, new Random(777));

        int[] shuffle_A = list.stream()
                              .mapToInt(Integer::intValue)
                              .toArray();

        return shuffle_A;
    }

    @Test
    void selectionSort() {
        int[] A = getNumArray();
        int[] shuffle_A = shuffleNumArray(A);

        Sorter sorter = new Sorter();
        int[] result = sorter.selectionSort(shuffle_A);

        // 정렬 후
        if (A.length <= 100) {
            System.out.println(Arrays.toString(result));
        }

        assertArrayEquals(result, A);
    }

    @Test
    void bubbleSort() {
        int[] A = getNumArray();
        int[] shuffle_A = shuffleNumArray(A);

        Sorter sorter = new Sorter();
        int[] result = sorter.bubbleSort(shuffle_A);

        // 정렬 후
        if (A.length <= 100) {
            System.out.println(Arrays.toString(result));
        }

        assertArrayEquals(result, A);
    }
}