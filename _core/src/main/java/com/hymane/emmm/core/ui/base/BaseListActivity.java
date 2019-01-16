package com.hymane.emmm.core.ui.base;

import com.google.android.material.appbar.AppBarLayout;
import com.hymane.emmm.core.R;
import com.hymane.emmm.core.R2;

import java.util.List;

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
 * Description:通用的列表页
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

    private boolean isLoadAll;
    private int mPageIndex;

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
        //页面首次刷新数据
        onRefresh();
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
    public abstract void onLoadMore();

    /***
     * 是否已加载全部
     * @return
     */
    public boolean isLoadAll() {
        return isLoadAll;
    }

    /***
     * 当前页
     * @return
     */
    public int pageIndex() {
        return mPageIndex;
    }

    /***
     * 刷新页
     */
    public void resetPage() {
        mPageIndex = 0;
    }

    /***
     * 下一页，当前页加1
     */
    public void nextPage() {
        nextPage(1);
    }

    /***
     * 跳转指定页
     * @param offset 调到offset页
     */
    public void nextPage(int offset) {
        mPageIndex += offset;
    }

    /***
     * 统一处理数据集更新
     * @param items
     */
    protected void notifyItemRangeInserted(List items) {
        int preSize = mItems.size();
        if (pageIndex() == 0 && !mItems.isEmpty()) {
            mItems.clear();
            mAdapter.notifyItemRangeRemoved(0, preSize);
        }
        mItems.addAll(items);
        mAdapter.notifyItemRangeInserted(preSize, items.size());
        nextPage();
    }

    /***
     *  检查是否还有更多数据待加载
     * @param size 本次请求数据集大小
     */
    public void checkHasMoreData(int size) {
        isLoadAll = size < PAGE_SIZE;
    }

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
