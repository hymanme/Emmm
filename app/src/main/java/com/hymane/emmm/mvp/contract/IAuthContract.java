package com.hymane.emmm.mvp.contract;

import com.hymane.emmm.mvp.BaseViewImpl;
import com.hymane.emmm.mvp.IBaseContract;
import com.hymane.emmm.network.utils.SimpleObserver;
import com.hymane.emmm.response.BaseResp;
import com.hymane.emmm.response.RefreshToken;
import com.hymane.emmm.response.Token;
import com.hymane.emmm.response.User;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/12/4
 * Description:
 */
public interface IAuthContract {
    interface Model extends IBaseContract.Model {
        void login(String userId, String password, SimpleObserver<Token> observer);

        void refreshToken(String token, SimpleObserver<BaseResp<RefreshToken>> observer);
    }

    interface View extends IBaseContract.View {

    }

    interface Presenter extends IBaseContract.Presenter {
        void login(String userId, String password);

        void refreshToken(String token);
    }
}
