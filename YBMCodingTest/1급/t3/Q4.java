// 다음과 같이 import를 사용할 수 있습니다.
package Daou.Grade1.t3;

import java.util.*;

public class Q4 {
    public int solution(String s1, String s2) {
        // 여기에 코드를 작성해주세요.
        int answer = 0;
        answer = Math.min(returnLength(s1,s2), returnLength(s2,s1));
        return answer;
    }

    public int returnLength(String s1, String s2){
        String s = "";
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if(i >= s1.length()){
                    s+= s2.charAt(j);
                }
                else if(s1.charAt(i) == s2.charAt(j)){
                    s += s1.charAt(i);
                    i++;
                }
                else{
                    if(s2.charAt(0) == s1.charAt(i)){
                        s += s1.charAt(i);
                        i++;
                        j = 0;
                    }
                    else{
                        s += s1.charAt(i);
                        i++;
                        j = -1;
                    }
                }
            }
        }

        return s.length();
    }

    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
        Q4 sol = new Q4();
        String s1 = new String("ababc");
        String s2 = new String("abcdab");
        int ret = sol.solution(s1, s2);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret + " 입니다.");
    }
}
/*

#문제4
두 문자열 s1과 s2를 붙여서 새 문자열을 만들려 합니다. 이때, 한 문자열의 끝과 다른 문자열의 시작이 겹친다면, 겹치는 부분은 한 번만 적습니다.

예를 들어 s1 = "ababc", s2 = "abcdab"일 때, 아래와 같이 s1 뒤에 s2를 붙이면 새 문자열의 길이는 9입니다.

  ![string2.png](https://grepp-programmers.s3.amazonaws.com/files/ybm/b4bd8f93a2/61f4238b-bcc7-435c-a203-da6ebb27d968.png)

그러나 s2 뒤에 s1을 붙이면 새 문자열의 길이는 8로, 더 짧게 만들 수 있습니다.

   ![string3.png](https://grepp-programmers.s3.amazonaws.com/files/ybm/e983c2cd38/9ffb7a01-73f3-47d5-aa39-b97543cf7973.png)

두 문자열 s1과 s2가 매개변수로 주어질 때, s1과 s2를 붙여서 만들 수 있는 문자열 중, 가장 짧은 문자열의 길이를 return 하도록 solution 메소드를 완성해주세요.

---
#####매개변수 설명
두 문자열 s1과 s2가 solution 메소드의 매개변수로 주어집니다.

* s1과 s2의 길이는 1 이상 100 이하입니다.
* s1과 s2는 알파벳 소문자로만 이루어져 있습니다.

---
#####return 값 설명
s1과 s2를 붙여서 만들 수 있는 문자열 중, 가장 짧은 문자열의 길이를 return 해주세요.

---
#####예시

| s1      | s2       | return |
|---------|----------|--------|
| "ababc" | "abcdab" | 8      |

#####예시 설명
문제에 주어진 예시와 같습니다.

 */