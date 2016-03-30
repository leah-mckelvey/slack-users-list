package com.interview.leah.slacklist;

import android.app.ListFragment;
import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.interview.leah.slacklist.model.User;
import com.interview.leah.slacklist.model.UserModel;

import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends ListFragment {
    private List<User> users;
    private ImageLoader loader;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UserModel.getInstance().registerObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                users = UserModel.getInstance().getUsers();
            }
        });
        users = UserModel.getInstance().getUsers();
        loader = ((UserListApplication) getActivity().getApplication()).getImageLoader();

        ListAdapter adapter = new ListAdapter() {
            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEnabled(int position) {
                return true;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {
//                UserModel.getInstance().registerObserver(observer);
            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public int getCount() {
                return users.size();
            }

            @Override
            public Object getItem(int position) {
                return users.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView==null){
                    // inflate the layout
                    LayoutInflater inflater = getActivity().getLayoutInflater();
                    convertView = inflater.inflate(R.layout.item_user, parent, false);
                }
                NetworkImageView imageView = (NetworkImageView) convertView.findViewById(R.id.profileThumbnail);
                imageView.setImageUrl(users.get(position).getProfile().image_24, loader);
                TextView firstNameTextView = (TextView) convertView.findViewById(R.id.firstName);
                firstNameTextView.setText(users.get(position).getProfile().firstName);
                TextView lastNameTextView = (TextView) convertView.findViewById(R.id.lastName);
                lastNameTextView.setText(users.get(position).getProfile().lastName);
                return convertView;
            }

            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        };
        setListAdapter(adapter);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }
}
