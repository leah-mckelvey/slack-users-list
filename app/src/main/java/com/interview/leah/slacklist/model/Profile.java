package com.interview.leah.slacklist.model;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import org.json.JSONObject;

/**
 * Responsibilities:
 * Store Profile data
 *
 * Out of scope:
 * Anything that's not storing profile data.
 */
@Table
public class Profile extends SugarRecord {
    public String firstName;
    public String lastName;
    public String realName;
    public String email;
    public String skype;
    public String phone;
    public String title;
    public String image_24;
    public String image_32;
    public String image_48;
    public String image_72;
    public String image_192;

    public static Profile fromJson(JSONObject profileObject) {
        Profile returnProfile = new Profile();
        returnProfile.firstName = profileObject.optString("first_name", "");
        returnProfile.lastName = profileObject.optString("last_name", "");
        returnProfile.realName = profileObject.optString("real_name", "");
        returnProfile.email = profileObject.optString("email", "");
        returnProfile.skype = profileObject.optString("skype", "");
        returnProfile.phone = profileObject.optString("phone", "");
        returnProfile.title = profileObject.optString("title", "");
        returnProfile.image_24 = profileObject.optString("image_24", "");
        returnProfile.image_32 = profileObject.optString("image_32", "");
        returnProfile.image_48 = profileObject.optString("image_48", "");
        returnProfile.image_72 = profileObject.optString("image_72", "");
        returnProfile.image_192 = profileObject.optString("image_192", "");
        save(returnProfile);
        return returnProfile;
    }
}
