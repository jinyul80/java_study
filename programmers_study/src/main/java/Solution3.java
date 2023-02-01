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
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.stream.Stream;

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

    // 주차 요금 계산
    public int[] solution9(int[] fees, String[] records) {

        // 차량 입차 관리 Map
        Map<String, Integer> timeMap = new HashMap<>();
        // 차량별 누적 주차시간 Map
        Map<String, Integer> totalTimeMap = new TreeMap<>();

        for (String record : records) {
            String[] strList = record.split(" ");
            int minute = timeToMinute(strList[0]);
            String carNo = strList[1];

            if (strList[2].equals("IN")) {
                // 입차 시간 기록
                timeMap.put(carNo, minute);

            } else {
                // 출차인 경우 주차 시간 계산
                int parkingMinute = minute - timeMap.get(carNo);
                totalTimeMap.put(carNo, totalTimeMap.getOrDefault(carNo, 0) + parkingMinute);

                // 입차 map에서 삭제
                timeMap.remove(carNo);
            }
        }

        // 출차 기록이 없는 차량 처리
        for (Entry<String, Integer> entry : timeMap.entrySet()) {
            int parkingMinute = (23 * 60 + 59) - entry.getValue();
            String carNo = entry.getKey();
            totalTimeMap.put(carNo, totalTimeMap.getOrDefault(carNo, 0) + parkingMinute);
        }

        // 요금 계산
        int[] answer = new int[totalTimeMap.size()];
        int idx = 0;

        for (Entry<String, Integer> entry : totalTimeMap.entrySet()) {
            int fee = fees[1];

            if (entry.getValue() > fees[0]) {
                fee += Math.ceil((float) (entry.getValue() - fees[0]) / fees[2]) * fees[3];
            }

            answer[idx] = fee;
            idx++;
        }

        return answer;
    }

    // 주차요금계산 문제 시간 문자열을 분으로 바꾸는 함수
    public int timeToMinute(String str) {
        String[] arr = str.split(":");
        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }

    // 할인행사
    public int solution10(String[] want, int[] number, String[] discount) {
        int answer = 0;

        // 구매할 항목
        Map<String, Integer> buyMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            buyMap.put(want[i], number[i]);
        }

        for (int idx = 0; idx < discount.length - 9; idx++) {
            // 인덱스부터 10일씩 할인 목록
            Map<String, Integer> itemMap = new HashMap<>();

            for (int j = idx; j < idx + 10; j++) {
                String key = discount[j];
                itemMap.put(key, itemMap.getOrDefault(key, 0) + 1);
            }

            // 구매할 항목을 모두 구매 할 수 있는지 체크
            boolean flag = true;
            for (Entry<String, Integer> entry : buyMap.entrySet()) {
                if (!itemMap.containsKey(entry.getKey())) {
                    flag = false;
                    break;
                } else if (entry.getValue() != itemMap.get(entry.getKey())) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                answer++;
            }
        }

        return answer;
    }

    // 디펜스 게임
    public int solution11(int n, int k, int[] enemy) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < enemy.length; i++) {
            int e = enemy[i];
            pq.add(e);

            if (n < e) {
                if (k == 0) {
                    break;
                } else {
                    k--;
                    n += pq.poll() - e;
                }
            } else {
                n -= e;
            }

            answer++;
        }

        return answer;
    }

    // 마법의 엘리베이터
    public int solution12(int storey) {
        int answer = 0;

        Stack<Integer> stack = new Stack<>();

        // 숫자를 한자리씩 나누어서 stack에 추가
        Stream.of(String.valueOf(storey)
                        .split(""))
              .mapToInt(Integer::parseInt)
              .forEach(stack::push);

        while (!stack.isEmpty()) {
            int num = stack.pop();

            if (num > 5) {
                // 5 초과인 경우 윗자리에 +1 적용
                answer += 10 - num;

                if (stack.isEmpty()) {
                    answer++;
                } else {
                    int temp = stack.pop() + 1;
                    stack.push(temp);
                }
            } else if (num == 5) {
                // 5 인 경우 윗자리를 기준으로 어떻게 할 것인지 결정
                answer += 10 - num;

                if (!stack.isEmpty() && stack.peek() >= 5) {
                    int temp = stack.pop() + 1;
                    stack.push(temp);
                }
            } else {
                // 5 미만인 경우
                answer += num;
            }
        }

        return answer;
    }

    // 테이블 해시 함수
    public int solution13(int[][] data, int col, int row_begin, int row_end) {

        // 정렬 전 data
        System.out.println(Arrays.deepToString(data));

        // 정렬
        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[col - 1] == o2[col - 1]) {
                    return o2[0] - o1[0];
                } else {
                    return o1[col - 1] - o2[col - 1];
                }
            }
        });

        // 정렬 후 data
        System.out.println("*** change ***");
        System.out.println(Arrays.deepToString(data));

        // row별 나머지 합
        List<Integer> modList = new ArrayList<>();
        for (int i = row_begin - 1; i <= row_end - 1; i++) {
            int[] row = data[i];

            int modSum = 0;
            for (int j = 0; j < row.length; j++) {
                modSum += row[j] % (i + 1);
            }

            modList.add(modSum);

            System.out.printf("RowIndex: %d, Data: '%s', modSum: %d\n", i, Arrays.toString(row),
                modSum);
        }

        // 나머지 리스트의 누적 XOR 연산
        int answer = modList.get(0);
        for (int i = 1; i < modList.size(); i++) {
            answer = (answer ^ modList.get(i));
        }

        return answer;
    }

    // 시소짝궁
    public long solution14(int[] weights) {
        long answer = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int weight : weights) {
            map.put(weight, map.getOrDefault(weight, 0) + 1);
        }

        Set<Integer> keySet = new HashSet<>(map.keySet());

        for (int key : keySet) {

            int weightCount = map.get(key);
            if (weightCount > 1) {
                answer += (long) weightCount * (weightCount - 1) / 2;
            }

            int findKey = 0;
            int w = 0;

            w = key * 2;
            findKey = w / 3;
            if (w % 3 == 0) {
                if (map.containsKey(findKey)) {
                    answer += (long) weightCount * map.get(findKey);
                }
            }
            findKey = w / 4;
            if (w % 4 == 0) {
                if (map.containsKey(findKey)) {
                    answer += (long) weightCount * map.get(findKey);
                }
            }

            w = key * 3;
            findKey = w / 2;
            if (w % 2 == 0) {
                if (map.containsKey(findKey)) {
                    answer += (long) weightCount * map.get(findKey);
                }
            }
            findKey = w / 4;
            if (w % 4 == 0) {
                if (map.containsKey(findKey)) {
                    answer += (long) weightCount * map.get(findKey);
                }
            }

            w = key * 4;
            findKey = w / 2;
            if (w % 2 == 0) {
                if (map.containsKey(findKey)) {
                    answer += (long) weightCount * map.get(findKey);
                }
            }
            findKey = w / 3;
            if (w % 3 == 0) {
                if (map.containsKey(findKey)) {
                    answer += (long) weightCount * map.get(findKey);
                }
            }

            map.remove(key);
        }

        return answer;
    }
}
