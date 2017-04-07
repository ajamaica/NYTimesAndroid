package com.brounie.trivago;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.brounie.trivago.Adapter.SearchLoadMoreAdapter;
import com.brounie.trivago.Util.ArrayUtil;
import com.brounie.trivago.shared.NYTimesNetworkManager;
import com.brounie.trivago.shared.NYTimesCustomListener;
import com.iamtheib.infiniterecyclerview.InfiniteAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private int page = 0;
    private SearchLoadMoreAdapter adapter;
    private ArrayList<JSONObject> articles = new ArrayList< JSONObject>();

    @BindView(R.id.recycleView) RecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NYTimesNetworkManager.getInstance(this);
        ButterKnife.bind(this);

        get_viewed();
    }

    private InfiniteAdapter.OnLoadMoreListener mLoadMoreListener = new InfiniteAdapter.OnLoadMoreListener() {
        @Override
        public void onLoadMore() {


            /*final int currSize = mDummyData.size();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    addMoreData();
                    mSampleAdapter.moreDataLoaded(currSize, mDummyData.size() - currSize);
                    mSampleAdapter.setShouldLoadMore(false);
                }
            }, 5000);*/
        }
    };

    public void get_viewed(){
        NYTimesNetworkManager.getInstance().mostviewed("all-sections","1", new NYTimesCustomListener<JSONArray>()
        {
            @Override
            public void getResult(JSONArray result)
            {
                if (result.length() >0)
                {
                    articles = ArrayUtil.convert(result);
                    adapter = new SearchLoadMoreAdapter(MainActivity.this, articles);

                    recycleView.setHasFixedSize(true);
                    recycleView.setAdapter(adapter);
                    recycleView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    adapter.setOnLoadMoreListener(mLoadMoreListener);


                }
            }
        });
    }

    public void get_article(String q, int page ){
        NYTimesNetworkManager.getInstance().articlesearch(q,page, new NYTimesCustomListener<JSONArray>()
        {
            @Override
            public void getResult(JSONArray result)
            {
                if (result.length() >0)
                {
                    System.out.println(result);
                }
            }
        });
    }
}
