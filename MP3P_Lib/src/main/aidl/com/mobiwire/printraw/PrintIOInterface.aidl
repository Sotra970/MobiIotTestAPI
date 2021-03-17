package com.mobiwire.printraw;

interface PrintIOInterface {
 
    boolean powerOn(boolean onoff);
    byte[] transmit(in byte[] sendBuf, int sendLen);

    boolean getPowerState();
    
}
