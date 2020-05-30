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
import com.nicholasrutherford.challengerandroid.R;
import com.nicholasrutherford.challengerandroid.helpers.TypefaceHelper;

public class LoadingDialogFragment extends DialogFragment {
    private TypefaceHelper typefaceHelper = new TypefaceHelper();
    private TextView tvLoading;

    public LoadingDialogFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.alert_dialog_loading_layout,container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvLoading = view.findViewById(R.id.tvLoading);
        typefaceHelper.setTypefaceOfHeader(tvLoading, getContext());
        getDialog().setTitle("Fetching API Data");
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

}