package com.nicholasrutherford.challengerandroid.adapters.alerts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nicholasrutherford.challengerandroid.R;
import com.nicholasrutherford.challengerandroid.viewholders.alerts.ChallengesImagesViewHolder;

import java.util.List;

public class ChallengesImagesAdapt extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> listOfImages;

    public ChallengesImagesAdapt(Context mContext, List<String> listOfImages) {
        this.mContext = mContext;
        this.listOfImages = listOfImages;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_for_challenge_layout,parent,false);
        return new ChallengesImagesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChallengesImagesViewHolder challengesImagesViewHolder = (ChallengesImagesViewHolder) holder;
        challengesImagesViewHolder.main(position, listOfImages, mContext);
    }

    @Override
    public int getItemCount() {
        if(listOfImages == null) {
            return 0;
        } else {
            return listOfImages.size();
        }
    }
}
