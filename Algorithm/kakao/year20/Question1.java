package kakao.year20;

public class Question1 {

    public static void main(String[] args) {
        Question1 q1 = new Question1();

//        String s = "aabbaccc";
        String s = "ababcdcdababcdcd";
//        String s = "abcabcdede";
        System.out.println(q1.solution(s));

    }

    public int solution(String s){
        int answer = s.length();

        for (int i = 1; i < s.length()/2 +1; i++) {

            String prev = "";
            int cnt = 1;
            StringBuilder sb = new StringBuilder();
            boolean check = false;

            for (int j = 0; j < s.length(); j=j+i) {

                if(j+i > s.length()){
                    String tail = s.substring(j,s.length());
                    if(prev.equals(tail)){
                        sb.append((cnt+1)+prev);
                    }
                    else{
                        if(cnt == 1){
                            sb.append(prev);
                        }
                        else{
                            sb.append(cnt + prev);
                        }
                        sb.append(tail);
                    }
                    check = true;
                    break;
                }
                if(j == 0){
                    prev = s.substring(0,j+i);
                    continue;
                }
                else{
                    String ts = s.substring(j,j+i);
                    if(ts.equals(prev)){// 같은 때,
                        cnt++;
                    }
                    else{//다를 때,
                        if(cnt == 1){
                            sb.append(prev);
                        }
                        else{
                            sb.append(cnt + prev);
                            cnt = 1;
                        }
                        prev = ts;
                    }
                }
            }

            if(!check) {
                if (cnt == 1) {
                    sb.append(prev);
                } else {
                    sb.append(cnt + prev);
                }
            }

//            System.out.println(sb.toString());
            int resultLength = sb.toString().length();

            answer = Math.min(resultLength, answer);
        }

        return answer;
    }


}
