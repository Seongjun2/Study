package Algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem16235 {

    static int n;
    static int m;
    static int k;
    static int[][] energy;
    static int[][] ground;

    static Queue<Tree> deadTrees= new LinkedList<>();
    static PriorityQueue<Tree> trees = new PriorityQueue<>();
    static Queue<Tree> breedTrees = new LinkedList<>();

    static int[] dr = {-1,-1,-1,0,0,1,1,1};
    static int[] dc = {-1,0,1,-1,1,-1,0,1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        k = Integer.parseInt(str[2]);

        energy = new int[n][n];
        ground = new int[n][n];

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                energy[i][j] = Integer.parseInt(str[j]);
                ground[i][j] = 5;
            }
        }
        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int row = Integer.parseInt(str[0])-1;
            int col = Integer.parseInt(str[1])-1;
            int age = Integer.parseInt(str[2]);

            Tree tree = new Tree(row, col, age);
            trees.add(tree);
        }

        solution();
        System.out.println(trees.size());
    }

    public static void solution(){
        for(int i = 0; i< k;i++){
            workSpring();
            workSummer();
            workFall();
            workWinter();
        }
    }

    private static void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(ground[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void workSpring(){
        //봄 : 나이 만큼 양분을 먹고, 나이가 1 증가 하나의 칸에 여러개의 나무가 있다면 나이 어린 나무부터 먹는다. 나이만큼 못 먹으면 즉시 죽는다.
        PriorityQueue<Tree> tempQ = new PriorityQueue<>();

        while(!trees.isEmpty()){
            Tree tree = trees.poll();
            if(ground[tree.row][tree.col] < tree.age ){//양분 불충분
                deadTrees.add(tree);
            }
            else{
                ground[tree.row][tree.col] -= tree.age;
                tree.age++;
                if(tree.age % 5 == 0){
                    breedTrees.add(tree);
                }
                else{
                    tempQ.add(tree);
                }
            }
        }
        trees = new PriorityQueue<>(tempQ);
    }

    public static void workSummer(){
        //여름 : 봄에 죽은 나무가 양분으로 변함. 죽은 나무 나이/2 -> 양분

        while(!deadTrees.isEmpty()){
            Tree tree = deadTrees.poll();
            ground[tree.row][tree.col] += tree.age/2;
        }
    }

    public static void workFall(){
        //가을 : 나무 번식 - 나이가 5의 배수, 인접한 8개의 칸에 나이가 1인 나무 생성.

        while(!breedTrees.isEmpty()){
            Tree tree = breedTrees.poll();
            for(int i = 0; i < 8; i++){
                int nr = tree.row+ dr[i];
                int nc = tree.col+ dc[i];

                if(nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

                Tree newTree = new Tree(nr,nc,1);
                trees.add(newTree);
            }
            trees.add(tree);
        }
    }

    public static void workWinter(){
        //겨울 : 양분을 추가. 각 칸에 추가되는 양분의 양은 입력으로 주어짐.\
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ground[i][j] += energy[i][j];
            }
        }
    }

    static class Tree implements Comparable<Tree>{
        int row;
        int col;
        int age;

        public Tree(int row, int col, int age) {
            this.row = row;
            this.col = col;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }
}

/*
r,c
m그루의 나무를 심었다.
봄 : 나이 만큼 양분을 먹고, 나이가 1 증가 하나의 칸에 여러개의 나무가 있다면 나이 어린 나무부터 먹는다. 나이만큼 못 먹으면 즉시 죽는다.
여름 : 봄에 죽은 나무가 양분으로 변함. 죽은 나무 나이/2 -> 양분
가을 : 나무 번식 - 나이가 5의 배수, 인접한 8개의 칸에 나이가 1인 나무 생성.
겨울 : 양분을 추가. 각 칸에 추가되는 양분의 양은 입력으로 주어짐.
k년이 지난 후 상도의 땅에 살아 있는 나무의 개수
 */