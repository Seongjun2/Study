package Algo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem13460 {

    static int n;
    static int m;
    static int[][] board;
    static boolean[][][][] visited;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    //위, 오른, 아래, 왼

   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str;
        str = sc.nextLine().split(" ");

        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);

        board= new int[n][m];
        visited = new boolean[10][10][10][10];

        Marble marble = new Marble();
        //board 초기화
        for(int i = 0; i< n; i++){
            str = sc.nextLine().split("");
            for (int j = 0; j < m; j++) {
                if(str[j].equals("0")){
                    board[i][j] = 0;
                }
                else if(str[j].equals("#")){
                    board[i][j] = -1;
                }
                else if(str[j].equals(".")){
                    board[i][j] = -2;
                }
                else if(str[j].equals("B")){
                    marble.br = i;
                    marble.bc = j;
                    board[i][j] = -2;
                }
                else if(str[j].equals("R")){
                    marble.rr = i;
                    marble.rc = j;
                    board[i][j] = -2;
                }
            }
        }

        bfs(marble);
    }

    private static void bfs(Marble marble){
        Queue<Marble> q = new LinkedList<>();
        q.add(marble);

        while(!q.isEmpty()){
            Marble m = q.poll();
            visited[m.rr][m.rc][m.br][m.bc] = true;

            if(m.cnt >= 10){
                System.out.println(-1);
                return;
            }

            for (int i = 0; i < 4; i++) {

                //파란 구슬 ㄱ
                int bnr = m.br;
                int bnc = m.bc;

                while(board[bnr+dr[i]][bnc+dc[i]] != -1){
                    bnr += dr[i];
                    bnc += dc[i];
                    if (board[bnr][bnc] == 0) {
                        break;
                    }
                }
                //빨간 구슬 ㄱ
                int rnr = m.rr;
                int rnc = m.rc;

                while(board[rnr+ dr[i]][rnc + dc[i]] != -1){
                    rnr += dr[i];
                    rnc += dc[i];
                    if(board[rnr][rnc] == 0){
                        break;
                    }
                }

                //파란 구슬 빠지면 x
                if(board[bnr][bnc] == 0) continue;

                //빨간 구슬만 빠졌을 때
                if(board[rnr][rnc] == 0){
                    System.out.println(m.cnt+1);
                    return;
                }

                //둘다 안 빠짐(위치 조정)
                if(rnr == bnr && rnc == bnc){//같은 위치일 때
                    switch (i){
                        case 0://위
                            if(m.rr>m.br) rnr++;
                            else bnr++;
                            break;
                        case 1://오른
                            if(m.rc > m.bc) bnc--;
                            else rnc--;
                            break;
                        case 2://아래
                            if(m.rr>m.br) bnr--;
                            else rnr--;
                            break;
                        case 3://왼
                            if(m.rc>m.bc) rnc++;
                            else bnc++;
                            break;
                    }
                }

                if(!visited[rnr][rnc][bnr][bnc]) q.add(new Marble(rnr,rnc,bnr,bnc,m.cnt+1));
            }
        }
        System.out.println(-1);
    }

    static class Marble{
        int rr;
        int rc;
        int br;
        int bc;
        int cnt;

        public Marble() {
        }

        public Marble(int rr, int rc, int br, int bc, int cnt) {
            this.rr = rr;
            this.rc = rc;
            this.br = br;
            this.bc = bc;
            this.cnt = cnt;
        }
    }
}
/*
파란구슬 빨간구슬

기울이기

빨간 구슬 통과시 성공
파란 구슬 빠지면 실패
동시에 빠져도 실패

0 -> 0 구멍
# -> -1 벽
. -> -2 길
B -> -3
R -> -4

 */