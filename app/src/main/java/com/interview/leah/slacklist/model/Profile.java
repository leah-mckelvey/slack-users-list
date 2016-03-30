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
    private String firstName;
    private String lastName;
    private String realName;
    private String email;
    private String skype;
    private String phone;
    private String title;
    private String image_24;
    private String image_32;
    private String image_48;
    private String image_72;
    private String image_192;

    public Profile() {

    }

    public Profile(String firstName, String lastName, String realName, String email, String skype, String phone, String title, String image_24, String image_32, String image_48, String image_72, String image_192) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.realName = realName;
        this.email = email;
        this.skype = skype;
        this.phone = phone;
        this.title = title;
        this.image_24 = image_24;
        this.image_32 = image_32;
        this.image_48 = image_48;
        this.image_72 = image_72;
        this.image_192 = image_192;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRealName() {
        return realName;
    }

    public String getEmail() {
        return email;
    }

    public String getSkype() {
        return skype;
    }

    public String getPhone() {
        return phone;
    }

    public String getTitle() {
        return title;
    }

    public String getImage_24() {
        return image_24;
    }

    public String getImage_32() {
        return image_32;
    }

    public String getImage_48() {
        return image_48;
    }

    public String getImage_72() {
        return image_72;
    }

    public String getImage_192() {
        return image_192;
    }

    public static Profile fromJson(JSONObject profileObject) {
        String firstName = profileObject.optString("first_name", "");
        String lastName = profileObject.optString("last_name", "");
        String realName = profileObject.optString("real_name", "");
        String email = profileObject.optString("email", "");
        String skype = profileObject.optString("skype", "");
        String phone = profileObject.optString("phone", "");
        String title = profileObject.optString("title", "");
        String image_24 = profileObject.optString("image_24", "");
        String image_32 = profileObject.optString("image_32", "");
        String image_48 = profileObject.optString("image_48", "");
        String image_72 = profileObject.optString("image_72", "");
        String image_192 = profileObject.optString("image_192", "");
        Profile returnProfile = new Profile(firstName, lastName, realName, email, skype, phone, title, image_24, image_32, image_48, image_72, image_192);
        return returnProfile;
    }

    public void persist() {
        save(this);
    }
}
