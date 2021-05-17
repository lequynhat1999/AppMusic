package com.example.appmusic.Retrofit2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
//    khi retrofit muốn khởi tạo, nó cần phải có 1 url để nó gửi dữ liệu lên.
    public static Retrofit getClient(String baseurl) // trả về cấu hình sau khi set up cho retrofit
    {
        OkHttpClient builder = new OkHttpClient.Builder() // tương tác với server thông qua okhttp
                .readTimeout(5000, TimeUnit.MILLISECONDS) // thời gian đọc đợi quá lâu thì sẽ ngắt
                .writeTimeout(5000,TimeUnit.MILLISECONDS) // thời gian viết đợi quá lâu thì sẽ ngắt
                .connectTimeout(10000,TimeUnit.MILLISECONDS) // thời gian đợi trước khi ngắt việc kết nối
                .retryOnConnectionFailure(true) // khôi phục lại kết nối bị lỗi và thử lại xem có kết nối được k
                .protocols(Arrays.asList(Protocol.HTTP_1_1)) // set lại giao thức mạng
                .build(); // thực hiện

        Gson gson = new GsonBuilder().setLenient().create(); // hỗ trợ việc convert từ dữ liệu dạng json về dữ liệu dạng biến java

        retrofit = new Retrofit.Builder() // gán vào retrofit
                .baseUrl(baseurl)// khởi tạo nhờ vào 1 url - đường dẫn cơ sở để khởi tạo retrofit
                .client(builder) // kiểm soát okhttpclient, những thuộc tính mình đã set cho retrofit thì gọi qua thuộc tính client
                .addConverterFactory(GsonConverterFactory.create(gson)) // khi có dữ liệu dạng json, muốn chuyển dữ liệu về dạng gson biến của java
                .build();

        return  retrofit;
    }
}
