package com.example.instagram;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.Date;

public class PostDetailsActivity extends AppCompatActivity {

    TextView tvTimeStamp;
    TextView tvUsername;
    TextView tvDescription;
    ImageView ivImage;
    ImageView ivProfilePicture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        tvUsername = findViewById(R.id.tvUsername);
        tvDescription = findViewById(R.id.tvDescription);
        tvTimeStamp = findViewById(R.id.tvTimeStamp);
        ivImage = findViewById(R.id.ivImage);
        ivProfilePicture = findViewById(R.id.ivProfilePicture);

        Post post = Parcels.unwrap(getIntent().getParcelableExtra("EXTRA_POST"));
        Date date = post.getCreatedAt();
        String caption = post.getDescription();
        ParseFile image = post.getImage();
        String userName = post.getUser().toString();

        tvUsername.setText(userName);
        tvTimeStamp.setText("." + calculateTimeAgo(date));
        tvDescription.setText(caption);
        ivProfilePicture.setImageResource(R.drawable.mickey_mouse);
        Glide.with(this).load(image.getUrl()).into(ivImage);
    }

    public static String calculateTimeAgo(Date createdAt){
        int SECOND_MILLIS = 1000;
        int MINUTE_MILLIS = 60 * SECOND_MILLIS;
        int HOUR_MILLIS = 60 * MINUTE_MILLIS;
        int DAY_MILLIS = 24 * HOUR_MILLIS;

        try {
            createdAt.getTime();
            long time = createdAt.getTime();
            long now = System.currentTimeMillis();

            final long diff = now - time;
            if (diff < MINUTE_MILLIS) {
                return "just now";
            } else if (diff < 2 * MINUTE_MILLIS) {
                return "a minute ago";
            } else if (diff < 50 * MINUTE_MILLIS) {
                return diff / MINUTE_MILLIS + " m";
            } else if (diff < 90 * MINUTE_MILLIS) {
                return "an hour ago";
            } else if (diff < 24 * HOUR_MILLIS) {
                return diff / HOUR_MILLIS + " h";
            } else if (diff < 48 * HOUR_MILLIS) {
                return "yesterday";
            } else {
                return diff / DAY_MILLIS + " d";
            }
        } catch (Exception e) {
            Log.i("Error:", "getRelativeTimeAgo failed", e);
            e.printStackTrace();
        }

        return "";
    }
}
