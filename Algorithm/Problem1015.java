package Algo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Problem1015 {

    public static void main(String[] args) {
        int[] array;
        Numbers[] numbers;
        Scanner sc =new Scanner(System.in);

        int n = sc.nextInt();
        array = new int[n];
        numbers = new Numbers[n];

        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }

        for(int i = 0; i< n;i++){
            numbers[i] = new Numbers(i,array[i]);
        }

        Arrays.sort(numbers, Comparator.comparing(Numbers::getValue));

        for(int i = 0; i< n;i++){
            numbers[i].seq = i;
        }

        Arrays.sort(numbers, Comparator.comparing(Numbers::getIndex));

        for(int i = 0; i < numbers.length;i++){
            if(i == 0){
                System.out.print(numbers[i].seq);
            }
            else{
                System.out.print(" " + numbers[i].seq);
            }
        }
    }

    static class Numbers{
        int index;
        int value;
        int seq;

        public int getIndex() {
            return index;
        }

        public int getValue() {
            return value;
        }

        public int getSeq() {
            return seq;
        }

        public Numbers(int index, int value){
            this.index = index;
            this.value = value;
        }
    }
}
