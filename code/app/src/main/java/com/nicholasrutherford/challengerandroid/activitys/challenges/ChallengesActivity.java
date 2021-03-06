package com.nicholasrutherford.challengerandroid.activitys.challenges;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nicholasrutherford.challengerandroid.R;
import com.nicholasrutherford.challengerandroid.activitys.accounts.LoginActivity;
import com.nicholasrutherford.challengerandroid.adapters.ChallengesAdapt;
import com.nicholasrutherford.challengerandroid.data.Challenge;
import com.nicholasrutherford.challengerandroid.helpers.TypefaceHelper;
import com.nicholasrutherford.challengerandroid.services.APIUtils;
import com.nicholasrutherford.challengerandroid.services.challenges.ChallengeService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChallengesActivity extends AppCompatActivity {

    private RecyclerView rvMain;
    private ChallengesAdapt mainView;
    private ChallengeService challengeService;
    private Button btnAddAChallengeBtn;
    private TypefaceHelper typefaceHelper = new TypefaceHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_activity);
        main();
    }

    private void main() {
        challengeService = APIUtils.getChallengeService();
        setUpIds();
        setUpTypeface();
        setFocusAndNestedRecycler();
        grabAllChallengesInitAdapter();
        createAChallenge();
    }

    private void setUpIds() {
        rvMain = findViewById(R.id.rvMain);
        btnAddAChallengeBtn = findViewById(R.id.btnAddChallenge);
    }

    private void setUpTypeface() {
        typefaceHelper.setTypefaceOfHeader(btnAddAChallengeBtn,getApplicationContext());
    }

    private void setFocusAndNestedRecycler(){
        rvMain.setFocusable(false);
        rvMain.setNestedScrollingEnabled(false);
    }

    private void grabAllChallengesInitAdapter() {
        Call<List<Challenge>> challenges = challengeService.getAllChallenges();
        challenges.enqueue(new Callback<List<Challenge>>() {
            @Override
            public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
                System.out.println(response.body());
                mainView = new ChallengesAdapt(getApplicationContext(), response.body());
                rvMain.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rvMain.setAdapter(mainView);
            }

            @Override
            public void onFailure(Call<List<Challenge>> call, Throwable t) {
                System.out.println("Error");
            }
        });
    }

    private void createAChallenge() {
        btnAddAChallengeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddAChallengeActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_top, menu);
        return true;
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            startLoginActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}