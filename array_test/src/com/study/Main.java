package com.study;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Solution s1 = new Solution();

        // Hash 테스트1
        String[] participants = {"Joe", "Leo", "James", "Joe"};
        String[] completion = {"Joe", "James", "Leo"};

        String r1 = s1.solution1(participants, completion);

        if (r1.equals("")) {
            System.out.println("모두 완주성공");

        } else {
            System.out.println("탈락자: " + r1);
        }

        // Hash 테스트2
        // String[] phone_book = {"97674223", "1195524421", "119"};
        String[] phone_book = {"123", "456", "789"};

        boolean r = s1.solution2(phone_book);
        System.out.println("Answer: " + r);

    }

}

class Solution {

    public String solution1(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> hm = new HashMap<>();
        for (String person : participant) hm.put(person, hm.getOrDefault(person, 0) + 1);
        for (String person : completion) hm.put(person, hm.get(person) - 1);

        for (String key : hm.keySet()){
            if (hm.get(key) != 0) {
                answer = key;
            }
        }

        return answer;
    }

    public boolean solution2(String[] phone_book) {
        boolean answer = true;

        HashMap<String, Integer> hm = new HashMap<>();
        for(String key : phone_book) hm.put(key, 0);

        for (String sourceString : phone_book) {
            for (int j = sourceString.length() - 1; j > 0; j--) {
                String findString = sourceString.substring(0, j);
                if (hm.containsKey(findString)) {
                    answer = false;
                    break;
                }
            }

            if (!answer) {
                break;
            }
        }

        return answer;
    }
}