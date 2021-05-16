package com.example.appmusic.Retrofit2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
//    khi retrofit muốn khởi tạo, nó cần phải có 1 url để nó gửi dữ liệu lên.
    public static Retrofit getClient(String baseurl)
    {
        OkHttpClient builder = new OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS) // thời gian đọc
                .writeTimeout(5000,TimeUnit.MILLISECONDS) // thời gian viết
                .connectTimeout(10000,TimeUnit.MILLISECONDS) // thời gian đợi trước khi ngắt việc kết nối
                .retryOnConnectionFailure(true) // khôi phục lại kết nối bị lỗi và thử lại xem có kết nối được k
                .build();

        Gson gson = new GsonBuilder().setLenient().create(); // hỗ trợ việc convert

        retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)// khởi tạo nhờ vào 1 url - đường dẫn cơ sở để khởi tạo retrofit
                .client(builder) // kiểm soát okhttpclient, những thuộc tính mình đã set cho retrofit thì gọi qua thuộc tính client
                .addConverterFactory(GsonConverterFactory.create(gson)) // khi có dữ liệu dạng json, muốn chuyển dữ liệu về dạng gson và từ gson về biến của java
                .build();

        return  retrofit;
    }
}
