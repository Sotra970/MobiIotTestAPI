package com.mobiiot.androidqapi.api.Utils;

import android.graphics.Bitmap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOutils {

	/**
	 * 输入流转字节流
	 * */
	public static byte[] InputStreamToByte(InputStream is) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int ch;
		while ((ch = is.read(buffer)) != -1) {
			bytestream.write(buffer, 0, ch);
		}
		byte data[] = bytestream.toByteArray();
		bytestream.close();
		return data;
	}

	/**
	 * bitmap 转 inputStream
	 * 
	 * @param bm
	 * @return
	 */
	public static InputStream bitmap2inputstream(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		InputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		return isBm;

	}

	/**
	 * bitmap 转 字节数据。
	 * 
	 * @param bm
	 * @return
	 */
	public static byte[] bitmap2byteArrays(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] datas = baos.toByteArray();
		return datas;
	}

	// redmine yyy begin
	public static String readPrinterStatus(String path) throws IOException {
		InputStream is = null;
		String result = null;
		try {

			File file = new File(path);

			if (!file.exists()) {
				android.util.Log.e("yanyongyong777", "read partition,The current path does not exist. path is : " + path);
				return null;
			}

			is = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			StringBuilder stringBuilder = new StringBuilder();

			while ((len = is.read(buffer)) > 0) {
				stringBuilder.append(new String(buffer, 0, len, "utf-8"));
			}

			result = stringBuilder.toString();

		} catch (Exception e) {
			android.util.Log.e("yanyongyong", "IOException      " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (is != null) {
				is.close();
			}
		}
		android.util.Log.e("yanyongyong777", "read partition , finally result is： " + result);
		return result;
	}

	// redmine yyy end

	// redmine yyy begin
	/**
	 * 
	 * @return 1: 正常打印
	 * 				0： 盖子打开
	 * 				16：无纸。。高温
	 * 				17：高温，有纸。
	 * 				-1 ：未知异常。
	 * @throws IOException
	 */
	public static int getPrinterStatus() throws IOException {
		InputStream is = null;
		String result = null;
		String path = "/proc/printer";
		int printerStatus = -1;
		try {

			File file = new File(path);

			if (!file.exists()) {
				android.util.Log.e("yanyongyong777", "read partition,The current path does not exist. path is : " + path);
				return -1;
			}

			is = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			StringBuilder stringBuilder = new StringBuilder();

			while ((len = is.read(buffer)) > 0) {
				stringBuilder.append(new String(buffer, 0, len, "utf-8"));
			}

			result = stringBuilder.toString();
			printerStatus = Integer.parseInt(result.trim());

		} catch (Exception e) {
			android.util.Log.e("yanyongyong", "IOException      " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (is != null) {
				is.close();
			}
		}
		android.util.Log.e("yanyongyong777", "read partition , finally result is： " + printerStatus);

		return printerStatus;
	}

	// redmine yyy end

}
