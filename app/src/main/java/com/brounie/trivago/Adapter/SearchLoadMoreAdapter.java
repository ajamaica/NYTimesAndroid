package com.brounie.trivago.Adapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.brounie.trivago.Adapter.ArticleHolder.ArticleHolder;
import com.brounie.trivago.Adapter.ArticleHolder.LoadingViewHolder;
import com.brounie.trivago.R;
import com.iamtheib.infiniterecyclerview.InfiniteAdapter;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by arturojamaica on 06/04/17.
 */

public class SearchLoadMoreAdapter extends InfiniteAdapter<RecyclerView.ViewHolder> {

    private List<JSONObject> data;
    private Context mContext;

    public SearchLoadMoreAdapter(Context context, List<JSONObject> data) {
        mContext = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder getLoadingViewHolder(ViewGroup parent) {
        View loadingView = LayoutInflater.from(mContext).inflate(R.layout.list_loading_view, parent, false);
        return new LoadingViewHolder(loadingView);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public int getViewType(int position) {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View dummyView = LayoutInflater.from(mContext).inflate(R.layout.article_cell, parent, false);
        return new ArticleHolder(dummyView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            ObjectAnimator animator = ObjectAnimator.ofFloat(loadingViewHolder.loadingImage, "rotation", 0, 360);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setInterpolator(new LinearInterpolator());
            animator.setDuration(1000);
            animator.start();
            return;
        }
        else {
            // data.get(position)
            ((ArticleHolder) holder).tv.setText("ewddew");
        }

        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getVisibleThreshold() {
        return 2;
    }
}