package Algo;

import java.util.*;

public class NightTour {

    static Set<String> set = new HashSet<>();
    public static void main(String[] args) {

        boolean[][] check = new boolean[6][6];
        String result = "Valid";
        Scanner sc = new Scanner(System.in);

        Map<String, Integer> map = new HashMap<>();

        map.put("A",0);
        map.put("B",1);
        map.put("C",2);
        map.put("D",3);
        map.put("E",4);
        map.put("F",5);

        set.add("-2,-1");
        set.add("-2,1");
        set.add("-1,-2");
        set.add("-1,2");
        set.add("1,-2");
        set.add("1,2");
        set.add("2,-1");
        set.add("2,1");

        String start = "";
        int startF = 0;
        int startB = 0;
        int prevF = 0;
        int prevB = 0;
        int lastF = 0;
        int lastB = 0;

        for (int i = 0; i < 36; i++) {
            String s = sc.nextLine();
            int front = map.get(String.valueOf(s.charAt(0)));
            int back = Character.getNumericValue(s.charAt(1))-1;

            if(i == 0){
                start = s;
                prevF = front;
                prevB = back;
                startF = front;
                startB = back;
                check[front][back] = true;
                continue;
            }

            if(front < 0 || back < 0 || front >=6 || back >= 6) {//벗어나는 경우
                result = "Invalid";
                break;
            }

            if(check[front][back]){//이미 방문한 곳
                result = "Invalid";
                break;
            }

            if(!possibleMove(prevF, prevB, front, back)){//말이 갈 수 없는 경우
                result = "Invalid";
                break;
            }

            check[front][back] = true;
            prevF = front;
            prevB = back;

            if(i == 35){//마지막까지 오면 체스판의 좌표 기억
                lastF = front;
                lastB = back;
            }
        }
        if(result.equals("Valid")){
            if(!possibleMove(lastF,lastB, startF, startB)){
                result = "Invalid";
            }
        }

        System.out.println(result);

    }

    public static boolean possibleMove(int prevF, int prevB, int front, int back){
        int mr = front-prevF;
        int mc = back-prevB;

        if(set.contains(mr+","+mc)){
            return true;
        }
        else return false;
    }
}
