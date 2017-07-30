package com.startup.mvp.interfaces.register;

/**
 * Created by Harish Penta on 24-7-2017.
 */
public interface RegisterView {

    void showProgress();

    void hideProgress();

    void setErrorUserName();

    void setErrorEmail();

    void setErrorPassword();

    void setErrorRepeatPassword();

    void setErrorEmptyFields();

    void navigateToMain();

}
