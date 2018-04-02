package com.pxy.tutor.socket;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class SubPolThread implements Runnable {
    private Socket connection;
    private List<Byte> receiveDatas;
    public static Map<String, SocketConfig> client = new HashMap<>();

    public SubPolThread(Socket conSocket) {
        this.connection = conSocket;
        receiveDatas = new ArrayList<>();

    }

    /**
     * 读取客户端信息
     *
     * @param inputStream
     */
    private void readMessageFromClient(InputStream inputStream) throws IOException {

        int available = inputStream.available();
        byte[] data = new byte[available];
        inputStream.read(data);
        List<Byte> bytes = ListUtil.bytesToList(data);
        receiveDatas.addAll(bytes);
        parseCommand();

    }


    private void parseCommand() {
        while (receiveDatas.size() > 0) {
            if (receiveDatas.get(0) != -86) {
                receiveDatas.remove(0);
                return;
            }
            byte[] bytes = ListUtil.listTobyte(receiveDatas);
            DataStream stream = new DataStream(bytes);
            try {
                byte head = stream.readByte();
                int length = stream.readUint();
                boolean isTutor = stream.readBoolean();
                String NO = stream.readShortString();
                String targetNo = stream.readShortString();
                String Content = stream.readShortString();
                byte foot = stream.readByte();
                int totalLength = 1 + 4 + length + 1;
                byte[] parseData = ListUtil.getRange(ListUtil.listTobyte(receiveDatas), 0, totalLength);
                System.out.println(Content);
                ListUtil.removeRange(receiveDatas, 0, totalLength);
                client.remove(NO);
                SocketConfig config = new SocketConfig();
                config.ip = connection.getInetAddress().getHostAddress();
                config.port = 1504;
                client.put(NO, config);
                SocketConfig socketConfig = client.get(targetNo);
                if (socketConfig != null) {
                    try {
                        Socket socket = new Socket(socketConfig.ip, socketConfig.port);
                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write(parseData);
                        outputStream.flush();
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 响应客户端信息
     *
     * @param outputStream
     * @param string
     */
    private void writeMsgToClient(OutputStream outputStream, String string) throws IOException {
        Writer writer = new OutputStreamWriter(outputStream);
        writer.append("I am server message!!!");
        writer.flush();
        writer.close();
    }


    @Override
    public void run() {
        try {
            readMessageFromClient(connection.getInputStream());
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
