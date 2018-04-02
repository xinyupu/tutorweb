package com.pxy.tutor.socket;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubPolParse {

    private Socket connection;
    private List<Byte> receiveDatas;
    public static Map<String, SocketConfig> client = new HashMap<>();


    public SubPolParse(Socket conSocket) {
        this.connection = conSocket;
        receiveDatas = new ArrayList<>();
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

    /**
     * 读取客户端信息
     *
     * @param inputStream
     */
    private void readMessageFromClient(InputStream inputStream) throws IOException {
        int available = inputStream.available();
        byte[] data = new byte[1024];
        inputStream.read(data);
        List<Byte> bytes = ListUtil.bytesToList(data);
        receiveDatas.addAll(bytes);
        parseCommand();

    }

    int i = 0;

    private void parseCommand() {
        while (receiveDatas.size() > 0) {
            if (receiveDatas.get(0) != -86) {
                receiveDatas.remove(0);
            } else {
                try {
                    byte[] bytes = ListUtil.listTobyte(receiveDatas);
                    DataStream stream = new DataStream(bytes);
                    byte head = stream.readByte();
                    int length = stream.readUint();
                    boolean isTutor = stream.readBoolean();
                    String NO = stream.readShortString();
                    String targetNo = stream.readShortString();
                    String Content = stream.readShortString();
                    String hostIp = stream.readShortString();
                    System.out.println(Content);
                    byte foot = stream.readByte();
                    int totalLength = 1 + 4 + length + 1;
                    byte[] parseData = ListUtil.getRange(ListUtil.listTobyte(receiveDatas), 0, totalLength);
                    receiveDatas.clear();
                    client.remove(NO);
                    SocketConfig config = new SocketConfig();
                    config.ip =hostIp;
                    config.port = 1504;
                    client.put(NO, config);
                    if (!targetNo.equals("0")) {
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
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
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


}
