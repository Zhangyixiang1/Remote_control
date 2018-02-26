package com.example.zhangyx1.remotecontrol;

import java.util.Observable;
import java.util.Observer;
import java.net.DatagramPacket;
/**
 * Created by zhangyx1 on 2018/2/26.
 */

public class Rev_Data implements Observer {
String data;
    DatagramPacket packet;
    @Override
    public void update(Observable o, Object arg) {
        packet=(DatagramPacket)arg;

        data=Comm.byte2HexStr(packet.getData());
        System.out.println(data);
    }
}
