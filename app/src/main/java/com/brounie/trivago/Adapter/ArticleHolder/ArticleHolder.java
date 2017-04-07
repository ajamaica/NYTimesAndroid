package com.brounie.trivago.Adapter.ArticleHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.brounie.trivago.R;

/**
 * Created by arturojamaica on 06/04/17.
 */

public class ArticleHolder extends RecyclerView.ViewHolder {

    public TextView tv;

    public ArticleHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.tv);
    }
}