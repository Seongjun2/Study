package Algo;

import java.util.Scanner;

public class Problem14888 {

    static int n;
    static int[] array;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }

        int plus = sc.nextInt();
        int minus = sc.nextInt();
        int multi = sc.nextInt();
        int div = sc.nextInt();

        dfs(plus,minus,multi,div, array[0], 1, 0);
        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int plus, int minus, int multi, int div, int result, int idx, int cnt) {
        if(cnt == n-1){
            max = Math.max(max, result);
            min = Math.min(min, result);
            return ;
        }
        if(idx >= n) return ;
        for(int i = 0; i < 4;i++){
            switch (i){
                case 0:
                    if(plus == 0) continue;
                    dfs(plus-1,minus,multi,div,result+array[idx], idx+1, cnt+1);
                    break;
                case 1:
                    if(minus == 0) continue;
                    dfs(plus,minus-1,multi,div,result-array[idx], idx+1,cnt+1);
                    break;
                case 2:
                    if(multi == 0) continue;
                    dfs(plus,minus,multi-1,div,result*array[idx], idx+1,cnt+1);
                    break;
                case 3:
                    if(div == 0) continue;
                    dfs(plus,minus,multi,div-1,result/array[idx], idx+1,cnt+1);
                    break;
            }
        }
    }

}
