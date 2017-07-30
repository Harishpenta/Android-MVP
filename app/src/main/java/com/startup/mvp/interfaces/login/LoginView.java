package com.startup.mvp.interfaces.login;

/**
 * Created by Harish Penta on 24-7-2017.
 */
public interface LoginView {


    void startAnimation();

    void resetLoginAnimationOnError();

    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();
}
