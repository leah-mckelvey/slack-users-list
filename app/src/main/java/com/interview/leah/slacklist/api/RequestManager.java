package com.interview.leah.slacklist.api;

import android.content.Context;
import android.support.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsibilities:
 * Initiating Http Volley requests
 * Passing results into model
 *
 * Out of scope:
 * Parsing out model objects
 * Updating UI
 */
public class RequestManager {
    public static final String TEST_TOKEN = "xoxp-5048173296-5048487710-19045732087-b5427e3b46";
    public static final String TEST_URL = "https://slack.com/api/users.list?token=%s";

    private final List<RequestListener> listeners = new ArrayList<>();

    private final RequestQueue queue;

    public RequestManager(Context context) {
        this.queue = Volley.newRequestQueue(context);
    }

    public void sendRequest(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    Boolean isOk = jsonObject.getBoolean("ok");
                    JSONArray array = jsonObject.getJSONArray("members");
                    handleResponse(isOk, array);
                } catch (JSONException jsonException) {
                    handleResponse(false, null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                handleResponse(false, null);
            }
        });
        queue.add(jsonObjectRequest);
    }

    private void handleResponse(boolean success, @Nullable JSONArray jsonInput) {
        for (RequestListener listener : listeners) {
            if (success) {
                listener.onSuccess(jsonInput);
            } else {
                listener.onFailure();
            }
        }
    }

    public void addRequestListener(RequestListener listener) {
        listeners.add(listener);
    }

    public interface RequestListener {
        void onSuccess(JSONArray users);
        void onFailure();
    }
}
