import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ProgrammersHash {

    // 해시 - 전화번호 목록
    public boolean solution1(String[] phone_book) {
        boolean answer = true;

        HashMap<String, Integer> map = new HashMap<>();
        for (String s : phone_book) {
            map.put(s, 1);
        }

        for (String s : phone_book) {
            for (int j = 1; j < s.length(); j++) {
                String key = s.substring(0, j);
                if (map.containsKey(key)) {
                    return false;
                }
            }
        }

        return answer;
    }

    // 해시 - 완주하지 못한 선수
    public String solution2(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> map = new HashMap<>();

        for (String key : participant) {
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        for (String key : completion) {
            map.put(key, map.get(key) - 1);
        }

        for (String key : map.keySet()) {
            if (map.get(key) != 0) {
                answer = key;
            }
        }

        return answer;
    }

    // 해시 - 위장
    public int solution3(String[][] clothes) {
        int answer = 0;

        HashMap<String, Integer> map = new HashMap<>();

        for (String[] clothe : clothes) {
            String type = clothe[1];
            map.put(type, map.getOrDefault(type, 0) + 1);
        }

        answer++;

        for (String key : map.keySet()) {
            answer = answer * (map.get(key) + 1);
        }

        return answer - 1;
    }

    // 해시 - 메뉴 리뉴얼
    public String[] solution4(String[] orders, int[] course) {

        List<String> answerList = new ArrayList<>();

        // 1. 각 Orders 정렬
        for (int idx = 0; idx < orders.length; idx++) {
            char[] arr = orders[idx].toCharArray();
            Arrays.sort(arr);
            orders[idx] = String.valueOf(arr);
        }

        // 2. 각 order를 기준으로 courseLength 만큼의 조합 만들기
        Map<String, Integer> map = new HashMap<>();
        for (int courseLength : course) {
            map.clear();

            for (String order : orders) {
                var newCourseList = combination("", order, courseLength);

                for (String key : newCourseList) {
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
            }

            // 3. 가장 많은 조합 answer에 저장
            if (!map.isEmpty()) {
                var max = map.values()
                             .stream()
                             .max(Comparator.comparing(x -> x))
                             .orElseThrow(NoSuchElementException::new);
                if (max > 1) {
                    for (String key : map.keySet()) {
                        if (map.get(key) == max) {
                            answerList.add(key);
                        }
                    }
                }
            }
        }

        Collections.sort(answerList);

        String[] answer = answerList.toArray(String[]::new);
        return answer;
    }

    private List<String> combination(String order, String others, int count) {
        List<String> result = new ArrayList<>();

        for (int idx = 0; idx < others.length(); idx++) {
            if (count == 1) {
                String newCourse = order + others.charAt(idx);
                result.add(newCourse);

            } else {
                var newCourseList = combination(order + others.charAt(idx),
                    others.substring(idx + 1), count - 1);
                result.addAll(newCourseList);
            }
        }

        return result;
    }

    // 해시 - 카카오 순위 검색
    public int[] solution5(String[] info, String[] query) {
        int[] answer = new int[query.length];

        HashMap<String, List<Integer>> hm = new HashMap<>();

        for (String user : info) {
            String[] data = user.split(" ");

            for (String lang : new String[]{data[0], "-"}) {
                for (String job : new String[]{data[1], "-"}) {
                    for (String exps : new String[]{data[2], "-"}) {
                        for (String food : new String[]{data[3], "-"}) {
                            String[] keyData = {lang, job, exps, food};
                            String key = String.join(" ", keyData);
                            List<Integer> arr = hm.getOrDefault(key, new ArrayList<Integer>());

                            arr.add(Integer.parseInt(data[4]));
                            hm.put(key, arr);
                        }
                    }
                }
            }
        }

        for (List<Integer> arr : hm.values()) {
            arr.sort(null);
        }

        int i = 0;
        for (String q : query) {
            String[] data = q.split(" and ");
            int target = Integer.parseInt(data[3].split(" ")[1]);
            data[3] = data[3].split(" ")[0];
            String key = String.join(" ", data);

            if (hm.containsKey(key)) {
                List<Integer> list = hm.get(key);

                int left = 0;
                int right = list.size();
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (list.get(mid) >= target) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                answer[i] = list.size() - left;

                // 효율성 시간 초과로 실패
                /*int count = (int) list.parallelStream()
                                      .filter(x -> x >= target)
                                      .count();
                answer[i] = count;*/
            }

            i++;
        }

        return answer;
    }

    // 카카오 - 신고결과받기
    public int[] solution6(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        HashSet<String> reportSet = new HashSet<>();
        for (String rep : report) {
            reportSet.add(rep);
        }

        HashMap<String, List<String>> notifyMap = new HashMap<>();
        for (String rep : reportSet) {
            String[] tempArray = rep.split(" ");
            String user = tempArray[0];
            String badUser = tempArray[1];

            List<String> arr = notifyMap.getOrDefault(badUser, new ArrayList<String>());
            arr.add(user);
            notifyMap.put(badUser, arr);
        }

        HashMap<String, Integer> reportHash = new HashMap<>();
        for (List<String> notifyList : notifyMap.values()) {
            if (notifyList.size() >= k) {
                for (String user : notifyList) {
                    reportHash.put(user, reportHash.getOrDefault(user, 0) + 1);
                }
            }
        }

        for (int i = 0; i < id_list.length; i++) {
            answer[i] = reportHash.getOrDefault(id_list[i], 0);
        }

        return answer;
    }

}
