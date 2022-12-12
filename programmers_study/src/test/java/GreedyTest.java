import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GreedyTest {

    Greedy sol = new Greedy();

    @Test
    void solution1() {
        int[] losts = {2, 4};
        int[] reserves = {1, 3, 5};

        int result = sol.solution1(5, losts,  reserves);
        assertEquals(result, 5);
    }
}