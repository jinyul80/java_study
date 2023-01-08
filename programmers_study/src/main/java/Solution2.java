import java.util.Arrays;

public class Solution2 {

    // K번째수
    public int[] solution1(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            int[] command = commands[i];
            int idxS = command[0] - 1;
            int idxE = command[1];
            int choice = command[2] - 1;

            int[] arr = Arrays.copyOfRange(array, idxS, idxE);
            Arrays.sort(arr);

            answer[i] = arr[choice];
        }

        return answer;
    }

    // 소수 만들기
    public int solution2(int[] nums) {
        int answer = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int num = nums[i] + nums[j] + nums[k];
                    if (isPrime(num)) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }

    // 에라토스테네스의 체
    boolean isPrime(int num) {
        if (num == 0 || num == 1) {
            return false;
        }

        int limit = (int) Math.sqrt(num);

        for (int idx = 2; idx <= limit; idx++) {
            if (num % idx == 0) {
                return false;
            }
        }

        return true;
    }

    public int solution3(int[] d, int budget) {
        int answer = 0;

        Arrays.sort(d);
        int sum = 0;

        for (int i = 0; i < d.length; i++) {
            sum += d[i];
            if (sum <= budget) {
                answer++;
            } else {
                break;
            }
        }

        return answer;
    }

}
