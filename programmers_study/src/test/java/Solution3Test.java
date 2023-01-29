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
        int[] elements = {7, 9, 1, 1, 4};

        int answer = s3.solution6(elements);
        int actual = 18;

        assertEquals(answer, actual);
    }

    @Test
    void sol_혼자놀기의달인() {
        int[] cards = {8, 6, 3, 7, 2, 5, 1, 4};

        int answer = s3.solution7(cards);
        int actual = 12;

        assertEquals(answer, actual);
    }

    @Test
    void sol_두큐합같게만들기1() {
        int[] queue1 = {3, 2, 7, 2};
        int[] queue2 = {4, 6, 5, 1};

        int answer = s3.solution8(queue1, queue2);
        int actual = 2;

        assertEquals(answer, actual);
    }

    @Test
    void sol_두큐합같게만들기2() {
        int[] queue1 = {1, 2, 1, 2};
        int[] queue2 = {1, 10, 1, 2};

        int answer = s3.solution8(queue1, queue2);
        int actual = 7;

        assertEquals(answer, actual);
    }

    @Test
    void sol_두큐합같게만들기3() {
        int[] queue1 = {1, 1};
        int[] queue2 = {1, 5};

        int answer = s3.solution8(queue1, queue2);
        int actual = -1;

        assertEquals(answer, actual);
    }

    @Test
    void sol_주차요금계산() {
        int[] fees = {180, 5000, 10, 600};
        String[] recodes = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT",
            "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};

        int[] answer = s3.solution9(fees, recodes);
        int[] actual = {14600, 34400, 5000};

        assertArrayEquals(answer, actual);
        ;
    }

    @Test
    void 할인행사() {
        String[] wants = {"banana", "apple", "rice", "pork", "pot"};
        int[] numbers = {3, 2, 2, 2, 1};
        String[] discounts = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork",
            "banana", "pork", "rice", "pot", "banana", "apple", "banana"};

        int answer = s3.solution10(wants, numbers, discounts);
        int actual = 3;

        assertEquals(answer, actual);
    }

    @Test
    void 디펜스게임() {
        int n = 7;
        int k = 3;
        int[] enemys = {4, 2, 4, 5, 3, 3, 1};

        int answer = s3.solution11(n, k, enemys);
        int actual = 5;

        assertEquals(answer, actual);
    }

    @Test
    void 디펜스게임2() {
        int n = 3;
        int k = 5;
        int[] enemys = {1, 2, 10};

        int answer = s3.solution11(n, k, enemys);
        int actual = 3;

        assertEquals(answer, actual);
    }

    @Test
    void 마법의엘리베이터() {
        int storey = 95;

        int answer = s3.solution12(storey);
        int actual = 6;

        assertEquals(answer, actual);
    }

    @Test
    void 테이블해시함수() {
        int[][] data = {{2,2,6},{1,5,10},{4,2,9},{3,8,3}};
        int col = 2;
        int row_begin = 2;
        int row_end = 3;

        int answer = s3.solution13(data, col, row_begin, row_end);
        int actual = 4;

        assertEquals(answer, actual);

    }
}