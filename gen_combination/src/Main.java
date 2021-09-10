import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String[] arr = {"A", "B", "C", "D", "E"};
        int r = 3;
        boolean[] visited = new boolean[arr.length];

        System.out.println("\n문자 배열 " + arr.length + "개 중에 " + r + "개 조합");

        List<String> result = combi(arr, visited, 0, 3);

        for (String str : result) {
            System.out.println(str);
        }

        int[] arrNum = {1, 2, 3, 4};
        visited = new boolean[arrNum.length];

        r = 3;
        System.out.println("\n숫자 배열 " + arrNum.length + "개 중에 " + r + "개 조합");
        comb2(arrNum, visited, 0, r);

    }

    // 문자 배열 조합
    static List<String> combi(String[] arr, boolean[] visited, int depth, int r) {
        List<String> re = new ArrayList<>();

        // System.out.println("Visited:" + Arrays.toString(visited) + ", depth:" + depth + ", r:" + r);

        if ( r == 0 ) {
            re.add(getCombiString(arr, visited));

        } else if ( depth == arr.length ) {
            return re;

        } else {
            visited[depth] = true;
            List<String> tmp = combi(arr, visited, depth + 1, r -1);
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

        for(int i = 0; i < arr.length; i++) {
            if(visited[i] == true)
                sb.append(arr[i]);
        }

        System.out.println(sb.toString());

        return sb.toString();
    }

    // 숫자 배열 조합
    static void comb2(int[] arr, boolean[] visited, int depth, int r) {
        if(r == 0) {
            print(arr, visited);
            return;
        }
        if(depth + r > arr.length) {
            return;
        } else {
            visited[depth] = true;
            comb2(arr, visited, depth + 1, r - 1);

            visited[depth] = false;
            comb2(arr, visited, depth + 1, r);
        }
    }

    // 배열 출력
    static void print(int[] arr, boolean[] visited) {
        for(int i = 0; i < arr.length; i++) {
            if(visited[i] == true)
                System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
