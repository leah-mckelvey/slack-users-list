package com.interview.leah.slacklist;

import android.app.Application;

import com.interview.leah.slacklist.api.RequestManager;

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
        requestManager.sendRequest(String.format(RequestManager.TEST_URL, RequestManager.TEST_TOKEN));
    }
}
