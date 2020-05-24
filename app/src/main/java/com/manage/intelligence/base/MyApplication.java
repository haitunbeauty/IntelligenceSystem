package com.manage.intelligence.base;

import android.app.Application;

import com.manage.intelligence.data.db.AppDatabase;

import androidx.room.Room;

public class MyApplication extends Application {

    private static  MyApplication mInstance;
    private AppDatabase appDB;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        appDB = Room.databaseBuilder(this,AppDatabase.class,"user_info")
                .addMigrations()
                .allowMainThreadQueries()
                .build();
    }

    public static MyApplication getInstance(){
        return mInstance;
    }

    public AppDatabase getAppDB(){
        return appDB;
    }

}
