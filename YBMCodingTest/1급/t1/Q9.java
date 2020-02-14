package Daou.Grade1.t1;

class Q9 {
    public String func_a(String str, int len){
        String padZero = "";
        int padSize = len - str.length();
        for(int i = 0; i < padSize; i++)
            padZero += "0";
        return padZero + str;
    }

    public int solution(String binaryA, String binaryB) {
        int maxLength = Math.max(binaryA.length(), binaryB.length());
        binaryA = func_a(binaryA, maxLength);
        binaryB = func_a(binaryB, maxLength);

        int hammingDistance = 0;
        for(int i = 0; i < maxLength; i++)
            if(binaryA.charAt(i) != binaryB.charAt(i))
                hammingDistance += 1;
        return hammingDistance;
    }

    // The following is main method to output testcase.
    public static void main(String[] args) {
        Q9 sol = new Q9();
        String binaryA = "10010";
        String binaryB = "110";
        int ret = sol.solution(binaryA, binaryB);

        // Press Run button to receive output.
        System.out.println("Solution: return value of the method is " + ret + " .");
    }
}

/*

#문제9
해밍 거리(Hamming distance)란 같은 길이를 가진 두 개의 문자열에서 같은 위치에 있지만 서로 다른 문자의 개수를 뜻합니다.
예를 들어 두 2진수 문자열이 "10010"과 "110"이라면, 먼저 두 문자열의 자릿수를 맞추기 위해 "110"의 앞에 0 두개를 채워 "00110"으로 만들어 줍니다.
 두 2진수 문자열은 첫 번째와 세 번째 문자가 서로 다르므로 해밍 거리는 2입니다.

* `1`0`0`1 0
* `0`0`1`1 0

두 2진수 문자열 binaryA, binaryB의 해밍 거리를 구하려 합니다. 이를 위해 다음과 같이 간단히 프로그램 구조를 작성했습니다

~~~
1단계. 길이가 더 긴 2진수 문자열의 길이를 구합니다.
2단계. 첫 번째 2진수 문자열의 길이가 더 짧다면 문자열의 앞에 0을 채워넣어 길이를 맞춰줍니다.
3단계. 두 번째 2진수 문자열의 길이가 더 짧다면 문자열의 앞에 0을 채워넣어 길이를 맞춰줍니다.
4단계. 길이가 같은 두 2진수 문자열의 해밍 거리를 구합니다.
~~~

두 2진수 문자열 binaryA와 binaryB가 매개변수로 주어질 때, 두 2진수의 해밍 거리를 return 하도록 solution 메소드를 작성했습니다. 이때, 위 구조를 참고하여 중복되는 부분은 func_a라는 메소드로 작성했습니다. 코드가 올바르게 동작할 수 있도록 빈칸을 알맞게 채워 전체 코드를 완성해주세요.

---
##### 매개변수 설명
두 2진수 문자열 binaryA와 binaryB가 solution 메소드의 매개변수로 주어집니다.

* binaryA의 길이는 1 이상 10 이하입니다.
* binaryA는 0 또는 1로만 이루어진 문자열이며, 0으로 시작하지 않습니다.
* binaryB의 길이는 1 이상 10 이하입니다.
* binaryB는 0 또는 1로만 이루어진 문자열이며, 0으로 시작하지 않습니다.

---
##### return 값 설명
두 2진수 문자열의 해밍 거리를 return 해주세요.

---
##### 예시

| binaryA | binaryB | return |
|---------|---------|--------|
| "10010" | "110"   | 2      |

##### 예시 설명
두 2진수의 자릿수는 각각 5와 3입니다. 자릿수를 맞추기 위해 "110" 앞에 0 두 개를 채워주면 "00110"이 됩니다.
 이제 두 2진수 문자열의 해밍 거리를 구하면 다음과 같습니다.

* `1`0`0`1 0
* `0`0`1`1 0

위와 같이 첫 번째와 세 번째 문자가 서로 다르므로, 해밍 거리는 2가 됩니다.

 */