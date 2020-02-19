// 다음과 같이 import를 사용할 수 있습니다.
package Daou.Grade1.t4;

import java.util.*;

public class Q8 {

    public int solution(int[] card, int n) {
        // 여기에 코드를 작성해주세요.
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        boolean[] visited = new boolean[card.length];

        int answer = 0;
        boolean check = false;

        dfs(card, set, list, visited,"");

        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) == n){
                answer = i+1;
                check = true;
                break;
            }
        }
        if(!check) answer = -1;
        return answer;
    }

    public void dfs(int[] card, Set<Integer> set, List<Integer> list, boolean[] visited,String s){

        if(s.length() == card.length){
            int num = Integer.parseInt(s);
            if(!set.contains(num)){
                set.add(num);
                list.add(num);
            }
            return ;
        }

        for (int i = 0; i < card.length; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            dfs(card,set,list,visited,s+card[i]);
            visited[i] = false;
        }
    }
    
    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
        Q8 sol = new Q8();
        int card1[] = {1, 2, 1, 3};
        int n1 = 1312;
        int ret1 = sol.solution(card1, n1);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret1 + " 입니다.");

        int card2[] = {1, 1, 1, 2};
        int n2 = 1122;
        int ret2 = sol.solution(card2, n2);
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret2 + " 입니다.");
    }    
}

/*

#문제8
1 이상 9 이하 숫자가 적힌 카드를 이어 붙여 숫자를 만들었습니다. 이때, 숫자 카드를 조합해 만든 수 중에서 n이 몇 번째로 작은 수인지 구하려 합니다.

예를 들어, 숫자 카드 1, 2, 1, 3로 만들 수 있는 수를 작은 순으로 나열하면 [1123, 1132, 1213, 1231, 1312, ... , 3121, 3211]입니다.
n이 1312라면, 숫자 카드를 조합해 만든 수 중 n은 n은 5번째로 작은 수입니다.

숫자 카드를 담은 배열 card, 수 n이 매개변수로 주어질 때 숫자 카드를 조합해 만든 수 중에서 n이 몇 번째로 작은 수인지 return 하도록 solution 메소드를 완성해주세요.

---

#####매개변수 설명

카드에 적힌 숫자를 담은 배열 card, 수 n이 solution 메소드의 매개변수로 주어집니다.

* card는 길이가 9 이하인 배열입니다.
* card의 원소는 1 이상 9 이하인 자연수입니다.
* n은 999,999,999 이하인 자연수입니다.
* n의 자릿수는 배열 card의 길이와 같습니다.
* n의 각 자리의 숫자는 1 이상 9 이하입니다.

---

#####return 값 설명

숫자 카드를 조합해 만든 수 중에서 n이 몇 번째로 작은 수인지 return 해주세요.

* 만약, n을 만들 수 없다면 -1을 return 해주세요.

---
#####예시

| card | n | return |
|----|----|----|
| [1, 2, 1, 3] | 1312 | 5 |
| [1, 1, 1, 2] | 1122 | -1 |

#####예시 설명

예시 #1
앞서 설명한 예와 같습니다.

예시 #2
숫자 카드를 조합하면 [1112, 1121, 1211, 2111]를 만들 수 있습니다. 따라서 1122는 만들 수 없습니다.

 */