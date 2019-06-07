package com.ghareeb.twitterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView usernameTV = findViewById(R.id.usernameTV);
        String username = getIntent().getStringExtra(MainActivity.TWITTER_USERNAME);
        usernameTV.setText(username);
        final TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        new MyTwitterApiClient(session).getUserApiCustomService().show(session.getUserId()).enqueue(new Callback<User>() {
            @Override
            public void success(Result<User> result) {
                TextView infoTV = findViewById(R.id.infoTV);
                String info = "Name: " + result.data.name +
                        "\nLocation: " + result.data.location +
                        "\nFriends: " + result.data.friendsCount +
                        "\nFollowers: " + result.data.followersCount;
                infoTV.setText(info);
                ImageView profileImage = findViewById(R.id.profileImageView);
                Picasso.with(getBaseContext()).load(result.data.profileImageUrl).resize(300 ,300)
                        .into(profileImage);

            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_SHORT).show();
            }
        });

        Button folowersBtn = findViewById(R.id.followersButton);
        folowersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyTwitterApiClient(session).getUserApiCustomService().getFollowers(session.getUserId()).enqueue(new Callback<TwitterFollowerList>() {
                    @Override
                    public void success(Result<TwitterFollowerList> result) {
                        ListView followersLV = findViewById(R.id.folloersLV);
                        ArrayAdapter<String> followersArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);


                       List<String> userNames = new ArrayList<>();

                       for (User user : result.data.getUsers()){
                           userNames.add(user.name);
                       }
                       followersArrayAdapter.addAll(userNames);
                        followersLV.setAdapter(followersArrayAdapter);

                    }

                    @Override
                    public void failure(TwitterException exception) {

                        Toast.makeText(getApplicationContext(), "faild to get followers list!",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
