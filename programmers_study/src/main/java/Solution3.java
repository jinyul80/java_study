import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution3 {

    // 귤 고르기
    public int solution1(int k, int[] tangerine) {
        int answer = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : tangerine) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer> typeCount = new ArrayList<>(map.values());

        typeCount.sort(Comparator.reverseOrder());

        for (int i = 0; i < typeCount.size(); i++) {
            answer++;
            k -= typeCount.get(i);
            if (k <= 0) {
                break;
            }
        }

        return answer;
    }

    // 점 찍기
    public long solution2(int k, int d) {
        long answer = 0;

        for (int x = 0; x <= d; x += k) {
            double yDist = Math.sqrt(Math.pow(d, 2) - Math.pow(x, 2));
            int yCount = (int) (yDist / k);

            System.out.printf("%d %.3f %d\n", x, yDist, yCount);
            answer += yCount + 1;
        }

        return answer;
    }

}
