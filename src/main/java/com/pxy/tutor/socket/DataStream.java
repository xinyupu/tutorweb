package com.pxy.tutor.socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class DataStream {

    private ByteArrayOutputStream _outputStream = null;
    private ByteArrayInputStream _inputStream = null;
    public byte[] datas = null;

    public DataStream() {
        _outputStream = new ByteArrayOutputStream();
    }

    public DataStream(byte[] datas) {
        this.datas = datas;
        _inputStream = new ByteArrayInputStream(datas);
    }

    public Boolean canRead() {
        if (_inputStream != null) {
            return _inputStream.available() > 0;
        }
        return false;
    }

    public byte readByte() throws IOException {
        byte[] buff = new byte[1];
        _inputStream.read(buff);
        return buff[0];
    }

    public int readInt16() throws IOException {
        byte[] buff = new byte[2];
        _inputStream.read(buff);
        int[] buff2 = convert(buff);
        return (buff2[0] << 8) + buff2[1];
    }

    public int readUint() throws IOException {
        byte[] buff = new byte[4];
        _inputStream.read(buff);
        int[] buff2 = convert(buff);
        return (buff2[0] << 24) + (buff2[1] << 16) + (buff2[2] << 8) + buff2[3];
    }

    public long readLong() throws IOException {
        byte[] buff = new byte[8];
        _inputStream.read(buff);
        long num = 0;
        for (int ix = 0; ix < 8; ++ix) {
            num <<= 8;
            num |= (buff[ix] & 0xff);
        }
        return num;
    }

    public byte[] readBtArray(int len) throws IOException {
        byte[] buff = new byte[len];
        _inputStream.read(buff);
        return buff;
    }

    public String readShortString() throws IOException {
        return readShortString("UTF-8");
    }

    public String readShortString(String charsetName) throws IOException {
        int len = readInt16();
        byte[] datas = readBtArray(len);
        String str = new String(datas, charsetName);
        return str;
    }

    public Date readDateTime() throws IOException {
        long tick = readLong();
        return new Date(tick);
    }

    public boolean readBoolean() throws IOException {
        return readByte() == 1;
    }

    public void writeByte(byte b) throws IOException {
        byte[] buff = new byte[1];
        buff[0] = b;
        _outputStream.write(buff);
    }

    public void writeUint(int i) throws IOException {
        byte[] buff = new byte[4];
        buff[0] = (byte) (i >> 24);
        buff[1] = (byte) (i >> 16);
        buff[2] = (byte) (i >> 8);
        buff[3] = (byte) (i >> 0);

        _outputStream.write(buff);
    }

    public void writeLong(long i) throws IOException {
        byte[] buff = new byte[8];

        buff[7] = (byte) (i >> 56);
        buff[6] = (byte) (i >> 48);
        buff[5] = (byte) (i >> 40);
        buff[4] = (byte) (i >> 32);
        buff[3] = (byte) (i >> 24);
        buff[2] = (byte) (i >> 16);
        buff[1] = (byte) (i >> 8);
        buff[0] = (byte) (i >> 0);

        _outputStream.write(buff);
    }

    public void writeInt16(int i) throws IOException {
        byte[] buff = new byte[2];
        buff[0] = (byte) (i >> 8);
        buff[1] = (byte) (i >> 0);
        _outputStream.write(buff);
    }

    public void writeByteArray(byte[] datas) throws IOException {
        _outputStream.write(datas);
    }

    public int WriteShortString(String msg) throws IOException {
        byte[] buff = GetStringBytes(msg);
        writeInt16(buff.length);
        writeByteArray(buff);
        return 2 + buff.length;
    }

    public int WriteShortString(String msg, String format) throws IOException {
        byte[] buff = msg.getBytes(format);
        writeInt16(buff.length);
        writeByteArray(buff);
        return 2 + buff.length;
    }

    public void WriteDateTime(Date date) throws IOException {
        long tick = date.getTime();
    }

    public void writeBoolean(boolean bool) throws IOException {
        writeByte((byte) (bool ? 1 : 0));
    }

    public byte[] GetStringBytes(String msg) throws UnsupportedEncodingException {
        return msg.getBytes("UTF8");
    }

    public byte[] toBytes() throws IOException {
        if (_inputStream != null) {
            byte[] buff = new byte[_inputStream.available()];
            _inputStream.read(buff);
            return buff;
        }
        if (_outputStream != null) {
            byte[] buff = _outputStream.toByteArray();
            return buff;
        }
        return null;
    }

    public void close() {
        try {
            if (_inputStream != null) {
                _inputStream.close();
            }
            if (_outputStream != null) {
                _outputStream.close();
            }
        } catch (Exception e) {

        }
    }

    public int length() {
        if (datas != null)
            return datas.length;
        return 0;
    }

    public int position() {
        if (datas != null && _inputStream != null)
            return datas.length - _inputStream.available();
        return -1;
    }

    private int[] convert(byte[] datas) {
        int[] lst = new int[datas.length];
        for (int i = 0; i < datas.length; i++) {
            lst[i] = datas[i];
            if (datas[i] < 0)
                lst[i] = (int) (datas[i] + 256);
        }
        return lst;
    }

    public static int convertToInt(byte data) {
        if (data > 0)
            return (int) data;
        return (int) (data + 256);
    }

    public static int convertToInt(byte[] buff2) {
        return (buff2[0] << 24) + (buff2[1] << 16) + (buff2[2] << 8) + buff2[3];
    }
}
