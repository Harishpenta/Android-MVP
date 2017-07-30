package com.startup.mvp.helper;



import com.startup.mvp.interfaces.register.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Harish Penta on 24-7-2017.
 */
public class UtilsImpl implements Utils {

    //validate email correct example@example.com
    private final Pattern regexEmail =   Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    @Override
    public boolean validateEmail(String email) {
        Matcher matcher = regexEmail.matcher(email);

        return matcher.find();
    }

    @Override
    public boolean validatePassword(String password) {
        if(password.length() > 6)
            return true;

        return false;
    }

}
