package com.hymane.emmm.reader;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hymane.emmm.core.rxbus.RxBusHelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.OnClick;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2019/1/16
 * Description:
 */
@Route(path = "/reader/ReaderActivity")
public class ReaderActivity extends AppCompatActivity {
    @OnClick(R2.id.tv_hh)
    void hh() {
        RxBusHelper.post(1);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reader_activity_home);
    }
}
