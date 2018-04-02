package com.pxy.tutor.socket;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class SocketService {

    private ServerSocket serverSocket;
    private Socket connection = null;
    private ExecutorService service;


    public static void main(String[] agr0) {
        new SocketService();

    }

    public SocketService() {
        service = Executors.newCachedThreadPool();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(1503);
                    while (true) {
                        //接收客户端连接的socket对象

                        //接收客户端传过来的数据，会阻塞
                        connection = serverSocket.accept();
                        service.execute(new Runnable() {
                            @Override
                            public void run() {
                                new SubPolParse(connection);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
