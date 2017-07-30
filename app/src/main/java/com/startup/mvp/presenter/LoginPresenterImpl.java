package com.startup.mvp.presenter;


import com.startup.mvp.interactor.LoginInteractorImpl;
import com.startup.mvp.interfaces.login.LoginInteractor;
import com.startup.mvp.interfaces.login.LoginPresenter;
import com.startup.mvp.interfaces.login.LoginView;
import com.startup.mvp.interfaces.login.OnLoginFinishedListener;

/**
 * Created by Harish Penta on 24-7-2017.
 */
public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.startAnimation();
        }
        loginInteractor.login(username, password, this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.resetLoginAnimationOnError();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.resetLoginAnimationOnError();
        }
    }

    @Override
    public void onSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }
}
