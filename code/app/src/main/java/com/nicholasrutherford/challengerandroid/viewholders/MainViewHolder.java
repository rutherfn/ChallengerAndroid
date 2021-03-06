package com.nicholasrutherford.challengerandroid.viewholders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nicholasrutherford.challengerandroid.R;
import com.nicholasrutherford.challengerandroid.activitys.challenges.ChallengesActivity;
import com.nicholasrutherford.challengerandroid.helpers.TypefaceHelper;

import java.util.List;

public class MainViewHolder extends RecyclerView.ViewHolder {

    private TextView tvModel;
    private TypefaceHelper typefaceHelper = new TypefaceHelper();

    public MainViewHolder(@NonNull View itemView) {
        super(itemView);
        tvModel = itemView.findViewById(R.id.tvModels);
    }

     public void main(int pos, List<String> listOfContent, Context mContext) {
        tvModel.setText(listOfContent.get(pos));
        typefaceHelper.setTypefaceOfBodyRegular(tvModel, mContext);
        modelOnClickListener(listOfContent,pos);
    }

    private void modelOnClickListener(final List<String> listOfContent, final int pos) {
        tvModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String modelClicked = listOfContent.get(pos);

                switch (modelClicked) {
                    case "* Challenges":
                        Intent intent = new Intent(v.getContext(), ChallengesActivity.class);
                        v.getContext().startActivity(intent);
                        break;
                    case "* Branding":

                        break;
                    case "* Onboarding":
                        // st
                        break;
                }
            }
        });
    }

}