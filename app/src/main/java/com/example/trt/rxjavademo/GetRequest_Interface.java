package com.example.trt.rxjavademo;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Trt on 2019/3/2.
 */

public interface GetRequest_Interface {

//    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
//    Observable<Translation> getCall();
    // 注解里传入 网络请求 的部分URL地址
    // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
    // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
    // 采用Observable<...>接口
    // getCall()是接受网络请求数据的方法

    @GET("sug")
    Call<ProductResult> listProduct(@QueryMap HashMap<String,String> maps);

    @POST("postTest")
    @Multipart
    Call<JsonBean> postTest(@Part("cmd")String cmd, @Part("fund_key") int key, @Part MultipartBody.Part file);
}