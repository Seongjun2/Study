package kakao.MockExam;

import java.util.Stack;

public class Test1 {

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] moves = {1,5,3,5,1,2,1,4};

        System.out.println(test1.solution(board, moves));
    }

    public int solution(int[][] board, int[] moves) {
        Stack<Integer> stack = new Stack<>();

        int answer = 0;

        for (int i = 0; i < moves.length; i++) {
            int moveIdx = moves[i]-1;

            for (int j = 0; j < board.length; j++) {
                int doll = board[j][moveIdx];
                if(doll != 0){
                    if(stack.isEmpty()) {
                        stack.push(doll);
                        board[j][moveIdx] = 0;
                        break;
                    }
                    if(stack.peek() == doll){
                        answer+= 2;
                        stack.pop();
                    }
                    else{
                        stack.push(doll);
                    }
                    board[j][moveIdx] = 0;
                    break;
                }
            }

        }
        return answer;
    }
}
