package kakao.MockExam;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Test2 {

    public static void main(String[] args) {

        Test2 test2 = new Test2();

        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
//        String s ="{{1,2,3},{2,1},{1,2,4,3},{2}}";
//        String s = "{{20,111},{111}}";

        int[] result = test2.solution(s);

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public int[] solution(String s) {
        Set<Integer> set = new HashSet<>();
        int[] answer = {};
        //앞 뒤 자르기
        s = s.substring(1,s.length()-1);
        // , 기준으로 자르기
        String[] tokens = s.split("},");
        answer = new int[tokens.length];

        //문자열 길이로 정렬
        Arrays.sort(tokens, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() > o2.length()){
                    return 1;
                }
                else{
                    return -1;
                }
            }
        });

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if(token.charAt(0) == '{'){
                token = token.substring(1,token.length());
            }
            if(token.charAt(token.length()-1) == '}'){
                token = token.substring(0,token.length()-1);
            }

            String[] commaTokens = token.split(",");

            for (int j = 0; j < commaTokens.length; j++) {
                int num = Integer.parseInt(commaTokens[j]);

                if(!set.contains(num)) {
                    set.add(num);
                    answer[i] = num;
                }
            }
        }

        return answer;
    }

}
