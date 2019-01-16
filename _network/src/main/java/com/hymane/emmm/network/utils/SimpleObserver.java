package com.hymane.emmm.network.utils;

import android.content.Context;
import android.net.ParseException;
import android.util.Log;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2016-08-05
 * Description: 基础Observer封装
 */
public abstract class SimpleObserver<T> implements Observer<T> {
    private final String TAG = this.getClass().getSimpleName();

    //逻辑上的code
    public static final int SUCCESS = 1000;
    public static final int EXCEPTION = 0;
    public static final int FAIL = -1;

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    private static final int PARSE_EXCEPTION = 800;
    private static final int TIME_OUT_EXCEPITON = 801;
    private static final int CONNECT_EXCEPTION = 802;
    private static final int UNKOWN_HOST_EXCEPTION = 803;

    private static final int OTHER_EXCEPTION = 900;

    private Object tag;

    private SimpleObserver() {
    }

    public SimpleObserver(Object tag) {
        this.tag = tag;
        if (tag instanceof Context) {
            Log.w(TAG, "Do not place Android context classes in this tag, this is a memory leak,");
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        //tag 如果来源不是 BaseActivity 或者 BaseFragment
        //谨记 必须在tag 回收销毁的时候 调用RxCompositeMap.getInstance().clear(tag);
        if (tag != null) {
            RxCompositeManager.getInstance().add(tag, d);
        }
        onStart();
    }

    @Override
    public void onNext(T t) {
        if (t != null) {
            onSuccess(t);
        } else {
            onFail(PARSE_EXCEPTION, "服务器返回数据为空");
        }
    }


    @Override
    public void onError(Throwable e) {
        String ex;
        int code;
        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpException = (HttpException) e;
            Log.e(TAG, "onError: HttpException " + httpException.message() + "---" + httpException.code());
            code = httpException.code();
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex = "网络出错了";  //均视为网络错误
                    break;
            }
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = "数据解析错误";            //均视为解析错误
            code = PARSE_EXCEPTION;
            Log.e(TAG, "onError: Parse " + ex + " " + e.toString());
        } else if (e instanceof SocketTimeoutException
                || e instanceof TimeoutException) {
            ex = "网络连接超时，请检查您的网络状态";
            code = TIME_OUT_EXCEPITON;
            Log.e(TAG, "onError: SocketTimeoutException " + ex + " " + e.toString());
        } else if (e instanceof ConnectException) {
            ex = "网络异常，请检查您的网络状态";
            code = CONNECT_EXCEPTION;
            Log.e(TAG, "onError: ConnectException " + ex + " " + e.toString());
        } else if (e instanceof UnknownHostException) {
            code = UNKOWN_HOST_EXCEPTION;
            ex = "网络异常，请检查您的网络状态";
            Log.e(TAG, "onError: UnknownHostException " + ex + " " + e.toString());
        } else {
            ex = e.getMessage();         //未知错误或服务器返回
            code = OTHER_EXCEPTION;
            e.printStackTrace();
            Log.e(TAG, "onError: OtherException " + ex + " " + e.toString());
        }
        onFail(code, ex);
        onEnd();
    }

    @Override
    public void onComplete() {
        onEnd();
    }

    /**
     * 调用开始
     */
    public abstract void onStart();

    /**
     * 返回成功
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * 返回失败
     *
     * @param code
     * @param msg
     */
    public abstract void onFail(int code, String msg);

    /**
     * 调用结束
     * 因为onComplete 和 onError 是互斥的，所以在两个方法结束之后都需要调用onEnd()
     */
    public abstract void onEnd();

}