package com.brounie.trivago.Adapter.ArticleHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.brounie.trivago.R;

/**
 * Created by arturojamaica on 06/04/17.
 */

public class LoadingViewHolder extends RecyclerView.ViewHolder {

    public ImageView loadingImage;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        loadingImage = (ImageView) itemView.findViewById(R.id.loadingImage);
    }
}