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

    @Test
    void sol_순위검색() {
        String[] infos = {"java backend junior pizza 150", "python frontend senior chicken 210",
            "python frontend senior chicken 150", "cpp backend senior pizza 260",
            "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] querys = {"java and backend and junior and pizza 100",
            "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
            "- and backend and senior and - 150", "- and - and - and chicken 100",
            "- and - and - and - 150"};

        int[] answer = p.solution5(infos, querys);
        assertArrayEquals(answer, new int[]{1, 1, 1, 1, 2, 4});
    }

    @Test
    void sol_신고결과받기() {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] reportList = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo",
            "apeach muzi"};
        int k = 2;
        int[] actual = {2,1,1,0};

        int[] answer = p.solution6(id_list, reportList, k);

        assertArrayEquals(answer, actual);

    }


}