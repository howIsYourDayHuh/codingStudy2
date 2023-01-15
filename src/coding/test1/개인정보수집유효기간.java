package coding.test1;

import java.util.ArrayList;
import java.util.Arrays;

public class 개인정보수집유효기간 {


    public static void main(String[] args){
        String today = "2022.02.28";
        String[] terms = {"T 24","P 23", "A 18", "B 6", "C 3", "D 23", "E 12"};
        String[] privacies = {"2020.01.01 T", "2020.01.28 P", "2019.12.1 E", "2020.01.28 D", "2020.06.28 A", "2020.01.28 A","2019.12.1 A","2021.01.01 B", "2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};



        // 1. 모든 달은 28일까지 있다고 가정합니다.
        // 1일이면 -> 28일


        // 들어온거 맞지??? 값 체크 체크
        if(!_doInputValuesHaveData(today, terms, privacies)) _log("FALSE");

        // 유효기간
        String expiryDuration = "";

        // 너 내 동료로 변신!
        ArrayList<Integer> arrayOfIndex = new ArrayList<>();
        // index
        int index = 0;
        // 돌려라~
        for(String privacy : privacies) {
            index++;

            // 날짜 <-> 구간 분리
            String[] datas = privacy.split(" ");
            // 유효일자 구하기
            expiryDuration = _getExpiryDurationByComaprison(datas, terms);

            // 날짜 짜르기
            String[] date = datas[0].split("\\.");
            // 유효기간과 함께 달을 더한 것
            int summedMonth = Integer.parseInt(date[1])
                    + Integer.parseInt(expiryDuration);


            _log("[summedMonth]" + summedMonth);

            if (summedMonth > 12) {

                int calculatedYears = 0;
                int leftOverMonths = 0;

                calculatedYears = summedMonth / 12;
                leftOverMonths = summedMonth % 12;

                // 년도
                date[0] = Integer.toString(Integer.parseInt(date[0]) + calculatedYears);
                if (leftOverMonths < 10) {
                    date[1] = "0" + leftOverMonths;
                } else {
                    date[1] = Integer.toString(leftOverMonths);
                }


                _log("[울지마 바보야1]" + date[1]);
                _log("[울지마 바보야2]" + leftOverMonths);
                // 달


            } else  {
                if (summedMonth < 10) {
                    date[1] = "0" + summedMonth;
                } else {
                    date[1] = Integer.toString(summedMonth);
                }
            }

            if(date[2].equals("01")){
                date[2] = "28";
                if(Integer.parseInt(date[1]) > 1){
                    int num = Integer.parseInt(date[1]) - 1;
                    if (num < 10) {
                        date[1] = "0" + num;
                    } else {
                        date[1] = Integer.toString(num);
                    }
                } else {
                    date[1] = "12";
                    date[0] = Integer.toString(Integer.parseInt(date[0]) - 1);
                }
            } else {
                int num = Integer.parseInt(date[2]) - 1;
                if (num < 10) {
                    date[2] = "0" + num;
                } else {
                    date[2] = Integer.toString(num);
                }
            }

            if(Integer.parseInt(date[1]) == 0){
                date[0] = Integer.toString(Integer.parseInt(date[0]) - 1);
                date[1] = "12";
            }

            _log("[YEAR]" + date[0]);
            _log("[MONTH]" + date[1]);
            _log("[DATE]" + date[2]);

            String dateTot = date[0] + date[1] + date[2];


            if (_checkWhetherExpiryDateIsOverOrNot(dateTot, today)) {
                arrayOfIndex.add(index);
            }

        } // ~ // ~ for(String privacy : privacies){

        int[] answer = arrayOfIndex.stream().mapToInt(Integer::intValue).toArray();

        _log("[ANSWER]" + answer.toString());
        for (int one : answer) {
            _log("[ANSWER]" + one);
        }
    }

    private static boolean _checkWhetherExpiryDateIsOverOrNot(String dateTot, String today) {

        int dateTotInt = Integer.parseInt(dateTot);
        int date = Integer.parseInt(today.replace(".", ""));

        _log("[dateTotInt]" + dateTotInt);
        _log("[date]" + date);
        if(date > dateTotInt){
            return true;
        }

        return false;
    } // ~ private static boolean _checkWhetherExpiryDateIsOverOrNot(String[] date, String today) {

    private static String _getExpiryDurationByComaprison(String[] datas, String[] terms) {

        for (String term : terms){

            String[] termData = term.split(" ");
            if(datas[1].equals(termData[0])){

                return termData[1];

            }

        }

        return "0";
    } // ~ private static String _getExpiryDurationByComaprison(String[] datas, String[] terms) {

    private static boolean _doInputValuesHaveData(String today, String[] terms, String[] privacies){

        if(today == null){
            return false;
        }

        if(terms == null){
            return false;
        }

        if(privacies == null){
            return false;
        }

        return true;
    } // ~ private static boolean _doInputValuesHaveData(String today, String[] terms, String[] privacies){


    private static void _log(String sentence){

        if(sentence != null){

            System.out.println("[LOG]" + sentence);

        }

    } // ~ private static void _log(String sentence){

}
