package com.example.appmusic.Retrofit2;

public class APIUtils { // class chứa url
    public static final String Base_Url = "http://192.168.0.101:8080/AppMusic/";

    public static DataClient getData() // func này dùng để gửi và nhận dữ liệu về để chứa trong interface
    {
        return RetrofitClient.getClient(Base_Url).create(DataClient.class); // khởi tạo nơi để chứa dữ liệu
    }
}
