// CSscanner.aidl

package com.mobydata;

import com.mobydata.CSscannerCallback;
import com.mobydata.EVResult;

interface CSscanner {

    void setScannerVibrating(boolean isOpen);

    void setScannerBeep(boolean isOpen);

    boolean scannerHardwareExist();

    void scannerEnable();

    void scannerDisable();

    void scannerDestroy();

    void startScanner();

    void stopScanner();

    void setFlash(boolean isOpen);

    void setScanMode(String mode);

    void getScanResult(in CSscannerCallback cb);

    boolean isScannerOpened();

    boolean setContinuousModeDelay(int delay);

    void SaveScannedCode(boolean isSave, String folderPath);

}