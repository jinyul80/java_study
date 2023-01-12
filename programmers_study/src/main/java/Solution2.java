import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    // 예산
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

    // 다트게임
    public int solution4(String dartResult) {
        Pattern pattern = Pattern.compile("[0-9]+[SDT][*#]?");
        Matcher matcher = pattern.matcher(dartResult);
        int[] scores = new int[3];
        int idx = 0;

        while (matcher.find()) {
            String str = matcher.group();

            int tempNum = Integer.parseInt(str.replaceAll("[A-Z#*]", ""));
            String expStr = str.replaceAll("[0-9#*]", "");
            int exp = switch (expStr) {
                case "D" -> 2;
                case "T" -> 3;
                default -> 1;
            };
            String specialStr = str.replaceAll("[0-9A-Z]", "");
            int special = 1;
            if (specialStr.equals("*")) {
                special = 2;

                if (idx != 0) {
                    scores[idx - 1] *= special;
                }

            } else if (specialStr.equals("#")) {
                special = -1;
            }

            scores[idx] = (int) (Math.pow(tempNum, exp) * special);
            idx++;
        }

        return Arrays.stream(scores)
                     .sum();
    }

    // 비밀지도
    public String[] solution5(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            String numStr1 = sol5_change(arr1[i], n);
            String numStr2 = sol5_change(arr2[i], n);

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < numStr1.length(); j++) {
                if (numStr1.charAt(j) == '1' || numStr2.charAt(j) == '1') {
                    sb.append('#');
                } else {
                    sb.append(' ');
                }
            }
           answer[i] = sb.toString();
        }

        return answer;
    }

    private String sol5_change(int number, int length) {
        // 진법변환        
        StringBuilder sb = new StringBuilder(Integer.toString(number, 2));
        while (sb.length() < length) {
            sb.insert(0, "0");
        }

        return sb.toString();
    }

    // x만큰 간격이 있는 n개의 숫자
    public long[] solution6(int x, int n) {
        long[] answer = new long[n];

        for (int i = 0; i < n ; i++) {
            answer[i] = (long) x + ((long) x * i);
        }

        return answer;
    }

    // 행렬의 덧셈
    public int[][] solution7(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr1[0].length];

        for (int i = 0; i < arr1.length ; i++) {
            for (int j = 0; j < arr1[0].length ; j++) {
                answer[i][j] = arr1[i][j] + arr2[i][j];
            }
        }

        return answer;
    }

    // 핸드폰 번호 가리기
    public String solution8(String phone_number) {

        StringBuilder sb = new StringBuilder();
        var starLength = phone_number.length() - 4;

        for (int i = 0; i < starLength ; i++) {
            sb.append("*");
        }
        sb.append(phone_number.substring(starLength));

        return sb.toString();
    }

    // 하샤드 수
    public boolean solution9(int x) {
        boolean answer = true;

        if (x > 10) {
            String numStr = Integer.toString(x);
            char[] arr = numStr.toCharArray();

            int div = 0;
            for (int i = 0; i < arr.length ; i++) {
                div += Character.getNumericValue(arr[i]);
            }

            if (x % div != 0) {
                answer = false;
            }
        }

        return answer;
    }

}
