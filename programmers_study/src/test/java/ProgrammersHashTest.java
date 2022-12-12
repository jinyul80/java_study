import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProgrammersHashTest {

    private final ProgrammersHash p = new ProgrammersHash();

    @Test
    void solution_전화번호목록() {
        String[] ph = {"119", "97674223", "1195524421"};

        boolean answer = p.solution1(ph);
        assertFalse(answer);

    }

    @Test
    void solution_완주하지못한선수() {
        String[] participant = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki"};

        String answer = p.solution2(participant, completion);
        assertEquals(answer, "leo");
    }

    @Test
    void solution_위장() {
        String[][] clothes = {{"yellow_hat", "headgear"},
            {"blue_sunglasses", "eyewear"},
            {"green_turban", "headgear"}};

        int answer = p.solution3(clothes);
        assertEquals(answer, 5);
    }

    @Test
    void solution_메뉴리뉴얼() {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};

        String[] answer = p.solution4(orders, course);
        String[] result = {"AC", "ACDE", "BCFG", "CDE"};
        
        assertArrayEquals(answer, result);
    }
}