import java.util.Arrays;

// 양궁대회
public class ArcheryCompetition {

    int maxDiff = 0;
    int[] ryanInfo;
    int[] apeachInfo;
    int[] winCase;

    public int[] solution(int n, int[] info) {
        // 라이언이 이긴 경우의 점수별 화살 정보
        ryanInfo = new int[info.length];

        apeachInfo = info;

        // 모든 경우의 수 탐색
        winCase = new int[info.length - 1];
        dfs(n, 0);

        // 이긴 경우가 없으면 '[-1]'
        if (maxDiff == 0) {
            return new int[]{-1};
        }

        return ryanInfo;
    }

    public void dfs(int maxArrow, int startIdx) {
        for (int i = startIdx; i < winCase.length; i++) {
            winCase[i]++;

            // 모든 경우의 수에서 라이언이 이겼는지 체크
            isRyanWin(maxArrow);

            dfs(maxArrow, i + 1);
            winCase[i]--;
        }
    }

    private void isRyanWin(int maxArrow) {
        int ryanScore = 0, apeachScore = 0, cnt = 0;
        int[] tempRyan = new int[ryanInfo.length];

        // 각 점수별로 누가 이겼는지 체크
        for (int j = 0; j < winCase.length; j++) {
            if (winCase[j] != 0) {
                // 라이언이 이긴 경우 라이언 점수에 더하기, 그리고 사용한 화살수에 더하기
                ryanScore += 10 - j;
                tempRyan[j] = apeachInfo[j] + 1;
                cnt += apeachInfo[j] + 1;

            } else if (apeachInfo[j] > 0) {
                // 어피치가 이긴 경우 어피치 점수에 더하기
                apeachScore += 10 - j;
            }
        }

        if (cnt > maxArrow) {
            // 사용한 화살 개수가 많은 경우 제외
            return;
        } else {
            // 남은 화살은 0점 칸에 넣기
            tempRyan[tempRyan.length - 1] = maxArrow - cnt;
        }

        if (ryanScore > apeachScore) {
            // 라이언 점수가 더 큰 경우 현재까지 최고 점수인지 체크
            int diff = ryanScore - apeachScore;
            if (diff > maxDiff) {
                maxDiff = diff;
                ryanInfo = tempRyan.clone();

            } else if (diff == maxDiff) {
                // 현재까지 최고 점수와 동점이라면
                // 낮은 점수의 화살수가 많은 경우를 라이언 점수별 화살 정보로 변경
                for (int j = ryanInfo.length-1; j >= 0; j--) {
                    if (ryanInfo[j] < tempRyan[j]) {
                        ryanInfo = tempRyan.clone();
                        break;
                    } else if (ryanInfo[j] > tempRyan[j]) {
                        break;
                    }
                }
            }
        }
    }
}
