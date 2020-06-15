package Algo;

import java.util.Scanner;

public class Problem1343 {

    public static void main(String[] args) {
        String[] polys = {"AAAA","BB"};

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        String result = solution(polys, input);

        System.out.println(result);
    }

    public static String solution(String[] polys, String input) {

        StringBuilder sb = new StringBuilder();
        int cnt = 0;

        for(int i = 0; i < input.length();i++){
            if(input.charAt(i) == '.'){
                if(check(cnt,polys,sb) == -1){
                    return "-1";
                }
                sb.append(".");
                cnt = 0;
            }
            else{
                cnt++;
            }
        }
        if(cnt != 0){
            if(check(cnt,polys,sb) == -1){
                return "-1";
            }
        }

        return sb.toString();
    }
    public static int check(int cnt, String[] polys, StringBuilder sb){
        if(cnt % 2 != 0){
            return -1;
        }

        if(cnt >= 4){
            int v = cnt/4;
            int r = cnt%4;

            while(v > 0){
                sb.append(polys[0]);
                v--;
            }
            if(r != 0 ) sb.append(polys[1]);
        }
        else if(cnt == 2){
            sb.append(polys[1]);
        }

        return 0;
    }
}
