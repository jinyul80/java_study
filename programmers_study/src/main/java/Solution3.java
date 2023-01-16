import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

            int cnt = map.keySet().size();
            if (cnt == set.size()) {
                answer++;
            } else if (cnt < set.size()) {
                break;
            }

        }

        return answer;
    }

}
