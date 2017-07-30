package com.startup.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.startup.mvp.R;
import com.startup.mvp.interfaces.register.RegisterPresenter;
import com.startup.mvp.interfaces.register.RegisterView;
import com.startup.mvp.presenter.RegisterPresenterImpl;


public class Register extends AppCompatActivity implements RegisterView, View.OnClickListener {

    private EditText register_username, register_email, register_password, register_password2;
    private Button btnRegister;
    private ProgressBar progressBar;

    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        register_username = (EditText) findViewById(R.id.register_username);
        register_email = (EditText) findViewById(R.id.register_email);
        register_password = (EditText) findViewById(R.id.register_password);
        register_password2 = (EditText) findViewById(R.id.register_password2);

        btnRegister = (Button) findViewById(R.id.submit);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        presenter = new RegisterPresenterImpl(this);
        btnRegister.setOnClickListener(this);


    }

    @Override
    public void showProgress() {
        btnRegister.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setErrorUserName() {

        register_username.setError("Please enter Username");

    }

    @Override
    public void setErrorEmail() {
        register_email.setError("Please enter Email");
    }

    @Override
    public void setErrorPassword() {
        register_password.setError("Please enter Password");
    }

    @Override
    public void setErrorRepeatPassword() {

        register_password2.setError("Please enter Repeat Password");
    }

    @Override
    public void setErrorEmptyFields() {
        Toast.makeText(this, "All fields are empty", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToMain() {

        startActivity(new Intent(Register.this, Main.class));

    }

    @Override
    public void onClick(View v) {

        presenter.ValidateRegister(register_username.getText().toString(), register_email.getText().toString(), register_password.getText().toString(), register_password2.getText().toString());

    }
}
