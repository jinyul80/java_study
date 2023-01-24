import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

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

    // 숫자 카드 나누기
    public int solution3(int[] arrayA, int[] arrayB) {
        int answer = 0;

        int minA = Arrays.stream(arrayA)
                         .min()
                         .getAsInt();
        int minB = Arrays.stream(arrayB)
                         .min()
                         .getAsInt();

        for (int min : new int[]{minA, minB}) {
            // 약수 리스트
            List<Integer> divisorList = getDivisorList(min);

            for (int i = divisorList.size() - 1; i >= 0; i--) {
                int div = divisorList.get(i);

                boolean flagA = Arrays.stream(arrayA)
                                      .allMatch(v -> v % div == 0);
                boolean flagB = Arrays.stream(arrayB)
                                      .allMatch(v -> v % div != 0);

                if (flagA && flagB) {
                    if (div > answer) {
                        answer = div;
                    }
                    break;
                }

                boolean flagC = Arrays.stream(arrayB)
                                      .allMatch(v -> v % div == 0);
                boolean flagD = Arrays.stream(arrayA)
                                      .allMatch(v -> v % div != 0);

                if (flagC && flagD) {
                    if (div > answer) {
                        answer = div;
                    }
                    break;
                }

            }
        }

        return answer;
    }

    /**
     * 약수 리스트 조회
     *
     * @param num 약수를 구하려는 수
     * @return 약수 리스트
     */
    private List<Integer> getDivisorList(int num) {
        List<Integer> divisorList = new ArrayList<>();
        for (int i = 1; i < Math.sqrt(num); i++) {
            if (num % i == 0) {
                divisorList.add(i);

                if (num / i != i) {
                    divisorList.add(num / i);
                }
            }
        }

        divisorList.sort(Comparator.naturalOrder());
        return divisorList;
    }

    // 롤케이크자르기
    public int solution4(int[] topping) {
        int answer = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : topping) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < topping.length - 1; i++) {
            int num = topping[i];
            set.add(num);

            if (map.get(num) == 1) {
                map.remove(num);
            } else {
                map.put(num, map.get(num) - 1);
            }

            int cnt = map.keySet()
                         .size();
            if (cnt == set.size()) {
                answer++;
            } else if (cnt < set.size()) {
                break;
            }

        }

        return answer;
    }

    // 택배상자
    public int solution5(int[] order) {
        int answer = 0;

        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < order.length + 1; i++) {
            if (i == order[answer]) {
                // 현재 box번호가 order번호와 일치하는 경우
                answer++;

                while (stack.size() > 0) {
                    // 보조 컨베이어의 최상단의 box번호가 order번호가 일치하는제 체크
                    if (stack.peek() == order[answer]) {
                        answer++;
                        stack.pop();
                    } else {
                        break;
                    }
                }

            } else if (i < order[answer]) {
                // 현재 box번호가 order번호 보다 작은 경우 보조 컨베이어로 이동
                stack.push(i);
            } else {
                // 현재 box번호가 order번호 보다 큰 경우 종료
                break;
            }
        }

        return answer;
    }

    // 연속 부분 수열 합의 개수
    public int solution6(int[] elements) {
        // 연속 수열을 위해 배열 2배로 새로 형성
        int[] newElements = new int[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
            newElements[i + elements.length] = elements[i];
        }

        // 중복 수 제거를 위해 Set 사용
        Set<Integer> set = new HashSet<>();

        for (int len = 1; len <= elements.length; len++) {
            for (int idx = 0; idx < elements.length; idx++) {
                // idx부터 len길이까지 배열을 새로 만들고 합계를 Set에 추가
                set.add(Arrays.stream(newElements, idx, idx + len)
                              .sum());
            }
        }

        return set.size();
    }

    // 혼자 놀기의 달인
    public int solution7(int[] cards) {
        int answer = 0;

        int[] opened = new int[cards.length];
        List<Integer> scoreList = new ArrayList<>();

        int openCount = 0;
        int score = 0;
        int idx = 0;
        while (true) {
            if (opened[idx] == 0) {
                // 처음 Open한 상자인 경우
                openCount++;
                score++;
                opened[idx] = 1;

                idx = cards[idx] - 1;

            } else {
                // 이미 열린 상자인 경우
                scoreList.add(score);
                score = 0;

                // 상자를 모두 열었으면 종료
                if (openCount == cards.length) {
                    break;
                }

                // Open하지 않은 상자 번호를 조회
                for (int i = 0; i < cards.length; i++) {
                    if (opened[i] == 0) {
                        idx = i;
                        break;
                    }
                }
            }
        }

        // 점수 리스트 정렬해서 최고 점수 2개 곱셈
        if (scoreList.size() > 1) {
            scoreList.sort(Comparator.reverseOrder());
            answer = scoreList.get(0) * scoreList.get(1);
        }

        return answer;
    }

    // 두 큐 합 같게 만들기
    public int solution8(int[] queue1, int[] queue2) {
        // 큐 생성
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        int maxNum = 0;
        long sum1 = 0;
        long sum2 = 0;

        for (int i = 0; i < queue1.length; i++) {
            int num1 = queue1[i];
            q1.add(num1);
            maxNum = Math.max(maxNum, num1);
            sum1 += num1;

            int num2 = queue2[i];
            q2.add(num2);
            maxNum = Math.max(maxNum, num2);
            sum2 += num2;
        }

        // 최대 숫자가 전체합의 반보다 큰 경우 -1 리턴
        if (maxNum > (sum1 + sum2) / 2) {
            return -1;
        } else if (sum1 == sum2) {
            return 0;
        }

        int loofCount = 0;
        while (sum1 != sum2) {
            // 무한 반복 방지
            if (loofCount >= queue1.length * 3) {
                return -1;
            }

            if (sum1 < sum2) {
                int num = q2.poll();
                q1.add(num);

                sum2 -= num;
                sum1 += num;
            } else {
                int num = q1.poll();
                q2.add(num);

                sum1 -= num;
                sum2 += num;
            }
            loofCount++;
        }

        return loofCount;
    }

}
