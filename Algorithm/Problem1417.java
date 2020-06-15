package Algo;

import java.util.*;

public class Problem1417 {
    public static void main(String[] args) {

        int result = 0;
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int n = sc.nextInt();
        int std = sc.nextInt();
        for(int i = 0; i < n-1; i++){
            int input = sc.nextInt();
            if(input >= std ) pq.add(input);
        }

        while(true){
            if(pq.isEmpty()) break;
            int poll = pq.poll();
            if(poll >= std ){
                poll--;
                std++;
                if(poll > 0) pq.add(poll);
            }
            result++;
            if(pq.isEmpty()) break;
            if(std > pq.peek()){
                break;
            }
        }
        System.out.println(result);

    }

}
/*
   1. 큰 것만 내림차순 정렬
   2. 숫자 관리(map)
   3

3
5
8
7

5
3
2
8
7
1


 */