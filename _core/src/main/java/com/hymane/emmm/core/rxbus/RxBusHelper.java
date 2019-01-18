package com.hymane.emmm.core.rxbus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2019/1/18
 * Description:
 */
public class RxBusHelper {
    /**
     * 发布消息
     *
     * @param o
     */
    public static void post(Object o) {
        RxBus.getDefault().post(o);
    }

    /**
     * 接收消息,并在主线程处理,并保存Disposable
     *
     * @param clazz
     * @param listener
     * @param <T>
     */
    public static <T> void doOnMainThread(Object tag, Class<T> clazz, OnEventListener<T> listener) {
        RxCompositeManager.getInstance().add(tag,
                RxBus.getDefault()
                        .toFlowable(clazz)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(listener::onEvent, throwable -> listener.onError()));
    }

    public static <T> void doOnMainThread(Class<T> clazz, OnEventListener<T> listener) {
        RxBus.getDefault()
                .toFlowable(clazz)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listener::onEvent, throwable -> listener.onError());
    }

    /**
     * 接收消息,并在子线程处理,并保存Disposable
     *
     * @param tag
     * @param clazz
     * @param listener
     * @param <T>
     */
    public static <T> void doOnChildThread(Object tag, Class<T> clazz, OnEventListener<T> listener) {
        RxCompositeManager.getInstance().add(tag, RxBus.getDefault()
                .toFlowable(clazz)
                .subscribeOn(Schedulers.newThread())
                .subscribe(listener::onEvent, throwable -> listener.onError()));
    }

    public static <T> void doOnChildThread(Class<T> clazz, OnEventListener<T> listener) {
        RxBus.getDefault()
                .toFlowable(clazz)
                .subscribeOn(Schedulers.newThread())
                .subscribe(listener::onEvent, throwable -> listener.onError());
    }

    /***
     * 取消订阅
     * @param tag
     */
    public static void remove(Object tag) {
        RxCompositeManager.getInstance().remove(tag);
    }

    public interface OnEventListener<T> {
        void onEvent(T t);

        void onError();
    }

}
