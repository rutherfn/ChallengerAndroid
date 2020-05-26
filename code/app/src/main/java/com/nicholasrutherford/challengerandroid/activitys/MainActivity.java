package com.nicholasrutherford.challengerandroid.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import com.nicholasrutherford.challengerandroid.R;
import com.nicholasrutherford.challengerandroid.activitys.accounts.LoginActivity;
import com.nicholasrutherford.challengerandroid.adapters.MainAdapt;
import com.nicholasrutherford.challengerandroid.helpers.TypefaceHelper;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMain;
    private TextView tvAddContent;
    private List<String> listOfContent = new ArrayList<>();
    private MainAdapt mainAdapt;
    private TypefaceHelper typefaceHelper = new TypefaceHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main();
    }

    private void main() {
        setUpIds();
        setUpTypeface();
        setFocusAndNestedRecycler();
        initArrayList();
        initRecyclerAdapter();
    }

    private void setUpIds() {
        rvMain = findViewById(R.id.rvMain);
        tvAddContent = findViewById(R.id.tvAddContent);
    }

    private void setUpTypeface() {
        typefaceHelper.setTypefaceOfHeader(tvAddContent, getApplicationContext());
    }

    private void setFocusAndNestedRecycler(){
        rvMain.setFocusable(false);
        rvMain.setNestedScrollingEnabled(false);
    }

    private void initArrayList() {
        listOfContent.add("* Challenges");
        listOfContent.add("* Branding");
        listOfContent.add("* Onboarding");
        listOfContent.add("* Splash");
    }

    private void initRecyclerAdapter() {
        mainAdapt = new MainAdapt(getApplicationContext(), listOfContent);
        rvMain.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvMain.setAdapter(mainAdapt);
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
        switch (item.getItemId()) {
            case R.id.logout:
                startLoginActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}