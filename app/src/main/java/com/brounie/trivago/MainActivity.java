package com.brounie.trivago;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
    private String query = "";
    private SearchLoadMoreAdapter adapter;
    private ArrayList<JSONObject> popular_articles = new ArrayList< JSONObject>();
    private ArrayList<JSONObject> search_articles = new ArrayList< JSONObject>();

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
            page = page + 1;
            get_article(query,page);
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


                    popular_articles = ArrayUtil.convert(result);


                }
            }
        });
    }

    public void get_article(String q, final int page ){
        if(q.isEmpty()){
            return;
        }


        NYTimesNetworkManager.getInstance().articlesearch(q,page, new NYTimesCustomListener<JSONArray>()
        {
            @Override
            public void getResult(JSONArray result)
            {
                if (result.length() >0)
                {
                    if(page == 0){
                        search_articles = ArrayUtil.convert(result);
                        adapter = new SearchLoadMoreAdapter(MainActivity.this, search_articles);

                        recycleView.setHasFixedSize(true);
                        recycleView.setAdapter(adapter);
                        recycleView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        adapter.setOnLoadMoreListener(mLoadMoreListener);
                    }else{
                        ArrayList<JSONObject> retrived_articles = ArrayUtil.convert(result);
                        adapter.moreDataLoaded(search_articles.size(), retrived_articles.size());
                        search_articles.addAll(ArrayUtil.convert(result));

                    }

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                query = s;
                get_article(query,0);
                return false;
            }
        });
        MenuItem menuItem = menu.findItem(R.id.search);
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                get_viewed();
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }
        });

        return true;
    }
}
