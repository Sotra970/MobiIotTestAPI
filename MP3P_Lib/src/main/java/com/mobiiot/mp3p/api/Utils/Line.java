package com.mobiiot.mp3p.api.Utils;

public class Line {

    String data;
    int textSize;
    int textDirectiion;
    int  textFont;
    int alignment;
    boolean isBold;
    boolean isUnderline;

    public Line(String data, int textSize, int textDirectiion, int textFont, int alignment, boolean isBold, boolean isUnderline) {
        this.data = data;
        this.textSize = textSize;
        this.textDirectiion = textDirectiion;
        this.textFont = textFont;
        this.alignment = alignment;
        this.isBold = isBold;
        this.isUnderline = isUnderline;
    }

    public String getData() {
        return data;
    }

    public int getTextSize() {
        return textSize;
    }

    public int getTextDirectiion() {
        return textDirectiion;
    }

    public int getTextFont() {
        return textFont;
    }

    public int getAlignment() {
        return alignment;
    }

    public boolean isBold() {
        return isBold;
    }

    public boolean isUnderline() {
        return isUnderline;
    }
}
