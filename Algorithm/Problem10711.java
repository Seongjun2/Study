package Algo;

import java.lang.reflect.Array;
import java.util.*;

public class Problem10711 {

    static int n;
    static int m;

    static int[][] matrix;
    static int[][] adj;
    static int[] dr = {-1,-1,-1,0,0,1,1,1};
    static int[] dc = {-1,0,1,-1,1,-1,0,1};
    static boolean[][] visited;
    static Queue<Position> q = new LinkedList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] str;
        str = sc.nextLine().split(" ");

        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);

        matrix= new int[n][m];
        adj = new int[n][m];
        visited = new boolean[n][m];
        for(int i = 0; i< n; i++){
            str = sc.nextLine().split("");
            for (int j = 0; j < m; j++) {
                if(str[j].equals(".")){
                    matrix[i][j] = -1;
                }
                else{
                    int num = Integer.parseInt(str[j]);
                    matrix[i][j] = num;
                    if(num < 9) q.add(new Position(i,j));
                }
            }
        }

        initAdj();
        solution();
    }

    private static void initAdj(){

        while(!q.isEmpty()){
            Position p = q.poll();
            for (int j = 0; j < 8; j++) {
                int nr = p.row + dr[j];
                int nc = p.col + dc[j];

                if(matrix[nr][nc] == -1) adj[p.row][p.col]++;
            }
        }

        for (int i = 1; i < n-1; i++) {
            for (int j = 1; j < m-1; j++) {
                if(adj[i][j] >= matrix[i][j] && matrix[i][j] != -1) {
                    q.add(new Position(i,j));
                    visited[i][j] = true;
                }
            }
        }
    }
    private static void solution() {
        Queue<Position> removeQ = new LinkedList<>();

        int year = 0;
        while(!q.isEmpty()){
            int size = q.size();
            while(size > 0){
                Position p = q.poll();

                for (int i = 0; i < 8; i++) {
                    int nr = p.row + dr[i];
                    int nc = p.col + dc[i];

                    if(matrix[nr][nc] == 9) continue;
                    if(matrix[nr][nc] == -1) continue;
                    if(visited[nr][nc]) continue;

                    adj[nr][nc]++;
                    if(adj[nr][nc] >= matrix[nr][nc]) {
                        q.add(new Position(nr,nc));
                        visited[nr][nc] = true;
                    }

                }
                removeQ.add(p);
                size--;
            }
            while(!removeQ.isEmpty()){
                Position p = removeQ.poll();
                matrix[p.row][p.col] = -1;
                adj[p.row][p.col] = 0;
            }
            year++;
        }
        if(removeQ.size() == 0){
            System.out.println(year);
        }
    }

    static class Position{
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
