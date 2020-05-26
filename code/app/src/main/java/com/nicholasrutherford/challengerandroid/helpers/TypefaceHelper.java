package com.nicholasrutherford.challengerandroid.helpers;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class TypefaceHelper {

    public void setTypefaceOfHeader(TextView tvText, Context mContext) {
       Typeface headerTypeface = Typeface.createFromAsset(mContext.getAssets(),
                "rubikbold.ttf");
       tvText.setTypeface(headerTypeface);
    }

    public void setTypefaceOfHeaderRegular(TextView tvText, Context mContext) {
        Typeface regularHeaderTypeface = Typeface.createFromAsset(mContext.getAssets(),
                "rubikregular.ttf");
        tvText.setTypeface(regularHeaderTypeface);
    }

    public void setTypefaceOfBodyRegular(TextView tvText, Context mContext) {
        Typeface regularBodyTypeface = Typeface.createFromAsset(mContext.getAssets(),
                "sansproregular.otf");
        tvText.setTypeface(regularBodyTypeface);
    }
}
