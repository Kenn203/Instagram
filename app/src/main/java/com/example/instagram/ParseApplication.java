package com.example.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("RtebtLODLoVyxrf5BqqhI9KkJvU4k7JSyWtgmIUf")
                .clientKey("uH8ScVPuaGzWE1XoGkPylJEVZ38IFMgndsgWKeSm")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
