import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AlgoTrainer {


    public void bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1, arr);
                }
            }
        }
    }

    public void bucketSort(int[] arr, int buckets) { // bucket with another sort

        int size = 10;
        ArrayList<ArrayList<Integer>> bucket = new ArrayList<>();
        int max = -1;

        for (int i = 0; i < buckets; i++) {
            bucket.add(new ArrayList<>());
        }

        // find max value in arr O(n)
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // insert nums into bucket
        for (int i = 0; i < bucket.size(); i++) {
            bucket.get((int) Math.floor(buckets * arr[i] / max)).add(arr[i]);
        }

        // sort each individual bucket
        int index = 0;
        for (int i = 0; i < bucket.size(); i++) {
            // someSort(bucket.get(i));

            // concat into input
            for (Integer num : bucket.get(i)) {
                arr[index++] = num;
            }
        }
    }

    public void selectionSort(int[] arr) {

        // loop nums
        for (int i = 0; i < arr.length; i++) {

            int min = i;

            for (int c = i + 1; c < arr.length; c++) {
                if (arr[c] < arr[min]) {
                    min = c;
                }
            }
            swap(i, min, arr);
        }
    }

    public void insertionSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.println(i);
            int j = i;
            while (j > 0 && arr[j - 1] > arr[j]) {
                swap(j - 1, j, arr);
                j--;
            }
        }
    }

    public void quickSort(int[] arr, int l, int r) {

        if (l >= r) {
            return;
        }

        int div = partition(arr, l, r);
        quickSort(arr, l, div - 1);
        quickSort(arr, div + 1, r);
    }

    public int partition(int[] arr, int l, int r) {

        int pivot = r;
        r--; // to avoid pivot

        while (l <= r) {

            // from left
            while (l <= r && arr[l] <= arr[pivot]) {
                l++;
            }

            // from right
            while (r >= l && arr[r] >= arr[pivot]) {
                r--;
            }

            if (l < r) {
                swap(l, r, arr);
            }
        }

        swap(l, pivot, arr);
        return l;
    }


    public void heapSort(int[] arr) {

        // heapify arr (except leaf)
        for (int i = arr.length / 2; i >= 0; i--) {
            heapify(arr, i, arr.length - 1);
        }

        for (int i = arr.length - 1; i > 0; i--) {
            swap(0, i, arr);
            heapify(arr, 0, i - 1);
        }
    }

    public void heapify(int[] arr, int i, int limit) {

        int left = (i * 2) + 1;
        int right = (i * 2) + 2;
        int max = i;

        if (left <= limit && arr[left] > arr[max]) {
            max = left;
        }

        if (right <= limit && arr[right] > arr[max]) {
            max = right;
        }

        if (max != i) {
            swap(i, max, arr);
            heapify(arr, max, limit);
        }

    }

    public static void swap(int from, int to, int[] arr) {
        int temp = arr[to];
        arr[to] = arr[from];
        arr[from] = temp;
    }


    public void mergeSort(int[] arr, int n) {

        if (n < 2) {
            return;
        } // 1 element means already sorted

        int[] s1 = new int[(n / 2)];
        int[] s2 = new int[n - (n / 2)];
        int mid = n / 2;


        for (int i = 0; i < mid; i++) {
            s1[i] = arr[i];
        }

        for (int i = mid; i < n; i++) {
            s2[i - mid] = arr[i];
        }

        mergeSort(s1, n / 2);
        mergeSort(s2, n - (n / 2));

        merge(s1, s2, arr);

    }

    public int[] merge(int[] s1, int[] s2, int[] arr) {

        int index = 0;
        int i = 0;
        int j = 0;

        // while both arrs have elements, sort them into c
        while (i < s1.length && j < s2.length) {
            if (s1[i] <= s2[j]) {
                arr[index++] = s1[i++];
            } else {
                arr[index++] = s2[j++];
            }
        }

        // one of the arrs must now be empty
        while (i < s1.length) {
            arr[index++] = s1[i++];
        }

        while (j < s2.length) {
            arr[index++] = s2[j++];

        }

        return arr;
    }

    public void radixSort(int[] arr) {


        final int radix = 10;

        ArrayList<ArrayList<Integer>> bucket = new ArrayList<>(radix);

        // fill bucket
        for (int i = 0; i < radix; i++) {
            bucket.add(new ArrayList<>());
        }

        int placement = 1, num = -1;
        boolean finished = false;

        while (!finished) {
            finished = true;

            // loop through input and put into bucket
            for (int i = 0; i < arr.length; i++) {

                num = arr[i] / placement;
                bucket.get(num % radix).add(arr[i]);

                if (num > 0 && finished) {
                    finished = false;
                }
            }

            // loop bucket to extract nums
            int index = 0;
            for (int i = 0; i < bucket.size(); i++) {
                for (Integer n : bucket.get(i)) {
                    arr[index++] = n;
                }
                bucket.get(i).clear();

            }

            placement *= radix;
        }
    }

    public int binarySearch(int[] arr, int l, int r, int x) {

        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr[mid] == x)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (arr[mid] > x)
                return binarySearch(arr, l, mid - 1, x);

            // Else the element can only be present
            // in right subarray
            return binarySearch(arr, mid + 1, r, x);
        } else {
            return -1;
        }
    }
}
