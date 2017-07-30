package com.startup.mvp.interfaces.login;

/**
 * Created by Harish Penta on 24-7-2017.
 */
public interface OnLoginFinishedListener {

    void onUsernameError();

    void onPasswordError();

    void onSuccess();
}
