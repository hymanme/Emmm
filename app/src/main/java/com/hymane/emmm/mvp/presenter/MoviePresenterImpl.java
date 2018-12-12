package com.hymane.emmm.mvp.presenter;

import android.util.Log;

import com.hymane.emmm.mvp.BasePresenter;
import com.hymane.emmm.mvp.contract.IMovieContract;
import com.hymane.emmm.mvp.model.MovieModelImpl;
import com.hymane.emmm.network.utils.SimpleObserver;
import com.hymane.emmm.response.douban.MovieResp;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/12/12
 * Description:
 */
public class MoviePresenterImpl extends BasePresenter<IMovieContract.Model, IMovieContract.View> implements IMovieContract.Presenter {

    /***
     * 业务逻辑交由 MVP - M 层处理
     * @param view MVP - V 层
     * @param owner LifecycleOwner
     */
    public MoviePresenterImpl(@Nullable IMovieContract.View view, LifecycleOwner owner) {
        super(new MovieModelImpl(), view, owner);
    }

    @Override
    public void getTopMovies(int start, int count) {
        model().getTopMovies(start, count, new SimpleObserver<List<MovieResp.Subject>>(this) {
            @Override
            public void onStart() {
                if (isViewAttached()) {
                    view().showLoading();
                }
            }

            @Override
            public void onSuccess(List<MovieResp.Subject> subjects) {
                Log.d(TAG, "onSuccess: movies:" + subjects.size());
                if (isViewAttached()) {
                    view().onGetTopMovies(subjects);
                }
            }

            @Override
            public void onFail(int code, String msg) {

            }

            @Override
            public void onEnd() {
                if (isViewAttached()) {
                    view().hideLoading();
                }
            }
        });
    }
}
