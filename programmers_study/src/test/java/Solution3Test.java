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

}