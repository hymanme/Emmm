package com.hymane.emmm.mvp.model;

import com.hymane.emmm.mvp.BaseModelImpl;
import com.hymane.emmm.mvp.contract.ILoginContract;
import com.hymane.emmm.network.Server;
import com.hymane.emmm.network.api.ApiConstant;
import com.hymane.emmm.network.utils.SimpleObserver;
import com.hymane.emmm.response.UserResp;

import java.util.HashMap;

import io.reactivex.functions.Function;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/11/12
 * Description:
 */
public class LoginModelImpl extends BaseModelImpl implements ILoginContract.Model {
    @Override
    public void login(final String userId, String password, SimpleObserver<UserResp> observer) {
        HashMap<String, Object> params = newParams();
        params.put("userId", userId);
        params.put("password", password);
        Server.instance().get(ApiConstant.User.LOGIN, params, observer);
    }

    @Override
    public void saveUser2DB(String userId, String password, SimpleObserver<String> observer) {
        HashMap<String, Object> params = newParams();
        params.put("userId", userId);
        params.put("password", password);
        Server.instance().xGet(ApiConstant.User.LOGIN, params, UserResp.User.class)
                .map(new Function<UserResp.User, String>() {
                    @Override
                    public String apply(UserResp.User user) throws Exception {
                        return user.name;
                    }
                })
                .subscribe(observer);
    }
}
