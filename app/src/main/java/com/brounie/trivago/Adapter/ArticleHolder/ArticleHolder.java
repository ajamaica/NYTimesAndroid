package com.brounie.trivago.Adapter.ArticleHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.brounie.trivago.R;

import butterknife.BindView;

/**
 * Created by arturojamaica on 06/04/17.
 */

public class ArticleHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public  TextView date;
    public  TextView extrac;
    public  ImageView image;

    public ArticleHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.headline);
        date = (TextView) itemView.findViewById(R.id.date);
        extrac = (TextView) itemView.findViewById(R.id.extrac);
        image = (ImageView) itemView.findViewById(R.id.image);

    }

}