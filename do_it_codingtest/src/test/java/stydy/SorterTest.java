package stydy;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class SorterTest {

    @Test
    void selectionSort() {
        int N = 20;
        int[] A = new int[N];

        for (int i = 0; i < N ; i++) {
            A[i] = N - i;
        }

        List<Integer> list = Arrays.stream(A)
                                   .boxed()
                                   .collect(Collectors.toList());

        Collections.shuffle(list, new Random(777));

        int[] shuffle_A = list.stream()
            .mapToInt(Integer::intValue)
            .toArray();

        // 정렬 전      
        System.out.println(Arrays.toString(shuffle_A));
        
        Sorter sorter = new Sorter();
        int[] result = sorter.selectionSort(shuffle_A);

        // 정렬 후
        System.out.println(Arrays.toString(result));

        assertArrayEquals(result, A);


    }
}