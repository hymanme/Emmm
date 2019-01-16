package com.hymane.emmm.mvp.model;

import com.hymane.emmm.api.ApiConstant;
import com.hymane.emmm.mvp.BaseModelImpl;
import com.hymane.emmm.mvp.contract.IAuthContract;
import com.hymane.emmm.network.Server;
import com.hymane.emmm.network.function.ConvertFunction;
import com.hymane.emmm.network.utils.RxSchedulers;
import com.hymane.emmm.network.utils.SimpleObserver;
import com.hymane.emmm.response.BaseResp;
import com.hymane.emmm.response.RefreshToken;
import com.hymane.emmm.response.Token;
import com.hymane.emmm.response.User;

import java.util.HashMap;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/11/12
 * Description:
 */
public class AuthModelImpl extends BaseModelImpl implements IAuthContract.Model {

    @Override
    public void login(String userId, String password, SimpleObserver<User> observer) {
        HashMap<String, Object> params = newParams();
        params.put("userId", userId);
        params.put("password", password);

        Server.instance().xPost(ApiConstant.Auth.REFRESH_TOKEN, params, BaseResp.class)
                .map(new ConvertFunction<BaseResp, Token>())
                .flatMap((Function<Token, ObservableSource<User>>) token -> {
                    HashMap<String, Object> up = newParams();
                    return Server.instance().xGet(ApiConstant.Auth.LOGIN, up, User.class);
                })
                .compose(RxSchedulers.applyObservableAsync())
                .subscribe(observer);
    }

    @Override
    public void refreshToken(String token, SimpleObserver<BaseResp<RefreshToken>> observer) {

    }
}
