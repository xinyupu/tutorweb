package com.pxy.tutor.socket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListUtil
{
	public static void removeRange(@SuppressWarnings("rawtypes") List lst, int startIndex, int length)
	{
		for (int i = startIndex; i < startIndex + length; i++)
		{
			lst.remove(0);
		}
	}

	public static List<Byte> addbytesToList(List<Byte> list, byte[] bytes)
	{
		for (byte b : bytes)
		{
			list.add((Byte) b);
		}
		return list;
	}

	public static byte[] listTobyte(List<Byte> list)
	{
		if (list == null || list.size() < 0)
			return null;
		byte[] bytes = new byte[list.size()];
		int i = 0;
		Iterator<Byte> iterator = list.iterator();
		while (iterator.hasNext())
		{
			bytes[i] = iterator.next();
			i++;
		}
		return bytes;
	}

	public static List<Byte> bytesToList(byte[] bytes)
	{
		List<Byte> result = new ArrayList<Byte>();
		for (byte b : bytes)
		{
			result.add(b);
		}
		return result;
	}

	public static byte[] getRange(byte[] bytes, int stratIndex, int length)
	{
		byte[] result = new byte[length];
		for (int i = 0; i < length; i++)
		{
			result[i] = bytes[stratIndex + i];
		}
		return result;
	}

	public static String getString(byte[] datas)
	{
		String str = "";
		for (byte bt : datas)
		{
			String hex = Integer.toHexString(bt & 0xFF);
			if (hex.length() == 1)
				hex = "0" + hex;
			str += hex + " ";
		}
		str = str.trim();
		return str;
	}
}
