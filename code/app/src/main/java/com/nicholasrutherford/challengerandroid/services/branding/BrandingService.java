package com.nicholasrutherford.challengerandroid.services.branding;

import com.nicholasrutherford.challengerandroid.data.Branding;
import com.nicholasrutherford.challengerandroid.data.Challenge;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BrandingService {

    @GET("all/branding")
    Call<List<Branding>> getAllBranding();

    @POST("create/branding")
    Call<Branding> addBranding(@Body Branding branding);

    @PUT("update/branding/{id}")
    Call<Challenge> updateBranding(@Path("id") int id, @Body Branding branding);

    @DELETE("branding/delete/{id}")
    Call<ResponseBody> deleteBranding(@Path("id") int id);
}
