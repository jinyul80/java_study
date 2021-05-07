package com.study;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        String[] participants = {"Joe", "Leo", "James", "Joe"};
        String[] finished_person = {"Joe", "James", "Leo"};

        String fail_person = array_check1(participants, finished_person);

        System.out.println("탈락자: " + fail_person);

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
