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
 * Create at 2017-08-09
 * Description:简单数据解析function，只返回返回值1000结果
 */
public class NetworkFunction<T> implements Function<Response<ResponseBody>, T> {
    private Observer<T> mObserver;

    private static Gson mGson;

    static {
        mGson = new Gson();
    }

    public NetworkFunction(Observer<T> observer) {
        mObserver = observer;
    }

    @Override
    public T apply(@NonNull Response<ResponseBody> response) throws Exception {
        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            if (body != null) {
                Class<T> clazz = getClassFromInterface(mObserver);
                if (clazz == null) {
                    throw new Exception("获取接口泛型参数异常");
                }
                String result = body.string();
                return mGson.fromJson(result, clazz);
            } else {
                throw new UnknownHostException();
            }
        } else {
            throw new UnknownHostException();
        }
    }

    /**
     * 获取泛型
     *
     * @param observer
     * @return
     */
    private Class<T> getClassFromInterface(Observer<T> observer) {
        try {
            Type[] interfaceTypes = observer.getClass().getGenericInterfaces();
            Type type;
            if (interfaceTypes.length == 0) { //类实现了Observer这个接口
                type = observer.getClass().getGenericSuperclass();
            } else { //直接实现Observer
                type = interfaceTypes[0];
            }
            if (type == null) {
                return null;
            }
            if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
                Type item = (((ParameterizedType) type).getActualTypeArguments())[0];
                return ((Class<T>) item);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}