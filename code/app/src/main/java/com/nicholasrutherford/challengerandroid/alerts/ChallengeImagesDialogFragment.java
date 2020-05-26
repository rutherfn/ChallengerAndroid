package com.nicholasrutherford.challengerandroid.alerts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nicholasrutherford.challengerandroid.R;
import com.nicholasrutherford.challengerandroid.adapters.alerts.ChallengesImagesAdapt;
import com.nicholasrutherford.challengerandroid.helpers.TypefaceHelper;

import java.util.ArrayList;
import java.util.List;

public class ChallengeImagesDialogFragment extends DialogFragment {

    private TypefaceHelper typefaceHelper = new TypefaceHelper();
    private TextView tvClickImage;
    private List<String> listOfImages = new ArrayList<>();
    private RecyclerView rvAlertChallenges;
    private ChallengesImagesAdapt challengesImagesAdapt;

    public ChallengeImagesDialogFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.alert_dialog_challenges_images_layout, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        main(view);
        getDialog().setTitle("Select Image");
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    private void main(View view) {
        clearImages();
        setUpIds(view);
        setTypefaceForHeader();
        initListOfImages();
        setFocusAndNestedRecycler();
        initRecyclerAdapter();
    }

    private void clearImages() {
        if(listOfImages.size() >= 1) {
            listOfImages.clear();
        }
    }

    private void setUpIds(View view) {
        tvClickImage = view.findViewById(R.id.tvClickImage);
        rvAlertChallenges = view.findViewById(R.id.rvAlertChallenges);
    }

    private void setTypefaceForHeader() {
        typefaceHelper.setTypefaceOfHeader(tvClickImage, getContext());
    }

    private void initListOfImages() {
        listOfImages.add("https://fitnessfor10.com/wp-content/uploads/2019/05/Fitness-for-10-Home-Licensing-Information.jpg");
        listOfImages.add("https://remakemyplate.com/wp-content/uploads/2019/08/Keto-Cooking-Challenge-650-x-476.jpg");
        listOfImages.add("https://snpha.org/images/MH.png");
    }

    private void setFocusAndNestedRecycler() {
        rvAlertChallenges.setFocusable(false);
        rvAlertChallenges.setNestedScrollingEnabled(false);
    }

    private void initRecyclerAdapter() {
        challengesImagesAdapt =  new ChallengesImagesAdapt(getContext(), listOfImages);
        rvAlertChallenges.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAlertChallenges.setAdapter(challengesImagesAdapt);
    }


}
