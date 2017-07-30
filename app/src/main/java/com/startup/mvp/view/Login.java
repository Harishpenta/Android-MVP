package com.startup.mvp.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.startup.mvp.R;
import com.startup.mvp.interfaces.login.LoginPresenter;
import com.startup.mvp.interfaces.login.LoginView;
import com.startup.mvp.presenter.LoginPresenterImpl;


public class Login extends AppCompatActivity implements LoginView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private LoginPresenter presenter;
    private ImageView imageView;
    private TextView txtSigninBtn;
    private FrameLayout buttonLoginBlock;
    private View reveal;
    ValueAnimator anim;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        buttonLoginBlock = (FrameLayout) findViewById(R.id.buttonLoginBlock);
        txtSigninBtn = (TextView) findViewById(R.id.lblSigninBtn);
        reveal = (View) findViewById(R.id.reveal);
        //findViewById(R.id.button).setOnClickListener(this);
        buttonLoginBlock.setOnClickListener(this);
        //findViewById(R.id.buttonLoginBlock).setOnClickListener(this);

        presenter = new LoginPresenterImpl(this);

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void startAnimation() {
        animateButtonWidth(300, 110, 250);
        fadeOutTextAndProgressDialog();
    }

    @Override
    public void resetLoginAnimationOnError() {


        progressBar.setVisibility(View.GONE);
        txtSigninBtn.setVisibility(View.VISIBLE);

        buttonLoginBlock.getMeasureAllChildren();

    }

    @Override
    public void showProgress() {
        //progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        // progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        username.setError(getString(R.string.username_error));
        buttonLoginBlock.setEnabled(true);
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
        buttonLoginBlock.setEnabled(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void navigateToHome() {
        nextAction();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button) {

            //presenter.validateCredentials(username.getText().toString(), password.getText().toString());
        } else if (v.getId() == R.id.buttonLoginBlock) {

            buttonLoginBlock.setEnabled(false);
            presenter.validateCredentials(username.getText().toString(), password.getText().toString());
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void nextAction() {


        revealButton();
        fadeOutProgressDialog();
        delayedStartNextActivity();
    }

    private void delayedStartNextActivity() {

        startActivity(new Intent(Login.this, Main.class));

    }

    private void fadeOutProgressDialog() {

        progressBar.animate().alpha(0f).setDuration(200).start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void revealButton() {
        buttonLoginBlock.setElevation(0f);
        reveal.setVisibility(View.VISIBLE);

        int cx = reveal.getWidth();
        int cy = reveal.getHeight();

        int x = (int) ((110) / 2 + buttonLoginBlock.getX());
        int y = (int) ((110) / 2 + buttonLoginBlock.getY());

        float finalRadius = Math.max(cx, cy) * 1.2f;

        Animator animator = ViewAnimationUtils.createCircularReveal(reveal, x, y, 110, finalRadius);

        animator.setDuration(350);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                finish();
            }
        });


    }

    private void fadeOutTextAndProgressDialog() {


        txtSigninBtn.animate().alpha(0f)
                .setDuration(250)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        showProgressBarWhite();
                    }
                })
                .start();


    }

    private void showProgressBarWhite() {

        progressBar.getIndeterminateDrawable()
                .setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN);
        progressBar.setVisibility(View.VISIBLE);

    }

    private void animateButtonWidth(int width, int height, int duration) {

        anim = ValueAnimator.ofInt(width, height);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int val = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = buttonLoginBlock.getLayoutParams();
                layoutParams.width = val;
                buttonLoginBlock.setLayoutParams(layoutParams);

            }

        });
        anim.setDuration(duration);
        anim.start();

    }
}
