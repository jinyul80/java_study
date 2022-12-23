import static org.junit.jupiter.api.Assertions.*;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
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

    @Test
    void sol_약수개수성능비교(){

        long number = 1567349871579687498L;
        
        Map<Integer, Integer> sol1map = new HashMap<>();
        Map<Integer, Integer> sol2map = new HashMap<>();

        // Loop 타입
        long beforeTime = System.currentTimeMillis();
        int count1 = s1.getDivisorCount1(number);
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime);
        NumberFormat formater = NumberFormat.getInstance();
        
        System.out.printf("Loop 방식: %s (ms)\n", formater.format(secDiffTime));

        // 소인수분해 타입
        beforeTime = System.currentTimeMillis();
        int count2 = s1.getDivisorCount2(number);
        afterTime = System.currentTimeMillis();
        secDiffTime = (afterTime - beforeTime);
        System.out.printf("소인수분해 방식: %s (ms)\n", formater.format(secDiffTime));

        System.out.printf("Number: %d, count1: %d, count2: %d\n", number, count1, count2);

    }
}