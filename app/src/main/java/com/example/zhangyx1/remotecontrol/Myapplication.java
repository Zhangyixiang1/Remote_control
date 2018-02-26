package com.example.zhangyx1.remotecontrol;

import android.app.Application;

/**
 * Created by xiangzi on 2018/2/26.
 */

public class Myapplication extends Application {

    public static Socket_Tcp socket_tcp;
    private static Myapplication application;

    public static Myapplication getApplication() {
        return application;

    }



    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }
}
