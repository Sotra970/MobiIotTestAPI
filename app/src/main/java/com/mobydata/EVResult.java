package com.mobydata;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class EVResult implements Parcelable {
    private int stringLength;
    private String stringValue;
    private int symbology;
    private int subtype;
    private String AimID;
    private String box;
    private String center;
    private int linkage;
    private int errors;
    private int protection;
    private int boxOrientation;
    private int inverseCode;
    private int frameID;
    private int rowsSymbolSize;
    private int colsSymbolSize;
    private int checkDigitType;
    private int decoderType;
    private int decoderIndex;
    private int decodeTime;
    private String partOfMultipleResult;


	public EVResult(EVResult result) {
		this.stringLength = result.stringLength;
		this.stringValue = result.stringValue;
		this.symbology = result.symbology;
		this.subtype = result.subtype;
		this.AimID = result.AimID;
		this.box = result.box;
		this.center = result.center;
		this.linkage = result.linkage;
		this.errors = result.errors;
		this.protection = result.protection;
		this.boxOrientation = result.boxOrientation;
		this.inverseCode = result.inverseCode;
		this.frameID = result.frameID;
		this.rowsSymbolSize = result.rowsSymbolSize;
		this.colsSymbolSize = result.colsSymbolSize;
		this.checkDigitType = result.checkDigitType;
		this.decoderType = result.decoderType;
		this.decoderIndex = result.decoderIndex;
		this.decodeTime = result.decodeTime;
		this.partOfMultipleResult = result.partOfMultipleResult;
	}

    public EVResult(){
        this.stringLength = 0;
        this.stringValue = null;
        this.symbology = 0;
        this.subtype = 0;
        this.AimID = null;
        this.box = null;
        this.center = null;
        this.linkage = 0;
        this.errors = 0;
        this.protection = 0;
        this.boxOrientation = 0;
        this.inverseCode = 0;
        this.frameID = 0;
        this.rowsSymbolSize = 0;
        this.colsSymbolSize = 0;
        this.checkDigitType = 0;
        this.decoderType = 0;
        this.decoderIndex = 0;
        this.decodeTime = 0;
        this.partOfMultipleResult = null;
    }

    public EVResult(int stringLength, String stringValue, int symbology, int subtype, String AimID, String box, String center, int linkage, int errors, int protection, int boxOrientation, int inverseCode, int frameID, int rowsSymbolSize, int colsSymbolSize, int checkDigitType, int decoderType, int decoderIndex, int decodeTime, String partOfMultipleResult) {
        this.stringLength = stringLength;
        this.stringValue = stringValue;
        this.symbology = symbology;
        this.subtype = subtype;
        this.AimID = AimID;
        this.box = box;
        this.center = center;
        this.linkage = linkage;
        this.errors = errors;
        this.protection = protection;
        this.boxOrientation = boxOrientation;
        this.inverseCode = inverseCode;
        this.frameID = frameID;
        this.rowsSymbolSize = rowsSymbolSize;
        this.colsSymbolSize = colsSymbolSize;
        this.checkDigitType = checkDigitType;
        this.decoderType = decoderType;
        this.decoderIndex = decoderIndex;
        this.decodeTime = decodeTime;
        this.partOfMultipleResult = partOfMultipleResult;
    }

    //public static final Parcelable.Creator<APN> CREATOR = new Parcelable.Creator<APN>() {}
    public static final Creator<EVResult> CREATOR = new Creator<EVResult>() {
        @Override
        public EVResult createFromParcel(Parcel in) {
            Log.d("sun", "EVResult : - createFromParcel");
            return new EVResult(in.readInt(),    in.readString(), in.readInt(),
                                in.readInt(),    in.readString(), in.readString(),
                                in.readString(), in.readInt(),    in.readInt(),
                                in.readInt(),    in.readInt(),    in.readInt(),
                                in.readInt(),    in.readInt(),    in.readInt(),
                                in.readInt(),    in.readInt(),    in.readInt(),
                                in.readInt(),    in.readString());
        }

        @Override
        public EVResult[] newArray(int size) {
            return new EVResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.e("sun", "service :  - writeToParcel");
        dest.writeInt(stringLength);
        dest.writeString(stringValue);
        dest.writeInt(symbology);
        dest.writeInt(subtype);
        dest.writeString(AimID);
        dest.writeString(box);
        dest.writeString(center);
        dest.writeInt(linkage);
        dest.writeInt(errors);
        dest.writeInt(protection);
        dest.writeInt(boxOrientation);
        dest.writeInt(inverseCode);
        dest.writeInt(frameID);
        dest.writeInt(rowsSymbolSize);
        dest.writeInt(colsSymbolSize);
        dest.writeInt(checkDigitType);
        dest.writeInt(decoderType);
        dest.writeInt(decoderIndex);
        dest.writeInt(decodeTime);
        dest.writeString(partOfMultipleResult);
    }

    public int getStringLength() {
        return stringLength;
    }

    public void setStringLength(int StringLength) {
        this.stringLength = stringLength;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public int getSymbology() {
        return symbology;
    }

    public void setSymbology(int symbology) {
        this.symbology = symbology;
    }

    public int getSubtype() {
        return subtype;
    }

    public void setSubtype(int subtype) {
        this.subtype = subtype;
    }

    public String getAimID() {
        return AimID;
    }

    public void setAimID(String AimID) {
        this.AimID = AimID;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public int getLinkage() {
        return linkage;
    }

    public void setLinkage(int linkage) {
        this.linkage = linkage;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public int getBoxOrientation() {
        return boxOrientation;
    }

    public void setBoxOrientation(int boxOrientation) {
        this.boxOrientation = boxOrientation;
    }

    public int getInverseCode() {
        return inverseCode;
    }

    public void setInverseCode(int inverseCode) {
        this.inverseCode = inverseCode;
    }

    public int getFrameID() {
        return frameID;
    }

    public void setFrameID(int frameID) {
        this.frameID = frameID;
    }

    public int getRowsSymbolSize() {
        return rowsSymbolSize;
    }

    public void setRowsSymbolSize(int rowsSymbolSize) {
        this.rowsSymbolSize = rowsSymbolSize;
    }

    public int getColsSymbolSize() {
        return colsSymbolSize;
    }

    public void setColsSymbolSize(int colsSymbolSize) {
        this.colsSymbolSize = colsSymbolSize;
    }

    public int getCheckDigitType() {
        return checkDigitType;
    }

    public void setCheckDigitType(int checkDigitType) {
        this.checkDigitType = checkDigitType;
    }

    public int getDecoderType() {
        return decoderType;
    }

    public void setDecoderType(int decoderType) {
        this.decoderType = decoderType;
    }

    public int getDecoderIndex() {
        return decoderIndex;
    }

    public void setDecoderIndex(int decoderIndex) {
        this.decoderIndex = decoderIndex;
    }

    public int getDecodeTime() {
        return decodeTime;
    }

    public void setDecodeTime(int decodeTime) {
        this.decodeTime = decodeTime;
    }

    public String getPartOfMultipleResult() {
        return partOfMultipleResult;
    }

    public void setPartOfMultipleResult(String partOfMultipleResult) {
        this.partOfMultipleResult = partOfMultipleResult;
    }


    @Override
    public String toString() {
        return "Code: " + stringValue + ", len: " + stringLength
                 + "\nSymbology: " + symbology + ", Subtype: " + subtype + ", AIM: " + AimID
                 + "\nBox: " + box + ", Center: " + center + "\nLinkage: " + linkage
                 + ", Wait for next result: " + partOfMultipleResult + "\nBoxOrientation: " + boxOrientation
                 + ", InverseCode: " + inverseCode + ", FrameID: " + frameID
                 + "\nDecoder type: " + decoderType  + ", index: " + decoderIndex + ", Decode time: " + decodeTime;
    }
}

