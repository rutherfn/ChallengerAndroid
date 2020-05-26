package com.nicholasrutherford.challengerandroid.services.Splash;

import com.nicholasrutherford.challengerandroid.data.Splash;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SplashService {

    @GET("all/splash")
    Call<List<Splash>> getAllSplashContent();

    @PUT("update/splash/{id}")
    Call<Splash> updateSplashContent(@Path("id") int id, @Body Splash splash);
}
