package com.study;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String[] participants = {"Joe", "Leo", "James"};
        String[] finished_person = {"Joe", "James"};

        String fail_person = array_check1(participants, finished_person);

        System.out.println("탈라자: " + fail_person);

    }

    public static String array_check1(String[] participants, String[] finished_list) {
        String result = "";

        for (String person : participants) {
            if (Arrays.stream(finished_list).noneMatch(person::equals)) {
                result = person;
                break;
            }
        }

        return result;
    }

}
