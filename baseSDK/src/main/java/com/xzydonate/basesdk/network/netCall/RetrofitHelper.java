package com.xzydonate.basesdk.network.netCall;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dell on 2018/4/24.
 */

public class RetrofitHelper {

    private static String BASE_URL = "http://gank.io/api/";
    private static final String BASE_WAN_URL = "https://www.wanandroid.com/";
    private static Converter.Factory converter = GsonConverterFactory.create();
    private static CallAdapter.Factory callAdapter = RxJava2CallAdapterFactory.create();

    private static final int TIMEOUT = 30;

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                // 添加Header
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder builder = chain.request().newBuilder();
                    return chain.proceed(builder.build());
                }
            })
            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                //添加HttpLoggingInterceptor拦截器方便调试接口
                @Override
                public void log(String message) {
                    Log.d("RetrofitHelper", message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build();

    public static Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            // 添加Gson转换器
            .addConverterFactory(converter)
            // 添加Retrofit到RxJava的转换器
            .addCallAdapterFactory(callAdapter)
            .client(okHttpClient)
            .build();

    public static Retrofit RETROFIT_WAN = new Retrofit.Builder()
            .baseUrl(BASE_WAN_URL)
            // 添加Gson转换器
            .addConverterFactory(converter)
            // 添加Retrofit到RxJava的转换器
            .addCallAdapterFactory(callAdapter)
            .client(okHttpClient)
            .build();

}



