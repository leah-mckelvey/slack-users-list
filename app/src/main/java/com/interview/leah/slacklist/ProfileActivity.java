package com.interview.leah.slacklist;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.interview.leah.slacklist.model.User;
import com.interview.leah.slacklist.model.UserModel;


public class ProfileActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bindViews();
    }

    private void bindViews() {
        int id = getIntent().getIntExtra("id", 0);
        User user = UserModel.getInstance().getUsers().get(id);
        NetworkImageView profileImageView = (NetworkImageView) findViewById(R.id.profileImageView);
        profileImageView.setImageUrl(user.getProfile().getImage_72(), ((UserListApplication) getApplication()).getImageLoader());
        TextView userNameView = (TextView) findViewById(R.id.realname);
        userNameView.setText(user.getProfile().getRealName());
        TextView titleView = (TextView) findViewById(R.id.title);
        titleView.setText(user.getProfile().getTitle());

    }
}
