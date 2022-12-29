import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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

    // 성격유형 검사하기
    public String solution2(String[] survey, int[] choices) {
        String answer = "";

        String[] types = {"RT", "CF", "JM", "AN"};
        Map<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < survey.length ; i++) {
            String surveyType = survey[i];
            if (choices[i] < 4) {
                char key = surveyType.charAt(0);
                hashMap.put(key,  hashMap.getOrDefault(key, 0) + (4-choices[i]));

            } else if (choices[i] > 4) {
                char key = surveyType.charAt(1);
                hashMap.put(key,  hashMap.getOrDefault(key, 0) + (choices[i]-4));
            }
        }

        for (String type: types       ) {
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

        for (int i = 0; i < s.length() ; i++) {
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
        for (int idx = 0; idx <= t.length() - len ; idx++) {
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

        for (int i = 0; i < score.length ; i++) {
            if (i <= k) {
                scoreList.add(score[i]);

            } else if (score[i] > minScore){
                scoreList.add(score[i]);

            }

            scoreList.sort(Comparator.reverseOrder());
            if (scoreList.size() > k) {
                scoreList.remove(k);
            }

            minScore = scoreList.get(scoreList.size()-1);
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
            } else if (Arrays.stream(win_nums).anyMatch(v -> v == num)) {
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


}
