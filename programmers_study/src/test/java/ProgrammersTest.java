import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProgrammersTest {

    private final Programmers p = new Programmers();

    @Test
    void solution_hamberger1() {
        int[] nums = {2, 1, 1, 2, 3, 1, 2, 3, 1};

        int ans = p.solution_hamberger(nums);

        assertEquals(ans, 2);
    }

    @Test
    void solution_hamberger2() {
        int[] nums = {2, 1, 1, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1};

        int ans = p.solution_hamberger(nums);

        assertEquals(ans, 3);
    }

    @Test
    void solution_ongali1() {
        String[] babbling = {"aya", "yee", "u", "maa"};

        int score = p.solution2(babbling);
        assertEquals(score, 1);

    }

    @Test
    void solution_ongali2() {
        String[] babbling = {"ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"};

        int score = p.solution2(babbling);
        assertEquals(score, 2);

    }

    @Test
    void solution_cola1() {
        int score = p.solution3(2, 1, 20);
        assertEquals(score, 19);

        score = p.solution3(3, 1, 20);
        assertEquals(score, 9);
    }

    @Test
    void solution_삼총사() {
        int[] nums = {-2, 3, 0, 2, -5};
        int score = p.solution4(nums);
        assertEquals(score, 2);
    }

    @Test
    void solution_숫자짝궁() {
        String X = "123456785321654648213198453132484613213218132187132165489";
        String Y = "5548732168321348463154984321324754732166545785";

        String answer = p.solution5(X, Y);
        assertEquals(answer, "98888877666655555444444433333332222211111");


    }


    @Test
    void solution_소수찾기() {
        String numbers = "1739";
        int answer = p.solution8(numbers);
        assertEquals(answer, 32);
    }

    @Test
    void sol_키패드누르기() {
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";
        String answer = p.solution9(numbers, hand);
        String actual = "LRLLLRLLRRL";
        assertEquals(answer, actual);
    }

    @Test
    void sol_타겟넘버() {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;

        int answer = p.solution10(numbers, target);
        int actual = 5;

        assertEquals(answer, actual);
    }

    @Test
    void sol_약수의개수와덧셈() {
        int left = 13;
        int right = 17;

        int answer = p.solution11(left, right);
        int actual = 43;
        assertEquals(answer, actual);
    }

    @Test
    void sol_기사단원의무기1() {
        int number = 5;
        int limit = 3;
        int power = 2;

        int answer = p.solution12(number, limit, power);
        int actual = 10;
        assertEquals(answer, actual);
    }

    @Test
    void sol_기사단원의무기2() {
        int number = 10;
        int limit = 3;
        int power = 2;

        int answer = p.solution12(number, limit, power);
        int actual = 21;
        assertEquals(answer, actual);
    }

    @Test
    void sol_문자열나누기() {
        String s = "aaabbaccccabba";

        int answer = p.solution12(s);
        int actual = 3;
        assertEquals(answer, actual);
    }

    @Test
    void sol_숫자문자열과영단어() {
        String s = "2three45sixseven";

        int answer = p.solution13(s);
        int actual = 234567;
        assertEquals(answer, actual);
    }

}