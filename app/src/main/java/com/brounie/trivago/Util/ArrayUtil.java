package com.brounie.trivago.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by arturojamaica on 06/04/17.
 */

public class ArrayUtil
{
    public static ArrayList<JSONObject> convert(JSONArray jArr)
    {
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        try {
            for (int i=0, l=jArr.length(); i<l; i++){
                list.add(jArr.getJSONObject(i));
            }
        } catch (JSONException e) {}

        return list;
    }

    public static JSONArray convert(Collection<Object> list)
    {
        return new JSONArray(list);
    }

}