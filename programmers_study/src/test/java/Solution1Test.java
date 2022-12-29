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

    @Test
    void test() {
        int[] numbers = {1,2,3,4,6,7,8,0};
        int answer = 0;

        int[] nums = new int[10];


        for (int i = 0; i < numbers.length ; i++) {
            nums[numbers[i]] = 1;
        }

        for (int i = 0; i < nums.length ; i++) {
            if (nums[i] == 0) {
                answer += i;
            }
        }
        System.out.println(answer);

    }

    @Test
    void sol_성격유형검사하기() {
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = {5, 3, 2, 7, 5};

        String answer = s1.solution2(survey, choices);
        String actual = "TCMA";

        assertEquals(answer, actual);

    }

    @Test
    void sol_가장가까운같은글자() {
        String str = "banana";

        int[] answer = s1.solution3(str);
        int[] actual = {-1, -1, -1, 2, 2, 2};

        assertArrayEquals(answer, actual);
    }

    @Test
    void sol_크기가작은부분문자열() {
        String t = "500220839878";
        String p = "7";

        int answer = s1.solution4(t, p);
        int actual = 8;

        assertEquals(answer, actual);
    }

    @Test
    void sol_명예의전당() {
        int k = 3;
        int[] scores = {10, 100, 20, 150, 1, 100, 200};

        int[] answer = s1.solution5(k, scores);
        int[] actual = {10, 10, 10, 20, 20, 100, 100};

        assertArrayEquals(answer, actual);

    }

}