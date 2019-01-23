package com.hymane.emmm.reader;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hymane.emmm.core.rxbus.RxBusHelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2019/1/16
 * Description:
 */
@Route(path = "/reader/ReaderActivity")
public class ReaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reader_activity_home);
        findViewById(R.id.tv_hh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBusHelper.post(1);
            }
        });
    }
}
