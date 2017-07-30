package com.startup.mvp.presenter;


import com.startup.mvp.interactor.MainInteractorImpl;
import com.startup.mvp.interfaces.main.MainInteractor;
import com.startup.mvp.interfaces.main.MainPresenter;
import com.startup.mvp.interfaces.main.MainView;
import com.startup.mvp.model.Photo;

import java.util.List;

/**
 * Created by Harish Penta on 24-7-2017.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.mainInteractor = new MainInteractorImpl(this);
    }

    @Override
    public void StartProcess() {
        if (mainView != null) {
            mainView.showProgress();
            mainInteractor.getAllData();
        }
    }


    @Override
    public void onSucessDataLoad(List<Photo> result) {

        if (mainView != null) {
            mainView.setDataToListview(result);
            mainView.hideProgress();
        }

    }

    @Override
    public void onErrorDataLoad(String error) {

        if (mainView != null) {
            mainView.showError(error);
            mainView.hideProgress();
        }

    }


    @Override
    public void onDestroy() {
        mainView = null;
    }

}
