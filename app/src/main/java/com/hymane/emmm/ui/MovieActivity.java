package com.hymane.emmm.ui;

import android.content.Intent;
import android.widget.Toast;

import com.hymane.emmm.core.ui.base.BaseListActivity;
import com.hymane.emmm.mvp.contract.IMovieContract;
import com.hymane.emmm.mvp.presenter.MoviePresenterImpl;
import com.hymane.emmm.response.douban.MovieResp;
import com.hymane.emmm.ui.binder.MovieBinder;

import java.util.List;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/12/12
 * Description:
 */
public class MovieActivity extends BaseListActivity {

    private MoviePresenterImpl presenter;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("Movie");
        presenter = new MoviePresenterImpl(view, this);
    }

    @Override
    protected void register() {
        mAdapter.register(MovieResp.Subject.class, new MovieBinder(view));
    }

    @Override
    public void onRefresh() {
        resetPage();
        presenter.getTopMovies(pageIndex() * PAGE_SIZE, PAGE_SIZE);
    }

    @Override
    public void onLoadMore() {
        if (!isLoadAll()) {
            presenter.getTopMovies(pageIndex() * PAGE_SIZE, PAGE_SIZE);
        }
    }

    private IMovieContract.View view = new IMovieContract.ViewImpl() {
        @Override
        public void onGetTopMovies(List<MovieResp.Subject> subjects) {
            notifyItemRangeInserted(subjects);
            checkHasMoreData(subjects.size());
        }

        @Override
        public void showLoading() {
            showLoadingView();
        }

        @Override
        public void hideLoading() {
            hideLoadingView();
        }

        @Override
        public void onFailed(int code, String msg) {
            Toast.makeText(MovieActivity.this, "error:" + msg, Toast.LENGTH_SHORT).show();
        }
    };
}
