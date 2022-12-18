import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Programmers {

    // 햄버거 만들기
    public int solution_hamberger(int[] ingredient) {
        int answer = 0;

        StringBuilder sb = new StringBuilder();

        for (int idx : ingredient) {
            sb.append(idx);

            if (sb.length() >= 4) {
                String str = sb.substring(sb.length() - 4);

                if (str.equals("1231")) {
                    answer++;

                    sb = new StringBuilder(sb.substring(0, sb.length() - 4));
                }
            }
        }

        return answer;
    }

    // 옹알이2
    public int solution2(String[] babbling) {
        int answer = 0;

        String[] canWord = {"aya", "ye", "woo", "ma"};

        for (String str : babbling) {

            StringBuilder sb = new StringBuilder(str);
            String findWord = "";
            boolean loopFlag = true;

            while (loopFlag) {
                loopFlag = false;

                for (String word : canWord) {

                    if (findWord.equals(word)) {
                        continue;
                    }

                    int idx = sb.indexOf(word);
                    if (idx == 0) {
                        loopFlag = true;
                        findWord = word;

                        int wordLength = word.length();

                        StringBuilder tmp = new StringBuilder(sb.substring(wordLength));
                        sb = new StringBuilder(tmp);

                        if (sb.length() == 0) {
                            answer++;
                            loopFlag = false;
                        }
                        break;
                    }
                }
            }
        }

        return answer;
    }

    // 콜라 문제
    public int solution3(int a, int b, int n) {
        int answer = 0;

        int cnt = n / a;
        answer = cnt * b;
        int n_ = n - (a * cnt) + answer;

        if (n_ >= a) {
            int sub_answer = solution3(a, b, n_);
            answer += sub_answer;
        }

        return answer;
    }

    // 삼총사
    public int solution4(int[] number) {
        int answer = 0;

        for (int x = 0; x < number.length - 2; x++) {
            int num1 = number[x];

            for (int y = x + 1; y < number.length - 1; y++) {
                int num2 = number[y];

                for (int z = y + 1; z < number.length; z++) {
                    int num3 = number[z];

                    if ((num1 + num2 + num3) == 0) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }

    // 숫자 짝궁
    public String solution5(String X, String Y) {
        String answer = "";

        List<String> matchNums = new ArrayList<>();

        for (int num = 0; num < 10; num++) {
            int countX = 0;
            int countY = 0;
            String findStr = String.valueOf(num);

            int idx = X.indexOf(findStr);
            while (idx != -1) {
                countX++;
                idx = X.indexOf(findStr, idx + 1);
            }

            if (countX == 0) {
                continue;
            }

            idx = Y.indexOf(findStr);
            while (idx != -1) {
                countY++;
                idx = Y.indexOf(findStr, idx + 1);
            }

            int countLoop = Math.min(countX, countY);

            for (int x = 0; x < countLoop; x++) {
                matchNums.add(findStr);
            }
        }

        if (matchNums.size() == 0) {
            answer = "-1";
        } else {
            matchNums.sort(Comparator.reverseOrder());

            if (matchNums.get(0)
                         .equals("0")) {
                answer = "0";
            } else {
                StringBuilder sb = new StringBuilder();
                matchNums.forEach(sb::append);
                answer = sb.toString();
            }
        }

        return answer;
    }

    // 소수 찾기
    public int solution8(String numbers) {
        int count = 0;

        List<Integer> tempList = sol8_recurisive("", numbers);
        HashSet<Integer> hs = new HashSet<>(tempList);

        System.out.println(hs.toString());

        for (int num : hs) {
            if (sol8_isPrime(num)) {
                count++;
            }
        }

        return count;
    }

    List<Integer> sol8_recurisive(String comb, String other) {
        List<Integer> numList = new ArrayList<>();

        if (!comb.equals("")) {
            numList.add(Integer.valueOf(comb));
        }

        for (int idx = 0; idx < other.length(); idx++) {
            String newComb = comb + other.charAt(idx);
            String newOther = other.substring(0, idx) + other.substring(idx + 1);

            List<Integer> tempList = sol8_recurisive(newComb, newOther);
            numList.addAll(tempList);
        }

        return numList;
    }

    // 에라토스테네스의 체
    boolean sol8_isPrime(int num) {
        if (num == 0 || num == 1) {
            return false;
        }

        int limit = (int) Math.sqrt(num);

        for (int idx = 2; idx < limit; idx++) {
            if (num % idx == 0) {
                return false;
            }
        }

        return true;
    }

    // 카카오 - 키패드 누르기
    public String solution9(int[] numbers, String hand) {
        String answer = "";

        Position left = new Position(3, 0);
        Position right = new Position(3, 2);
        Position numPos;

        for (int num : numbers) {
            numPos = new Position((num - 1) / 3, (num - 1) % 3);
            if (num == 0) {
                numPos = new Position(3, 1);
            }

            String finger = numPos.getFinger(left, right, hand);

            answer += finger;
            if (finger.equals("L")) {
                left = numPos;
            } else {
                right = numPos;
            }
        }

        return answer;
    }

    class Position {

        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public String getFinger(Position left, Position right, String hand) {
            String finger = hand.equals("right") ? "R" : "L";

            if (this.col == 0) {
                finger = "L";
            } else if (this.col == 2) {
                finger = "R";
            } else {
                int leftDist = left.getDistance(this);
                int rightDist = right.getDistance(this);

                if (leftDist < rightDist) {
                    finger = "L";
                } else if (leftDist > rightDist) {
                    finger = "R";
                }
            }

            return finger;
        }

        public int getDistance(Position p) {
            return Math.abs(this.row - p.row) + Math.abs(this.col - p.col);
        }
    }

}
