package com.study;

import com.sun.jdi.IncompatibleThreadStateException;

import javax.management.relation.RelationSupport;
import java.sql.Array;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Solution s1 = new Solution();

        // Hash 테스트1
//        String[] participants = {"Joe", "Leo", "James", "Joe"};
//        String[] completion = {"Joe", "James", "Leo"};
//
//        String r1 = s1.solution1(participants, completion);
//
//        if (r1.equals("")) {
//            System.out.println("모두 완주성공");
//
//        } else {
//            System.out.println("탈락자: " + r1);
//        }
//
//        // Hash 테스트2
//        // String[] phone_book = {"97674223", "1195524421", "119"};
//        String[] phone_book = {"123", "456", "789"};
//
//        boolean r = s1.solution2(phone_book);
//        System.out.println("Answer: " + r);
//
//        // Test3
//        int[] arr =  {1, 5, 2, 6, 3, 7, 4};
//        int[][] com = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
//
//        int[] r3 = s1.solution3(arr, com);
//
//        System.out.println(Arrays.toString(r3));

//        // Test4

        int n = 5;
        int[] lost = {1, 2, 3};
        int[] reserve = {2, 3, 4};

        int r4 = s1.solution4(n, lost, reserve);
        System.out.println("참석 가능 인원: " + r4);

    }

}

class Solution {

    public String solution1(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> hm = new HashMap<>();
        for (String person : participant) hm.put(person, hm.getOrDefault(person, 0) + 1);
        for (String person : completion) hm.put(person, hm.get(person) - 1);

        for (String key : hm.keySet()) {
            if (hm.get(key) != 0) {
                answer = key;
            }
        }

        return answer;
    }

    public boolean solution2(String[] phone_book) {
        boolean answer = true;

        HashMap<String, Integer> hm = new HashMap<>();
        for (String key : phone_book) hm.put(key, 0);

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

    public int[] solution3(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {

            int[] command = commands[i];

            int s_idx = command[0] - 1;
            int e_idx = command[1];
            int idx = command[2] - 1;

            int[] temp = Arrays.stream(array, s_idx, e_idx).toArray();
            Arrays.sort(temp);

            answer[i] = temp[idx];
        }

        return answer;
    }

    public int solution4(int n, int[] lost, int[] reserve) {
        int answer = 0;

        List<Integer> participant = new ArrayList<>();

        List<Integer> lostList = new ArrayList<>();
        for(int num: lost) {
            if (IntStream.of(reserve).noneMatch(x -> x == num)) {
                lostList.add(num);
            }
        }

        List<Integer> reserveList = new ArrayList<>();
        for(int num: reserve) {
            if (IntStream.of(lost).noneMatch(x -> x == num)) {
                reserveList.add(num);
            }
        }

        for (int i = 1; i <= n; i++) {

            if (lostList.contains(i)) {
                if(reserveList.contains(i)) {
                    participant.add(i);
                    reserveList.remove((Integer) i);
                } else {
                    if(reserveList.contains(i - 1)) {
                        participant.add(i);
                        reserveList.remove((Integer) (i - 1));
                    } else if(reserveList.contains(i + 1)) {
                        participant.add(i);
                        reserveList.remove((Integer) (i + 1));
                    }
                }

            } else {
                participant.add(i);
            }
        }

        System.out.println("참석 명단: " + participant);
        answer = participant.size();

        return answer;
    }

}