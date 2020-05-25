package com.nicholasrutherford.challengerandroid.viewholders;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nicholasrutherford.challengerandroid.R;
import com.nicholasrutherford.challengerandroid.activitys.challenges.ChallengesActivity;
import java.util.List;

public class MainViewHolder extends RecyclerView.ViewHolder {

    private TextView tvModel;

    public MainViewHolder(@NonNull View itemView) {
        super(itemView);
        tvModel = itemView.findViewById(R.id.tvModels);
    }

     public void main(int pos, List<String> listOfContent) {
        tvModel.setText(listOfContent.get(pos));
        modelOnClickListener(listOfContent,pos);
    }

    private void modelOnClickListener(final List<String> listOfContent, final int pos) {
        tvModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String modelClicked = listOfContent.get(pos);

                if(modelClicked.equals("* Challenges")) {
                    Intent intent = new Intent(v.getContext(), ChallengesActivity.class);
                    v.getContext().startActivity(intent);
                } else if(modelClicked.equals("* Branding")) {

                } else if(modelClicked.equals("* Onboarding")) {
                    // st
                }
            }
        });
    }

}
