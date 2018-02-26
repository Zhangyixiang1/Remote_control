package com.example.zhangyx1.remotecontrol;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContentActivity extends AppCompatActivity {
    Socket_Tcp socket_tcp;
    Handler mHandler;

    Runnable mRunnable0;
    SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
     // replaceFragment(new RightFragment());
        mHandler = new Handler();//创建Handler

RightFragment rightFragment=(RightFragment)getSupportFragmentManager().findFragmentById(R.id.right_fragment);
        rightFragment.updateUIByRunnable();
    }

    private  void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.right_fragment,fragment);
        transaction.commit();
    }

}
