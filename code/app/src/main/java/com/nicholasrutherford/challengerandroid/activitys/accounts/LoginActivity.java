package com.nicholasrutherford.challengerandroid.activitys.accounts;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.nicholasrutherford.challengerandroid.R;
import com.nicholasrutherford.challengerandroid.activitys.MainActivity;
import com.nicholasrutherford.challengerandroid.activitys.challenges.ChallengesActivity;
import com.nicholasrutherford.challengerandroid.alerts.LoadingDialogFragment;
import com.nicholasrutherford.challengerandroid.data.Account;
import com.nicholasrutherford.challengerandroid.helpers.TypefaceHelper;
import com.nicholasrutherford.challengerandroid.services.APIUtils;
import com.nicholasrutherford.challengerandroid.services.accounts.AccountService;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity  extends AppCompatActivity {

    // declarations
    private FragmentManager fm = getSupportFragmentManager();
    private LoadingDialogFragment loadingDialog = new LoadingDialogFragment();
    private AccountService accountService;
    private CircleImageView ivChallenge;
    private TextView tvLogin;
    private EditText etUsername, etPassword;
    private Button logInBtn;
    private TypefaceHelper typefaceHelper = new TypefaceHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        main();
    }

    private void main() {
        setUpIds();
        setUpTypeface();
        logInToAccount();
    }

    private void setUpIds() {
        ivChallenge = findViewById(R.id.ivChallenge);
        tvLogin = findViewById(R.id.tvLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        logInBtn = findViewById(R.id.logInBtn);
    }

    private void setUpTypeface() {
        typefaceHelper.setTypefaceOfHeader(logInBtn, getApplicationContext());
    }

    private void clearUI() {
        etUsername.getText().clear();
        etPassword.getText().clear();
    }

    private void fetchAccounts() {
        accountService = APIUtils.getAccountService();
        Call<List<Account>> accounts = accountService.getAllAccounts();

        accounts.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                checkEditorText(response);
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                dismissLoadingDialog();
            }
        });
    }

    private void logInToAccount() {
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingDialog();
                fetchAccounts();
            }
        });
    }

    private void checkEditorText(Response<List<Account>> response) {
        if(response.body().get(1).username.equals(etUsername.getText().toString()) &&
        response.body().get(1).password.equals(etPassword.getText().toString()) ||
                response.body().get(2).username.equals(etUsername.getText().toString()) &&
                        response.body().get(2).password.equals(etPassword.getText().toString())) {
            dismissLoadingDialog();
            startUpChallengeActivity();
            clearUI();
        } else  {
            dismissLoadingDialog();
            fireUpAlertForWrongCredentials();
            clearUI();
        }
    }

    private void startUpChallengeActivity() {
        Intent intent = new Intent(this, ChallengesActivity.class);
        startActivity(intent);
        finish();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void fireUpAlertForWrongCredentials() {
        AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
        alertDialog.setTitle("Wrong Credentials");
        alertDialog.setMessage("Sorry login info is not correct Patrick. Please try again.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void showLoadingDialog() {
        loadingDialog.show(fm, "loading dialog");
    }

    private void dismissLoadingDialog() {
        loadingDialog.dismiss();
    }

}