package com.hymane.emmm.mvp.contract;

import com.hymane.emmm.mvp.IBaseContract;
import com.hymane.emmm.network.utils.SimpleObserver;
import com.hymane.emmm.response.douban.MovieResp;

import java.util.List;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/12/12
 * Description:
 */
public interface IMovieContract {
    interface Model extends IBaseContract.Model {
        void getTopMovies(int start, int count, SimpleObserver<List<MovieResp.Subject>> observer);
    }

    interface View extends IBaseContract.View {
        void onGetTopMovies(List<MovieResp.Subject> subjects);
    }

    interface Presenter extends IBaseContract.Presenter {
        void getTopMovies(int start, int count);
    }

    public class ViewImpl implements View {

        @Override
        public void showLoading() {

        }

        @Override
        public void hideLoading() {

        }

        @Override
        public void onFailed(int code, String msg) {

        }

        @Override
        public void onGetTopMovies(List<MovieResp.Subject> subjects) {

        }
    }

}
