package com.brounie.trivago;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.brounie.trivago.shared.NYTimesNetworkManager;
import com.brounie.trivago.shared.NYTimesCustomListener;

public class MainActivity extends AppCompatActivity {

    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NYTimesNetworkManager.getInstance(this);






    }

    public void get_viewed(){
        NYTimesNetworkManager.getInstance().mostviewed("all-sections","1", new NYTimesCustomListener<String>()
        {
            @Override
            public void getResult(String result)
            {
                if (!result.isEmpty())
                {
                    System.out.println(result);
                }
            }
        });
    }

    public void get_article(String q, int page ){
        NYTimesNetworkManager.getInstance().articlesearch(q,page, new NYTimesCustomListener<String>()
        {
            @Override
            public void getResult(String result)
            {
                if (!result.isEmpty())
                {
                    System.out.println(result);
                }
            }
        });
    }
}
