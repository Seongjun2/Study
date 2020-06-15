package Algo;

import java.util.*;


public class Permutation {

    boolean[] check;
    public static void main(String[] args) {
        Permutation p = new Permutation();
        int[] array = {1,2,3,4};
        int[] returnArray = new int[2];

        int[] array2 = {1,2,3,4,5,6,7,8,9};
//        int[] array2 = {1,2,3};
        int n = 2;
        int[] rArray = new int[n];

        p.check = new boolean[array2.length];
        p.dfs(0,0,n,rArray,array2,0);
    }

    public void permutation(int[] array, int std, int cnt, int[] returnArray, int idx){
        if(cnt == std){
            for (int i = 0; i < returnArray.length; i++) {
                System.out.print(returnArray[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = idx; i < array.length; i++) {
            returnArray[cnt] = array[i];
            permutation(array, std, cnt+1, returnArray ,i+1);
        }
    }

    public void dfs(int idx, int cnt, int std, int[] rArray, int[] array,int t){
        if(cnt == std){
            for (int i = 0; i < rArray.length; i++) {
                System.out.print(rArray[i] + " ");
            }
            System.out.println();
            return ;
        }

        for (int i = t; i < array.length; i++) {
            if(check[i]) continue;
            rArray[idx] = array[i];
            check[i] = true;
            dfs(idx+1,cnt+1,std, rArray, array, i+1);
            check[i] = false;
        }
    }
}
