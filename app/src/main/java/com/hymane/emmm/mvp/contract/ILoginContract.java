package com.hymane.emmm.mvp.contract;

import com.hymane.emmm.mvp.BaseViewImpl;
import com.hymane.emmm.mvp.IBaseContract;
import com.hymane.emmm.network.utils.SimpleObserver;
import com.hymane.emmm.response.User;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/11/12
 * Description:
 */
public interface ILoginContract {
    interface Model extends IBaseContract.Model {
        void getUserProfile(String userId, String password, SimpleObserver<User> observer);

        void saveUser2DB(String userId, String password, SimpleObserver<String> observer);
    }

    interface View extends IBaseContract.View {
        void onLogin(User user);
    }

    interface Presenter extends IBaseContract.Presenter {
        void login(String userId, String password);
    }

    class ViewImpl extends BaseViewImpl implements View {

        @Override
        public void onLogin(User user) {

        }

        @Override
        public void showLoading() {

        }

        @Override
        public void hideLoading() {

        }

        @Override
        public void onFailed(int code, String msg) {

        }
    }
}
