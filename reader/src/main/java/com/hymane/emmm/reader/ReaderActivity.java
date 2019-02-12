package com.hymane.emmm.reader;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hymane.emmm.core.rxbus.RxBusHelper;
import com.hymane.emmm.core.ui.base.BaseActivity;

import butterknife.OnClick;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2019/1/16
 * Description:
 */
@Route(path = "/reader/ReaderActivity")
public class ReaderActivity extends BaseActivity {
    @OnClick(R2.id.tv_reader)
    void hh() {
        RxBusHelper.post(1);
        finish();
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.reader_activity_home);
    }

    @Override
    protected void initData() {
        setTitle("Reader");
    }

    @Override
    protected void initEvents() {
        findViewById(R.id.tv_reader).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBusHelper.post(1);
                finish();
            }
        });
    }
}
