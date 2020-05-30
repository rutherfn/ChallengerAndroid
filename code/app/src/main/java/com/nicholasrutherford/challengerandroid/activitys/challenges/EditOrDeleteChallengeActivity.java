package com.nicholasrutherford.challengerandroid.activitys.challenges;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.nicholasrutherford.challengerandroid.R;
import com.nicholasrutherford.challengerandroid.alerts.ChallengeImagesDialogFragment;
import com.nicholasrutherford.challengerandroid.alerts.LoadingDialogFragment;
import com.nicholasrutherford.challengerandroid.data.Challenge;
import com.nicholasrutherford.challengerandroid.data.Const;
import com.nicholasrutherford.challengerandroid.helpers.TypefaceHelper;
import com.nicholasrutherford.challengerandroid.services.APIUtils;
import com.nicholasrutherford.challengerandroid.services.challenges.ChallengeService;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditOrDeleteChallengeActivity extends AppCompatActivity {

    private TextView tvEditChallenge, tvTitle, tvBody, tvCategory, tvUploadImage, tvId;
    private EditText etTitle, etDescription;
    private Button btnSaveChanges, btnRemoveChallenge;
    private CircleImageView cvImageUpload;
    private Spinner spCategory;
    private TypefaceHelper typefaceHelper = new TypefaceHelper();
    private ChallengeService challengeService;
    private FragmentManager fm = getSupportFragmentManager();
    private LoadingDialogFragment loadingDialogFragment = new LoadingDialogFragment();
    private ChallengeImagesDialogFragment challengeImagesDialogFragment = new ChallengeImagesDialogFragment();
    private String categorySelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_or_delete_challenge);
        challengeService = APIUtils.getChallengeService();
        main();
    }

    private void main() {
        setUpIds();
        setUpTypefaces();
        setUpConst();
        Intent intent = getIntent();
        fetchChallenge(intent.getExtras().getString("challengeTitle"));
        setNewImageForChallenge();
        updateChallenge();
        deleteChallenge();
    }

    private void setUpIds() {
        tvEditChallenge = findViewById(R.id.tvEditChallenge);
        etTitle = findViewById(R.id.etTitle);
        tvTitle = findViewById(R.id.tvTitle);
        tvBody = findViewById(R.id.tvBody);
        tvCategory = findViewById(R.id.tvCategory);
        tvUploadImage = findViewById(R.id.tvUploadImage);
        etDescription = findViewById(R.id.etDescription);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        btnRemoveChallenge = findViewById(R.id.btnRemoveChallenge);
        cvImageUpload = findViewById(R.id.cvImageUpload);
        spCategory = findViewById(R.id.spCategory);
        tvId = findViewById(R.id.tvId);
    }

    private void setUpTypefaces() {
        typefaceHelper.setTypefaceOfHeader(tvEditChallenge,getApplicationContext());
        typefaceHelper.setTypefaceOfHeaderRegular(tvBody,getApplicationContext());
        typefaceHelper.setTypefaceOfHeaderRegular(tvCategory,getApplicationContext());
        typefaceHelper.setTypefaceOfHeaderRegular(tvUploadImage,getApplicationContext());
        typefaceHelper.setTypefaceOfHeader(btnSaveChanges,getApplicationContext());
        typefaceHelper.setTypefaceOfHeader(btnRemoveChallenge,getApplicationContext());
        typefaceHelper.setTypefaceOfBodyRegular(tvId,getApplicationContext());
    }

    private void setUpConst() {
        Const.SELECTED_IMAGE = "";
        Const.CURRENT_ACTIVITY_NUMBER = 1;
    }

    private void setSpinnerSelectedItem(String category) {
        switch (category) {
            case "Fitness":
                spCategory.setSelection(0);
                break;
            case "Mental":
                spCategory.setSelection(1);
                break;
            case "Cooking":
                spCategory.setSelection(2);
                break;
            case "Software Development":
                spCategory.setSelection(3);
                break;
        }
    }

    private void initSpinner(String category) {
        final String[] categories = new String[] {"Fitness", "Mental", "Cooking", "Software Development"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);
        setSpinnerSelectedItem(category);
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categorySelectedItem = categories[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void fetchChallenge(String challengeName) {
        Call<Challenge> challenge = challengeService.getChallengeByName(challengeName);
        challenge.enqueue(new Callback<Challenge>() {
            @Override
            public void onResponse(Call<Challenge> call, Response<Challenge> response) {
                setEditText(response.body().title, response.body().body, response.body().url, response.body().id);
                initSpinner(response.body().category);
            }

            @Override
            public void onFailure(Call<Challenge> call, Throwable t) {

            }
        });
    }

    private void deleteChallenge() {
        btnRemoveChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingDialog();
                Integer id = Integer.parseInt(tvId.getText().toString());
                Call<ResponseBody> rr = challengeService.deleteChallenge(id);
                rr.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code() == 200 || response.code() == 204) {
                    dismissLoadingDialog();
                    startChallengeActivity();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
            }
        });
    }

    private void startChallengeActivity() {
        Intent intent = new Intent(this, ChallengesActivity.class);
        startActivity(intent);
        finish();
    }

    private void setEditText(String title, String body, String url, Integer id) {
        etTitle.setText(title);
        etDescription.setText(body);
        Picasso.get().load(url).into(cvImageUpload);
        Const.CURRENT_IMAGE = url;
        String valueOfId = Integer.toString(id);
        tvId.setText(valueOfId);
    }

    private void setNewImageForChallenge() {
        cvImageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChallengeImageDialog();
            }
        });
    }

    private void updateChallenge() {
        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingDialog();
                Challenge challenge = new Challenge();
                Integer id = Integer.parseInt(tvId.getText().toString());
                challenge.setId(id);
                challenge.setTitle(etTitle.getText().toString());
                challenge.setBody(etDescription.getText().toString());
                challenge.setCategory(categorySelectedItem);
                if(Const.SELECTED_IMAGE.equals("")) {
                    challenge.setUrl(Const.CURRENT_IMAGE);
                } else {
                    challenge.setUrl(Const.SELECTED_IMAGE);
                }
                updateChallengeApi(challenge,id);
            }
        });
    }

    private void updateChallengeApi(Challenge challenge, Integer id) {
        Call<Challenge> cc = challengeService.updateChallenge(id, challenge);
        cc.enqueue(new Callback<Challenge>() {
            @Override
            public void onResponse(Call<Challenge> call, Response<Challenge> response) {
                dismissLoadingDialog();
                startChallengeActivity();
            }

            @Override
            public void onFailure(Call<Challenge> call, Throwable t) {
                dismissLoadingDialog();
                // network error
            }
        });

    }

    private void showLoadingDialog() {
        loadingDialogFragment.show(fm, "LoadingDialog");
    }

    private void dismissLoadingDialog() {
        loadingDialogFragment.dismiss();
    }

    private void showChallengeImageDialog() {
        challengeImagesDialogFragment.show(fm, "challengeImageDialog");
    }

    public void dismissChallengeImageDialogAndSetImage() {
        challengeImagesDialogFragment.dismiss();
        Picasso.get().load(Const.SELECTED_IMAGE).into(cvImageUpload);
    }

}