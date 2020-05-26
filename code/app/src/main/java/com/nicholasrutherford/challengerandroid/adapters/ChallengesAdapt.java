package com.nicholasrutherford.challengerandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nicholasrutherford.challengerandroid.R;;
import com.nicholasrutherford.challengerandroid.data.Challenge;
import com.nicholasrutherford.challengerandroid.viewholders.ChallengesViewHolder;
import java.util.List;

public class ChallengesAdapt extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Challenge> challenges;

    public ChallengesAdapt(Context mContext, List<Challenge> challenges) {
        this.mContext = mContext;
        this.challenges = challenges;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_challenge_layout, parent, false);
        return new ChallengesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChallengesViewHolder mainView = (ChallengesViewHolder) holder;
        mainView.main(position, challenges, mContext);
    }

    @Override
    public int getItemCount() {
        if(challenges == null) {
            return 0;
        }else {
            return challenges.size();
        }
    }

}