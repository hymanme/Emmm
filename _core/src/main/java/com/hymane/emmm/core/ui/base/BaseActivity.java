package com.hymane.emmm.core.ui.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hymane.emmm.core.R;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/12/12
 * Description:Activity基类，所有activity继承本类
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();
    protected Toolbar mToolbar;
    protected Unbinder unbinder;
    protected TextView mTitleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void init() {
        initContentView();
        unbinder = ButterKnife.bind(this);
        initData();
        initEvents();
    }

    /**
     * 绑定布局,在此方法内setContentView()
     */
    protected abstract void initContentView();

    /**
     * 绑定数据,对变量进行初始化操作
     */
    protected abstract void initData();

    /***
     * 初始化事件（监听事件等事件绑定）
     */
    protected abstract void initEvents();

    /**
     * 获取toolbar
     *
     * @return
     */
    public Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        if (mTitleView != null) {
            mTitleView.setText(getResources().getString(titleId));
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (mTitleView != null) {
            mTitleView.setText(title);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = findViewById(R.id.toolbar);
        mTitleView = findViewById(R.id.tv_title);
        initToolBar(mToolbar);
    }

    public void initToolBar(Toolbar mToolbar) {
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @TargetApi(19)
    protected void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();

        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 菜单按钮初始化
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds mItems to the action bar if it is present.
        getMenuInflater().inflate(getMenuID(), menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /***
     * 默认toolbar不带menu，复写该方法指定menu
     *
     * @return
     */
    protected int getMenuID() {
        return R.menu.menu_empty;
    }

    /**
     * 是否显示菜单  默认显示
     *
     * @return
     */
    protected boolean showMenu() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }
}
