package com.example.appmusic.Retrofit2;

import com.example.appmusic.Model.Album;
import com.example.appmusic.Model.ChuDe;
import com.example.appmusic.Model.Playlist;
import com.example.appmusic.Model.Quangcao;
import com.example.appmusic.Model.TheLoai;
import com.example.appmusic.Model.TheLoaiChuDe;
import com.example.appmusic.Model.User;
import com.example.appmusic.Model.WhiteList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataClient { // interface dùng để gửi nhận các phương thức tới server

    @FormUrlEncoded // sử dụng phương thức post, gửi dữ liệu lên dưới dạng chuỗi
    @POST("insert.php") // gán vào đuôi file php
    Call<String> InsertData(@Field("fullname") String fullname // @Field để truyền dữ liệu lên cho server
                            ,@Field("username") String username
                            ,@Field("password") String password
                            ,@Field("email") String email); // tạo 1 phương thức post để gửi dữ liệu lên server


    @FormUrlEncoded
    @POST("login.php")
    Call<List<User>> LoginData(@Field("username") String username
                                ,@Field("password") String password); // tương tác với phía server, đẩy dữ liệu từ client lên server



    @GET("songbanner.php")
    Call<List<Quangcao>>  GetDataBanner(); // chỉ đọc dữ liệu trên server về nên k cần truyền lên

    @GET("playlist.php")
    Call<List<Playlist>> GetDataPlaylist();

    @GET("chude_theloai.php")
    Call<TheLoaiChuDe> GetDataTheLoaiChuDe();

    @GET("album.php")
    Call<List<Album>> GetDataAlbum();

    @GET("whiteList.php")
    Call<List<WhiteList>> GetDataWhiteList();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<WhiteList>> GetDataDanhSachBaiHatTheoQuangCao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<WhiteList>> GetDataDanhSachBaiHatTheoPlayList(@Field("idplaylist") String idplaylist);

    @GET("listPlaylist.php")
    Call<List<Playlist>> GetDataListPlayList();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<WhiteList>> GetDataDanhSachBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);

    @GET("ListChuDe.php")
    Call<List<ChuDe>> GetDataListChuDe();

    @FormUrlEncoded
    @POST("TheLoaiTheoChuDe.php")
    Call<List<TheLoai>> GetDataTheLoaiTheoChuDe(@Field("idchude") String idchude);

    @GET("ListAlbum.php")
    Call<List<Album>> GetDataListAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<WhiteList>> GetDataDanhSachBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("UpdateLike.php")
    Call<String> UpdateLike(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("UpdateDislike.php")
    Call<String> UpdateDislike(@Field("luotthichh") String luotthichh, @Field("idbaihatt") String idbaihatt);

    @FormUrlEncoded
    @POST("search.php")
    Call<List<WhiteList>> GetSearchSong(@Field("key") String key);
}
