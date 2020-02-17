package com.software.common.util;

import java.util.Calendar;

public class AccountUtil {
    private static int studentNum = 0;
    public static String genAccount(String collegeId){
        studentNum += 1;
        String newStudentNum = String.format("%04d",studentNum);
        //今年年份
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String account = ""+year+(Integer.parseInt(collegeId)>9?collegeId:"0"+collegeId)+newStudentNum;
        return account;
    }

}
