package com.interview.leah.slacklist.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
    private List<DataListener> listeners = new ArrayList<>();
    private static UserModel instance = new UserModel();
    public static UserModel getInstance(){
        return instance;
    }
    public UserModel() {

    }

    public List<User> getUsers() {
        // If they're not in memory, load it from disk.
        if (users == null) {
            users = User.listAll(User.class);
            Log.d("Leah", "User length = " + users.size());
        }
        return users;
    }

    public void parseJson(JSONArray userArray) {
        // Our data is invalid at this point.  Wipe it and replace
        User.deleteAll(User.class);
        Profile.deleteAll(Profile.class);
        for (int i = 0; i < userArray.length(); i++) {
            try {
                if (users == null) {
                    users = new ArrayList<>();
                }
                JSONObject userObject = (JSONObject) userArray.get(i);
                User user = User.fromJson(userObject);
                if (!users.contains(user)) {
                    users.add(user);
                }
            } catch (JSONException jsonException) {
                return;
            }
        }
        notifyDataChanged();
    }

    private void notifyDataChanged() {
        for (DataListener listener : listeners) {
            listener.onDataChanged();
        }
    }

    public interface DataListener {
        void onDataChanged();
    }
}
