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

        String[] phone_book = {"119", "97674223", "1195524421"};

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

        Set keys = part_ht.keySet();

        Iterator<String> iter = keys.iterator();

        while (iter.hasNext()){
            String key = iter.next();

            if (com_ht.containsKey(key)) {
                int part_cnt = part_ht.get(key);
                int com_cnt = com_ht.get(key);

                if ( part_cnt != com_cnt ) {
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
        for (String pNumber : phone_book) {
            hm.put(pNumber, 0);
        }

        Set<String> keys = hm.keySet();

        for (String key : keys){
            if(keys.stream().filter(s -> s.startsWith(key)).count() >= 2) {
                answer = false;
                break;
            }
        }

        return answer;
    }
}