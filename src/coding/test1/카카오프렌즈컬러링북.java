package coding.test1;

import java.util.*;

public class 카카오프렌즈컬러링북 {


    public static void main(String[] args){
        
        // 카카오프렌즈컬리링 북
        // https://school.programmers.co.kr/learn/courses/30/lessons/1829
        int n = 4; // 가로
        int m = 6; // 세로

        int[][] linesOfPicture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};

        //int[][] linesOfPicture = {{1, 1, 1, 0}, {1, 1, 1, 0}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};

       // int[][] linesOfPicture = {{1, 2, 3, 0}, {3, 4, 3, 0}, {0, 4, 4, 5}, {6, 1, 2, 3}, {4, 4, 4, 4}, {5, 5, 1, 4}};

        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        int[] answer = new int[2];

        // 체크박스
        String[][] checkBoxArray = new String[m][n];
        // 숫자 나래비
        int[] numsList = new int[_bringLengthByCheckingNAndM(n, m)];
        List<Integer> rsltOfNums = new ArrayList<Integer>();



        _log("[가로]" + n);
        _log("[세로]" + m);

        int cnt = 0;
        for(int[] line : linesOfPicture){

            for(int num : line){

                numsList[cnt] = num;
                cnt++;

            } // ~ for(int num : line){

        } // ~ for(int[] line : linesOfPicture){

        for(int num : numsList){

            int cntNum = 0; // 키은팅

            int chkNum = num;

            for(int index = 0; index < m; index++){

                for(int subIndex = 0; subIndex < n; subIndex++){

                    // 체크박스가 비어있으면 진행
                    if(checkBoxArray[index][subIndex] == null){

                        if(linesOfPicture[index][subIndex] == chkNum){

                            cntNum++;
                            checkBoxArray[index][subIndex] = "○";

                        } // ~ if(linesOfPicture[index][subIndex] == chkNum){

                        if(linesOfPicture[index][subIndex] != chkNum){

                            break;

                        } // ~ if(linesOfPicture[index][subIndex] != chkNum){

                    } // ~ if(checkBoxArray[index][subIndex] == null){

                } // ~ for(int subIndex = 0; subIndex < n; subIndex++){

            } // ~ for(int index = 0; index < m; index++){

            if(chkNum != 0){

                if (cntNum != 0) {

                    rsltOfNums.add(cntNum);

                } // ~ if (cntNum != 0) {

            } //  ~ if(chkNum != 0){

        } // ~ for(int num : numsList){

        rsltOfNums.stream().filter(Objects::nonNull).forEach(System.out::println);

        numberOfArea = rsltOfNums.size();
        maxSizeOfOneArea = Collections.max(rsltOfNums);

        _log("[numberOfArea / maxSizeOfOneArea]" + numberOfArea + " / " + maxSizeOfOneArea);
    }

    private static boolean _isItOkayToCount(String[][] checkBoxArray, int chkNum, int index, int subIndex){

        boolean isItOkay = false;

        // 첫번쨰 줄
        if(index == 0){

            // 첫 행 첫 열
            if (subIndex == 0) {
                return true;
            }

            if (subIndex != 0) {


            }


        }

        return  isItOkay;

    } // ~ private static boolean _isItOkayToCount(int index, int subIndex){

    private static int _bringLengthByCheckingNAndM(int n, int m) {

        int length = 0;

        if(n != 0){

            if (m != 0) {

                length = n * m;

            }

        }

        _log("[넓이]" + length);

        return length;

    } // ~ private static int _bringLengthByCheckingNAndM(int n, int m) {

    private static boolean _isTheHeightEqualToM(int height, int[][] linesOfPicture){

        if(height != 0){

            if(linesOfPicture != null){

                _log("[세로길이]" + linesOfPicture.length);

                if(height == linesOfPicture.length){

                    return true;

                } // ~ if(height == linesOfPicture.length){

            } // ~ if(linesOfPicture != null){

        } // ~ if(height != null){

        return false;

    } // ~ private void _log(String sentence){

    private static void _log(String sentence){

        if(sentence != null){

            System.out.println("[log]" + sentence);

        } // ~ if(sentence != null){

    } // ~ private void _log(String sentence){

}
