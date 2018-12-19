package com.hymane.emmm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hymane.emmm.R;
import com.hymane.emmm.core.ui.base.BaseActivity;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @OnClick(R.id.button)
    void buttonClick() {
        startActivity(new Intent(this, MovieActivity.class));
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvents() {

    }
}
