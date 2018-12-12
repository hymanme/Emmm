package com.hymane.emmm.core.ui.activity;

import com.google.android.material.appbar.AppBarLayout;
import com.hymane.emmm.core.R;
import com.hymane.emmm.core.R2;
import com.hymane.emmm.core.ui.base.BaseActivity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/12/12
 * Description:
 */
public abstract class BaseListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    protected static final int PAGE_SIZE = 20;
    @BindView(R2.id.swipe_refresh_widget)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R2.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R2.id.appBarLayout)
    AppBarLayout appBarLayout;

    protected LinearLayoutManager mLayoutManager;
    protected MultiTypeAdapter mAdapter;
    protected Items mItems;

    protected boolean isLoadAll;
    protected int page;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_common_list);
    }

    @Override
    protected void initData() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.recycler_color1, R.color.recycler_color2,
                R.color.recycler_color3, R.color.recycler_color4);
        //设置布局管理器
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        mRecyclerView.setAdapter(mAdapter);
        //注册列表item类型
        register();
    }

    @Override
    protected void initEvents() {
        onRefresh();//页面首次刷新数据
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollDetector());
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    /***
     * 注册item的类型
     * 如：mAdapter.register(WalletRecordBean.Record.class, new WalletRecordBinder());
     * 可直接在方法内使用父类mAdapter
     */
    protected abstract void register();

    /***
     * 刷新列表数据
     */
    @Override
    public abstract void onRefresh();

    /***
     * 加载更多
     */
    protected abstract void onLoadMore();

    public void showLoadingView() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    public void hideLoadingView() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {
        private int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                onLoadMore();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }
    }
}
