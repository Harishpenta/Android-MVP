package com.startup.mvp.interfaces.main;

import android.view.View;

/**
 * Created by Harish Penta on 24-7-2017.
 */
public interface OnRecyclerViewItemClickListener<Photo> {

    void onItemClick(View view, Photo model);
}
