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
import com.nicholasrutherford.challengerandroid.activitys.MainActivity;
import com.nicholasrutherford.challengerandroid.alerts.ChallengeImagesDialogFragment;
import com.nicholasrutherford.challengerandroid.data.Challenge;
import com.nicholasrutherford.challengerandroid.helpers.TypefaceHelper;
import com.nicholasrutherford.challengerandroid.services.APIUtils;
import com.nicholasrutherford.challengerandroid.services.challenges.ChallengeService;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAChallengeActivity extends AppCompatActivity {

    private TextView tvAddChallenge, tvTitle, tvBody, tvCategory, tvUploadImage;
    private TypefaceHelper typefaceHelper = new TypefaceHelper();
    private Spinner spCategory;
    private CircleImageView cvImageUpload;
    private Challenge challenge = new Challenge();
    private ChallengeService challengeService;
    private EditText etTitle, etBody, etCategory;
    private Button btnPostChallenge;
    private FragmentManager fm = getSupportFragmentManager();
    private ChallengeImagesDialogFragment challengeImagesDialogFragment =  new ChallengeImagesDialogFragment();
    public static String selectedImage = "";
    private String categorySelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_a_challenge_layout);
        challengeService = APIUtils.getChallengeService();
        main();
    }

    private void main() {
        setUpIds();
        setTypeface();
        setUpDataForView();
        confirmChallenge();
    }

    private void setUpIds() {
        tvAddChallenge = findViewById(R.id.tvAddChallenge);
        etTitle = findViewById(R.id.etTitle);
        etBody = findViewById(R.id.etBody);
        etCategory = findViewById(R.id.etCategory);
        btnPostChallenge = findViewById(R.id.btnUploadChallenge);
        cvImageUpload = findViewById(R.id.cvImageUpload);
        spCategory = findViewById(R.id.spCategory);
        tvTitle = findViewById(R.id.tvTitle);
        tvBody = findViewById(R.id.tvBody);
        tvCategory = findViewById(R.id.tvCategory);
        tvUploadImage = findViewById(R.id.tvUploadImage);
    }

    private void setTypeface() {
        typefaceHelper.setTypefaceOfHeader(tvAddChallenge, getApplicationContext());
        typefaceHelper.setTypefaceOfBodyRegular(tvTitle, getApplicationContext());
        typefaceHelper.setTypefaceOfBodyRegular(tvBody, getApplicationContext());
        typefaceHelper.setTypefaceOfBodyRegular(tvCategory,getApplicationContext());
        typefaceHelper.setTypefaceOfBodyRegular(tvUploadImage,getApplicationContext());
        typefaceHelper.setTypefaceOfHeader(btnPostChallenge, getApplicationContext());
    }

    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void setUpDataForView() {
        Picasso.get().load("https://user-images.githubusercontent.com/24874033/33524225-4ed3895e-d83e-11e7-8549-066c96bbab6c.png").into(cvImageUpload);
        initSpinner();
        uploadImageFromLibrary();
    }

    private void uploadImageFromLibrary() {
        cvImageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChallengeImageDialog();
            }
        });
    }

    private void initSpinner() {
        final String[] categories = new String[] {"Fitness", "Mental", "Cooking"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);

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

    private void confirmChallenge() {
//        btnPostChallenge.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!etTitle.getText().toString().equals("") && !etBody.getText().toString().equals("") && !etCategory.getText().toString().equals("") && !etUrl.getText().toString().equals("")) {
//                    createNewChallenge(etTitle.getText().toString(), etBody.getText().toString(), etCategory.getText().toString(), etUrl.getText().toString());
//                    clearUI();
//                    startMainActivity();
//                } else {
//                    clearUI();
//                    // post alert saying your missing something
//                }
//            }
//        });
    }

    private void createNewChallenge(String title, String body, String category, String url) {
        challenge.setId(0);
        challenge.setTitle(title);
        challenge.setBody(body);
        challenge.setCategory(category);
        challenge.setUrl(url);
        Call<Challenge> tt = challengeService.addChallenge(challenge);
        tt.enqueue(new Callback<Challenge>() {
            @Override
            public void onResponse(Call<Challenge> call, Response<Challenge> response) {
               // startMainActivity();
            }

            @Override
            public void onFailure(Call<Challenge> call, Throwable t) {
            }
        });
    }

    private void clearUI() {
        etTitle.getText().clear();
        etBody.getText().clear();
        etCategory.getText().clear();
    }

    private void showChallengeImageDialog() {
        challengeImagesDialogFragment.show(fm, "challengeImageDialog");
    }

    public void dismissChallengeImageDialogAndSetImage() {
        challengeImagesDialogFragment.dismiss();
        Picasso.get().load(selectedImage).into(cvImageUpload);
    }

}
