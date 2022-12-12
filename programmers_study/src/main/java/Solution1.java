import java.util.Arrays;
import java.util.LinkedList;
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

}
