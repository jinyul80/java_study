import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Greedy {

    public int solution1(int n, int[] lost, int[] reserve) {
        HashSet<Integer> reserveSet = new HashSet<>();
        HashSet<Integer> lostSet = new HashSet<>();

        for (int num : reserve) {
            reserveSet.add(num);
        }

        for (int num : lost) {
            if (reserveSet.contains(num)) {
                reserveSet.remove(num);
            } else {
                lostSet.add(num);
            }
        }

        for(int num: reserveSet) {
            if(lostSet.contains(num-1))
                lostSet.remove(num-1);
            else if (lostSet.contains(num+1))
                lostSet.remove(num+1);
        }

        return n - lostSet.size();
    }

    // 순열
    public void permutation() {
        int [] nums = {6, 7, 8};
        int length = 2;

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();

        backtracking(result, tempList, nums, length);

        System.out.println(result);

    }

    public void backtracking(List<List<Integer>> result, List<Integer> tempList,int[] nums, int length) {
        if (tempList.size() == length) {
            result.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) {
                    continue;
                }

                tempList.add(nums[i]);
                backtracking(result, tempList, nums, length);
                tempList.remove(Integer.valueOf(nums[i]));
            }
        }
    }

    // 조합
    public void combination() {
        int [] nums = {6, 7, 8};
        int length = 2;

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();

        backtracking2(result, tempList, nums, length, 0);

        System.out.println(result);

    }

    public void backtracking2(List<List<Integer>> result, List<Integer> tempList,int[] nums, int length, int start) {
        if (tempList.size() == length) {
            result.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i < nums.length; i++) {
                if (tempList.contains(nums[i])) {
                    continue;
                }

                tempList.add(nums[i]);
                backtracking2(result, tempList, nums, length, i+1);
                tempList.remove(Integer.valueOf(nums[i]));
            }
        }
    }

}
