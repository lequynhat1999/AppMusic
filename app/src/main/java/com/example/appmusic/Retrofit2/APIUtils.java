package com.example.appmusic.Retrofit2;

public class APIUtils { // class chứa url
//    public static final String Base_Url = "http://192.168.0.100:8080/AppMusic/";

    private  static String Base_Url = "https://zinglqn.000webhostapp.com/Server/";

    public static DataClient getData() // func này dùng để gửi và nhận dữ liệu về để chứa trong interface
    {
        return RetrofitClient.getClient(Base_Url).create(DataClient.class); // khởi tạo những phương thức trong DataClient để gửi lên
    }
}
