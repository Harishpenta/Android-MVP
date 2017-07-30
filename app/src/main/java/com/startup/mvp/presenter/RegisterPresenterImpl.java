package com.startup.mvp.presenter;


import com.startup.mvp.interactor.RegisterInteractorImpl;
import com.startup.mvp.interfaces.register.OnRegisterFinishedListener;
import com.startup.mvp.interfaces.register.RegisterInteractor;
import com.startup.mvp.interfaces.register.RegisterPresenter;
import com.startup.mvp.interfaces.register.RegisterView;

/**
 * Created by Harish Penta on 24-7-2017.
 */
public class RegisterPresenterImpl implements RegisterPresenter, OnRegisterFinishedListener {

    RegisterView view;
    RegisterInteractor interactor;

    public RegisterPresenterImpl(RegisterView view) {
        this.view = view;

        interactor = new RegisterInteractorImpl();
    }

    @Override
    public void ValidateRegister(String username, String email, String pass, String repeatpassword) {
        if (view != null) {
            view.showProgress();
        }
        interactor.register(username, email, pass, repeatpassword, this);
    }

    @Override
    public void onDestroy() {

        if (view != null) {
            view = null;
        }

    }

    @Override
    public void setErrorUsername() {
        if (view != null) {
            view.setErrorUserName();
            view.hideProgress();
        }
    }

    @Override
    public void setErrorEmail() {
        if (view != null) {
            view.setErrorEmail();
            view.hideProgress();
        }
    }

    @Override
    public void setErrorPassword() {
        if (view != null) {
            view.setErrorPassword();
        }
    }

    @Override
    public void setErrorRepeatPassword() {
        if (view != null) {
            view.setErrorRepeatPassword();
            view.hideProgress();
        }
    }

    @Override
    public void setErrorEmptyFields() {
        if (view != null) {
            view.setErrorEmptyFields();
            view.hideProgress();
        }
    }

    @Override
    public void onSucess() {
        if (view != null) {
            view.hideProgress();
            view.navigateToMain();
        }
    }
}
