package com.example.zhangyx1.remotecontrol;

import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by zhangyx1 on 2018/2/26.
 */

public class Socket_Tcp extends Observable implements Runnable{


    String SERVER_IP;
    int SERVER_PORT;
    //网络相关变量
    //最大帧长
    private static final int MAX_DATA_PACKET_LENGTH = 20;

    //端口
    Socket Tcp_Socket;
    //输入流
    InputStream Buffer_Receive_Stream;
    //网络连接标志
    private boolean Flag_Connect = false;
    //接收数据缓存
    private byte[] Buffer_Receive = new byte[MAX_DATA_PACKET_LENGTH];
    //接收数据包
    private DatagramPacket Packet_Receive;

    /**
     * 构造函数
     */
    public  Socket_Tcp(String IP,int Port){
        Packet_Receive = new DatagramPacket(Buffer_Receive, MAX_DATA_PACKET_LENGTH);
        SERVER_IP=IP;SERVER_PORT=Port;
    }
    /**
     * @brief 发送数据
     * @param data:需要发送的数据
     * @param len:数据字节数据
     */
    public synchronized void send(final byte[] data, final int len) {
        //判断是否连接服务器
        if (!Flag_Connect) {
            return;
        }

        //发送
        Thread_Send thread_send = new Thread_Send(data, len);
        new Thread(thread_send).start();
    }
    @Override
    public void run() {
        //连接服务器
        //端口
        try {
            Tcp_Socket = new Socket(SERVER_IP, SERVER_PORT);
            Buffer_Receive_Stream = Tcp_Socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //开启监听线程
        try
        {
            new Thread(new Runnable(){
                @Override
                public void run() {
                    while (true) {
                        //判断当前是否连接
                        if (!Flag_Connect)
                        {
                            continue;
                        }

                        //监听
                        try {
                            if (Buffer_Receive_Stream.available() > 0)
                            {
                                Packet_Receive.setLength(Buffer_Receive_Stream.read(Packet_Receive.getData()));
   //                            System.out.println(new String(Packet_Receive.getData(),0,Packet_Receive.getLength()));
                                //通知观察者
                                setChanged();
                                notifyObservers(Packet_Receive);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //检测连接状态
        while (true) {
            Flag_Connect = Tcp_Socket.isConnected();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("连接状态:" + Flag_Connect);
            if (!Flag_Connect) {
                SocketAddress address = new InetSocketAddress(SERVER_IP, SERVER_PORT);
                try {
                    Tcp_Socket.connect(address);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * @brief 发送线程
     */
    private class Thread_Send implements Runnable {
        //输出流
        OutputStream Buffer_Send;
        //发送数据缓存
        private byte[] Buf_Send = new byte[MAX_DATA_PACKET_LENGTH];
        private int Len = 0;

        /**
         * @brief 构造函数
         * @param data:需要发送的数据
         * @param len:数据字节数据
         */
        public Thread_Send(byte[] data, int len) {
            Len = len;
            for (int i = 0;i < Len;i++) {
                Buf_Send[i] = data[i];
            }

            //输出流
            try {
                Buffer_Send = Tcp_Socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                Buffer_Send.write(Buf_Send,0,Len);
                Buffer_Send.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
