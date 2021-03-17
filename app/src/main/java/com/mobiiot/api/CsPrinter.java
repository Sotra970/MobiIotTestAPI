package com.mobiiot.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.text.Html;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.mobiiot.api.Utils.AndroidBmpUtil;
import com.mobiiot.api.Utils.Line;
import com.mobiiot.api.Utils.PrinterServiceUtil;
import com.mobiiot.api.Utils.ServiceUtilIOPrint;
import com.mobiiot.api.Utils.Utils;
import com.mobiwire.printraw.PrintIOInterface;
import com.sagereal.printer.PrinterInterface;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CsPrinter {
    ArrayList<Bitmap> arrayBitmap;
    ArrayList<Line> stack;

    public CsPrinter(){
        arrayBitmap=new ArrayList<>();
        stack=new ArrayList<>();

    }

    public void addTextToStack(String data, int textSize, int textDirectiion, int  textFont, int alignment, boolean isBold, boolean isUnderline){
        stack.add(new Line(data,textSize,textDirectiion,textFont,alignment,isBold,isUnderline));
    }

    boolean stackStatus;
    public boolean printStack(){
        ArrayList<Boolean> listResult=new ArrayList<>();

        try {
            for(int i=0;i<stack.size();i++){
                boolean resultLine = printText_FullParm(stack.get(i).getData(),
                        stack.get(i).getTextSize(),
                        stack.get(i).getTextDirectiion(),
                        stack.get(i).getTextFont(),
                        stack.get(i).getAlignment(),
                        stack.get(i).isBold(),
                        stack.get(i).isUnderline());
                if(getPaperStatus()==true)
                    listResult.add(resultLine);
                else
                    listResult.add(false);

                Thread.sleep(200);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stackStatus=true;
        for(int i=0;i<listResult.size();i++){
            Utils.log("print result : "+i,"========>"+stackStatus);
            if(listResult.get(i)==false)
                stackStatus=false;
        }

        Utils.log("print result","========>"+stackStatus);
        return stackStatus;

    }

    public void addTextToPrint(String text,String textTwo, int textSize, boolean isBold, boolean isUnderline, int align,Typeface ttf){
        Bitmap bitmap = drawText(text, textTwo, textSize,  isBold,  isUnderline,  align,ttf);
        arrayBitmap.add(bitmap);
    }

    public void addBitmapToPrint(Bitmap bitmap){
        arrayBitmap.add(bitmap);
    }

    public void addBitmapFromRawToPrint(Context context, int res){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), res);
        arrayBitmap.add(bitmap);
    }


    /*public void addBarQrCodeToPrint(String str,com.google.zxing.BarcodeFormat type,int bmpWidth,int bmpHeight){
        try {
            Bitmap bitmap= createBarQrCode(str,type,bmpWidth,bmpHeight);
            arrayBitmap.add(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }*/

    public boolean print(boolean withMargin){
        Bitmap last=createSingleImageFromMultipleImages(arrayBitmap);
        Utils.log("print",last.getHeight()+" - "+last.getWidth());
        try {
            AndroidBmpUtil.save(last,"/sdcard/bmptoprint.bmp");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        //CsPrinter.printBitmap(this, last);
        boolean result = CsPrinter.printBitmap(last);
        this.arrayBitmap = new ArrayList();
        if(withMargin){
            CsPrinter.printEndLine();
            CsPrinter.printEndLine();
        }

        return result;
    }

    public static boolean printText(final String data){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return false;

            } else {
                Utils.log(MobiiotAPI.TAG, "print text");
                return printInterfaceService.printText_r(data);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return false;

        }
    }


    public static boolean printText_isBold_r(final String data, final boolean isBold){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return false;

            } else {
                Utils.log(MobiiotAPI.TAG, "print text bold");
                return printInterfaceService.printText_bold_r(data,isBold);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return false;

        }
    }

    public static boolean printText_isUnderline(final String data, final boolean isUnderline){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return false;
            } else {

                Utils.log(MobiiotAPI.TAG, "print text undeline");
                return printInterfaceService.printText_underline_r(data,isUnderline);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return false;

        }

    }


    public static boolean printText_FullParm( final String data, final int textSize, final int textDirectiion, final int  textFont, final int alignment, final boolean isBold, final boolean isUnderline){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return false;
            } else {
                Utils.log(MobiiotAPI.TAG, "print text full param");
                return printInterfaceService.printText_FullParam_r(data,textSize,textDirectiion,textFont,alignment,isBold,isUnderline);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return false;

        }

    }


    public static int getPrinterStatus(){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");

            } else {
                int value = printInterfaceService.getPrinterStatus();
                Utils.log(MobiiotAPI.TAG, "printer status : "+ value);
                return value;
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;

        }
        return -1;
    }

    public static int getCurrentVoltageStatus(){

        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");

            } else {
                int value = printInterfaceService.getCurrentVoltageStatus();
                Utils.log(MobiiotAPI.TAG, "current voltage status : "+ value);
                return value;
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;

        }
        return -1;
    }




    public static boolean printText_size(final String data, final int textSize){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");

                return false;
            } else {
                Utils.log(MobiiotAPI.TAG, "print text size");
                return printInterfaceService.printText_size_r(data,textSize);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return false;

        }
    }


    public static boolean printText_font(final String data, final int textFont){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return false;
            } else {
                Utils.log(MobiiotAPI.TAG, "print text font");
                return printInterfaceService.printText_font_r(data,textFont);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;

        }
    }
    public static boolean printText_size_font(final String data, final int textSize, final int textFont){

        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return false;
            } else {
                Utils.log(MobiiotAPI.TAG, "print text font");
                return printInterfaceService.printText_sizefont_r(data,textSize,textFont);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;

        }

    }

    public static boolean printBitmap(final String filePath){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return false;
            } else {
                Utils.log(MobiiotAPI.TAG, "print bitmap");
                return printInterfaceService.printBitmap_path_r(filePath);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean printBitmap(final String filePath,int speed){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return false;
            } else {
                Utils.log(MobiiotAPI.TAG, "print bitmap");
                return printInterfaceService.printBitmap_path_speed_r(filePath,speed);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*public static void printBitmap(final String filePath){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(AndroidGoCSApi.TAG, "service printer is KO");

            } else {

                File file=new File(filePath);

                int size = (int) file.length();
                byte[] bytes = new byte[size];
                try {
                    BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
                    buf.read(bytes, 0, bytes.length);
                    buf.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                printInterfaceService.printBitmap_bDate(bytes);
                Utils.log(AndroidGoCSApi.TAG, "print bitmap");
            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }

    }*/




    //void printTextHTML(in Context context, String html);

    //yyy :11
    public static boolean printBitmap(final byte[] byt){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return false;

            } else {

                Utils.log(MobiiotAPI.TAG, "print bitmap byte");
                return printInterfaceService.printBitmap_bytes_r(byt);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean printBitmap(final byte[] byt, final int speed){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return false;
            } else {

                Utils.log(MobiiotAPI.TAG, "print bitmap byte with speed");
                return printInterfaceService.printBitmap_bytes_speed_r(byt,speed);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }


    }

    public static boolean printBitmap(final Bitmap bitmap){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return false;

            } else {

                Utils.log(MobiiotAPI.TAG, "print bitmap bitmap");
                return printInterfaceService.printBitmap_btm_r(bitmap);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean printBitmap(final Bitmap bitmap, final int speed){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return false;
            } else {

                Utils.log(MobiiotAPI.TAG, "print bitmap bitmap with speed");
                return printInterfaceService.printBitmap_btm_speed_r(bitmap,speed);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }


    }

    public static boolean printEndLine(){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return false;
            } else {
                Utils.log(MobiiotAPI.TAG, "print end line");
                return printInterfaceService.printEndLine_r();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }


    /*public static void printBitmap_in(final byte[] byt){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(AndroidGoCSApi.TAG, "service printer is KO");

            } else {
                printInterfaceService.printBitmap_in(byt);
                Utils.log(AndroidGoCSApi.TAG, "print bitmap");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void printBitmap_inIs(Context mContext, final byte[] byt, final int speed){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(AndroidGoCSApi.TAG, "service printer is KO");

            } else {
                printInterfaceService.printBitmap_inIs(byt,speed);
                Utils.log(AndroidGoCSApi.TAG, "print bitmap with speed");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }*/

    public static void printTextHTML(final Context context, String html) {
        Utils.log("ismail", "printTextFormat");
        TextView view = new TextView(context);
        view.setTextColor(-16777216);
        view.setBackgroundColor(-1);
        view.setText(Html.fromHtml(html, new Html.ImageGetter() {
            public Drawable getDrawable(String source) {
                int dourceId = context.getResources().getIdentifier(source, "drawable", context.getPackageName());
                Drawable drawable = context.getResources().getDrawable(dourceId);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                return drawable;
            }
        }, (Html.TagHandler) null));
        view.setDrawingCacheEnabled(true);
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(view.getDrawingCache());
        Bitmap b2 = Bitmap.createBitmap(384, 384, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(b2);
        canvas.drawColor(-1);
        canvas.drawBitmap(getResizedBitmap(b, b.getWidth(), 384), 0.0F, 0.0F, (Paint) null);
        view.setDrawingCacheEnabled(false);
        Bitmap bitm=getResizedBitmap(b2, 384, 240);

        printBitmap(bitm);

    }

    /*public static void printBitmap(Context context, Bitmap bitmap){
        try {
            AndroidBmpUtil.save(bitmap,"/sdcard/test3.bmp");

            byte[] inputStreamToByte = null;
            FileInputStream is_black = new FileInputStream("/sdcard/test3.bmp");
            inputStreamToByte = InputStreamToByte(is_black);

            printBitmap_bDate_speed(inputStreamToByte, 2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

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

    public static byte[] getByteArray(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        return bos.toByteArray();
    }


    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = (float)newWidth / (float)width;
        float scaleHeight = (float)newHeight / (float)height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

    public static File savebitmap(Bitmap bmp) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        File f = new File("/sdcard/test1.bmp");
        f.createNewFile();
        FileOutputStream fo = new FileOutputStream(f);
        fo.write(bytes.toByteArray());
        fo.close();
        return f;
    }

    public Bitmap drawText(String text,String textTwo, int textSize, boolean isBold, boolean isUnderline, int align,Typeface ttf) {


        int textWidth=384;

        if(textTwo!=null){
            int llen=20,rlen=20;
            String text1=padRight(text,rlen);
            String text2=padLeft(textTwo,llen);
            text=text1+text2;
        }


        TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG
                | Paint.LINEAR_TEXT_FLAG);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTextSize(textSize);
        textPaint.setFakeBoldText(isBold);
        textPaint.setTypeface(ttf);
        if(isUnderline)
            textPaint.setFlags(Paint.UNDERLINE_TEXT_FLAG);


        StaticLayout mTextLayout = new StaticLayout(text, textPaint,
                textWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);


        if(align==0)
            mTextLayout = new StaticLayout(text, textPaint,
                    textWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        else if(align==1)
            mTextLayout = new StaticLayout(text, textPaint,
                    textWidth, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        else if(align==2)
            mTextLayout = new StaticLayout(text, textPaint,
                    textWidth, Layout.Alignment.ALIGN_OPPOSITE, 1.0f, 0.0f, false);


        Bitmap b = Bitmap.createBitmap(textWidth, mTextLayout.getHeight(), Bitmap.Config.RGB_565);
        Canvas c = new Canvas(b);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG
                | Paint.LINEAR_TEXT_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        c.drawPaint(paint);

        c.save();
        c.translate(0, 0);
        mTextLayout.draw(c);
        c.restore();

        return b;
    }

    public String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
        //String.format("%10s", "foo");
    }

    private Bitmap createSingleImageFromMultipleImages(ArrayList<Bitmap> listBitmap){

        int hight=0;
        for(int i=0;i<listBitmap.size();i++){
            hight=hight+listBitmap.get(i).getHeight();
        }

        Bitmap result = Bitmap.createBitmap(listBitmap.get(0).getWidth(), hight, listBitmap.get(0).getConfig());
        Canvas canvas = new Canvas(result);
        int hightToDraw=0;
        for(int i=0;i<listBitmap.size();i++){
            canvas.drawBitmap(listBitmap.get(i), 0f, hightToDraw, null);
            hightToDraw=hightToDraw+listBitmap.get(i).getHeight();

        }
        return result;
    }

    public static Bitmap createBarQrCode(String str,com.google.zxing.BarcodeFormat type,int bmpWidth,int bmpHeight) throws WriterException {
        BitMatrix matrix = null;
        try {
            matrix = new MultiFormatWriter().encode(str, type,bmpWidth,bmpHeight);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if(matrix.get(x, y)){
                    pixels[y * width + x] = 0xff000000;
                }else{
                    pixels[y * width + x] = 0xffffffff;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    public static boolean getPaperStatus() {
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return false;
            } else {
                int printerStatus = printInterfaceService.getPrinterStatus();
                Utils.log(MobiiotAPI.TAG, "printer status : "+ printerStatus);
                if (printerStatus == 1 || printerStatus == 0 || printerStatus == 16 || printerStatus == 17) {
                    if (printerStatus == 1 || printerStatus == 17) {
                        return true;
                    } else if (printerStatus == 16 || printerStatus == 0 ) {
                        return false;
                    }

                } else {
                    Utils.log(MobiiotAPI.TAG, "The printer status is KO, you can restart the device!");
                    return false;
                }

            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return false;

        }
        return false;
    }

    public static boolean getTempStatus() {
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return false;
            } else {
                int printerStatus = printInterfaceService.getPrinterStatus();
                Utils.log(MobiiotAPI.TAG, "printer status : "+ printerStatus);
                if (printerStatus == 1 || printerStatus == 0 || printerStatus == 16 || printerStatus == 17) {
                    if (printerStatus == 1) {
                        return true;
                    } else if (printerStatus == 17 || printerStatus == 16) {
                        return false;
                    }

                } else {
                    Utils.log(MobiiotAPI.TAG, "The printer status is KO, you can restart the device!");
                    return false;
                }

            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return false;

        }
        return false;
    }


    public static void printSetDarkness(int darkness){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
            } else {

                Utils.log(MobiiotAPI.TAG, "printSetDarkness : "+darkness);
                printInterfaceService.printSetDarkness(darkness);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    public static int printGetPrintedLength(){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return -1;
            } else {

                Utils.log(MobiiotAPI.TAG, "printGetPrintedLength");
                return printInterfaceService.printGetPrintedLength();
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }


    }

    public static Boolean printText_inverse_r(String data, boolean isInverse){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return null;
            } else {

                Utils.log(MobiiotAPI.TAG, "printGetPrintedLength");
                return printInterfaceService.printText_inverse_r(data, isInverse);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }


    }

    public static Boolean printText_FullParam2_r(String data, int textSize, int textDirection, int textFont, int alignment, boolean isBold, boolean isUnderline, boolean isInverse){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return null;
            } else {

                Utils.log(MobiiotAPI.TAG, "printText_FullParam2_r");
                return printInterfaceService.printText_FullParam2_r(data,textSize,textDirection,textFont,alignment,isBold,isUnderline, isInverse);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void printText_FullParam(String data, int textSize, int textDirection, int textFont, int alignment, boolean isBold, boolean isUnderline){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
            } else {

                Utils.log(MobiiotAPI.TAG, "printText_FullParam2_r");
                printInterfaceService.printText_FullParm(data,textSize,textDirection,textFont,alignment,isBold,isUnderline);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static Boolean printSetSyncMode(boolean sync){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return null;
            } else {

                Utils.log(MobiiotAPI.TAG, "printSetSyncMode");
                return printInterfaceService.printSetSyncMode(sync);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }


    }

    public static int getLastError(){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return -1;
            } else {

                Utils.log(MobiiotAPI.TAG, "printText_FullParam2_r");
                return printInterfaceService.getLastError();
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }


    }

    public static Boolean powerOn(boolean onoff){
        try {
            PrintIOInterface printInterfaceService = ServiceUtilIOPrint.getiMyAidlInterface();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return null;
            } else {

                Utils.log(MobiiotAPI.TAG, "powerOn");
                return printInterfaceService.powerOn(onoff);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }


    }
    public static byte[] transmit( byte[] sendBuf, int sendLen){
        try {
            PrintIOInterface printInterfaceService = ServiceUtilIOPrint.getiMyAidlInterface();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return null;
            } else {
                Utils.log(MobiiotAPI.TAG, "transmit");
                return printInterfaceService.transmit(sendBuf,sendLen);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }


    }




    public static Boolean getPowerState(){
        try {
            PrintIOInterface printInterfaceService = ServiceUtilIOPrint.getiMyAidlInterface();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
                return null;
            } else {

                Utils.log(MobiiotAPI.TAG, "getPowerState");
                return printInterfaceService.getPowerState();
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }


    }

    public static void printBitmapMPE(final byte[] byt, final int speed){
        try {
            PrinterInterface printInterfaceService = PrinterServiceUtil.getPrinterService();
            if (printInterfaceService == null) {
                Utils.log(MobiiotAPI.TAG, "service printer is KO");
            } else {

                Utils.log(MobiiotAPI.TAG, "print bitmap byte with speed");
                 printInterfaceService.printBitmap_bDate_speed(byt,speed);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
}
