package com.mobiiot.client.Activity;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobiiot.client.Adapter.PrinterTabAdapter;
import com.mobiiot.client.R;

public class PrinterActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printer);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);



        if(Build.MODEL.contains("MPE")){
            tabLayout.addTab(tabLayout.newTab().setText("Text"));
            tabLayout.addTab(tabLayout.newTab().setText("Bitmap"));
            tabLayout.addTab(tabLayout.newTab().setText("Long ticket"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        }else{
            tabLayout.addTab(tabLayout.newTab().setText("Text"));
            tabLayout.addTab(tabLayout.newTab().setText("Bitmap"));
            tabLayout.addTab(tabLayout.newTab().setText("Sync Mode"));
            tabLayout.addTab(tabLayout.newTab().setText("ESC/POS"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        }



        final PrinterTabAdapter adapter = new PrinterTabAdapter(this,getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}