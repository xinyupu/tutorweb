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
    private Selector selector = null;
    private ServerSocketChannel serverSocketChannel = null;
    private int port = 8000;
    private Charset charset = Charset.forName("UTF-8");

    private Map<String, SocketChannel> serverSocketMap;//记录socket的键值对

    public static void main(String[] agr0) {
        new SocketService();
    }

    public SocketService() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ExecutorService service = Executors.newFixedThreadPool(50);
                    serverSocket = new ServerSocket(1503);
                    while (true) {
                        //接收客户端连接的socket对象
                        Socket connection = null;
                        //接收客户端传过来的数据，会阻塞
                        connection = serverSocket.accept();
                        service.execute(new SubPolThread(connection));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
