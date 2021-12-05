package com.batararajadamanik.tubeshotel.ui.fitur.menu;

import com.batararajadamanik.tubeshotel.ui.fitur.foodorder.TransaksiFoodResponse;
import com.batararajadamanik.tubeshotel.ui.fitur.transaksi.TransaksiResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @Headers({"Accept: application/json"})
    @GET("menu")
    Call<MenuResponse> getAllMenu(@Query("data") String data);

    @Headers({"Accept: application/json"})
    @POST("menu")
    @FormUrlEncoded
    Call<MenuResponse> createMenu(@Field("name") String nama,
                                  @Field("price") Double price,
                                  @Field("photo") String photo);
    @Headers({"Accept: application/json"})
    @PUT("menu/{id}")
    @FormUrlEncoded
    Call<MenuResponse> updateMenu(@Path("id") String id,
                                  @Field("name") String nama,
                                  @Field("price") Double price,
                                  @Field("photo") String photo);

    @Headers({"Accept: application/json"})
    @DELETE("menu/{id}")
    Call<MenuResponse> deleteMenu(@Path("id") String id);

    @Headers({"Accept: application/json"})
    @GET("food")
    Call<TransaksiFoodResponse> getAllFood(@Query("data") String data);

    @Headers({"Accept: application/json"})
    @POST("food")
    @FormUrlEncoded
    Call<TransaksiFoodResponse> createFoodTransaksi(
            @Field("menu") String menu,
            @Field("price") Double price,
            @Field("amount") int amount,
            @Field("email") String email,
            @Field("customer_name") String customer_name,
            @Field("photo") String photo);

    @Headers({"Accept: application/json"})
    @PUT("food/{id}")
    @FormUrlEncoded
    Call<TransaksiFoodResponse> updatefood(@Path("id") String id,
                                           @Field("amount") int amount);

    @Headers({"Accept: application/json"})
    @DELETE("food/{id}")
    Call<TransaksiFoodResponse> deleteFoodTransaksi(@Path("id") String id);

    @Headers({"Accept: application/json"})
    @GET("food/{email}")
    Call<TransaksiFoodResponse> getFoodTransaksiByEmail(
            @Path("email") String email,
            @Query("data") String data);

    @Headers({"Accept: application/json"})
    @GET("room")
    Call<TransaksiResponse> getAllBooking(@Query("data") String data);

    @Headers({"Accept: application/json"})
    @POST("room")
    @FormUrlEncoded
    Call<TransaksiResponse> createBooking(
            @Field("email") String email,
            @Field("name") String name,
            @Field("room") String room,
            @Field("check_in_date") String check_in_date,
            @Field("check_out_date") String check_out_date,
            @Field("price") Double price);

    @Headers({"Accept: application/json"})
    @PUT("room/{id}")
    @FormUrlEncoded
    Call<TransaksiResponse> updateBooking(@Path("id") String id,
                                          @Field("email") String email,
                                          @Field("name") String name,
                                          @Field("room") String room,
                                          @Field("check_in_date") String check_in_date,
                                          @Field("check_out_date") String check_out_date,
                                          @Field("price") Double price);

    @Headers({"Accept: application/json"})
    @DELETE("room/{id}")
    Call<TransaksiResponse> deleteBooking(@Path("id") String id);

    @Headers({"Accept: application/json"})
    @GET("room/{email}")
    Call<TransaksiResponse> getBookingByEmail(
            @Path("email") String email,
            @Query("data") String data);


}
