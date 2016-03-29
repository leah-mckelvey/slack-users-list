package com.interview.leah.slacklist;

import android.app.Application;

import com.interview.leah.slacklist.api.RequestManager;
import com.interview.leah.slacklist.model.UserModel;

import org.json.JSONArray;

/**
 * Responsibilities:
 * Delegate all startup activity
 *
 * Out of scope:
 * Any actual processing.
 */
public class UserListApplication extends Application {
    private RequestManager requestManager;

    @Override
    public void onCreate() {
        requestManager = new RequestManager(this);
        requestManager.addRequestListener(new RequestManager.RequestListener() {
            @Override
            public void onSuccess(JSONArray users) {
                UserModel.getInstance().parseJson(users);
            }

            @Override
            public void onFailure() {
                //TODO
                //Maybe send a toast?
            }
        });
        requestManager.sendRequest(String.format(RequestManager.TEST_URL, RequestManager.TEST_TOKEN));
    }
}
