import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Solution3Test {

    Solution3 s3 = new Solution3();

    @Test
    void solution2() {

        int k = 2;
        int d = 4;

        long answer = s3.solution2(k, d);
        long actual = 6;

        assertEquals(answer, actual);
    }

    @Test
    void sol_숫자카드나누기() {
        int[] arrA = {10, 20};
        int[] arrB = {5, 17};

        int answer = s3.solution3(arrA, arrB);
        int actual = 10;

        assertEquals(answer, actual);
    }

    @Test
    void sol_롤케이크자리그() {
        int[] topping = {1, 2, 1, 3, 1, 4, 1, 2};

        int answer = s3.solution4(topping);
        int actual = 2;

        assertEquals(answer, actual);
    }

    @Test
    void sol_택배상자() {
        int[] orders = {5, 4, 3, 2, 1};

        int answer = s3.solution5(orders);
        int actual = 5;

        assertEquals(answer, actual);
    }

    @Test
    void sol_연속부분수열합의개수() {
        int[] elements = {7,9,1,1,4};

        int answer = s3.solution6(elements);
        int actual = 18;

        assertEquals(answer, actual);
    }

}