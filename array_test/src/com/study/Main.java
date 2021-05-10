package com.study;

import java.util.*;

public class Main {

    public static void main(String[] args) {

//        String[] participants = {"Joe", "Leo", "James", "Joe"};
//        String[] finished_person = {"Joe", "James", "Leo"};
//
//        String fail_person = array_check1(participants, finished_person);
//
//        System.out.println("탈락자: " + fail_person);

//        String[] phone_book = {"97674223", "1195524421", "119"};
        String[] phone_book = {"123", "456", "789"};

        Solution s1 = new Solution();
        boolean r = s1.solution2(phone_book);

        System.out.println("Anser: " + r);

    }

    public static String array_check1(String[] participant, String[] completion) {
        String answer = "";

        Hashtable<String, Integer> part_ht = new Hashtable<>();
        Hashtable<String, Integer> com_ht = new Hashtable<>();

        for (String person : participant) {
            if (part_ht.containsKey(person)) {
                int cnt = part_ht.get(person) + 1;
                part_ht.replace(person, cnt);

            } else {
                part_ht.put(person, 1);
            }
        }

        for (String person : completion) {
            if (com_ht.containsKey(person)) {
                int cnt = com_ht.get(person) + 1;
                com_ht.replace(person, cnt);

            } else {
                com_ht.put(person, 1);
            }
        }

        Set<String> keys = part_ht.keySet();

        for (String key : keys) {
            if (com_ht.containsKey(key)) {
                int part_cnt = part_ht.get(key);
                int com_cnt = com_ht.get(key);

                if (part_cnt != com_cnt) {
                    answer = key;
                }

            } else {
                answer = key;
            }
        }

        return answer;
    }


}

class Solution {
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