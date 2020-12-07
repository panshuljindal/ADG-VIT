package com.example.adgexternals;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface userClient {

    @POST("signup")
    Call<User> signupuser(@Body User user);

    @POST("login")
    Call<loginResponse> loginUser(@Body loginrequest loginrequest);

    @GET("getuser")
    Call<root> getUser(@Header("auth-token") String authtoken);

    @GET("management/get-random-questions")
    Call<ResponseBody> getQuestionManagement(@Header("auth-token") String authtoken);

    @GET("design/get-random-questions")
    Call<ResponseBody> getQuestionDesign(@Header("auth-token") String token);

    @GET("technical/get-random-questions")
    Call<ResponseBody> getQuestionTechnical(@Header("auth-token") String token);

    //@POST("management/submit")

    //@POST("design/submit")

    //@POST("technical/submit")


}
