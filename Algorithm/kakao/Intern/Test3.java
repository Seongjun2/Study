package kakao.MockExam;

import java.util.HashSet;
import java.util.Set;

public class Test3 {

    public static void main(String[] args) {
        Test3 test3 = new Test3();

//        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
//        String[] banned_id = {"fr*d*", "abc1**"};
//
//        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
//        String[] banned_id = {"*rodo", "*rodo", "******"};

        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "*rodo", "******", "******"};


        System.out.println(test3.solution(user_id, banned_id));

    }

    public int solution(String[] user_id, String[] banned_id) {

        Set<String> set = new HashSet<>();
        boolean[] userCheck = new boolean[user_id.length];

        dfs(banned_id, 0, user_id, userCheck, set);
        return set.size();
    }

    public void dfs(String[] banned_id, int banIdx, String[] user_id, boolean[] visited, Set<String> set){
        if(banIdx == banned_id.length){
            String s = "";
            for (int i = 0; i < visited.length; i++) {
                if(visited[i]) {
                    s += user_id[i] + " ";
                }
            }
            if(!set.contains(s)) {
                set.add(s);
            }
            return ;
        }
        for (int i = 0; i < user_id.length; i++) {
            if(visited[i]) continue;
            String user = user_id[i];
            String ban = banned_id[banIdx];

            if(user.length() != ban.length()) continue;
            //문자열 길이가 같을 때,

            boolean check = true;
            for (int k = 0; k < user.length(); k++) {
                if(ban.charAt(k) != user.charAt(k) && ban.charAt(k) != '*') {
                    check = false;
                    break;
                }
            }
            if(check) {
                visited[i] = true;
                dfs(banned_id, banIdx+1, user_id, visited, set);
                visited[i] = false;
            }
        }
    }
}
