package com.hymane.emmm.network;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2017-08-09
 * Description:统一的公共Service接口
 */
public interface ApiService {
    @GET
    Observable<Response<ResponseBody>> get(
            @Url String url,
            @QueryMap Map<String, Object> fields
    );

    @FormUrlEncoded
    @POST
    Observable<Response<ResponseBody>> post(
            @Url String url,
            @FieldMap Map<String, Object> fields
    );

    @FormUrlEncoded
    @PUT
    Observable<Response<ResponseBody>> put(
            @Url String url,
            @FieldMap Map<String, Object> fields
    );

    @FormUrlEncoded
    @DELETE
    Observable<Response<ResponseBody>> delete(
            @Url String url,
            @FieldMap Map<String, Object> fields
    );

    @FormUrlEncoded
    @PATCH
    Observable<Response<ResponseBody>> patch(
            @Url String url,
            @FieldMap Map<String, Object> fields
    );

    @Streaming
    @GET
    Observable<Response<ResponseBody>> download(
            @Url String url
    );
}