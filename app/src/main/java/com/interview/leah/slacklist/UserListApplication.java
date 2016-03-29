package com.interview.leah.slacklist;

import android.app.Application;

import com.interview.leah.slacklist.api.RequestManager;
import com.interview.leah.slacklist.model.UserModel;
import com.orm.SugarContext;

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
        super.onCreate();
        SugarContext.init(this);
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

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
