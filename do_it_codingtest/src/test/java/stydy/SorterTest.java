package stydy;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.junit.jupiter.api.RepeatedTest;

class SorterTest {

    int[] getNumArray() {
        int N = 50000;
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

        // 배열 랜덤하게 섞기
        int[] shuffle_A = list.stream()
                              .mapToInt(Integer::intValue)
                              .toArray();

        // 오름차순 정렬 적용
        // Arrays.sort(shuffle_A);

        // 정렬 전 배열 출력
        if (A.length <= 100) {
            System.out.println("정렬 전:" + Arrays.toString(shuffle_A));
        }

        return shuffle_A;
    }

    @RepeatedTest(5)
    void selectionSort() {
        int[] A = getNumArray();
        int[] shuffle_A = shuffleNumArray(A);

        Sorter sorter = new Sorter();
        int[] result = sorter.selectionSort(shuffle_A);

        // 정렬 후
        if (A.length <= 100) {
            System.out.println("정렬 후:" + Arrays.toString(result));
        }

        assertArrayEquals(result, A);
    }

    @RepeatedTest(5)
    void bubbleSort() {
        int[] A = getNumArray();
        int[] shuffle_A = shuffleNumArray(A);

        Sorter sorter = new Sorter();
        int[] result = sorter.bubbleSort(shuffle_A);

        // 정렬 후
        if (A.length <= 100) {
            System.out.println("정렬 후:" + Arrays.toString(result));
        }

        assertArrayEquals(result, A);
    }

    @RepeatedTest(5)
    void insertionSort() {
        int[] A = getNumArray();
        int[] shuffle_A = shuffleNumArray(A);

        Sorter sorter = new Sorter();
        int[] result = sorter.insertionSort(shuffle_A);

        // 정렬 후
        if (A.length <= 100) {
            System.out.println("정렬 후:" + Arrays.toString(result));
        }

        assertArrayEquals(result, A);
    }

    @RepeatedTest(5)
    void shellSort() {
        int[] A = getNumArray();
        int[] shuffle_A = shuffleNumArray(A);

        Sorter sorter = new Sorter();
        int[] result = sorter.shellSort(shuffle_A);

        // 정렬 후
        if (A.length <= 100) {
            System.out.println("정렬 후:" + Arrays.toString(result));
        }

        assertArrayEquals(result, A);
    }

    @RepeatedTest(5)
    void mergeSort() {
        int[] A = getNumArray();
        int[] shuffle_A = shuffleNumArray(A);

        Sorter sorter = new Sorter();
        int[] result = sorter.mergeSort(shuffle_A);

        // 정렬 후
        if (A.length <= 100) {
            System.out.println("정렬 후:" + Arrays.toString(result));
        }

        assertArrayEquals(result, A);
    }
}