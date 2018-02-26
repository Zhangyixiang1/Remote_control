package com.example.zhangyx1.remotecontrol;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText Edt_ip;
    EditText Edt_port;
    String ip;
    int port;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // replaceFragment(new RightFragment());
        Edt_ip = (EditText) findViewById(R.id.editText_IP);
        ip = Edt_ip.getText().toString();
        Edt_port = (EditText) findViewById(R.id.editText_Port);
        port = Integer.parseInt(Edt_port.getText().toString());
        final Myapplication myapplication = Myapplication.getApplication();
        findViewById(R.id.btn_cnn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        myapplication.socket_tcp = new Socket_Tcp("10.0.2.2", 1888);
                        myapplication.socket_tcp.addObserver(new Rev_Data());

                        new Thread(myapplication.socket_tcp).start();
                        try {
                            Thread.sleep(2000);
                            Intent intent=new Intent(MainActivity.this,ContentActivity.class);
                            startActivity(intent);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
            }
        });
        findViewById(R.id.btn_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "11 11 22 22 33 33 44 44 55 66";
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                myapplication.socket_tcp.send(Comm.HexCommandtoByte(data.getBytes()), Comm.HexCommandtoByte(data.getBytes()).length);
            }
        });
    }


}
