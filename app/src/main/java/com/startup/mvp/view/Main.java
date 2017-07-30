package com.startup.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.startup.mvp.R;
import com.startup.mvp.adapter.PhotoAdapter;
import com.startup.mvp.interfaces.main.MainPresenter;
import com.startup.mvp.interfaces.main.MainView;
import com.startup.mvp.interfaces.main.OnRecyclerViewItemClickListener;
import com.startup.mvp.model.Photo;
import com.startup.mvp.presenter.MainPresenterImpl;

import java.util.List;

public class Main extends AppCompatActivity implements MainView {

    private ProgressBar pgrLoadingMain;
    private MainPresenter mainPresenter;
    private RecyclerView rwPhotos;
    private PhotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        pgrLoadingMain = (ProgressBar) findViewById(R.id.pgrLoadingMain);
        rwPhotos = (RecyclerView) findViewById(R.id.rwPhotos);

        mainPresenter = new MainPresenterImpl(this);


        rwPhotos.setHasFixedSize(true);
        //rwPhotos = (RecyclerView) findViewById(R.id.RecyclerView);
        adapter = new PhotoAdapter(Main.this, R.layout.card_photo);
        rwPhotos.setAdapter(adapter);
        rwPhotos.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this.getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rwPhotos.setLayoutManager(llm);
        adapter.setOnItemClickListener(new OnRecyclerViewItemClickListener<Photo>() {
            @Override
            public void onItemClick(View view, Photo viewModel) {
                adapter.remove(viewModel);
            }
        });
        mainPresenter.StartProcess();
    }

    @Override
    public void showProgress() {


        pgrLoadingMain.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {

        pgrLoadingMain.setVisibility(View.GONE);
    }

    @Override
    public void setDataToListview(List<Photo> result) {
        adapter.setListPhoto(result);

        //this.result.setText(result);
        //Toast.makeText(getApplicationContext(), "Data Loaded Successfully", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showError(String error) {


        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {

        return getApplicationContext();

    }


    @Override
    protected void onDestroy() {
        mainPresenter.onDestroy();
        super.onDestroy();
    }


}

