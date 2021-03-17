package com.sagereal.printer;
import android.graphics.Bitmap;

interface PrinterInterface {
 
    //print text
    void printText(String data);
    void printText_isBold(String data,boolean isBold);
    void printText_isUnderline(String data,boolean isUnderline);
    void printText_size(String data, int textSize);
    void printText_font(String data, int textFont);
    void printText_size_font(String data, int textSize,int textFont);
    int printText_FullParm(String data, int textSize, int textDirectiion, int  textFont,int alignment,boolean isBold,boolean isUnderline);
	
	
    //print photo
    void printBitmap(String filePath);
    //void printTextHTML(in Context context, String html);
    void printBitmap_bDate(in byte[] byt);
    void printBitmap_bDate_speed(in byte[] byt, int speed);
	
    //Print blank content,One line
    void printEndLine();
	
    //get the current status of the printer
    int getPrinterStatus();
    //redmine 142912 licong add print screenshot 20180802 begin
    void printBitmap_in(in byte[] byt);
    void printBitmap_inIs(in byte[] byt, int speed);
    //redmine 142912 licong add print screenshot 20180802 end
    int getCurrentVoltageStatus();


    //print text with result
    boolean printText_r(String data);
    boolean printText_bold_r(String data, boolean isBold);
    boolean printText_underline_r(String data, boolean isUnderline);
    boolean printText_size_r(String data, int textSize);
    boolean printText_font_r(String data, int textFont);
    boolean printText_sizefont_r(String data, int textSize, int textFont);
    boolean printText_FullParam_r(String data, int textSize, int textDirection, int textFont, int alignment, boolean isBold, boolean isUnderline);

    //print photo with result
    boolean printBitmap_path_r(String filePath);
    boolean printBitmap_path_speed_r(String filePath, int speed);
    boolean printBitmap_bytes_r(in byte[] byt);
    boolean printBitmap_bytes_speed_r(in byte[] byt, int speed);
    boolean printBitmap_btm_r(in Bitmap bitmap);
    boolean printBitmap_btm_speed_r(in Bitmap bitmap, int speed);

    boolean printEndLine_r();

    void printSetDarkness(int darkness);
    int printGetPrintedLength();

    boolean printText_inverse_r(String data, boolean isInverse);
    boolean printText_FullParam2_r(String data, int textSize, int textDirection, int textFont, int alignment, boolean isBold, boolean isUnderline, boolean isInverse);
    
    boolean printSetSyncMode(boolean sync);

    int getLastError();
}
