package com.interview.leah.slacklist.model;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Raw storage for a single user.
 * Sugar ORM storage object
 *
 * Responsibilities:
 * Store user object data
 * 1:1 correlation with Json
 *
 * Out of scope:
 * Everything that isn't storing data.
 */
public class User extends SugarRecord {
    public String userId;
    public String name;
    public String color;
    public boolean isAdmin;
    public boolean deleted;
    public boolean isOwner;
    public boolean has2fa;
    public boolean hasFiles;
    private Profile profile;
    // This is because of a querk of how SugarOrm works.  This is how to save a 1:1 correlation
    private long profileId;

    public static User fromJson(JSONObject userObject) {
        User returnUser = new User();
        returnUser.userId = userObject.optString("id", "");
        returnUser.name = userObject.optString("name", "");
        returnUser.deleted = userObject.optBoolean("deleted");
        returnUser.color = userObject.optString("color", "");
        returnUser.isAdmin = userObject.optBoolean("is_admin");
        returnUser.isOwner = userObject.optBoolean("is_owner");
        returnUser.has2fa = userObject.optBoolean("has_2fa");
        returnUser.hasFiles = userObject.optBoolean("has_files");
        returnUser.profile = Profile.fromJson(userObject.optJSONObject("profile"));
        return returnUser;
    }

    public void persist() {
        //Weird hack that violates the immutable rule.  At the same time, there's no way to have the profileId before the profile itself is persisted.
        profile.persist();
        profileId = profile.getId();
        Profile tempProfile = Profile.findById(Profile.class, profileId);
        save(this);
    }
    public Profile getProfile() {
        if (profile == null) {
            profile = Profile.findById(Profile.class, profileId);
        }
        return profile;
    }
}
