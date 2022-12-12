import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Solution1Test {

    Solution1 s1 = new Solution1();
    @Test
    void printHelloWorld() {
        s1.printHelloWorld();
    }

    @Test
    void sol_프린터1(){
        int[] priorities = {2, 1, 3, 2};
        int locatino = 2;

        int result = s1.solution1(priorities, locatino);
        assertEquals(result, 1);
    }

    @Test
    void sol_프린터2(){
        int[] priorities = {1, 1, 9, 1, 1, 1};
        int locatino = 0;

        int result = s1.solution1(priorities, locatino);
        assertEquals(result, 5);
    }
}