package com.startup.mvp.interfaces.register;

/**
 * Created by Harish Penta on 24-7-2017.
 */
public interface RegisterInteractor {
    void register(String username, String email, String pass, String repeatpass, OnRegisterFinishedListener listener);
}
