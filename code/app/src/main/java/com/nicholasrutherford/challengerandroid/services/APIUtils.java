package com.nicholasrutherford.challengerandroid.services;

import com.nicholasrutherford.challengerandroid.services.client.RetrofitClient;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "https://mighty-brook-50625.herokuapp.com/challenger/";

    public static ChallengeService getChallengeService(){
        return RetrofitClient.getClient(API_URL).create(ChallengeService.class);
    }

    public static AccountService getAccountService() {
        return RetrofitClient.getClient(API_URL).create(AccountService.class);
    }

}