package com.nicholasrutherford.challengerandroid.viewholders.alerts;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nicholasrutherford.challengerandroid.R;
import com.nicholasrutherford.challengerandroid.activitys.MainActivity;
import com.nicholasrutherford.challengerandroid.activitys.challenges.AddAChallengeActivity;
import com.nicholasrutherford.challengerandroid.adapters.MainAdapt;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChallengesImagesViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivPlaceholderImage;

    public ChallengesImagesViewHolder(@NonNull View itemView) {
        super(itemView);
        ivPlaceholderImage = itemView.findViewById(R.id.ivPlaceholderImage);
    }

    public void main(Integer pos, List<String> listOfImages, Context mContext) {
        setImages(listOfImages, pos);
        setImageAndCloseDialog(listOfImages, pos, mContext);
    }

    public void setImages(List<String> listOfImages, Integer pos) {
        Picasso.get().load(listOfImages.get(pos)).into(ivPlaceholderImage);
    }

    private void setImageAndCloseDialog(final List<String> listOfImages, final Integer pos, final Context mContext) {
        ivPlaceholderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAChallengeActivity.selectedImage = listOfImages.get(pos);
                ((AddAChallengeActivity)mContext).dismissChallengeImageDialogAndSetImage();
            }
        });
    }
}
