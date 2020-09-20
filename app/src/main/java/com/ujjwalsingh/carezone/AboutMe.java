package com.ujjwalsingh.carezone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class AboutMe extends AppCompatActivity {
        ImageView facebook;
        ImageView phone;
        ImageView email;
        ImageView twitter;
        ImageView backButton;
        ImageView linkedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        facebook = findViewById(R.id.facebook_url);
        phone = findViewById(R.id.phone_call);
        backButton = findViewById(R.id.backButton);
        email = findViewById(R.id.email_url);
        twitter = findViewById(R.id.twitter_url);
        linkedIn= findViewById(R.id.linkedIn_url);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFacebook();
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTwitter();
            }
        });

     linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLinkedIn();
            }
        });

     email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmail();
            }
        });

     phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhone();
            }
        });



    }
    public void openFacebook(){
        Uri uri = Uri.parse("https://www.facebook.com/dip.samajdar");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void openTwitter(){
        Uri uri = Uri.parse("https://twitter.com/home?lang=en");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void openLinkedIn(){
        //Uri uri = Uri.parse("https://www.linkedin.com/in/rishabh-sharma-0178931a1/");
        Uri uri = Uri.parse("https://www.linkedin.com/in/dip-prakash-samajdar-0aaab6106/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void openPhone(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel: 7612794474"));
        startActivity(intent);
    }
    public void openEmail(){
        Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "dip.samajdar@iiitdmj.ac.in"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
        intent.putExtra(Intent.EXTRA_TEXT, "your_text");
        startActivity(intent);
    }
}