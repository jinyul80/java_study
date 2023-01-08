import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Solution2Test {

    Solution2 s2 = new Solution2();

    @Test
    void sol_K번째수() {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

        int[] answer = s2.solution1(array, commands);
        int[] actual = {5, 6, 3};

        assertArrayEquals(answer, actual);
    }

    @Test
    void sol_소수만들기() {
        int[] nums = {1, 2, 7, 6, 4};

        int answer = s2.solution2(nums);
        int actual = 4;

        assertEquals(answer, actual);
    }

    @Test
    void sol_예산() {
        int[] d = {1,3,2,5,4};
        int budget = 9;

        int answer = s2.solution3(d, budget);
        int actual = 3;

        assertEquals(answer, actual);

    }


}