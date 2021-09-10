package com.study;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class kakaotest1 {
    public static void main(String[] args) {

        var s1 = new Solution1();
        // String id = "";
        // id = "z-.^.";
        // // id = "...!@BaT#*..y.abcdefghijklm";
        // var result = s1.solution1(id);


        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2, 3, 4};

        String[] result = s1.solution2(orders, course);

        System.out.println(Arrays.toString(result));
    }
}

class Solution1 {
    //신규 ID 추천
    public String solution1(String new_id) {
        String answer = "";

        new_id = new_id.toLowerCase();

        new_id = new_id.replaceAll("[^-_.0-9a-z]", "");

        while (true) {
            if (new_id.contains("..")) {
                new_id = new_id.replaceAll("\\.\\.", "\\.");
            } else {
                break;
            }
        }

        if (new_id.startsWith(".")) {
            new_id = new_id.substring(1);
        }
        if (new_id.endsWith(".")) {
            new_id = new_id.substring(0, new_id.length() - 1);
        }
        if (new_id.length() == 0) {
            new_id = "a";
        } else if (new_id.length() > 15) {
            new_id = new_id.substring(0, 15);
            if (new_id.endsWith(".")) {
                new_id = new_id.substring(0, new_id.length() - 1);
            }
        }
        if (new_id.length() <= 2) {
            var lastChar = new_id.charAt(new_id.length() - 1);

            while (new_id.length() <= 2) {
                new_id = new_id + lastChar;
            }
        }

        answer = new_id;

        return answer;
    }

    //메뉴 리뉴얼
    public String[] solution2(String[] orders, int[] course) {
        String[] answer = {};
        List<String> newMenuList = new ArrayList<String>();

        Map<String, Integer> hm = new HashMap<String, Integer>();

        for (String order : orders) {
            String[] arr = order.split("");
            Arrays.sort(arr);

            boolean[] visited = new boolean[arr.length];
            for (int num : course) {
                if ( arr.length >= num ) {
                    List<String> order_combination_list = combi(arr, visited, 0, num);

                    for (String order_combination : order_combination_list) {
                        hm.put(order_combination, hm.getOrDefault(order_combination, 0) + 1);
                    }
                }
            }
        }

        for (int num : course) {
            Map<String, Integer> opt = hm.entrySet().stream()
                        .filter(e -> e.getKey().length() == num)
                        .collect(Collectors.toMap(k -> k.getKey(), v ->v.getValue()));

            if ( opt.size() == 0 ) {
                continue;
            }

            int maxOrderCount = Collections.max(opt.values());
            if (maxOrderCount > 1) {
                opt.entrySet().stream()
                        .filter(e -> e.getValue().equals(maxOrderCount))
                        .forEach(e -> newMenuList.add(e.getKey()));
            }
        }

        newMenuList.sort(Comparator.naturalOrder());
        answer = newMenuList.toArray(new String[newMenuList.size()]);

        return answer;
    }

    static List<String> combi(String[] arr, boolean[] visited, int depth, int r) {
        List<String> re = new ArrayList<>();

        // System.out.println("Visited:" + Arrays.toString(visited) + ", depth:" + depth + ", r:" + r);

        if (r == 0) {
            re.add(getCombiString(arr, visited));

        } else if (depth == arr.length) {
            return re;

        } else {
            visited[depth] = true;
            List<String> tmp = combi(arr, visited, depth + 1, r - 1);
            if (tmp.size() > 0) {
                re.addAll(tmp);
            }


            visited[depth] = false;
            tmp = combi(arr, visited, depth + 1, r);
            if (tmp.size() > 0) {
                re.addAll(tmp);
            }
        }

        return re;
    }

    static String getCombiString(String[] arr, boolean[] visited) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            if (visited[i] == true)
                sb.append(arr[i]);
        }

        return sb.toString();
    }
}