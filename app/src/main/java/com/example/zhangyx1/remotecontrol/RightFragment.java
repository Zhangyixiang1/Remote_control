package com.example.zhangyx1.remotecontrol;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhangyx1.remotecontrol.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangyx1 on 2018/2/26.
 */


public class RightFragment extends Fragment {
    TextView mText;
    View view;
    Button btn;
    SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view  =inflater.inflate(R.layout.right_fragment,container,false);
      btn=(Button) view.findViewById(R.id.btn_refresh);
      btn.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v) {
              updateUIByRunnable();
          }
      });
        return  view;

    }
    public void updateUIByRunnable(){
        mText = (TextView) view.findViewById(R.id.txt_rev);//一个TextView
        mText.post(new Runnable() {

            @Override
            public void run() {


                      Date curDate =  new Date(System.currentTimeMillis());
                      mText.setText(formatter.format(curDate));

            }
        });

    }
}
