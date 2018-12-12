package com.hymane.emmm.mvp.presenter;

import com.hymane.emmm.mvp.BasePresenter;
import com.hymane.emmm.mvp.contract.ILoginContract;
import com.hymane.emmm.mvp.model.LoginModelImpl;
import com.hymane.emmm.network.utils.SimpleObserver;
import com.hymane.emmm.response.User;

import androidx.lifecycle.LifecycleOwner;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/11/12
 * Description:
 */
public class LoginPresenterImpl extends BasePresenter<ILoginContract.Model, ILoginContract.View> implements ILoginContract.Presenter {

    public LoginPresenterImpl(ILoginContract.View view, LifecycleOwner owner) {
        super(new LoginModelImpl(), view, owner);
    }

    @Override
    public void login(final String userId, String password) {
        model().getUserProfile(userId, password, new SimpleObserver<User>(this) {
            @Override
            public void onStart() {
                if (isViewAttached()) {
                    view().showLoading();
                }
            }

            @Override
            public void onSuccess(User user) {
                if (isViewAttached()) {
                    view().onLogin(user);
                }
            }

            @Override
            public void onFail(int code, String msg) {
                if (isViewAttached()) {
                    view().onFailed(code, msg);
                }
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
