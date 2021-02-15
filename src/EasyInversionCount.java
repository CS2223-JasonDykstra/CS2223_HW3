public class EasyInversionCount {

    /*
    This algorithm has a run time of O(n^2) since it utilizes bubble sort
    to count the number of inversions in a given array.
     */

    public static void main(String[] args){
        int[] testArr = new int[]{3, 2, 1};

        System.out.println("Number of inversions in " + printArray(testArr) + " is: " + bubbleSort(testArr));
    }

    //returns the number of swaps performed, which is the number of inversions in the array
    public static int bubbleSort(int arr[]) {
        int n = arr.length;
        int counter = 0;
        for (int i = 0; i < n - 1; ++i) {
            for (int j = 0; j < n - i - 1; ++j) {
                if (arr[j] > arr[j + 1]) {
                    ++counter;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return counter;
    }

    public static String printArray(int[] arr){
        int n = arr.length;
        String answer = "[";
        for (int i=0; i < n; ++i) {
            answer += (arr[i]);
            answer += i == n - 1 ? "" : " ";
        }
        answer += "]";
        return answer;
    }
}
