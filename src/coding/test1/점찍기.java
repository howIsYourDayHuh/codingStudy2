package coding.test1;

public class 점찍기 {

    public static void main(String[] args){

        long answer = 0;

        long k = 1;
        long d = 5;

        // https://ksb-dev.tistory.com/254 <- 이거 때문에 형변환 문제였다는 걸 알았찌만
        // 참조하지는 마세요! 답이 있슴돠라
        // 다 만들고는 도저히 왜     문제가 풀리지 않을까
        // 하다가 형 변환 문제였구나 해서
        // 그냥 전부 long 으로 바꿔버렸습니다... ㅎㅎ
        // 그러니 풀렸어요 ㅋㅋ
        long D를K로나눈숫자 = d/k;
        // 이러면 y 는 고정된다.

        // 길이의 최대 길이를 구한다.
        long dd = d * d;

        // 이거 long 아니고 int이면 11, 13, 14였나가 오류나요
        long totCnt = 0; // 1 -> 00
        for(long index = 0; index <= D를K로나눈숫자; index ++){

            long kindex = index * k; // index * k -> 값
            long kindexkindex = kindex * kindex; // y 길이
            long ddMinuskindexkindex = dd - kindexkindex; // 최대 길이 - y길이

            long sqrt = (long) Math.sqrt(ddMinuskindexkindex); // 루트(x길이)
            long cnt = (sqrt / k) + 1; // 1은 0

            totCnt += cnt;


        }

        answer = totCnt;
        _log("[ANSWER]" + answer);

    }

    private static void _log(String sentence){
        if(sentence != null){
            System.out.println("[LOG]" + sentence);
        }
    }

}