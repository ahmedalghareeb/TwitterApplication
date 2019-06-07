package com.ghareeb.twitterapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class MainActivity extends AppCompatActivity {

    TwitterLoginButton loginButton;
    public static final String TWITTER_USERNAME = "TWITTER_USERNAME";
    public static final String TWITTER_AUTH_TOKEN = "TWITTER_AUTH_TOKEN";
    public static final String TWITTER_AUTH_SECRET = "TWITTER_AUTH_SECRET";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);

        setContentView(R.layout.activity_main);


        loginButton = findViewById(R.id.twitterLogInBtn);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();

                String userName = session.getUserName();
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra(TWITTER_USERNAME,userName);
                intent.putExtra(TWITTER_AUTH_TOKEN,authToken.token);
                intent.putExtra(TWITTER_AUTH_SECRET,authToken.secret);
                startActivity(intent);

            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(getApplicationContext(),"Login Faild", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        loginButton.onActivityResult(requestCode,resultCode,data);
    }
}
