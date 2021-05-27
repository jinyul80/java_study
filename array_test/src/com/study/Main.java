package com.study;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Solution s1 = new Solution();

        // Hash 테스트1
        /*String[] participants = {"Joe", "Leo", "James", "Joe"};
        String[] completion = {"Joe", "James", "Leo"};

        String r1 = s1.solution1(participants, completion);

        if (r1.equals("")) {
            System.out.println("모두 완주성공");

        } else {
            System.out.println("탈락자: " + r1);
        }*/

        // Hash 테스트2
        /*String[] phone_book = {"97674223", "1195524421", "119"};

        boolean r = s1.solution2(phone_book);
        System.out.println("Answer: " + r);

        // Test3
        int[] arr =  {1, 5, 2, 6, 3, 7, 4};
        int[][] com = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

        int[] r3 = s1.solution3(arr, com);

        System.out.println(Arrays.toString(r3));*/


        // Test4
        /*int n = 5;
        int[] lost = {1, 2, 3};
        int[] reserve = {2, 3, 4};

        int r4 = s1.solution4(n, lost, reserve);
        System.out.println("참석 가능 인원: " + r4);*/

        // 크레인 인형뽑기
        /*int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] moves = {1,5,3,5,1,2,1,4};

        int result5 = s1.solution5(board, moves);
        System.out.println("터진 인형: " + result5);
*/

        // 모의고사
        /*int[] answers = {1,3,2,4,2};
        int[] result = s1.solution6(answers);

        System.out.println("출력: " + Arrays.toString(result));*/

        // 소수 만들기
        int[] nums = {1,2,7,6,4};
        int re = s1.solution7(nums);

        System.out.println("소수의 개수: " + re);


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

        List<Integer> participant = new ArrayList<>();

        List<Integer> lostList = new ArrayList<>();
        for (int num : lost) {
            if (IntStream.of(reserve).noneMatch(x -> x == num)) {
                lostList.add(num);
            }
        }

        List<Integer> reserveList = new ArrayList<>();
        for (int num : reserve) {
            if (IntStream.of(lost).noneMatch(x -> x == num)) {
                reserveList.add(num);
            }
        }

        for (int i = 1; i <= n; i++) {

            if (lostList.contains(i)) {
                if (reserveList.contains(i)) {
                    participant.add(i);
                    reserveList.remove((Integer) i);
                } else {
                    if (reserveList.contains(i - 1)) {
                        participant.add(i);
                        reserveList.remove((Integer) (i - 1));
                    } else if (reserveList.contains(i + 1)) {
                        participant.add(i);
                        reserveList.remove((Integer) (i + 1));
                    }
                }

            } else {
                participant.add(i);
            }
        }

        System.out.println("참석 명단: " + participant);
        int answer = participant.size();

        return answer;
    }

    // 크레인 인형뽑기
    public int solution5(int[][] board, int[] moves) {
        int answer = 0;

        Stack<Integer> basket = new Stack<>();

        List<LinkedList<Integer>> boardList = new ArrayList<>();

        int rowSize = board.length;
        int colSize = board[0].length;

        for (int col = 0; col < colSize; col++) {
            LinkedList<Integer> qu = new LinkedList<>();

            for (int row = 0; row < rowSize; row++) {
                int toyNum = board[row][col];
                if (toyNum > 0) {
                    qu.add(toyNum);
                }
            }

            boardList.add(qu);
        }

        for (int crane : moves) {
            int idx = crane - 1;
            var qu = boardList.get(idx);

            if (qu.size() > 0) {
                int toyNum = qu.pop();

                if (basket.size() == 0) {
                    basket.add(toyNum);

                } else {
                    int testNum = basket.peek();

                    if (toyNum == testNum) {
                        answer += 2;
                        basket.pop();
                    } else {
                        basket.add(toyNum);
                    }
                }
            }
        }

        return answer;
    }

    // 모의고사
    public int[] solution6(int[] answers) {
        int[] answer = {};
        List<Integer> tempAnswer = new ArrayList<>();

        HashMap<Integer, int[]> hm = new HashMap<>();
        hm.put(1, new int[]{1, 2, 3, 4, 5});
        hm.put(2, new int[]{2, 1, 2, 3, 2, 4, 2, 5});
        hm.put(3, new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5});

        int[] score = new int[3];

        for (int i = 0; i < answers.length; i++) {
            // people1
            int ansIdx = i % 5;
            int[] answerList = hm.get(1);

            if (answers[i] == answerList[ansIdx]) {
                score[0] += 1;
            }

            // people2
            ansIdx = i % 8;
            answerList = hm.get(2);

            if (answers[i] == answerList[ansIdx]) {
                score[1] += 1;
            }

            // people3
            ansIdx = i % 10;
            answerList = hm.get(3);

            if (answers[i] == answerList[ansIdx]) {
                score[2] += 1;
            }

        }

        int maxScore = Arrays.stream(score).max().getAsInt();
        if (maxScore == 0) return answer;

        for (int i = 0; i < score.length; i++) {
            if (score[i] == maxScore) {
                tempAnswer.add(i + 1);
            }
        }

        answer = new int[tempAnswer.size()];
        for (int i = 0; i < tempAnswer.size(); i++) {
            answer[i] = tempAnswer.get(i);
        }

        return answer;
    }

    // 소수 만들기
    public int solution7(int[] nums) {
        int answer = 0;

        // List<Integer> findList = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    // System.out.println(String.format("인덱스 : %d, %d, %d", i, j, k));
                    // System.out.println(String.format("숫자 : %d, %d, %d", nums[i], nums[j], nums[k]));

                    int sum = nums[i] + nums[j] + nums[k];

                    boolean flag = false;

                    int maxNum = (int) Math.sqrt(sum);
                    for (int num = 2; num <= maxNum; num++) {
                        if (sum % num == 0) {
                            flag = true;
                            break;
                        }
                    }

                    if (!flag) {
                        answer++;
                        // findList.add(sum);
                    }
                }
            }
        }

        // System.out.println(Arrays.toString(findList.toArray()));

        return answer;
    }


}