package com.nicholasrutherford.challengerandroid.viewholders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.nicholasrutherford.challengerandroid.R;
import com.nicholasrutherford.challengerandroid.activitys.challenges.EditOrDeleteChallengeActivity;
import com.nicholasrutherford.challengerandroid.data.Challenge;
import com.nicholasrutherford.challengerandroid.helpers.TypefaceHelper;
import com.squareup.picasso.Picasso;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChallengesViewHolder extends RecyclerView.ViewHolder {

    private CircleImageView ivChallenge;
    private ConstraintLayout cnsLayoutChallenge;
    private TypefaceHelper typefaceHelper = new TypefaceHelper();
    private TextView tvChallengeTitle, tvChallengeDesc, tvChallengeCategory, editOrDeleteChallenge;

    public ChallengesViewHolder(@NonNull View itemView) {
        super(itemView);
        ivChallenge = itemView.findViewById(R.id.ivChallenge);
        tvChallengeTitle = itemView.findViewById(R.id.tvChallengeTitle);
        tvChallengeDesc = itemView.findViewById(R.id.tvChallengeDesc);
        tvChallengeCategory = itemView.findViewById(R.id.tvChallengeCategory);
        cnsLayoutChallenge = itemView.findViewById(R.id.cnsLayoutChallenge);
        editOrDeleteChallenge = itemView.findViewById(R.id.tvEditOrDeleteChallenge);
    }

    public void main(final int pos, final List<Challenge> challenges, Context mContext) {
        setUpUI(pos, challenges);
        setUpTypeface(mContext);
        startEditChallengeActivity(pos,challenges);
    }

    private void startEditChallengeActivity(final int pos, final List<Challenge> challenges) {
        editOrDeleteChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditOrDeleteChallengeActivity.class);
                intent.putExtra("challengeTitle", challenges.get(pos).getTitle());
                v.getContext().startActivity(intent);
            }
        });
    }

    private void setUpUI(int pos, List<Challenge> challenges) {
        Picasso.get().load(challenges.get(pos).url).into(ivChallenge);
        tvChallengeTitle.setText(challenges.get(pos).title);
        tvChallengeDesc.setText(challenges.get(pos).body);
        tvChallengeCategory.setText("Category: " + challenges.get(pos).category);
    }

    private void setUpTypeface(Context mContext) {
        typefaceHelper.setTypefaceOfHeader(tvChallengeTitle, mContext);
        typefaceHelper.setTypefaceOfBodyRegular(tvChallengeDesc,mContext);
        typefaceHelper.setTypefaceOfHeaderRegular(tvChallengeCategory, mContext);
        typefaceHelper.setTypefaceOfHeader(editOrDeleteChallenge, mContext);
    }

}