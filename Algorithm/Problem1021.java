package Algo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Problem1021 {

    static int n;
    static int m;
    static Map<Integer,Integer> queue = new HashMap<>();
    static int[] popNums;
    static int front;
    static int rear;
    static int result = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        popNums = new int[m];

        for (int i = 0; i < m; i++) {
            popNums[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            queue.put(i+1, i);
        }

        solution();
        System.out.println(result);
    }

    private static void solution() {
        for(int i = 0; i< m;i++){
            int popNum = popNums[i];

            if(queue.get(popNum) == 0) {
                pop(popNum);
                continue;
            }
            //가까운 방향을 찾는다.
            int[] directions = findNear(popNum);
            if(directions[0]== 0){//왼쪽으로 간다.
                move(0, directions[1]);
            }
            else{//오른쪽으로 간다.
                move(1, directions[1]);
            }
            result += directions[1];

            pop(popNum);
        }
    }
    private static void move(int dir, int dist){
        Set<Integer> keySet = queue.keySet();

        if(dir == 0){//왼쪽
            for(int key : keySet){
                int value = queue.get(key);
                int moveDist = value-dist;
                if(moveDist < 0){
                    moveDist+= n;
                }
                queue.put(key,moveDist);
            }
        }
        else{//오른쪽
            for(int key : keySet){
                int value = queue.get(key);
                int moveDist = value+dist;
                if(moveDist >= n){
                    moveDist %= n;
                }
                queue.put(key,moveDist);
            }
        }
    }

    private static void pop(int num){
        int idx = queue.remove(num);
        Set<Integer> keySet = queue.keySet();
        for(int key : keySet){
            int bigIdx = queue.get(key);
            if(bigIdx > idx){
                queue.put(key,bigIdx-1);
            }
        }
        n--;
    }

    private static int[] findNear(int num){

        int[] directions = new int[2];
        int popIdx = queue.get(num);
        int left = popIdx;
        int right = n - popIdx;

        if(left > right){//오른쪽이 짧아
            directions[0] = 1;
            directions[1] = right;
        }
        else {
            directions[0] = 0;
            directions[1] = left;
        }

        return directions;
    }
}
