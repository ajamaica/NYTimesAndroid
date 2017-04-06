package com.brounie.trivago.shared;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by arturojamaica on 06/04/17.
 */

public class NYTimesNetworkManager {
    private static final String TAG = "NetworkManager";
    private static NYTimesNetworkManager instance = null;

    private static final String prefixURL = "https://api.nytimes.com/svc";
    private static final String NY_API_KEY = "28fed6d7f29348f984a91bbbfe14eada";

    //for Volley API
    public RequestQueue requestQueue;

    private NYTimesNetworkManager(Context context)
    {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        //other stuf if you need
    }

    public static synchronized NYTimesNetworkManager getInstance(Context context)
    {
        if (null == instance)
            instance = new NYTimesNetworkManager(context);
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized NYTimesNetworkManager getInstance()
    {
        if (null == instance)
        {
            throw new IllegalStateException(NYTimesNetworkManager.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    public void mostviewed(Object param1, final NYTimesCustomListener<String> listener)
    {

        String url = prefixURL + "/mostpopular/v2/mostviewed/all-sections/1.json?api-key="+ NY_API_KEY;

        Map<String, Object> jsonParams = new HashMap<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.d(TAG + ": ", "somePostRequest Response : " + response.toString());
                        if(null != response.toString())
                            listener.getResult(response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        if (null != error.networkResponse)
                        {
                            Log.d(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);
                            listener.getResult("Error");
                        }
                    }
                });

        requestQueue.add(request);
    }
}
