package com.sagereal.io_service;
 interface IOInterface {
 
boolean initSamCardRedear(int cardNumber); 
boolean closeSamCardRedear(int cardNumber); 
boolean sendDataToSamCard(int cardNumber , in byte[] dataFrame, int length);
byte [] receiveDataFromSamCard( int cardNumber);
}
