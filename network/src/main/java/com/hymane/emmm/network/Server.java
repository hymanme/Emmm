package com.hymane.emmm.network;

import com.hymane.emmm.network.function.NetworkFunction;
import com.hymane.emmm.network.function.XNetworkFunction;
import com.hymane.emmm.network.utils.RxSchedulers;
import com.hymane.emmm.network.utils.ServiceFactory;
import com.hymane.emmm.network.utils.SimpleObserver;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2017-08-09
 * Description:网络请求Rxjava包装类
 */
public class Server {
    private volatile static Server instance;
    private ApiService mApiService;

    private Server() {
        mApiService = ServiceFactory.getInstance(ApiService.class);
    }

    public static Server instance() {
        if (instance == null) {
            synchronized (Server.class) {
                if (instance == null) {
                    instance = new Server();
                }
            }
        }
        return instance;
    }

    /***
     * 基本get方法
     * @param url 请求网址
     * @param parameters 请求参数
     * @return
     */
    public <T> Observable<T> xGet(final String url, Map<String, Object> parameters, Class<T> clazz) {
        return mApiService.get(url, parameters)
                .map(new XNetworkFunction<T>(clazz))
                .compose(RxSchedulers.<T>applyObservableAsync());
    }

    /***
     * 简单get接口
     * @param url 请求网址
     * @param parameters 请求参数
     * @param observer 回调
     * @param <T> 回调数据类型
     */
    public <T> void get(final String url, Map<String, Object> parameters, SimpleObserver<T> observer) {
        mApiService.get(url, parameters)
                .map(new NetworkFunction<>(observer))
                .compose(RxSchedulers.<T>applyObservableAsync())
                .subscribe(observer);
    }

    /***
     * 基本post方法
     * @param url 请求网址
     * @param parameters 请求参数
     * @return
     */
    public <T> Observable<T> xPost(final String url, Map<String, Object> parameters, Class<T> clazz) {
        return mApiService.post(url, parameters)
                .map(new XNetworkFunction<T>(clazz))
                .compose(RxSchedulers.<T>applyObservableAsync());
    }

    /***
     * 简单post接口
     * @param url 请求网址
     * @param fields 请求数据体
     * @param observer 回调
     * @param <T> 回调数据类型
     */
    public <T> void post(final String url, Map<String, Object> fields, SimpleObserver<T> observer) {
        mApiService.post(url, fields)
                .map(new NetworkFunction<>(observer))
                .compose(RxSchedulers.<T>applyObservableAsync())
                .subscribe(observer);
    }

    /***
     * 简单put接口
     * @param url 请求网址
     * @param fields 请求数据体
     * @param observer 回调
     * @param <T> 回调数据类型
     */
    public <T> void put(final String url, Map<String, Object> fields, SimpleObserver<T> observer) {
        mApiService.put(url, fields)
                .map(new NetworkFunction<>(observer))
                .compose(RxSchedulers.<T>applyObservableAsync())
                .subscribe(observer);
    }
    /***
     * 基本post方法
     * @param url 请求网址
     * @param parameters 请求参数
     * @return
     */
    public <T> Observable<T> xPut(final String url, Map<String, Object> parameters, Class<T> clazz) {
        return mApiService.put(url, parameters)
                .map(new XNetworkFunction<T>(clazz))
                .compose(RxSchedulers.<T>applyObservableAsync());
    }
    /***
     * 简单delete接口
     * @param url 请求网址
     * @param fields 请求数据体
     * @param observer 回调
     * @param <T> 回调数据类型
     */
    public <T> void delete(final String url, Map<String, Object> fields, SimpleObserver<T> observer) {
        mApiService.delete(url, fields)
                .map(new NetworkFunction<>(observer))
                .compose(RxSchedulers.<T>applyObservableAsync())
                .subscribe(observer);
    }

    /***
     * 简单patch接口
     * @param url 请求网址
     * @param fields 请求数据体
     * @param observer 回调
     * @param <T> 回调数据类型
     */
    public <T> void patch(final String url, Map<String, Object> fields, SimpleObserver<T> observer) {
        mApiService.patch(url, fields)
                .map(new NetworkFunction<>(observer))
                .compose(RxSchedulers.<T>applyObservableAsync())
                .subscribe(observer);
    }

    /***
     * 公共download接口请求方法
     * @param url 请求网址
     * @param fields 请求数据体
     * @param observer 回调
     */
//    public void download(final String url, Map<String, String> fields, Observer<File> observer) {
//        mApiService.<ResponseBody>download(url)
//                .map(new DownloadFunction(fields.get("fileName")))
//                .compose(RxSchedulers.applyObservableAsync())
//                .subscribe(observer);
//    }
}