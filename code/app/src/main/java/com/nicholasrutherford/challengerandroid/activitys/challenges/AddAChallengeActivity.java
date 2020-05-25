package com.nicholasrutherford.challengerandroid.activitys.challenges;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nicholasrutherford.challengerandroid.R;
import com.nicholasrutherford.challengerandroid.activitys.MainActivity;
import com.nicholasrutherford.challengerandroid.data.Challenge;
import com.nicholasrutherford.challengerandroid.services.APIUtils;
import com.nicholasrutherford.challengerandroid.services.ChallengeService;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAChallengeActivity extends AppCompatActivity {

    private static final int PICK_FROM_GALLERY = 2;
    TextView tvAddChallenge;
    Spinner spCategory;
    CircleImageView cvImageUpload;
    Challenge challenge = new Challenge();
    ChallengeService challengeService;
    EditText etTitle, etBody, etCategory;
    Button btnPostChallenge;
    String categorySelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_a_challenge_layout);
        challengeService = APIUtils.getChallengeService();
        main();
    }

    private void main() {
        setUpIds();
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
                selectImage();
            }
        });
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_FROM_GALLERY);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();

            try {
                Bitmap img = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                if(img != null) {
                    cvImageUpload.setImageBitmap(img);
                } else {
                    // set stock image here
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
