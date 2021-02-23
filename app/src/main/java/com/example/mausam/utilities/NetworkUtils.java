package com.example.mausam.utilities;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mausam.R;

import org.json.JSONObject;

public final class NetworkUtils {
    private static final String DYNAMIC_WEATHER_URL =
            "https://andfun-weather.udacity.com/weather";
    NetworkUtils context=NetworkUtils.this;
    public static void  volley(Context context){
        TextView textView =(TextView)((Activity)context).findViewById(R.id.response);
        textView.setText("Processing");
        Log.v("response","data retrieving");
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, DYNAMIC_WEATHER_URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                         textView.setText(response.toString());
                        Log.v("response","data retrieved");
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText(error.toString());

                    }
                });
        queue.add(jsonObjectRequest);

    }
}
