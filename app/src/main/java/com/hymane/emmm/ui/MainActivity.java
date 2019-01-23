package com.hymane.emmm.ui;

import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hymane.emmm.R;
import com.hymane.emmm.core.rxbus.RxBusHelper;
import com.hymane.emmm.core.ui.base.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @OnClick(R.id.button)
    void buttonClick() {
        ARouter.getInstance().build("/reader/ReaderActivity").navigation();
//        startActivity(new Intent(this, MovieActivity.class));
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
        RxBusHelper.doOnChildThread(Integer.class, new RxBusHelper.OnEventListener<Integer>() {
            @Override
            public void onEvent(Integer integer) {
                ((Button)findViewById(R.id.button)).setText("修改了");
            }

            @Override
            public void onError() {

            }
        });
    }
}
