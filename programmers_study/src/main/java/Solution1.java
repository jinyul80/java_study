import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class Solution1 {

    public void printHelloWorld() {
        System.out.println("Hello World!!!");
    }

    public int solution1(int[] priorities, int location) {
        int answer = 0;

        // [idx, priority]
        Queue<int[]> queue = new LinkedList<>();

        for (int idx = 0; idx < priorities.length; idx++) {
            int[] task = {idx, priorities[idx]};
            queue.add(task);
        }

        while (!queue.isEmpty()) {
            int[] task = queue.remove();
            if (queue.stream()
                     .anyMatch(x -> x[1] > task[1])) {
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
        for (int i = 1; i <= Math.sqrt(number); i++) {
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

    // 성격유형 검사하기
    public String solution2(String[] survey, int[] choices) {
        String answer = "";

        String[] types = {"RT", "CF", "JM", "AN"};
        Map<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < survey.length; i++) {
            String surveyType = survey[i];
            if (choices[i] < 4) {
                char key = surveyType.charAt(0);
                hashMap.put(key, hashMap.getOrDefault(key, 0) + (4 - choices[i]));

            } else if (choices[i] > 4) {
                char key = surveyType.charAt(1);
                hashMap.put(key, hashMap.getOrDefault(key, 0) + (choices[i] - 4));
            }
        }

        for (String type : types) {
            int score1 = hashMap.getOrDefault(type.charAt(0), 0);
            int score2 = hashMap.getOrDefault(type.charAt(1), 0);

            if (score1 > score2) {
                answer += type.charAt(0);
            } else if (score1 < score2) {
                answer += type.charAt(1);
            } else {
                char[] arr = type.toCharArray();
                Arrays.sort(arr);
                answer += arr[0];
            }
        }

        return answer;
    }

    // 가장 가까운 같은 글자
    public int[] solution3(String s) {
        int[] answer = new int[s.length()];

        Map<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);

            if (hashMap.containsKey(key)) {
                answer[i] = i - hashMap.get(key);
            } else {
                answer[i] = -1;
            }

            hashMap.put(key, i);
        }

        return answer;
    }

    // 크기가 작은 부분문자열
    public int solution4(String t, String p) {
        int answer = 0;

        long limitNum = Long.parseLong(p);
        int len = p.length();
        for (int idx = 0; idx <= t.length() - len; idx++) {
            String str = t.substring(idx, idx + len);
            long checkNum = Long.parseLong(str);
            if (checkNum <= limitNum) {
                answer++;
            }
        }

        return answer;
    }

    // 명예의전당(1)
    public int[] solution5(int k, int[] score) {
        int[] answer = new int[score.length];

        List<Integer> scoreList = new ArrayList<>();
        int minScore = 0;

        for (int i = 0; i < score.length; i++) {
            if (i <= k) {
                scoreList.add(score[i]);

            } else if (score[i] > minScore) {
                scoreList.add(score[i]);

            }

            scoreList.sort(Comparator.reverseOrder());
            if (scoreList.size() > k) {
                scoreList.remove(k);
            }

            minScore = scoreList.get(scoreList.size() - 1);
            answer[i] = minScore;

        }

        return answer;
    }

    // 로또의 최고 순위와 최저 순위
    public int[] solution6(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        int maxCount = 0;
        int minCount = 0;
        for (int i = 0; i < lottos.length; i++) {
            int num = lottos[i];
            if (num == 0) {
                maxCount++;
            } else if (Arrays.stream(win_nums)
                             .anyMatch(v -> v == num)) {
                maxCount++;
                minCount++;
            }
        }

        maxCount = maxCount == 0 ? 1 : maxCount;
        minCount = minCount == 0 ? 1 : minCount;

        answer[0] = 7 - maxCount;
        answer[1] = 7 - minCount;

        return answer;
    }

    // 음양 더하기
    public int solution7(int[] absolutes, boolean[] signs) {
        int answer = 0;

        for (int i = 0; i < absolutes.length; i++) {
            if (signs[i]) {
                answer += absolutes[i];
            } else {
                answer -= absolutes[i];
            }
        }

        return answer;
    }

    // 내적구하기
    public int solution8(int[] a, int[] b) {
        int answer = 0;

        for (int i = 0; i < a.length; i++) {
            answer += (a[i] * b[i]);
        }

        return answer;
    }

    // 크레인 인형뽑기
    public int solution9(int[][] board, int[] moves) {
        int answer = 0;

        Stack<Integer> stack = new Stack<>();
        for (int idx = 0; idx < moves.length; idx++) {
            int col = moves[idx] - 1;

            for (int row = 0; row < board.length; row++) {
                if (board[row][col] == 0) {
                    continue;
                }

                int toyNum = board[row][col];
                board[row][col] = 0;

                if (stack.isEmpty()) {
                    stack.push(toyNum);
                } else {
                    if (toyNum == stack.peek()) {
                        answer += 2;
                        stack.pop();
                    } else {
                        stack.push(toyNum);
                    }
                }

                break;
            }
        }

        return answer;
    }

    // 3진법 뒤집기
    public int solution10(int n) {
        int answer = 0;

        String str = Integer.toString(n, 3);
        String reverse = new StringBuilder(str).reverse()
                                               .toString();
        answer = Integer.parseInt(reverse, 3);

        return answer;
    }

    // 두개 뽑아서 더하기
    public int[] solution11(int[] numbers) {

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }

        return set.stream()
                  .sorted()
                  .mapToInt(Integer::intValue)
                  .toArray();
    }

    // 모의고사
    public int[] solution12(int[] answers) {

        Map<Integer, int[]> resultMap = new HashMap<>();
        resultMap.put(1, new int[]{1, 2, 3, 4, 5});
        resultMap.put(2, new int[]{2, 1, 2, 3, 2, 4, 2, 5});
        resultMap.put(3, new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5});

        Map<Integer, Integer> scoreMap = new HashMap<>();

        for (int i = 0; i < answers.length; i++) {
            for (Entry<Integer, int[]> entry : resultMap.entrySet()) {
                int key = entry.getKey();
                int[] answerSheet = entry.getValue();

                if (answers[i] == answerSheet[i % answerSheet.length]) {
                    scoreMap.put(key, scoreMap.getOrDefault(key, 0) + 1);
                }
            }
        }

        int maxScore = Collections.max(scoreMap.values());
        List<Integer> bestList = new ArrayList<>();
        for (int key : scoreMap.keySet()) {
            if (maxScore == scoreMap.get(key)) {
                bestList.add(key);
            }
        }

        return bestList.stream()
                       .mapToInt(Integer::intValue)
                       .toArray();
    }

    // 실패율
    public int[] solution13(int N, int[] stages) {
        int[] answer = new int[N];

        int[] stageChallenger = new int[N + 1];
        int[] stageCurrent = new int[N + 1];

        for (int stage : stages) {
            for (int j = 0; j < stage; j++) {
                stageChallenger[j]++;
            }

            stageCurrent[stage - 1]++;
        }

        Map<Integer, Float> hashMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (stageCurrent[i] == 0) {
                hashMap.put(i, 0.0F);
            } else {
                hashMap.put(i, (stageCurrent[i] / (float) stageChallenger[i]));
            }
        }

        List<Float> failRateList = hashMap.values()
                                          .stream()
                                          .distinct()
                                          .sorted(Comparator.reverseOrder())
                                          .collect(Collectors.toList());

        List<Integer> tempList = new ArrayList<>();
        for (float failRate : failRateList) {
            hashMap.entrySet()
                   .stream()
                   .filter(entry -> entry.getValue() == failRate)
                   .map(entry -> entry.getKey() + 1)
                   .forEach(tempList::add);
        }

        answer = tempList.stream()
                         .mapToInt(Integer::intValue)
                         .toArray();

        return answer;
    }

}
