package com.hymane.emmm.mvp;

import android.util.Log;

import com.hymane.emmm.core.rxbus.RxCompositeManager;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/11/1
 * Description: 基础Presenter，业务协调者。
 * 不处理复杂业务逻辑，详细业务逻辑处理交由Model层处理
 * 只负责协调Model和View的工作。
 *
 * @param <M> Model - M 接口
 * @param <V> Model - V 接口
 */
public class BasePresenter<M extends IBaseContract.Model, V extends IBaseContract.View> implements IBaseContract.Presenter, LifecycleObserver {
    public static final String TAG = BasePresenter.class.getSimpleName();
    //V层弱引用，防止内存泄漏
    private WeakReference<V> mViewRefer;
    private LifecycleOwner mOwner;
    @NonNull
    private M mModel;

    /***
     * 业务逻辑交由 MVP - M 层处理
     * @param model MVP - M 层
     * @param view MVP - V 层
     * @param owner LifecycleOwner
     */
    public BasePresenter(@NonNull M model, @Nullable V view, LifecycleOwner owner) {
        mViewRefer = new WeakReference<>(view);
        mModel = model;
        // Lifecycle，实现生命周期方法，
        // 子类可根据具体需求，处理特定逻辑
        this.mOwner = owner;
        if (owner != null) {
            owner.getLifecycle().addObserver(this);
        }
    }

    /***
     * 当前View是否可用，即页面是否还未销毁
     * @return true 可用|false：不可用
     */
    public boolean isViewAttached() {
        return mViewRefer != null && mViewRefer.get() != null;
    }

    /***
     * 获取当前View
     * @return 当前View，或者null(页面已销毁)
     * PS:调用前可使用{@link #isViewAttached()}进行验证
     */
    protected V view() {
        if (mViewRefer != null) {
            return mViewRefer.get();
        }
        return null;
    }

    /***
     * 获取当前业务逻辑处理类 - model
     * @return Model
     */
    protected M model() {
        return mModel;
    }

    /***
     * 清理View(context)层引用
     * 子类可重写本方法来处理页面结束之后
     * 还未请求或正在请求中当网络请求
     */
    @Override
    public void unsubscribe() {
        if (mViewRefer != null) {
            mViewRefer.clear();
            mViewRefer = null;
        }
        RxCompositeManager.getInstance().remove(this);
        if (mOwner != null) {
            mOwner.getLifecycle().removeObserver(this);
        }
        Log.d(TAG, "unsubscribe: Cleared mViewRefer、remove Composite and remove LifecycleOwner");
    }

    /*************** Lifecycle ****************/
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        unsubscribe();
    }

}
