package com.mobiiot.mp3p.api.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtils {

	// 等比缩放图片
    public static Bitmap zoomImg(Bitmap bm, int newWidth , int newHeight){
       // 获得图片的宽高
       int width = bm.getWidth();
       int height = bm.getHeight();
       // 计算缩放比例
       float scaleWidth = ((float) newWidth) / width;
       float scaleHeight = ((float) newHeight) / height;
       // 取得想要缩放的matrix参数
       Matrix matrix = new Matrix();
       matrix.postScale(scaleWidth, scaleHeight);
       // 得到新的图片
       Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }
	
	
	
	/**
	 * 将Bitmap存为 .bmp格式图片
	 * 
	 * @param bitmap
	 */

	private static final String path = "test.bmp";
	private static final String PATH = "file:///sdcard/" + path;
	private static final Uri imageUri = Uri.parse(PATH);

	public static void saveBmp(Context mcontent, Bitmap bitmap) {
		if (bitmap == null)
			return;
		// 位图大小
		int nBmpWidth = bitmap.getWidth();
		int nBmpHeight = bitmap.getHeight();
		// 图像数据大小
		int bufferSize = nBmpHeight * (nBmpWidth * 3 + nBmpWidth % 4);
		try {
			// 存储文件名
			String filename = getPathFromUri(mcontent, imageUri);
			android.util.Log.e("jiangcunbin", "photo save path : " + filename);
			File file = new File(filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fileos = new FileOutputStream(filename);
			// bmp文件头
			int bfType = 0x4d42;
			long bfSize = 14 + 40 + bufferSize;
			int bfReserved1 = 0;
			int bfReserved2 = 0;
			long bfOffBits = 14 + 40;
			// 保存bmp文件头
			writeWord(fileos, bfType);//2
			writeDword(fileos, bfSize);//4
			writeWord(fileos, bfReserved1);//2
			writeWord(fileos, bfReserved2);//2
			writeDword(fileos, bfOffBits);//4
			// bmp信息头
			long biSize = 40L;
			long biWidth = nBmpWidth;
			long biHeight = nBmpHeight;
			int biPlanes = 1;
			int biBitCount = 24;
			long biCompression = 0L;
			long biSizeImage = 3 * 384 * 384L;
			long biXpelsPerMeter = 0L;
			long biYPelsPerMeter = 0L;
			long biClrUsed = 0L;
			long biClrImportant = 0L;
			// 保存bmp信息头
			writeDword(fileos, biSize);//4
			writeLong(fileos, biWidth);//4
			writeLong(fileos, biHeight);//4
			writeWord(fileos, biPlanes);//2
			writeWord(fileos, biBitCount);//2
			writeDword(fileos, biCompression);//4
			writeDword(fileos, biSizeImage);//4
			writeLong(fileos, biXpelsPerMeter);//4
			writeLong(fileos, biYPelsPerMeter);//4
			writeDword(fileos, biClrUsed);//4
			writeDword(fileos, biClrImportant);//4
			// 像素扫描
			byte bmpData[] = new byte[bufferSize];
			int wWidth = (nBmpWidth * 3 + nBmpWidth % 4);
			for (int nCol = 0, nRealCol = nBmpHeight - 1; nCol < nBmpHeight; ++nCol, --nRealCol)
				for (int wRow = 0, wByteIdex = 0; wRow < nBmpWidth; wRow++, wByteIdex += 3) {
					int clr = bitmap.getPixel(wRow, nCol);
					bmpData[nRealCol * wWidth + wByteIdex] = (byte) Color.blue(clr);
					bmpData[nRealCol * wWidth + wByteIdex + 1] = (byte) Color.green(clr);
					bmpData[nRealCol * wWidth + wByteIdex + 2] = (byte) Color.red(clr);
				}

			fileos.write(bmpData);
			fileos.flush();
			fileos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected static void writeWord(FileOutputStream stream, int value)
			throws IOException {
		byte[] b = new byte[2];
		b[0] = (byte) (value & 0xff);
		b[1] = (byte) (value >> 8 & 0xff);
		stream.write(b);
	}

	protected static void writeDword(FileOutputStream stream, long value)
			throws IOException {
		byte[] b = new byte[4];
		b[0] = (byte) (value & 0xff);
		b[1] = (byte) (value >> 8 & 0xff);
		b[2] = (byte) (value >> 16 & 0xff);
		b[3] = (byte) (value >> 24 & 0xff);
		stream.write(b);
	}

	protected static void writeLong(FileOutputStream stream, long value)
			throws IOException {
		byte[] b = new byte[4];
		b[0] = (byte) (value & 0xff);
		b[1] = (byte) (value >> 8 & 0xff);
		b[2] = (byte) (value >> 16 & 0xff);
		b[3] = (byte) (value >> 24 & 0xff);
		stream.write(b);
	}

	public static String getPathFromUri(final Context context, final Uri uri) {
		if (null == uri) {
			return null;
		}
		final String scheme = uri.getScheme();
		String path = null;
		if (scheme == null)
			path = uri.getPath();
		else {
			if (ContentResolver.SCHEME_FILE.equals(scheme)) {
				path = uri.getPath();
			} else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
				Cursor cursor = context.getContentResolver().query(uri,
						new String[] { MediaStore.Images.ImageColumns.DATA },
						null, null, null);
				if (null != cursor) {
					if (cursor.moveToFirst()) {
						int index = cursor
								.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
						if (index > -1) {
							path = cursor.getString(index);
						}
					}
					cursor.close();
				}
			}
		}
		return path;
	}
}
