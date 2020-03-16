package kakao.summerwinter2019;

public class Origami {

    public static void main(String[] args) {
        Origami origami = new Origami();

        int[] result = origami.solution(20);

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public int[] solution(int n) {
        int[] answer = {};

        if(n == 1){
            answer = new int[1];
            answer[0] = 0;
        }
        else{
            int[] underArray = solution(n-1);
            int[] reversArray = reverse(underArray);
            answer = new int[underArray.length*2 + 1];
            System.arraycopy(underArray,0,answer,0,underArray.length);
            System.arraycopy(reversArray,0,answer,underArray.length,reversArray.length);
        }
        return answer;
    }

    public int[] reverse(int[] array){
        int[] rArray = new int[array.length+1];
        rArray[0] = 0;

        int idx = 1;
        for (int i = array.length-1; i >= 0; i--) {
            if(array[i] == 0){
                rArray[idx] = 1;
            }
            else{
                rArray[idx] = 0;
            }
            idx++;
        }

        return rArray;
    }
}
/*
해결 방법 : 재귀 사용

1. 규칙을 찾은 결과
이전 것(2인 경우 1의 것)과 대칭되어 이어지고 가운데 0이 붙는다.

 */
