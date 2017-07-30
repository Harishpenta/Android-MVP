package com.startup.mvp.interfaces.main;



import com.startup.mvp.model.Photo;

import java.util.List;

/**
 * Created by Harish Penta on 24-7-2017.
 */
public interface MainPresenter {

    void StartProcess();

    void onSucessDataLoad(List<Photo> result);

    void onErrorDataLoad(String error);

    void onDestroy();

}
