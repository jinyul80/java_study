import java.util.ArrayList;
import java.util.HashSet;

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
}
