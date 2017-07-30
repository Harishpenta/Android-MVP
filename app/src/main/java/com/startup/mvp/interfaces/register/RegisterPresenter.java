package com.startup.mvp.interfaces.register;

/**
 * Created by Harish Penta on 24-7-2017.
 */
public interface RegisterPresenter {

    void ValidateRegister(String username, String email, String pass, String repeatpassword);

    void onDestroy();

}
