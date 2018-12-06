package com.hymane.emmm.mvp.model;

import com.hymane.emmm.mvp.BaseModelImpl;
import com.hymane.emmm.mvp.contract.IAuthContract;
import com.hymane.emmm.network.Server;
import com.hymane.emmm.network.api.ApiConstant;
import com.hymane.emmm.network.function.ConvertFunction;
import com.hymane.emmm.network.utils.SimpleObserver;
import com.hymane.emmm.response.BaseResp;
import com.hymane.emmm.response.RefreshToken;
import com.hymane.emmm.response.Token;

import java.util.HashMap;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/11/12
 * Description:
 */
public class AuthModelImpl extends BaseModelImpl implements IAuthContract.Model {

    @Override
    public void login(String userId, String password, SimpleObserver<Token> observer) {
        HashMap<String, Object> params = newParams();
        params.put("userId", userId);
        params.put("password", password);

        Server.instance().xPost(ApiConstant.Auth.LOGIN, params, BaseResp.class)
                .map(new ConvertFunction<BaseResp, Token>())
                .subscribe(observer);
    }

    @Override
    public void refreshToken(String token, SimpleObserver<BaseResp<RefreshToken>> observer) {

    }
}
