package com.nicholasrutherford.challengerandroid.activitys.challenges;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.nicholasrutherford.challengerandroid.R;
import com.nicholasrutherford.challengerandroid.activitys.MainActivity;
import com.nicholasrutherford.challengerandroid.data.Challenge;
import com.nicholasrutherford.challengerandroid.services.APIUtils;
import com.nicholasrutherford.challengerandroid.services.challenges.ChallengeService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditOrDeleteChallengeActivity extends AppCompatActivity {

    TextView tvEditChallenge;
    EditText etTitle, etBody, etCategory, etUrl;
    Button btnEditChallenge, btnDeleteChallenge;
    ChallengeService challengeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_or_delete_challenge);
        challengeService = APIUtils.getChallengeService();
        main();
    }

    private void main() {
        setUpId();
        Intent intent = getIntent();
        fetchChallenge(intent.getExtras().getString("challengeTitle"));
        deleteChallenge();
        // updateChallenge
    }

    private void setUpId() {
        tvEditChallenge = findViewById(R.id.tvEditChallenge);
        etTitle = findViewById(R.id.etTitle);
        etBody = findViewById(R.id.etBody);
        etCategory = findViewById(R.id.etCategory);
        etUrl = findViewById(R.id.etUrl);
        btnEditChallenge = findViewById(R.id.btnEditChallenge);
        btnDeleteChallenge = findViewById(R.id.btnDeleteChallenge);
    }

    private void fetchChallenge(String challengeName) {
        Call<Challenge> challenge = challengeService.getChallengeByName(challengeName);
        challenge.enqueue(new Callback<Challenge>() {
            @Override
            public void onResponse(Call<Challenge> call, Response<Challenge> response) {
                setEditText(response.body().title, response.body().body, response.body().category, response.body().url);
            }

            @Override
            public void onFailure(Call<Challenge> call, Throwable t) {

            }
        });
    }

    private void deleteChallenge() {
        btnDeleteChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int idOfChallenge = Integer.parseInt(tvEditChallenge.getText().toString());
                Call<ResponseBody> rr = challengeService.deleteChallenge(idOfChallenge);
                rr.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code() == 200 || response.code() == 204) {
                    startMainActivity();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
            }
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void setEditText(String title, String body, String category, String url) {
        etTitle.setText(title);
        etBody.setText(body);
        etCategory.setText(category);
        etUrl.setText(url);
    }

    private void updateChallenge() {
        //        Call<Challenge> cc = challengeService.updateChallenge(13, chall);
//        cc.enqueue(new Callback<Challenge>() {
//            @Override
//            public void onResponse(Call<Challenge> call, Response<Challenge> response) {
//                System.out.println("It works");
//            }
//
//            @Override
//            public void onFailure(Call<Challenge> call, Throwable t) {
//                System.out.println("Error");
//            }
//        });
//
    }
}
