package com.hymane.emmm.mvp;

import java.util.HashMap;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/11/14
 * Description:Model 层基类，处理一些公共的业务逻辑，
 * 具体业务Model可继承本基类。
 */
public class BaseModelImpl implements IBaseContract.Model {

    /***
     * 创建一个map存放请求参数
     * @return HashMap
     */
    protected HashMap<String, Object> newParams() {
        return new HashMap<>();
    }
}
