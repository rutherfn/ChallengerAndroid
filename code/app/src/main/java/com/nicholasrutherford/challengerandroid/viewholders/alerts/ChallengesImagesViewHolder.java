package com.nicholasrutherford.challengerandroid.viewholders.alerts;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nicholasrutherford.challengerandroid.R;
import com.nicholasrutherford.challengerandroid.activitys.challenges.AddAChallengeActivity;
import com.nicholasrutherford.challengerandroid.activitys.challenges.EditOrDeleteChallengeActivity;
import com.nicholasrutherford.challengerandroid.data.Const;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChallengesImagesViewHolder extends RecyclerView.ViewHolder {

    private CircleImageView ivPlaceholderImage;

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
                Const.SELECTED_IMAGE = listOfImages.get(pos);
                if(Const.CURRENT_ACTIVITY_NUMBER == 0) {
                    ((AddAChallengeActivity) mContext).dismissChallengeImageDialogAndSetImage();
                } else if(Const.CURRENT_ACTIVITY_NUMBER == 1) {
                    ((EditOrDeleteChallengeActivity)mContext).dismissChallengeImageDialogAndSetImage();
                }
            }
        });
    }
}
