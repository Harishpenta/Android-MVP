package com.startup.mvp.interfaces.register;

/**
 * Created by Harish Penta on 24-7-2017.
 */
public interface OnRegisterFinishedListener {

    void setErrorUsername();

    void setErrorEmail();

    void setErrorPassword();

    void setErrorRepeatPassword();

    void setErrorEmptyFields();

    void onSucess();
}
