package Algo;

import java.util.Scanner;
import java.util.Stack;

public class Problem11053 {

    static int n;
    static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int[] array = new int[n];

        for(int i =0; i< n;i++){
            array[i] = sc.nextInt();
        }

        solution(array);
    }

    private static void solution(int[] array) {
        dp = new int[n];
        Pair[] tracking = new Pair[n];

        dp[0] = array[0];
        tracking[0] = new Pair(0,array[0]);
        int idx = 0;
        for(int i = 1; i< n;i++){
            if(dp[idx] < array[i]){
                dp[++idx] = array[i];
                tracking[i] = new Pair(idx, array[i]);
            }
            else{
                int ii = lower_bound(idx,array[i]);
                dp[ii] = array[i];

                tracking[i] = new Pair(ii,array[i]);
            }
        }

        Stack<Integer> stack = new Stack<>();
        int temp = idx;
        for(int i = n-1; i >= 0;i-- ){
            if(temp == tracking[i].idx){
                stack.push(tracking[i].value);
                --temp;
            }
        }
        System.out.println(stack.size());
//        while(!stack.isEmpty()){
//            System.out.println(stack.pop() + " ");
//        }
    }

    static int lower_bound(int end, int n){
        int start = 0;
        while(start < end){
            int mid = (start + end) / 2;
            if(dp[mid] >= n){
                end = mid;
            }else{
                start = mid + 1;
            }
        }
        return end;
    }

    static class Pair{
        int idx;
        int value;

        public Pair(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }
}
