package com.hymane.emmm.core.rxbus;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.subscribers.SerializedSubscriber;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2019/1/18
 * Description:
 */
public class RxBus {
    //相当于Rxjava中的Subject
    private final FlowableProcessor<Object> mBus;
    private static volatile RxBus sRxBus = null;

    private RxBus() {
        //调用toSerialized()方法，保证线程安全
        mBus = PublishProcessor.create().toSerialized();
    }

    public static synchronized RxBus getDefault() {
        if (sRxBus == null) {
            synchronized (RxBus.class) {
                if (sRxBus == null) {
                    sRxBus = new RxBus();
                }
            }
        }
        return sRxBus;
    }


    /**
     * 发送消息
     *
     * @param o
     */
    public void post(Object o) {
        new SerializedSubscriber<>(mBus).onNext(o);
    }

    /**
     * 确定接收消息的类型
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> Flowable<T> toFlowable(Class<T> clazz) {
        //过滤掉除了aClass类型外的消息
        return mBus.ofType(clazz);
    }

    /**
     * 判断是否有订阅者
     *
     * @return
     */
    public boolean hasSubscribers() {
        return mBus.hasSubscribers();
    }
}