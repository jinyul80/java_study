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
        int idx2 = 0;
        int num = 0;

        for (idx2 = 1; idx2 < arr.length; idx2++) {
            num = arr[idx2];

            for (idx1 = idx2 - 1; idx1 >= 0; idx1--) {
                if (arr[idx1] < num) {
                    arr[idx1 + 1] = arr[idx1];
                } else if (arr[idx1] > num) {
                    break;
                }
            }

            arr[idx1 + 1] = num;
        }

        return arr;
    }

}
