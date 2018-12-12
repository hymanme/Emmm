package com.hymane.emmm.ui;

import android.widget.Toast;

import com.hymane.emmm.core.ui.activity.BaseListActivity;
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
    protected void register() {
        presenter = new MoviePresenterImpl(view, this);
        mAdapter.register(MovieResp.Subject.class, new MovieBinder());
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("Movie");
    }

    @Override
    public void onRefresh() {
        page = 0;
        presenter.getTopMovies(page * PAGE_SIZE, PAGE_SIZE);
    }

    @Override
    protected void onLoadMore() {
        presenter.getTopMovies(page * PAGE_SIZE, PAGE_SIZE);
    }

    private IMovieContract.ViewImpl view = new IMovieContract.ViewImpl() {
        @Override
        public void onGetTopMovies(List<MovieResp.Subject> subjects) {
            mItems.addAll(subjects);
            page++;
            mAdapter.notifyDataSetChanged();
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
