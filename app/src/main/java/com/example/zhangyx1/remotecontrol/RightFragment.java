package com.example.zhangyx1.remotecontrol;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhangyx1.remotecontrol.R;

/**
 * Created by zhangyx1 on 2018/2/26.
 */

public class RightFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view  =inflater.inflate(R.layout.right_fragment,container,false);
        return  view;
    }
}
