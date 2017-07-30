package com.startup.mvp.interactor;

import android.os.Handler;

import com.startup.mvp.helper.UtilsImpl;
import com.startup.mvp.interfaces.register.OnRegisterFinishedListener;
import com.startup.mvp.interfaces.register.RegisterInteractor;

/**
 * Created by Harish Penta on 24-7-2017.
 */
public class RegisterInteractorImpl implements RegisterInteractor {

    private UtilsImpl utils;

    public RegisterInteractorImpl() {
        this.utils = new UtilsImpl();
    }

    @Override
    public void register(final String username, final String email, final String pass, final String repeatpass, final OnRegisterFinishedListener listener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Logic :D

                if (!username.equals("") && !email.equals("") && !pass.equals("") && !repeatpass.equals("")) {

                    if (utils.validateEmail(email) && utils.validatePassword(pass)) {

                        if (pass.equals(repeatpass)) {
                            listener.onSucess();
                        } else {
                            listener.setErrorRepeatPassword();
                        }
                    } else if (!utils.validateEmail(email)) {
                        listener.setErrorEmail();
                    } else {
                        listener.setErrorEmail();
                        listener.setErrorPassword();
                    }

                }

                if (username.equals("")) {
                    listener.setErrorUsername();
                }
                if (email.equals("")) {
                    listener.setErrorEmail();
                }
                if (pass.equals("")) {
                    listener.setErrorPassword();
                }
                if (repeatpass.equals("")) {
                    listener.setErrorRepeatPassword();
                }


            }
        }, 2000);
    }
}
