package com.hymane.emmm.network.function;

import com.hymane.emmm.network.response.BaseResp;

import io.reactivex.functions.Function;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/12/4
 * Description: 获取 T 的 R 数据，基类 BaseResp
 */
public class ConvertFunction<T, R> implements Function<T, R> {
    @Override
    @SuppressWarnings("unchecked")
    public R apply(T t) throws Exception {
        return ((BaseResp<R>) t).getData();
    }
}
