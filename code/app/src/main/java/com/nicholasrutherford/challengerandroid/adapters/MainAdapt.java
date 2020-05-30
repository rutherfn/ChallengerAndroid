package com.nicholasrutherford.challengerandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nicholasrutherford.challengerandroid.R;
import com.nicholasrutherford.challengerandroid.viewholders.MainViewHolder;
import java.util.List;

public class MainAdapt extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context mContext;
    private List<String> listOfContent;

    public MainAdapt(Context mContext, List<String> listOfContent) {
        this.mContext = mContext;
        this.listOfContent = listOfContent;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_endpoints_layout, parent, false);
        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MainViewHolder mainViewHolder = (MainViewHolder) holder;
        mainViewHolder.main(position,listOfContent, mContext);
    }

    @Override
    public int getItemCount() {
        return listOfContent.size();
    }

}