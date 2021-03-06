package com.hymane.emmm.network.function;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.UnknownHostException;

import androidx.annotation.NonNull;
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
    @SuppressWarnings("unchecked")
    private Class<T> getClassFromInterface(Observer<T> observer) {
        try {
            Type[] interfaceTypes = observer.getClass().getGenericInterfaces();
            Type type;
            if (interfaceTypes.length == 0) {
                //未实现任何接口
                type = observer.getClass().getGenericSuperclass();
            } else {
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