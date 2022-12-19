package coding.test1;

import java.util.*;
import java.util.stream.Collectors;

public class 숫자타자대회 {

    public static enum HANDS{
        RIGHT, LEFT
    }

    static class HandProcessor{

        private int distZero = 0;
        private int distFirst = 0;

        public enum DIRECTION{
            ONE_WAY, DIAGONAL
        }

        public HandProcessor(int distZero, int distFirst){
            this.distZero = distZero;
            this.distFirst = distFirst;
        }

        public int getDistZero() {
            return distZero;
        }

        public void setDistZero(int distZero) {
            this.distZero = distZero;
        }

        public int getDistFirst() {
            return distFirst;
        }

        public void setDistFirst(int distFirst) {
            this.distFirst = distFirst;
        }

        public int handProcessor(){

            int score = 0;

            int distZero = this.distZero;
            int distFirst = this.distFirst;
            _processorLog("[dist]" + distZero + "/" + distFirst) ;

            boolean areThereTwoZeroes = false;
            if(distZero == 0 && distFirst == 0){
                areThereTwoZeroes = true;
                score = 1;
            }

            if(!areThereTwoZeroes){
                int sumOfTwo = distZero + distFirst;
                while (sumOfTwo > 0){
                    _processorLog("[sumOfTwo Start]" + sumOfTwo) ;
                    DIRECTION direction = null;
                    if(distZero > 0 && distFirst > 0){
                        direction = DIRECTION.DIAGONAL;
                    }
                    if((distZero > 0 && distFirst == 0)
                            || (distFirst > 0 && distZero == 0)){
                        direction = DIRECTION.ONE_WAY;
                    }
                    // 대각선이면
                    if(direction != null && direction.equals(DIRECTION.DIAGONAL)){
                        distZero--;
                        distFirst--;
                        score = score + 3; // 대각선 3
                    }

                    // 한방향
                    if(direction != null && direction.equals(DIRECTION.ONE_WAY)){
                        if(distZero > 0){
                            distZero--;
                        }
                        if(distFirst > 0){
                            distFirst--;
                        }
                        score = score + 2; // 대각선 3
                    }

                    sumOfTwo = distZero + distFirst;

                    if(direction == null){
                        _processorLog("[direction null]") ;
                    }
                    _processorLog("[score]" + score );
                    _processorLog("[sumOfTwo End]" + sumOfTwo) ;
                }
            }

            _processorLog("[Final]" + score);
            return score;
        }

        public void _processorLog(String sentence){
            if(sentence != null){
                System.out.println("[_processorLog]:" + sentence);
            }
        }

    } // ~ static class HandProcessor{

    public static void main(String[] args){

        // 이거는 숫자 타자 대회
        // https://school.programmers.co.kr/learn/courses/30/lessons/136797
       // String numbers = "1756";
        // String numbers = "5123";
         String numbers = "45654";
        //  11  12  13
        //  21  22  23
        //  31  32  33
        //  41  42  44

        // 자리 그대로 -> 1
        // 상하좌우 -> 2
        // 대각선 -> 3
        // 먼 숫자라면 최소거리로 계산

        Map<String, int[]> phoneNums = new HashMap<String, int[]>();
        _setPhoneNums(phoneNums);

        Map<String, int[]> leftHand = new HashMap<String, int[]>();
        leftHand.put("4", new int[]{2, 1});

        Map<String, int[]> rightHand = new HashMap<String, int[]>();
        rightHand.put("6", new int[]{2, 3});

        int score = 0; // 점수
        int index = 0;
        // 문자열 짜르기
        if(numbers != null){
            
            String[] nums = numbers.split("");
            _log("[nums length]" + nums.length);
            // 왼손과 오른손 중 어디서 더 가까운지 판단
            for(String num : nums){

                // 번호의 좌표
                int[] numCoordinate = phoneNums.get(num);
                _log("[num]" + num);
                _log("[numCoordinate]" + numCoordinate[0] + " / " + numCoordinate[1]);
                // 왼손 거리
                int[] leftHandCoordinate = {0,0};
                for(int[] value : leftHand.values()){
                    leftHandCoordinate = value;
                }

                int[] rightHandCoordinate = {0,0};
                for(int[] value : rightHand.values()){
                    rightHandCoordinate = value;
                }

                _log("[num========================]" + num);
                _log("[score========================]" + score);
                _log("[rightHandCoordinate========================]" + rightHand.toString() + " / " + rightHandCoordinate[0] + " / " + rightHandCoordinate[1]);
                _log("[leftHandCoordinate=========================]" + leftHand.toString() + " / " + leftHandCoordinate[0] + " / " + leftHandCoordinate[1]);

                int leftScore = 0;
                int distLeftZero = 0;
                int distLeftFirst = 0;

                distLeftZero = Math.abs(numCoordinate[0] - leftHandCoordinate[0]);
                distLeftFirst = Math.abs(numCoordinate[1] - leftHandCoordinate[1]);
                _log("[distLeft]" + distLeftZero + " / " + distLeftFirst);
                HandProcessor leftHandProcessor = new HandProcessor(distLeftZero, distLeftFirst);
                leftScore = leftHandProcessor.handProcessor();
                _log("[leftScore]" + leftScore);

                // 오른손 거리


                int rightScore = 0;
                int distRightZero = 0;
                int distRightFirst = 0;

                distRightZero = Math.abs(numCoordinate[0] - rightHandCoordinate[0]);
                distRightFirst = Math.abs(numCoordinate[1] - rightHandCoordinate[1]);
                _log("[distRight]" + distRightZero + " / " + distRightFirst);
                HandProcessor rightHandProcessor = new HandProcessor(distRightZero, distRightFirst);
                rightScore = rightHandProcessor.handProcessor();
                _log("[rightScore]" + rightScore);
                
                if(leftScore > rightScore){ // 오른손 승
                    _log("[rightHand Won]");
                    score = score + rightScore;
                    rightHand = new HashMap<String, int[]>();
                    rightHand.put(num, phoneNums.get(num));
                }

                if(leftScore < rightScore){
                    _log("[leftHand Won]");
                    score = score + leftScore;
                    leftHand = new HashMap<String, int[]>();
                    leftHand.put(num, phoneNums.get(num));
                }
                
                // 중복이라면... 어디 하나에 그냥 주는게 아니라 
                // 정보를 완전히 흡수했다는 가정을 하고 나눠주는 걸 고려하지 않을까?
                if(leftScore == rightScore){

                    HANDS hand = null;
                    _log("[Same Same]");

                    // 다음 것 과의 거리를 계산해야 하지 않을까??

                    if(index < (nums.length -1 )){

                        hand = _whichHandIsCloserToNextNum(numCoordinate, nums[index + 1], rightHandCoordinate , leftHandCoordinate, phoneNums, nums, index + 1);

                        if(HANDS.LEFT.equals(hand)){
                            score = score + leftScore; // 일단 그냥
                            leftHand = new HashMap<String, int[]>();
                            leftHand.put(num, phoneNums.get(num));
                        }

                        if(HANDS.RIGHT.equals(hand)){
                            score = score + rightScore; // 일단 그냥
                            rightHand = new HashMap<String, int[]>();
                            rightHand.put(num, phoneNums.get(num));
                        }

                    }

                    if(index == (nums.length -1)){
                        score = score + rightScore;
                    }

                }

                index++;

            } // ~ for(String num : nums){

        } // ~ if(numbers != null){

        _log("[ULTIMATE SCORE]" + score);

    }


    // 왼손과 오른손중 어디서 더 가까운지 판단
    // 1. 정보를 전부 알고 있을까????????
    // 다음번호 정도를 알고 있지 않을까???
    private static HANDS _whichHandIsCloserToNextNum(int[] numCoordinate, String nextNum, int[] rightHandCoordinate, int[] leftHandCoordinate, Map<String, int[]> phoneNums, String[] nums, int nextIndex) {

        // 오른손이 그 번호를 받았을 떄
        int[] nextNumCoordinate = phoneNums.get(nextNum);

        HANDS hand = null;
        int rightTotScore      = 0;
        int leftTotScore       = 0;

        if(1 == 1){
            int leftScore = 0;
            int distLeftZero = 0;
            int distLeftFirst = 0;
            distLeftZero = Math.abs(nextNumCoordinate[0] - leftHandCoordinate[0]);
            distLeftFirst = Math.abs(nextNumCoordinate[1] - leftHandCoordinate[1]);
            _log("[distLeft]" + distLeftZero + " / " + distLeftFirst);
            HandProcessor leftHandProcessor = new HandProcessor(distLeftZero, distLeftFirst);
            leftScore = leftHandProcessor.handProcessor();
            _log("[leftScore]" + leftScore);

            int rightScore = 0;
            int distRightZero = 0;
            int distRightFirst = 0;
            distRightZero = Math.abs(nextNumCoordinate[0] - numCoordinate[0]);
            distRightFirst = Math.abs(nextNumCoordinate[1] - numCoordinate[1]);
            HandProcessor rightHandProcessor = new HandProcessor(distRightZero, distRightFirst);
            rightScore = rightHandProcessor.handProcessor();

            rightTotScore = Math.min(leftScore, rightScore);

        }

        if(2 == 2){
            int leftScore = 0;
            int distLeftZero = 0;
            int distLeftFirst = 0;
            distLeftZero = Math.abs(nextNumCoordinate[0] - numCoordinate[0]);
            distLeftFirst = Math.abs(nextNumCoordinate[1] - numCoordinate[1]);
            _log("[distLeft]" + distLeftZero + " / " + distLeftFirst);
            HandProcessor leftHandProcessor = new HandProcessor(distLeftZero, distLeftFirst);
            leftScore = leftHandProcessor.handProcessor();
            _log("[leftScore]" + leftScore);

            int rightScore = 0;
            int distRightZero = 0;
            int distRightFirst = 0;
            distRightZero = Math.abs(nextNumCoordinate[0] - rightHandCoordinate[0]);
            distRightFirst = Math.abs(nextNumCoordinate[1] - rightHandCoordinate[1]);
            HandProcessor rightHandProcessor = new HandProcessor(distRightZero, distRightFirst);
            rightScore = rightHandProcessor.handProcessor();

            leftTotScore = Math.min(leftScore, rightScore);

        }

        _log("[mid]VVVVVVVVV" + phoneNums.get(nextNum) + " / " + phoneNums.get(nextNum)[0] + " / " +  phoneNums.get(nextNum)[1]);
        _log("[mid]VVVVVVVVV" + rightTotScore);
        _log("[mid]VVVVVVVVV" + leftTotScore);
        hand = rightTotScore > leftTotScore ? HANDS.LEFT : HANDS.RIGHT;

        return hand;
        // 왼손이 그 번호를 받았을 때


    } // ~ private static void _whichHandIsCloser(int[] numCoordinate, Map<String,int[]> leftHand, Map<String,int[]> rightHand) {




    // 전화번호 좌표 정립
    private static void _setPhoneNums(Map<String,int[]> phoneNums) {

        phoneNums.put("1", new int[]{1, 1}); // 1
        phoneNums.put("2", new int[]{1, 2}); // 2
        phoneNums.put("3", new int[]{1, 3}); // 3

        phoneNums.put("4", new int[]{2, 1}); // 4
        phoneNums.put("5", new int[]{2, 2}); // 5
        phoneNums.put("6", new int[]{2, 3}); // 6

        phoneNums.put("7", new int[]{3, 1}); // 7
        phoneNums.put("8", new int[]{3, 2}); // 8
        phoneNums.put("9", new int[]{3, 3}); // 9

        phoneNums.put("*", new int[]{4, 1}); // *
        phoneNums.put("0", new int[]{4, 2}); // 0
        phoneNums.put("#", new int[]{4, 3}); // #

    } // ~ private static void _setPhoneNums(Map<String,int[]> phoneNums) {

    private static void _log(String sentence) {

        if(sentence != null){

            System.out.println("[log]" + sentence) ;

        } // ~ if(sentence != null){

    } // ~ private static void _log(String sentence) {
}
