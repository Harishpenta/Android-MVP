package com.startup.mvp.interfaces.main;

import android.content.Context;


import com.startup.mvp.model.Photo;

import java.util.List;

/**
 * Created by Harish Penta on 24-7-2017.
 */
public interface MainView {

    void showProgress();

    void hideProgress();

    void setDataToListview(List<Photo> result);

    void showError(String error);

    Context getContext();

}
