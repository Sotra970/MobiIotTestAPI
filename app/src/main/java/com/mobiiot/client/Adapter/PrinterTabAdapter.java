package com.mobiiot.client.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;

import com.mobiiot.client.TabView.Printer.OtherPrintFragment;
import com.mobiiot.client.TabView.Printer.PrintBitmapFragment;
import com.mobiiot.client.TabView.Printer.PrintEscPosFragment;
import com.mobiiot.client.TabView.Printer.PrintTextFragment;

public class PrinterTabAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public PrinterTabAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PrintTextFragment textFragment = new PrintTextFragment();
                return textFragment;
            case 1:
                PrintBitmapFragment bitmapFragment = new PrintBitmapFragment();
                return bitmapFragment;
            case 2:
                OtherPrintFragment otherFragment = new OtherPrintFragment();
                return otherFragment;
            case 3:
                PrintEscPosFragment escPosFragment = new PrintEscPosFragment();
                return escPosFragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}