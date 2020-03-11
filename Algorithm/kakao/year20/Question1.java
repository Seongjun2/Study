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

//            if(s.length()%i != 0) continue;//나누어 떨어지지 않으면.

            String prev = "";
            int cnt = 1;
            StringBuilder sb = new StringBuilder();
            boolean check = false;

            for (int j = 0; j < s.length(); j= j+i) {
                if(j+i >= s.length()){//범위를 넘어가면
                    sb.append(s.substring(j-i , s.length()));
                    check = true;
                    break;
                }
                if(j == 0){
                    prev = s.substring(0,j+i);
                }
                else{
                    String ts = s.substring(j, j+i);
                    if(prev.equals(ts)) cnt++;
                    else{//이전과 같지 않다.
                        if(cnt == 1){//한 번 나왔을 때,
                            sb.append(prev);
                            prev = ts;
                        }
                        else{//한 번 이상 나왔을 때,
                            sb.append(cnt+prev);
                            prev = ts;
                            cnt = 1;
                        }
                    }
                }
            }
            if(check == false){
                if(cnt == 1){//한 번 나왔을 때,
                    sb.append(prev);
                }
                else{//한 번 이상 나왔을 때,
                    sb.append(cnt+prev);
                }
            }
            String sbResult = sb.toString();
            answer = Math.min(sbResult.length(), answer);

        }

        return answer;
    }


}
