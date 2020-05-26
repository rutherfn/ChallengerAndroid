package com.nicholasrutherford.challengerandroid.services.challenges;

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

public interface ChallengeService {

    @GET("alls")
    Call<List<Challenge>> getAllChallenges();

    @GET("challenge/{title}")
    Call<Challenge> getChallengeByName(@Path("title") String title);

    @POST("create/challenge")
    Call<Challenge> addChallenge(@Body Challenge challenge);

    @PUT("update/challenge/{challengeId}")
    Call<Challenge> updateChallenge(@Path("challengeId") int id, @Body Challenge challenge);

    @DELETE("challenge/delete/{challengeId}/")
    Call<ResponseBody> deleteChallenge(@Path("challengeId") int challengeId);
}