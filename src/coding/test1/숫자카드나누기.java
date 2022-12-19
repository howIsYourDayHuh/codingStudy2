package coding.test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 숫자카드나누기 {



    public static void main(String[] args){
        
        // 숫자카드나누기
        // https://school.programmers.co.kr/learn/courses/30/lessons/135807
        // 1. 철수가 가진 카드들에 적힌 모든 숫자를 나눌 수 있고 영희가 가진 카드들에 적힌 모든 숫자들 중 하나도 나눌 수 없는 양의 정수 a
        // 2. 영희가 가진 카드들에 적힌 모든 숫자를 나눌 수 있고, 철수가 가진 카드들에 적힌 모든 숫자들 중 하나도 나눌 수 없는 양의 정수 a
        int answer = 0;

//        int[] arrayA = {10, 17};
//        int[] arrayB = {5, 20};

//        int[] arrayA = {10, 20};
//        int[] arrayB = {5, 17};

        int[] arrayA = {14, 35, 119};
        int[] arrayB = {18, 30, 102};

        if(_isLengthOfTwoArraysEqual(arrayA, arrayB)){

            _log("[let's get it]");

            int minNumOfA = _getThatMinNum(arrayA);
            int minNumOfB = _getThatMinNum(arrayB);

            _log("[max]" + minNumOfA + " / " + minNumOfB);

            // 1번 -> 철희 짱짱 방식
            int indexForA = 0;
            List<Integer> justBascketForChulsu = new ArrayList<>();
            List<Integer> ultimateBascketForChulsu = new ArrayList<>();
            // 모두를 나눌 수 있는 숫자가 있을까요~~~?
            for(int index = 1; index <= minNumOfA; index++){
                if((minNumOfA % index) == 0){
                    justBascketForChulsu.add(index);
                }
            }

            for(int index : justBascketForChulsu){

                int subIndex = 0;
                for(int numOfA : arrayA){

                    if((numOfA % index) == 0){
                        subIndex++;
                    }

                }

                _log("[subIndex]" + subIndex);
                if(subIndex == arrayA.length){

                    int subSubIndex = 0;
                    for(int numOfB : arrayB){

                        if((numOfB % index) != 0){
                            subSubIndex++;
                        }

                    }

                    if(subSubIndex == arrayA.length){
                        ultimateBascketForChulsu.add(index);
                    }
                }
            }

            // 2번 -> 영희 짱짱 방식
            int indexForB = 0;
            List<Integer> justBascketForYounghwi = new ArrayList<>();
            List<Integer> ultimateBascketForYounghwi = new ArrayList<>();
            // 모두를 나눌 수 있는 숫자가 있을까요~~~?

            // 모두를 나눌 수 있는 숫자가 있을까요~~~?
            for(int index = 1; index <= minNumOfB; index++){
                if((minNumOfB % index) == 0){
                    justBascketForYounghwi.add(index);
                }
            }

            for(int index : justBascketForYounghwi){

                int subIndex = 0;
                for(int numOfB : arrayB){

                    if((numOfB % index) == 0){
                        subIndex++;
                    }

                }

                _log("[subIndex]" + subIndex);
                if(subIndex == arrayB.length){

                    int subSubIndex = 0;
                    for(int numOfA : arrayA){

                        if((numOfA % index) != 0){
                            subSubIndex++;
                        }

                    }

                    if(subSubIndex == arrayB.length){
                        ultimateBascketForYounghwi.add(index);
                    }
                }
            }

            boolean emptinessOfCompetition = ultimateBascketForYounghwi.isEmpty() && ultimateBascketForChulsu.isEmpty();
            if(!emptinessOfCompetition){
                Integer maxNumOfChulsu = 0;
                if(!ultimateBascketForChulsu.isEmpty()) {
                    maxNumOfChulsu = Collections.max(ultimateBascketForChulsu);
                }

                Integer maxNumOfYounghwi = 0;
                if(!ultimateBascketForYounghwi.isEmpty()){
                    maxNumOfYounghwi = Collections.max(ultimateBascketForYounghwi);
                }

                answer = maxNumOfChulsu >= maxNumOfYounghwi ? maxNumOfChulsu : maxNumOfYounghwi;

            }

        } // ~ if(_isLengthOfTwoArraysEqual(arrayA, arrayB)){


        _log("[answer]" + answer);

    }

    private static int _getThatMinNum(int[] array) {
        int min = 999999999;

        for(int num : array) min = Math.min(num, min);

        return min;
    } // ~ private static int _getThatMaxNum(int[] arrayA) {

    private static boolean _isLengthOfTwoArraysEqual(int[] arrayA, int[] arrayB) {

        if(arrayA == null){
            return false;
        }

        if(arrayB == null){
            return false;
        }

        if(arrayA.length != arrayB.length){
            return false;
        }

        return true;

    } // ~ private static void _isLengthOfTwoArraysEqual(int[] arrayA, int[] arrayB) {


    private static void _log(String sentence){

        if(sentence != null){

            System.out.println("[_log]" + sentence);

        } // ~ if(sentence != null){

    } // ~ private static void _log(String sentence){

}

