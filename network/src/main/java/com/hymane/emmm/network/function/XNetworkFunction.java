package com.hymane.emmm.network.function;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018-11-30
 * Description:通用数据解析function
 */
public class XNetworkFunction<T> implements Function<Response<ResponseBody>, T> {
    private Class<T> mClazz;

    private static Gson mGson;

    static {
        mGson = new Gson();
    }

    public XNetworkFunction(Class<T> clazz) {
        this.mClazz = clazz;
    }

    @Override
    public T apply(@NonNull Response<ResponseBody> response) throws Exception {
        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            if (body != null) {
                if (mClazz == null) {
                    throw new Exception("获取接口泛型参数异常");
                }
                String result = body.string();
                return mGson.fromJson(result, mClazz);
            } else {
                throw new UnknownHostException();
            }
        } else {
            throw new UnknownHostException();
        }
    }
}