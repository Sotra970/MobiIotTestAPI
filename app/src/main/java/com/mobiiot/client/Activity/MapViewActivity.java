package com.mobiiot.client.Activity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.mobiiot.api.CsLocation;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.GPSTracker;
import com.mobiiot.client.R;
import com.mobiiot.client.model.Param;

import org.osmdroid.config.Configuration;
import org.osmdroid.config.IConfigurationProvider;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.io.File;
import java.util.ArrayList;

public class MapViewActivity extends AppCompatActivity {

    public static MapView mMapView;
    public static MapController mMapController;
    private RadioGroup radioGroupLocationMode;
    private RadioButton radioButtonMode1;
    private RadioButton radioButtonMode2;
    private RadioButton radioButtonMode3;

    private Switch switchLocation;
    private Button buttonSaveLocationMode;
    private LinearLayout layoutLocationMode;
    int selectedRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);Context appContext = getApplicationContext();
        Configuration.getInstance().load(appContext, PreferenceManager.getDefaultSharedPreferences(appContext));
        setContentView(R.layout.activity_map_view);



        selectedRadio=0;
        Typeface type = Typeface.createFromAsset(getAssets(),"CaviarDreams.ttf");
        this.radioGroupLocationMode= (RadioGroup) this.findViewById(R.id.radioGroup_locationMode);
        this.radioButtonMode1 = (RadioButton) this.findViewById(R.id.radioButton_mode1);radioButtonMode1.setTypeface(type);
        this.radioButtonMode2 = (RadioButton) this.findViewById(R.id.radioButton_mode2);radioButtonMode2.setTypeface(type);
        this.radioButtonMode3 = (RadioButton) this.findViewById(R.id.radioButton_mode3);radioButtonMode3.setTypeface(type);
        switchLocation=(Switch)findViewById(R.id.switchLocation) ;switchLocation.setTypeface(type);
        buttonSaveLocationMode=(Button)findViewById(R.id.buttonSaveLocationMode);buttonSaveLocationMode.setTypeface(type);
        layoutLocationMode=(LinearLayout)findViewById(R.id.layoutLocationMode);
        if(Build.VERSION.SDK_INT==29){
            layoutLocationMode.setVisibility(View.GONE);
        }


        Utils.sendActionToServiceWithParams(this,EnumAction.LOCATION_GET_ADDRESS.getAction(),null);
        Utils.sendActionToServiceWithParams(this,EnumAction.LOCATION_GET_LOCATION.getAction(),null);

        Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + GPSTracker.latitude + "\nLong: " + GPSTracker.longitude+"\n"+GPSTracker.address+" - "+GPSTracker.address, Toast.LENGTH_LONG).show();

        radioGroupLocationMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioButton_mode1:
                        selectedRadio=0;
                        break;
                    case R.id.radioButton_mode2:
                        selectedRadio=1;
                        break;
                    case R.id.radioButton_mode3:
                        selectedRadio=2;
                        break;
                }
            }
        });
        buttonSaveLocationMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value=selectedRadio;
                Log.e("location mode",value+"");
                ArrayList<Param> listParam=new ArrayList<>();
                listParam.add(new Param("mode",value));
                Utils.sendActionToServiceWithParams(MapViewActivity.this,EnumAction.LOCATION_SET_MODE.getAction(),listParam);
            }
        });

        switchLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("SWITCH Location 1",""+b);
                if(b)
                    Utils.sendActionToServiceWithParams(MapViewActivity.this,EnumAction.LOCATION_ENABLE.getAction(),null);
                else
                    Utils.sendActionToServiceWithParams(MapViewActivity.this,EnumAction.LOCATION_DISABLE.getAction(),null);
                /*Intent silenterService = new Intent(MapViewActivity.this, ExecuteFunctionsService.class);
                silenterService.setAction(EnumAction.SIM1.getAction());
                silenterService.putExtra("value",b);
                getActivity().startService(silenterService);*/
            }
        });

        mMapView = (MapView) findViewById(R.id.mapview);
        mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);
        mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mMapView.setBuiltInZoomControls(true);

        String address= CsLocation.getAddress();


            mMapController = (MapController) mMapView.getController();
            mMapController.setZoom(10);
            GeoPoint geopoint = new GeoPoint(GPSTracker.latitude, GPSTracker.longitude);
            mMapController.setCenter(geopoint);

        Marker startMarker = new Marker(mMapView);
        mMapController.setCenter(geopoint);
        startMarker.setPosition(geopoint);
        startMarker.setTitle(address+"\n("+GPSTracker.latitude+","+GPSTracker.longitude+")");
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mMapView.getOverlays().add(startMarker);
    }
}
