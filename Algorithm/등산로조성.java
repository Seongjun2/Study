package SWExpert;

import java.util.ListIterator;
import java.util.Scanner;

public class 등산로조성 {

    static int[][] matrix;
    static int n;
    static int k;
    static int maxHeight = 0;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int result;

    public static void main(String[] args) {
        등산로조성 test = new 등산로조성();
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        int num = 1;
        while(tc-- > 0) {
            n = sc.nextInt();
            k = sc.nextInt();

            result = 1;
            maxHeight = 0;

            matrix = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int height = sc.nextInt();
                    matrix[i][j] = height;
                    maxHeight = Math.max(height,maxHeight);
                }
            }

            test.solution();
            System.out.println("#"+ num++ + " " +result);
        }
    }

    private void solution() {

        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int height = matrix[i][j];

                //최대치 일 때만 돌아.
                if(height == maxHeight){
//                    System.out.println(i + "," + j);
                    visited[i][j] = true;
                    dfs(visited, i, j, false, 1, String.valueOf(matrix[i][j]));
                    visited[i][j] = false;
                }
            }
        }
    }

    private void dfs(boolean[][] visited, int row, int col, boolean cut, int length, String p) {

        for (int i = 0; i < 4; i++) {

            int nr = row + dr[i];
            int nc = col + dc[i];

            if(nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
            if(visited[nr][nc]) continue;

            int now = matrix[row][col];
            int next = matrix[nr][nc];

            if(now>next){//갈 수 있음.
                visited[nr][nc] = true;
                String addP = String.valueOf(matrix[nr][nc]);
                p += addP;
                dfs(visited, nr, nc, cut, length+1, p);
                p =p.substring(0,p.length()-addP.length());
                visited[nr][nc] = false;
            }
            else{//갈 수 없음.
                if(cut) continue;
//                if(now+k-1 <= next-k){//깎을 수 있음.
                    for (int j = 1; j <=k ; j++) {
                        if(next-j <= 0) break;
                        if(next-j < now){
                            matrix[nr][nc] -= j;
                            visited[nr][nc] = true;
                            String addP = String.valueOf(matrix[nr][nc]);
                            p += addP;
                            dfs(visited, nr, nc, true, length+1, p);
                            p = p.substring(0,p.length()-addP.length());
                            visited[nr][nc] = false;
                            matrix[nr][nc] += j;
                            break;
                        }
                    }
//                }
            }
        }
        result = Math.max(result, length);
//        System.out.println(p + " " + length);

//        System.out.println();
    }

    class Position{
        int row, col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}

/*

1
5 1
9 3 2 3 2
6 3 1 7 5
3 4 8 9 9
2 3 7 7 7
7 6 5 5 8

1
3 2
1 2 1
2 1 2
1 2 1
5 2

10
5 1
9 3 2 3 2
6 3 1 7 5
3 4 8 9 9
2 3 7 7 7
7 6 5 5 8
3 2
1 2 1
2 1 2
1 2 1
5 2
9 3 2 3 2
6 3 1 7 5
3 4 8 9 9
2 3 7 7 7
7 6 5 5 8
4 4
8 3 9 5
4 6 8 5
8 1 5 1
4 9 5 5
4 1
6 6 1 7
3 6 6 1
2 4 2 4
7 1 3 4
5 5
18 18 1 8 18
17 7 2 7 2
17 8 7 4 3
17 9 6 5 16
18 10 17 13 18
6 4
12 3 12 10 2 2
13 7 13 3 11 6
2 2 6 5 13 9
1 12 5 4 10 5
11 10 12 8 2 6
13 13 7 4 11 7
7 3
16 10 14 14 15 14 14
15 7 12 2 6 4 9
10 4 11 4 6 1 1
16 4 1 1 13 9 14
3 12 16 14 8 13 9
3 4 17 15 12 15 1
6 6 13 6 6 17 12
8 5
2 3 4 5 4 3 2 1
3 4 5 6 5 4 3 2
4 5 6 7 6 5 4 3
5 6 7 8 7 6 5 4
6 7 8 9 8 7 6 5
5 6 7 8 7 6 5 4
4 5 6 7 6 5 4 3
3 4 5 6 5 4 3 2
8 2
5 20 15 11 1 17 10 14
1 1 11 16 1 14 7 5
17 2 3 4 5 13 19 20
6 18 5 16 6 7 8 5
10 4 5 4 9 2 10 16
2 7 16 5 8 9 10 11
12 19 18 8 7 11 15 12
1 20 18 17 16 15 14 13

 */
