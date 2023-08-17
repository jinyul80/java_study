package stydy;

public class Sorter {


    /**
     * 선택 정렬 메서드(내림차순 정렬)
     *
     * @param arr
     * @return
     */
    public int[] selectionSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int temp_idx = i;

            // 내림차순 정렬을 위채 최대값 위치 찾기
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[temp_idx]) {
                    temp_idx = j;
                }
            }

            // 현재 위치의 값이 최대값 위치의 값보다 작은 경우 swap
            if (arr[i] < arr[temp_idx]) {
                int num = arr[i];
                arr[i] = arr[temp_idx];
                arr[temp_idx] = num;
            }
        }

        return arr;
    }


    /**
     * 버블 정렬 메서드(내림차순 정렬)
     *
     * @param arr
     * @return
     */
    public int[] bubbleSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                // 현재의 값이 다음 값보다 작은 경우 swap
                if (arr[j] < arr[j + 1]) {
                    int num = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = num;
                }
            }
        }

        return arr;
    }


    /**
     * 삽입 정렬 메서드(내림차순 정렬)
     *
     * @param arr
     * @return
     */
    public int[] insertionSort(int[] arr) {
        int idx1 = 0;
        int keyIdx = 0;
        int keyNum = 0;

        for (keyIdx = 1; keyIdx < arr.length; keyIdx++) {
            keyNum = arr[keyIdx];

            // Key 위치보다 앞에 있는 index들 비교
            for (idx1 = keyIdx - 1; idx1 >= 0; idx1--) {
                // 현재 값이 key값보다 작은 경우 swap
                if (arr[idx1] < keyNum) {
                    arr[idx1 + 1] = arr[idx1];
                } else if (arr[idx1] > keyNum) {
                    break;
                }
            }

            arr[idx1 + 1] = keyNum;
        }

        return arr;
    }


    /**
     * 쉘 정렬 메서드(내림차순 정렬) 삽입 정렬 알고리즘의 단점을 개선
     *
     * @param arr
     * @return
     */
    public int[] shellSort(int[] arr) {

        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            if (gap % 2 == 0) {
                gap++;
            }

            for (int i = 0; i < gap; i++) {
                shellSortSub(arr, i, gap);
            }
        }

        return arr;
    }

    /**
     * 쉘 정렬 서브 메서드(부분 삽입 정렬)
     *
     * @param arr
     * @param first
     * @param gap
     */
    void shellSortSub(int[] arr, int first, int gap) {
        int idx1 = 0;
        int keyIdx = 0;
        int keyNum = 0;

        // gap 수치만큼 이동하면서 반복
        for (keyIdx = first + gap; keyIdx < arr.length; keyIdx += gap) {
            keyNum = arr[keyIdx];

            // Key 위치보다 앞에 있는 index들 비교
            for (idx1 = keyIdx - gap; idx1 >= first; idx1 -= gap) {
                // 현재 값이 key값보다 작은 경우 swap
                if (arr[idx1] < keyNum) {
                    arr[idx1 + gap] = arr[idx1];
                } else if (arr[idx1] > keyNum) {
                    break;
                }
            }

            arr[idx1 + gap] = keyNum;
        }
    }

}
