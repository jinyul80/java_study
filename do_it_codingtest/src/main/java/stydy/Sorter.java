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
    private void shellSortSub(int[] arr, int first, int gap) {
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


    /**
     * 병합 정렬 메서드(내림차순 정렬)
     *
     * @param arr
     * @return
     */
    public int[] mergeSort(int[] arr) {

        int[] temp_arr = new int[arr.length];

        mergeSortSub(arr, temp_arr, 0, arr.length - 1);

        return arr;
    }

    /**
     * 병합 정렬 서브 메서드(배열 분리)
     *
     * @param arr
     * @param temp_arr
     * @param left
     * @param right
     */
    private void mergeSortSub(int[] arr, int[] temp_arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2; // 리스트를 균등 분할

            mergeSortSub(arr, temp_arr, left, mid);             // 앞 부분 리스트
            mergeSortSub(arr, temp_arr, mid + 1, right); // 뒷 부분 리스트

            merge(arr, temp_arr, left, mid, right); // 정렬된 2개의 부분 배열을 병합
        }

    }

    /**
     * 병합 정렬 서브 메서드(정렬하면서 병합)
     *
     * @param arr
     * @param temp_arr
     * @param left
     * @param mid
     * @param right
     */
    private void merge(int[] arr, int[] temp_arr, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            // 2개의 리스트에서 내림차순 정렬로 큰 값을 먼저 사용
            if (arr[i] < arr[j]) {
                temp_arr[k++] = arr[j++];
            } else {
                temp_arr[k++] = arr[i++];
            }
        }

        // 남아 있는 값들을 일괄로 복사
        if (i > mid) {
            for (int l = j; l <= right; l++) {
                temp_arr[k++] = arr[l];
            }
        } else {
            for (int l = i; l <= mid; l++) {
                temp_arr[k++] = arr[l];
            }
        }

        // 임시 배열에 있는 리스트를 원본 배열에 재복사
        for (int l = left; l <= right; l++) {
            arr[l] = temp_arr[l];
        }
    }


}
