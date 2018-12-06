package com.hymane.emmm.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hymane.emmm.R;
import com.hymane.emmm.mvp.contract.ILoginContract;
import com.hymane.emmm.mvp.presenter.LoginPresenterImpl;
import com.hymane.emmm.response.User;

public class MainActivity extends AppCompatActivity {

    private LoginPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new LoginPresenterImpl(view, this);
    }

    private ILoginContract.ViewImpl view = new ILoginContract.ViewImpl() {
        @Override
        public void onLogin(User.User user) {
            super.onLogin(user);
        }

        @Override
        public void showLoading() {
            super.showLoading();
        }

        @Override
        public void hideLoading() {
            super.hideLoading();
        }

        @Override
        public void onFailed(int code, String msg) {
            super.onFailed(code, msg);
        }
    };
}
