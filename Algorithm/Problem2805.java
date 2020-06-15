package Algo;

import java.util.Arrays;
import java.util.Scanner;

public class Problem2805 {

    static int n;
    static int[] trees;

    public static void main(String[] args) {


        int targetH;
        long result = 0;

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        trees = new int[n];

        targetH = sc.nextInt();
        for(int i = 0; i < n; i++){
            trees[i] = sc.nextInt();
        }
        Arrays.sort(trees);

        result = binarySearch(0, trees[n-1], targetH);
        System.out.println(result);
    }

    private static int binarySearch(int min, int max, int targetH) {
        int r = 0;

        while(max >= min) {
            r = (min + max) / 2;

            long sum = getSum(r);

            if(sum >= targetH){
                min = r+1;
            }
            else if(sum < targetH){
                max = r-1;
            }

        }

        return max;
    }

    private static long getSum(int number){
        long sum = 0;
        int cut = 0;
        for (int i = 0; i < n; i++) {
            cut = trees[i] - number;

            if(cut < 0){
                cut = 0;
            }
            sum+= cut;
        }
        return sum;
    }
}
/*
4 8
20 15 10 17
 */
