package kakao.summerwinter2019;

import java.math.BigInteger;

public class RightSquare {

    public static void main(String[] args) {

        RightSquare rs = new RightSquare();

        System.out.println(rs.solution(8,12));

    }

    public long solution(int w, int h){
        long answer = 1;

        long wLong = Long.parseLong(String.valueOf(w));
        long hLong = Long.parseLong(String.valueOf(h));

        if(w == h){//정사각형 일 때,
            answer = (wLong*hLong) - wLong;
        }
        else{//직사각형 일 때,
//            answer = (wLong*hLong) - ( wLong+hLong-gcd(wLong,hLong));
            long gcd = gcd(w,h);
            answer = wLong*hLong - (gcd * (wLong/gcd + hLong/gcd -1));
        }

        return answer;
    }

    private long gcd(long w, long h) {
        BigInteger b1 = BigInteger.valueOf(w);
        BigInteger b2 = BigInteger.valueOf(h);

        BigInteger gcd = b1.gcd(b2);

        return gcd.longValue();
    }
}
/*
해결 방법

1. 기울기를 측정하였다.
h = 12, w = 8 이라고 했을 때 기울기는 2/3 이다.(증가로 볼 때)
그러면 x가 2의 배수, y가 3의 배수 일 때 점을 지나는 것이다.
즉 4개의 점을 지나게 된다.

2. 최대 공약수
기울기로서 찾아낸 지나는 점은 4개
이 수는 8, 12의 최대공약수 이다.
이 점들로 4개의 블록이 생성 되는 것을 알 수 있다.

3. 블록들의 규칙을 찾아야 함
1*2의 경우 2개의 블록이 영향을 받음
2*3의 경우 4개의 블록이 영향을 받음
3*4의 경우 6개의 블록이 영향을 받음
 -> n + m - 1 개의 블록이 영향을 받음

4. 올바른 사각형의 계산
n 은 w/gcd
m 은 h/gcd
w * h - gcd(n + m - 1)
=> w * h - gcd(w/gcd + h/gcd - 1)






 */