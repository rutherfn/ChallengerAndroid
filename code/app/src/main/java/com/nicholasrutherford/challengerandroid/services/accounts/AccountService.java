package com.nicholasrutherford.challengerandroid.services.accounts;

import com.nicholasrutherford.challengerandroid.data.Account;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AccountService {

    @GET("all/accounts")
    Call<List<Account>> getAllAccounts();
}
