package com.example.appmusic.Retrofit2;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DataClient { // interface dùng để gửi nhận các phương thức tới server

    @FormUrlEncoded // sử dụng phương thức post, gửi dữ liệu lên dưới dạng chuỗi
    @POST("insert.php") // gán vào đuôi file php
    Call<String> InsertData(@Field("fullname") String fullname // @Field để truyền dữ liệu lên cho server
                            ,@Field("username") String username
                            ,@Field("password") String password
                            ,@Field("email") String email); // tạo 1 phương thức post để gửi dữ liệu lên server

}
