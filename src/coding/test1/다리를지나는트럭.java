package coding.test1;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class 다리를지나는트럭 {
    
    // 뭐지
    // Queue를 참조해서 만들어보고 있음
    // https://s205203.tistory.com/31
    // 위에 여기 참조해서 만들어보았습니다.
    static class 큐트레인 {
        private int 앞번호;
        private int 뒷번호;
        private int 최대수;
        private int 몇개나들어왔어;
        private Object[] 큐에들어온물건들;

        public 큐트레인(int 최대수){
            this.앞번호 = 0;
            this.뒷번호 = -1;
            this.최대수 = 최대수;
            this.큐에들어온물건들 = new Object[최대수];
        }

        public boolean 비어있나체크(){
            return 앞번호 == 뒷번호 + 1;
        }

        public boolean 꽉찬상태인지체크(){
            return 뒷번호 == 최대수 - 1;
        }

        public void 입력(Object 물건){
            if(꽉찬상태인지체크()) {
                _log("[입력] 한계최대치");
            } else {
                몇개나들어왔어++;
                큐에들어온물건들[++뒷번호] = 물건;
            }
        }

        public Object 물건보여주기(){
            if (비어있나체크()) {
                _log("[물건보여주기] 없음");
            } else {
                return 큐에들어온물건들[앞번호];
            }
            return null;
        }

        public Object 삭제(){
            Object 물건 = 물건보여주기();
            앞번호++;
            몇개나들어왔어--;
            return 물건;
        }
    }

    static class  그트럭Q {

        int 트럭번호 = 0; // 사실상 무게
        int 시작번호 = 0;
        int 트럭은그길을간다 = 0; // 다리길이

        public 그트럭Q(int 트럭번호, int 시작번호, int 트럭은그길을간다){
            this.트럭번호 = 트럭번호;
            this.시작번호 = 시작번호;
            this.트럭은그길을간다 = 트럭은그길을간다;
        }

        private int get트럭번호(){
            return this.트럭번호;
        }

        private int get시작번호(){
            return this.시작번호;
        }

        private int get트럭은그길을간다(){
            return  this.트럭은그길을간다;
        }

        private void set트럭번호(int 트럭번호){
            this.트럭번호 = 트럭번호;
        }

        private void set시작번호(int 시작번호){
            this.시작번호 = 시작번호;
        }

        private void set트럭은그길을간다(int 트럭은그길을간다){
            this.트럭은그길을간다 = 트럭은그길을간다;
        }

        private boolean 트럭은그다리를건넜는가(int 현재위치){
            return 현재위치 >= this.시작번호 + this.트럭은그길을간다;
        }
    }

    public static void main(String[] args){

        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = {7, 4, 5, 6};


        int answer = 0;

        List<Integer> 트럭무게를배열로전환 =
                Arrays.stream(truck_weights)
                        .boxed()
                        .collect(Collectors.toList());

        // 왜 링크드리스트만 가능하지?? ArrayList는 왜 불가능하지?
        // Queue<그트럭Q> 다리위트럭들 = new LinkedList<>();
        큐트레인 큐우 = new 큐트레인(truck_weights.length);


        boolean 트럭이아직다리위에있나봐 = true;
        int 인덱스 = 1;
        int 현재무게 = 0;

        while(트럭이아직다리위에있나봐){

            _log("[큐우.몇개나들어왔어]" + 큐우.몇개나들어왔어);
            _log("[현재무게]" + 현재무게);
            _log("[인덱스]" + 인덱스);

            그트럭Q 포터 = (그트럭Q) 큐우.물건보여주기();

            if(포터 != null){
                if(포터.트럭은그다리를건넜는가(인덱스)){
                    // 건넜구나!
                    현재무게 -= 포터.get트럭번호();
                    큐우.삭제();
                    _log("[큐우.몇개나들어왔어 삭제 후]" + 큐우.몇개나들어왔어);
                }
            }


            if (트럭무게를배열로전환.size() > 0) {
                if (weight >= (현재무게 + 트럭무게를배열로전환.get(0))) {
                    if (bridge_length >= 큐우.몇개나들어왔어) {
                        _log("[트럭무게를배열로전환.get(0)]" + 트럭무게를배열로전환.get(0));
                        그트럭Q 물건 = new 그트럭Q(트럭무게를배열로전환.get(0), 인덱스, bridge_length);
                        현재무게 += 트럭무게를배열로전환.get(0); // 트럭번호가 사실 무게니깐...
                        트럭무게를배열로전환.remove(0);
                        큐우.입력(물건);
                    }
                }
            }

            if(트럭무게를배열로전환.size() == 0 && 큐우.비어있나체크()){
                answer = 인덱스;
                트럭이아직다리위에있나봐 = false;
            }

            인덱스++;

        }

        _log("[ANSWER]" + answer);
    }

    public static void _log(String sentence){
        if(sentence != null){
            System.out.println("[LOG]" + sentence);
        }
    }

}
