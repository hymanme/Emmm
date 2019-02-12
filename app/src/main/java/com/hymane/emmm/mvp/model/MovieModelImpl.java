package com.hymane.emmm.mvp.model;

import com.hymane.emmm.api.ApiConstant;
import com.hymane.emmm.mvp.BaseModelImpl;
import com.hymane.emmm.mvp.contract.IMovieContract;
import com.hymane.emmm.network.Server;
import com.hymane.emmm.network.utils.RxSchedulers;
import com.hymane.emmm.network.utils.SimpleObserver;
import com.hymane.emmm.response.douban.MovieResp;

import java.util.HashMap;
import java.util.List;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/12/12
 * Description:
 */
public class MovieModelImpl extends BaseModelImpl implements IMovieContract.Model {
    @Override
    public void getTopMovies(int start, int count, SimpleObserver<List<MovieResp.Subject>> observer) {
        HashMap<String, Object> params = newParams();
        params.put("start", start);
        params.put("count", count);
        Server.instance().xGet(ApiConstant.Movie.TOP250, params, MovieResp.class)
                .map(movieResp -> movieResp.subjects)
                .compose(RxSchedulers.applyObservableAsync())
                .subscribe(observer);

        Server.instance().get("",params,observer);
    }
}
