package com.example.adgexternals;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
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

    @GET("management/get-quiz-questions")
    Call<List<questionObject>> getQuestionManagement(@Header("auth-token") String authtoken);

    @GET("design/get-quiz-questions")
    Call<List<questionObject>> getQuestionDesign(@Header("auth-token") String token);

    @GET("technical/get-quiz-questions/1")
    Call<List<questionObjectTechnical>> getQuestionTechnical(@Header("auth-token") String token);

    @POST("management/submit")
    Call<postQuestion> postQuestionManagement(
            @Header("auth-token") String authtoken,
            @Body postQuestion ques
    );

    @POST("design/submit")
    Call<postQuestion> postQuestionDesign(
            @Header("auth-token") String authtoken,
            @Body postQuestion ques
    );
    @POST("technical/submit")
    Call<postQuestion> postQuestionTechnical(@Header("auth-token") String authtoken, @Body List<postQuestion> ques);

}
