import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArcheryCompetitionTest {

    @Test
    void solution() {
        int n = 9;
        int[] infos = {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1};

        ArcheryCompetition sol = new ArcheryCompetition();

        int[] answer = sol.solution(n, infos);
        int[] actual = {1, 1, 2, 0, 1, 2, 2, 0, 0, 0, 0};

        assertArrayEquals(answer, actual);

    }
}