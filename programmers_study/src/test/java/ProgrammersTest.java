
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
}