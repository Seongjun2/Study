package Algo;

import java.util.*;

public class Problem10711__ {

    static int n;
    static int m;

    static int[][] matrix;
    static int[] dr = {-1,-1,-1,0,0,1,1,1};
    static int[] dc = {-1,0,1,-1,1,-1,0,1};
    static Map<String, Set<String>> map = new HashMap<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] str;
        str = sc.nextLine().split(" ");

        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);

        matrix= new int[n][m];

        for(int i = 0; i< n; i++){
            str = sc.nextLine().split("");
            for (int j = 0; j < m; j++) {
                if(str[j].equals(".")){
                    matrix[i][j] = -1;
                }
                else{
                    int num = Integer.parseInt(str[j]);
                    matrix[i][j] = num;
                    if(num < 9){
                        Set<String> set = new HashSet<>();
                        map.put(i+","+j, set);
                    }
                }
            }
        }
        init();
        solution();
    }

    private static void init(){

        for(Map.Entry<String, Set<String>> entry : map.entrySet()){
            String key = entry.getKey();
            int row = Character.getNumericValue(key.charAt(0));
            int col = Character.getNumericValue(key.charAt(2));
            Set<String> set = entry.getValue();

            for (int j = 0; j < 8; j++) {
                int nr = row + dr[j];
                int nc = col + dc[j];

                if(matrix[nr][nc] != -1) {
                    set.add(nr+","+nc);
                }
            }
        }
    }
    private static void solution(){

        int year = 0;
        while(true) {
            List<String> removeList = new LinkedList<>();
            Set<String> deleted = new HashSet<>();

            for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
                String key = entry.getKey();
                int row = Character.getNumericValue(key.charAt(0));
                int col = Character.getNumericValue(key.charAt(2));
                Set<String> set = entry.getValue();

                if (8 - set.size() >= matrix[row][col]) {//쓰러지는 곳
                    removeList.add(key);
                    for(String s : set){
                        int r = Character.getNumericValue(s.charAt(0));
                        int c = Character.getNumericValue(s.charAt(2));
                        if(matrix[r][c] != -1 && matrix[r][c] != 9) deleted.add(s);
                    }
                }
            }
            if(removeList.size() == 0){
                System.out.println(year);
                break;
            }

            for(String s : deleted){
                Set<String> set = map.get(s);
                for (int i = 0; i < removeList.size(); i++) {
                    String r = removeList.get(i);
                    if (set.contains(r)) {
                        set.remove(r);
                    }
                }
            }

            for (int i = 0; i < removeList.size(); i++) {
                String s = removeList.get(i);
                map.remove(s);
                matrix[Character.getNumericValue(s.charAt(0))][Character.getNumericValue(s.charAt(2))] = -1;
            }
            year++;

        }
    }
}
