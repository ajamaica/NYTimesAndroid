package com.brounie.trivago;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.brounie.trivago.shared.NYTimesNetworkManager;
import com.brounie.trivago.shared.NYTimesCustomListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NYTimesNetworkManager.getInstance(this);
        NYTimesNetworkManager.getInstance().mostviewed("", new NYTimesCustomListener<String>()
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
