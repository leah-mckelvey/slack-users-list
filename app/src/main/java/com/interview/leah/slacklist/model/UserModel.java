package com.interview.leah.slacklist.model;

import android.database.DataSetObservable;
import android.database.DataSetObserver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Responsibilities:
 * Be the memory storage for the user model objects
 * Parse from JSON when server call completes
 * Update objects that depend on the underlying information when a change occurs
 *
 * Out of scope:
 * Editing UI
 * Making server calls
 */
public class UserModel {
    private List<User> users;
    private DataSetObservable observable = new DataSetObservable();
    private static UserModel instance = new UserModel();
    public static UserModel getInstance(){
        return instance;
    }
    private UserModel() {

    }

    public List<User> getUsers() {
        // If they're not in memory, load it from disk.
        if (users == null) {
            users = User.listAll(User.class);
            observable.notifyChanged();
        }
        return Collections.unmodifiableList(users);
    }

    public void parseJsonAndPersist(JSONArray userArray) {
        // Our data is invalid at this point.  Wipe it and replace
        User.deleteAll(User.class);
        Profile.deleteAll(Profile.class);
        users = new ArrayList<>();
        for (int i = 0; i < userArray.length(); i++) {
            try {
                JSONObject userObject = (JSONObject) userArray.get(i);
                User user = User.fromJson(userObject);
                if (!users.contains(user)) {
                    users.add(user);
                }
            } catch (JSONException jsonException) {
                return;
            }
        }
        persist();
        observable.notifyChanged();
    }

    private void persist() {
        for (User user : users) {
            user.persist();
        }
    }

    public void registerObserver(DataSetObserver observer) {
        observable.registerObserver(observer);
    }
}
