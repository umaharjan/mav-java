package com.app.task.activity.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

 public class Apputils {

    public static Boolean emailValidation(String email){
        String regExpn=("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$");
        Pattern pattern= Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
       Matcher matcher=pattern.matcher(email);
        return matcher.matches();
    }


}
