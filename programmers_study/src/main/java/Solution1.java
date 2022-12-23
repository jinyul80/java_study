import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution1 {
    public void printHelloWorld() {
        System.out.println("Hello World!!!");
    }

    public int solution1(int[] priorities, int location) {
        int answer = 0;

        // [idx, priority]
        Queue<int[]> queue = new LinkedList<>();

        for (int idx = 0; idx < priorities.length ; idx++) {
            int[] task = {idx, priorities[idx]};
            queue.add(task);
        }

        while (!queue.isEmpty()){
            int[] task = queue.remove();
            if (queue.stream().anyMatch(x -> x[1] > task[1])) {
                queue.add(task);
            } else {
                answer++;
                if (task[0] == location) {
                    break;
                }
            }
        }

        return answer;
    }

    // 약수의 개수 구하기
    public int getDivisorCount1(long number) {
        int divisorCount = 0;
        for(int i = 1; i <= Math.sqrt(number); i++) {
            if (i == Math.sqrt(number)) {
                divisorCount++;
            } else if (number % i == 0) {
                divisorCount += 2;
            }
        }

        return divisorCount;
    }

    public int getDivisorCount2(long number) {
        int divisorCount = 0;

        Map<Long, Integer> map = new HashMap<>();
        int sqrtNum = (int) Math.sqrt(number);

        for (long i = 2; i <= sqrtNum; i++) {
            while (number % i == 0) {
                map.put(i, map.getOrDefault(i, 0) + 1);
                number /= i;
            }
        }

        if (number > 1) {
            map.put(number, map.getOrDefault(number, 0) + 1);
        }

        divisorCount = 1;
        for (int cnt : map.values()) {
            divisorCount *= cnt + 1;
        }

        return divisorCount;
    }



}
