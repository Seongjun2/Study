package kakao.MockExam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test4 {

    public static void main(String[] args) {
        Test4 test4 = new Test4();

        long k =10;
        long[] room_number = {1,3,4,1,3,1};

        System.out.println(test4.solution(k, room_number));
    }

    public long[] solution(long k, long[] room_number) {
        int n = room_number.length;

        List<Long> list = new ArrayList<>();
        Set<Long> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            long wantNumber = room_number[i];
            if(!set.contains(wantNumber)) {//배정되지 않은 방
                set.add(wantNumber);
                list.add(wantNumber);
            }
            else{//이미 배정된 방
                for (long j = wantNumber+1; j < k; j++) {
                    if(!set.contains(j)) {//배정되지 않은 방
                        set.add(j);
                        list.add(j);
                        break;
                    }
                }
            }
        }
        long[] result = new long[list.size()];
        for(int i= 0; i <list.size();i++){
            result[i] = list.get(i);
        }
        return result;
    }
}
