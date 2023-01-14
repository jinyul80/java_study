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
}