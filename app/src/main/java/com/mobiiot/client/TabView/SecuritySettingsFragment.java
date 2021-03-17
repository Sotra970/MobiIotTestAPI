package com.mobiiot.client.TabView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.mobiiot.client.Activity.Utils;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.R;
import com.mobiiot.client.model.Param;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class SecuritySettingsFragment extends Fragment {
    String TAG="SecuritySettingsFragment";

    View v;
    Typeface type;
    Button buttonHideSettings,buttonShowSettings;
    ArrayList<Integer> listSettingsToHide;
    int[]arraySettingsToHide;
    Switch switchHideStatusBar,switchBlockControlPanel;
    LinearLayout layoutListSettingsItems;

    ArrayList<SecurityItem> listSecurityItem;


    //Network & Internet
    private static final String WIFI_KEY="toggle_wifi";
    private static final String MOBILE_NETWORK_KEY="mobile_network_settings";
    private static final String DATA_USAGE_KEY="dashboard_tile_pref_com.android.settings.Settings$DataUsageSummaryActivity";
    private static final String HOTSPOT_TETHERING_KEY="tether_settings";
    private static final String SIM_CARDS_KEY="dashboard_tile_pref_com.android.settings.Settings$SimSettingsActivity";
    private static final String VPN_KEY="vpn_settings";
    private static final String AIRPLANE_MODE_KEY="toggle_airplane";
    //Connteced devices
    private static final String BLUETOOTH_KEY="toggle_bluetooth";
    private static final String NFC_KEY="toggle_nfc";
    private static final String ANDROID_BEAM_KEY="android_beam_settings";
    private static final String PRINTING_KEY="dashboard_tile_pref_com.android.settings.Settings$PrintSettingsActivity";
    private static final String USB_KEY="usb_mode";
    //Apps & notifications
    private static final String RECENTLY_OPENED_APPS_KEY="recent_apps_category";
    private static final String SEE_ALL_APPS_KEY="all_app_info";
    private static final String NOTIFICATIONS_KEY="dashboard_tile_pref_com.android.settings.Settings$ConfigureNotificationSettingsActivity";
    private static final String APP_PERMISSIONS_KEY="manage_perms";
    private static final String DEFAULT_APPS_KEY="dashboard_tile_pref_com.android.settings.Settings$AdvancedAppsActivity";
    private static final String SPECIAL_APP_ACCESS_KEY="special_access";
    //Battery
    private static final String LAST_FULL_CHARGE_KEY="last_full_charge";
    private static final String SCREEN_USAGE_SINCE_FULL_CHARGE_KEY="screen_usage";
    private static final String POWER_MANAGEMENT_KEY="power_management";
    private static final String BATTERY_SAVER_KEY="battery_saver_summary";
    private static final String BATTERY_PERCENTAGE_KEY="battery_percentage";
    private static final String BATTERY_SLEEP_KEY="screen_timeout_battery";
    private static final String APP_USAGE_SINCE_FULL_CHARGE_KEY="app_list";
    //Display
    private static final String BRIGHTNESS_LEVEL_KEY="brightness";
    private static final String WALLPAPER_KEY="wallpaper";
    private static final String DISPLAY_SLEEP_KEY="screen_timeout";
    private static final String AUTO_RORATE_SCREEN_KEY="auto_rotate";
    private static final String DISPLAY_FONT_SIZE_KEY="font_size";
    //Sound
    private static final String MEDIA_VOLUME_KEY="media_volume";
    private static final String ALARM_VOLUME_KEY="alarm_volume";
    private static final String RING_VOLUME_KEY="ring_volume";
    private static final String ALSO_VIBRATE_FOR_CALLS_KEY="vibrate_when_ringing";
    private static final String DO_NOT_DISTURB_PREFERENCES_KEY="zen_mode";
    private static final String PHONE_RINGTONE_KEY="ringtone";
    private static final String DEFAULT_NOTIFICATION_SOUND_KEY="notification_ringtone";
    private static final String DEFAULT_ALARM_SOUND_KEY="alarm_ringtone";
    private static final String DIAL_PAD_TONES_KEY="dial_pad_tones";
    private static final String SCREEN_LOCKING_SOUNDS_KEY="screen_locking_sounds";
    private static final String CHARGING_SOUNDS_KEY="charging_sounds";
    private static final String TOUCH_SOUNDS_KEY="touch_sounds";
    private static final String SOUND_ENHANCEMENT_KEY="sound_enhancement";
    //Storage
    private static final String USED_KEY="pref_summary";
    private static final String PHOTOS_VIDEOS_KEY="pref_photos_videos";
    private static final String MUSIC_AUDIO_KEY="pref_music_audio";
    private static final String GAMES_KEY="pref_games";
    private static final String MOVIE_TV_APPS_KEY="pref_movies";
    private static final String OTHER_APPS_KEY="pref_other_apps";
    private static final String FILES_KEY="pref_files";
    private static final String SYSTEM_KEY="pref_system";
    //DuraSpeed
    //Security & location
    private static final String DEVICE_SECURITY_KEY="security_category";
    private static final String SCREEN_LOCK_KEY="unlock_set_or_change";
    private static final String LOCK_SCREEN_PREFERENCE_KEY="lockscreen_preferences";
    private static final String LOCATION_KEY="location";
    private static final String SHOW_PASSWORDS_KEY="show_password";
    private static final String DEVICE_ADMIN_APPS_KEY="manage_device_admin";
    private static final String SIM_CARD_LOCK_KEY="sim_lock_settings";
    private static final String ENCRYPTION_CREDENTIALS_KEY="encryption_and_credential";
    private static final String TRUST_AGENTS_KEY="manage_trust_agents";
    private static final String SCREEN_PINNING_KEY="screen_pinning_settings";
    private static final String APPS_WITH_USAGE_ACCESS_KEY="usage_access";
    //Users & accounts
    private static final String USER_LIST_KEY="user_list";
    private static final String EMERGENCY_INFORMATION_KEY="emergency_info";
    private static final String AUTOMATICALLY_SYNC_DATA_KEY="auto_sync_account_data";
    //Accessibility
    private static final String VOLUME_KEY_SHOTCUT_KEY="accessibility_shortcut_preference";
    private static final String SCREEN_READERS_KEY="screen_reader_category";
    private static final String TEXT_TO_SPEECH_OUTPUT_KEY="tts_settings_preference";
    private static final String DISPLAY_KEY="display_category";
    private static final String ACCESSIBILITY_FONT_SIZE_KEY="font_size_preference_screen";
    private static final String MAGNIFICATION_KEY="magnification_preference_screen";
    private static final String LARGE_MOUSE_POINTER_KEY="toggle_large_pointer_icon";
    private static final String INTERACTION_CONTROLS_KEY="interaction_control_category";
    private static final String CLICK_AFTER_POINTER_STOPS_MOVING_KEY="autoclick_preference_screen";
    private static final String AUTO_ROTATE_SCREEN_KEY="toggle_lock_screen_rotation_preference";
    private static final String TOUCH_HOLD_DELAY_KEY="select_long_press_timeout_preference";
    private static final String AUDIO_ONSCREEN_TEXT_KEY="audio_and_captions_category";
    private static final String MONO_AUDIO_KEY="toggle_master_mono";
    private static final String CAPTIONS_KEY="captioning_preference_screen";
    private static final String EXPERIMENTAL_KEY="experimental_category";
    private static final String HIGH_CONTRAST_TEXT_KEY="toggle_high_text_contrast_preference";
    private static final String COLOR_CORRECTION_KEY="daltonizer_preference_screen";
    private static final String COLOR_INVERSION_KEY="toggle_inversion_preference";
    //System
    private static final String LANGUAGE_INPUT_KEY="dashboard_tile_pref_com.android.settings.Settings$LanguageAndInputSettingsActivity";
    private static final String GESTURES_KEY="gesture_settings";
    private static final String DATE_TIME_KEY="dashboard_tile_pref_com.android.settings.Settings$DateTimeSettingsActivity";
    private static final String DEVELOPER_OPTIONS_KEY="dashboard_tile_pref_com.android.settings.Settings$DevelopmentSettingsActivity";
    private static final String RESET_OPTIONS_KEY="reset_dashboard";
    private static final String SCHEDULE_POWER_ON_OFF_KEY="dashboard_tile_pref_com.mediatek.schpwronoff.AlarmClock";
    private static final String ABOUT_PHONE_KEY="dashboard_tile_pref_com.android.settings.Settings$DeviceInfoSettingsActivity";

    Intent silenterService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);

        v = inflater.inflate(R.layout.fragment_security_settings, container, false);
        type = Typeface.createFromAsset(getActivity().getAssets(),"CaviarDreams.ttf");


        buttonHideSettings=(Button) v.findViewById(R.id.buttonHideSettings);buttonHideSettings.setTypeface(type);
        buttonShowSettings=(Button) v.findViewById(R.id.buttonResetSettings);buttonShowSettings.setTypeface(type);

        switchBlockControlPanel=(Switch)v.findViewById(R.id.switchBlockPanel) ;switchBlockControlPanel.setTypeface(type);
        switchHideStatusBar=(Switch)v.findViewById(R.id.switchHideStatusBar) ;switchHideStatusBar.setTypeface(type);

        switchHideStatusBar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("HIDE STATUS BAR",""+b);
                if(b==true)
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.KIOSK_STATUSBAR_SHOW.getAction(),null);
                else
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.KIOSK_STATUSBAR_HIDE.getAction(),null);

            }
        });

        switchBlockControlPanel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("BLOCK CONTROL PANEL",""+b);
                if(b==true)
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.KIOSK_CONTROLPANEL_HIDE.getAction(),null);
                else
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.KIOSK_CONTROLPANEL_SHOW.getAction(),null);
            }
        });



        buttonShowSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("SEND SHOW SETTINGS","show settings");
                Utils.sendActionToServiceWithParams(getActivity(),EnumAction.KIOSK_SETTINGS_LEVEL1_RESET.getAction(),null);
                Utils.sendActionToServiceWithParams(getActivity(),EnumAction.KIOSK_SETTINGS_LEVEL2_RESET.getAction(),null);

            }
        });

        setUpListSystemPackage();


        final ArrayList<Integer> listSettingsToHide=new ArrayList<>();
        buttonHideSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<listSecurityItem.size();i++){
                    CheckBox checkBox=(CheckBox)getActivity().findViewById(i);
                    SecurityItem item=listSecurityItem.get(getindex(listSecurityItem,checkBox.getText().toString()));

                    if(checkBox.isChecked()){
                        Log.e("ismail",checkBox.getText()+"---"+checkBox.isChecked()+"");
                        if(item.getIndex()!=null) {
                            listSettingsToHide.add(item.getIndex());
                        }else{
                            ArrayList<Param> listParam=new ArrayList<>();
                            listParam.add(new Param("lines",item.getIsLevel1()));
                            Utils.sendActionToServiceWithParams(getActivity(),EnumAction.KIOSK_SETTINGS_LEVEL2_HIDE.getAction(),listParam);

                        }
                    }
                }
                if(listSettingsToHide.size()>0){
                    arraySettingsToHide=new int[listSettingsToHide.size()];
                    for(int i =0;i<listSettingsToHide.size();i++){
                        arraySettingsToHide[i]=listSettingsToHide.get(i);
                    }
                    try {
                        JSONArray jsonArray1 = new JSONArray(arraySettingsToHide);
                        ArrayList<Param> listParam=new ArrayList<>();
                        listParam.add(new Param("lines",jsonArray1.toString()));
                        Utils.sendActionToServiceWithParams(getActivity(),EnumAction.KIOSK_SETTINGS_LEVEL1_HIDE.getAction(),listParam);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



        return v;
    }



    public class SecurityItem{
        String name;
        String isLevel1;
        int subItems;
        Integer index;

        public SecurityItem(String name, String isLevel1, int subItems, Integer index) {
            this.name = name;
            this.isLevel1 = isLevel1;
            this.subItems = subItems;
            this.index = index;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIsLevel1() {
            return isLevel1;
        }

        public void setIsLevel1(String isLevel1) {
            this.isLevel1 = isLevel1;
        }

        public int getSubItems() {
            return subItems;
        }
    }
    
    public void fillListSecurityItem(){


        listSecurityItem=new ArrayList<>();
        listSecurityItem.add(new SecurityItem("Network & Internet",null,7,0));
        listSecurityItem.add(new SecurityItem("WI-FI",WIFI_KEY,7,null));
        listSecurityItem.add(new SecurityItem("Mobile network",MOBILE_NETWORK_KEY,7,null));
        listSecurityItem.add(new SecurityItem("Data usage",DATA_USAGE_KEY,7,null));
        listSecurityItem.add(new SecurityItem("Hotspot & tethering",HOTSPOT_TETHERING_KEY,7,null));
        listSecurityItem.add(new SecurityItem("Sim Cards",SIM_CARDS_KEY,7,null));
        listSecurityItem.add(new SecurityItem("VPN",VPN_KEY,7,null));
        listSecurityItem.add(new SecurityItem("Airplane mode",AIRPLANE_MODE_KEY,7,null));


        listSecurityItem.add(new SecurityItem("Connected devices",null,5,1));
        listSecurityItem.add(new SecurityItem("Bluetooth",BLUETOOTH_KEY,5,null));
        listSecurityItem.add(new SecurityItem("NFC",NFC_KEY,5,null));
        listSecurityItem.add(new SecurityItem("Android Beam",ANDROID_BEAM_KEY,5,null));
        listSecurityItem.add(new SecurityItem("Printing",PRINTING_KEY,5,null));
        listSecurityItem.add(new SecurityItem("USB",USB_KEY,5,null));

        listSecurityItem.add(new SecurityItem("Apps & notifications",null,6,2));
        listSecurityItem.add(new SecurityItem("Recently opened apps",RECENTLY_OPENED_APPS_KEY,6,null));
        listSecurityItem.add(new SecurityItem("See all apps",SEE_ALL_APPS_KEY,6,null));
        listSecurityItem.add(new SecurityItem("Notifications",NOTIFICATIONS_KEY,6,null));
        listSecurityItem.add(new SecurityItem("App permissions",APP_PERMISSIONS_KEY,6,null));
        listSecurityItem.add(new SecurityItem("Default apps",DEFAULT_APPS_KEY,6,null));
        listSecurityItem.add(new SecurityItem("Special app access",SPECIAL_APP_ACCESS_KEY,6,null));

        listSecurityItem.add(new SecurityItem("Battery",null,7,3));
        listSecurityItem.add(new SecurityItem("Last full charge",LAST_FULL_CHARGE_KEY,7,null));
        listSecurityItem.add(new SecurityItem("Screen Usage Since F.Charge",SCREEN_USAGE_SINCE_FULL_CHARGE_KEY,7,null));
        listSecurityItem.add(new SecurityItem("Power management",POWER_MANAGEMENT_KEY,7,null));
        listSecurityItem.add(new SecurityItem("Battery saver",BATTERY_SAVER_KEY,7,null));
        listSecurityItem.add(new SecurityItem("Battery percentage",BATTERY_PERCENTAGE_KEY,7,null));
        listSecurityItem.add(new SecurityItem("Sleep",BATTERY_SLEEP_KEY,7,null));
        listSecurityItem.add(new SecurityItem("App usage since F.Charge",APP_USAGE_SINCE_FULL_CHARGE_KEY,7,null));

        listSecurityItem.add(new SecurityItem("Display",null,5,4));
        listSecurityItem.add(new SecurityItem("Brightness level",BRIGHTNESS_LEVEL_KEY,5,null));
        listSecurityItem.add(new SecurityItem("Wallpaper",WALLPAPER_KEY,5,null));
        listSecurityItem.add(new SecurityItem("Sleep",DISPLAY_SLEEP_KEY,5,null));
        listSecurityItem.add(new SecurityItem("Auto-rotate screen",AUTO_RORATE_SCREEN_KEY,5,null));
        listSecurityItem.add(new SecurityItem("Font size",DISPLAY_FONT_SIZE_KEY,5,null));


        listSecurityItem.add(new SecurityItem("Sound",null,13,5));
        listSecurityItem.add(new SecurityItem("Media volume", MEDIA_VOLUME_KEY,13,null));
        listSecurityItem.add(new SecurityItem("Alarm volume", ALARM_VOLUME_KEY,13,null));
        listSecurityItem.add(new SecurityItem("Ring volume", RING_VOLUME_KEY,13,null));
        listSecurityItem.add(new SecurityItem("Also vibrate for calls", ALSO_VIBRATE_FOR_CALLS_KEY,13,null));
        listSecurityItem.add(new SecurityItem("Do not disturb preferences", DO_NOT_DISTURB_PREFERENCES_KEY,13,null));
        listSecurityItem.add(new SecurityItem("Phone Ringtone", PHONE_RINGTONE_KEY,13,null));
        listSecurityItem.add(new SecurityItem("Default notification sound", DEFAULT_NOTIFICATION_SOUND_KEY,13,null));
        listSecurityItem.add(new SecurityItem("Default alarm sound", DEFAULT_ALARM_SOUND_KEY,13,null));
        listSecurityItem.add(new SecurityItem("Dial pad tones", DIAL_PAD_TONES_KEY,13,null));
        listSecurityItem.add(new SecurityItem("Screen locking sounds", SCREEN_LOCKING_SOUNDS_KEY,13,null));
        listSecurityItem.add(new SecurityItem("Charging sounds", CHARGING_SOUNDS_KEY,13,null));
        listSecurityItem.add(new SecurityItem("Touch sounds", TOUCH_SOUNDS_KEY,13,null));
        listSecurityItem.add(new SecurityItem("Sound enhancement", SOUND_ENHANCEMENT_KEY,13,null));


        listSecurityItem.add(new SecurityItem("Storage",null,8,6));
        listSecurityItem.add(new SecurityItem("Storage used", USED_KEY,8,null));
        listSecurityItem.add(new SecurityItem("Photos & Videos", PHOTOS_VIDEOS_KEY,8,null));
        listSecurityItem.add(new SecurityItem("Music & Audio", MUSIC_AUDIO_KEY,8,null));
        listSecurityItem.add(new SecurityItem("Game", GAMES_KEY,8,null));
        listSecurityItem.add(new SecurityItem("Movie & Tv apps", MOVIE_TV_APPS_KEY,8,null));
        listSecurityItem.add(new SecurityItem("Other apps", OTHER_APPS_KEY,8,null));
        listSecurityItem.add(new SecurityItem("Files", FILES_KEY,8,null));
        listSecurityItem.add(new SecurityItem("System", SYSTEM_KEY,8,null));


        listSecurityItem.add(new SecurityItem("DuraSpeed",null,0,7));

        listSecurityItem.add(new SecurityItem("Security & location",null,11,8));
        listSecurityItem.add(new SecurityItem("Device security",  DEVICE_SECURITY_KEY,11,null));
        listSecurityItem.add(new SecurityItem("Screen Lock",  SCREEN_LOCK_KEY,11,null));
        listSecurityItem.add(new SecurityItem("Lock screen reference",  LOCK_SCREEN_PREFERENCE_KEY,11,null));
        listSecurityItem.add(new SecurityItem("location",  LOCATION_KEY,11,null));
        listSecurityItem.add(new SecurityItem("Show password",  SHOW_PASSWORDS_KEY,11,null));
        listSecurityItem.add(new SecurityItem("Device admin apps",  DEVICE_ADMIN_APPS_KEY,11,null));
        listSecurityItem.add(new SecurityItem("Sim card lock",  SIM_CARD_LOCK_KEY,11,null));
        listSecurityItem.add(new SecurityItem("Encryption & credentials",  ENCRYPTION_CREDENTIALS_KEY,11,null));
        listSecurityItem.add(new SecurityItem("Trust agents",  TRUST_AGENTS_KEY,11,null));
        listSecurityItem.add(new SecurityItem("Screen pinning",  SCREEN_PINNING_KEY,11,null));
        listSecurityItem.add(new SecurityItem("Apps with usage access",  APPS_WITH_USAGE_ACCESS_KEY,11,null));

        listSecurityItem.add(new SecurityItem("Users & accounts",null,11,9));
        listSecurityItem.add(new SecurityItem("Add account",  USER_LIST_KEY,11,null));
        listSecurityItem.add(new SecurityItem("Emergency information",  EMERGENCY_INFORMATION_KEY,11,null));
        listSecurityItem.add(new SecurityItem("Automatically sync data",  AUTOMATICALLY_SYNC_DATA_KEY,11,null));


        listSecurityItem.add(new SecurityItem("Accessibility",null,18,10));
        listSecurityItem.add(new SecurityItem("Volume key shortcut",  VOLUME_KEY_SHOTCUT_KEY,18,null));
        listSecurityItem.add(new SecurityItem("Screen readers",  SCREEN_READERS_KEY,18,null));
        listSecurityItem.add(new SecurityItem("Text-to-speech output",  TEXT_TO_SPEECH_OUTPUT_KEY,18,null));
        listSecurityItem.add(new SecurityItem("Display",  DISPLAY_KEY,18,null));
        listSecurityItem.add(new SecurityItem("Font size",  ACCESSIBILITY_FONT_SIZE_KEY,18,null));
        listSecurityItem.add(new SecurityItem("Magnification",  MAGNIFICATION_KEY,18,null));
        listSecurityItem.add(new SecurityItem("Large mouse pointer",  LARGE_MOUSE_POINTER_KEY,18,null));
        listSecurityItem.add(new SecurityItem("Interaction",  INTERACTION_CONTROLS_KEY,18,null));
        listSecurityItem.add(new SecurityItem("Click after Pointer",  CLICK_AFTER_POINTER_STOPS_MOVING_KEY,18,null));
        listSecurityItem.add(new SecurityItem("Auto rotate screen",  AUTO_ROTATE_SCREEN_KEY,18,null));
        listSecurityItem.add(new SecurityItem("Touch hold display",  TOUCH_HOLD_DELAY_KEY,18,null));
        listSecurityItem.add(new SecurityItem("Audio on screen text",  AUDIO_ONSCREEN_TEXT_KEY,18,null));
        listSecurityItem.add(new SecurityItem("Mono audio",  MONO_AUDIO_KEY,18,null));
        listSecurityItem.add(new SecurityItem("Captions",  CAPTIONS_KEY,18,null));
        listSecurityItem.add(new SecurityItem("Experimental",  EXPERIMENTAL_KEY,18,null));
        listSecurityItem.add(new SecurityItem("High contrast text",  HIGH_CONTRAST_TEXT_KEY,18,null));
        listSecurityItem.add(new SecurityItem("Color correction",  COLOR_CORRECTION_KEY,18,null));
        listSecurityItem.add(new SecurityItem("Color inversion",  COLOR_INVERSION_KEY,18,null));

        listSecurityItem.add(new SecurityItem("System",null,7,11));
        listSecurityItem.add(new SecurityItem("Language input",LANGUAGE_INPUT_KEY,7,null));
        listSecurityItem.add(new SecurityItem("Gesture",GESTURES_KEY,7,null));
        listSecurityItem.add(new SecurityItem("Date & time",DATE_TIME_KEY,7,null));
        listSecurityItem.add(new SecurityItem("Developer options",DEVELOPER_OPTIONS_KEY,7,null));
        listSecurityItem.add(new SecurityItem("Reset Option",RESET_OPTIONS_KEY,7,null));
        listSecurityItem.add(new SecurityItem("Schedule power on & off",SCHEDULE_POWER_ON_OFF_KEY,7,null));
        listSecurityItem.add(new SecurityItem("About",ABOUT_PHONE_KEY,7,null));
        
    }

    public void setUpListSystemPackage(){
        fillListSecurityItem();
        layoutListSettingsItems=(LinearLayout)v.findViewById(R.id.layoutSettingsList);


        int i;
        for(i=0;i<listSecurityItem.size();i++){

            LinearLayout.LayoutParams lp1=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            if(listSecurityItem.get(i).isLevel1!=null)
                lp1.setMargins(8,0,8,0);

            CheckBox checkbox=new CheckBox(getActivity());
            checkbox.setLayoutParams(lp1);
            checkbox.setId(i);

            checkbox.setBackgroundColor(getResources().getColor(R.color.Kiosk));
            checkbox.setText(listSecurityItem.get(i).getName());
            checkbox.setTypeface(type);
            checkbox.setTextColor(getResources().getColor(R.color.white));
            final int finalI = i;
            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(listSecurityItem.get(finalI).isLevel1==null){

                        int index=getindex(listSecurityItem,listSecurityItem.get(finalI).getName());

                        /*for(int j = 0; j<listSecurityItem.get(finalI).getSubItems(); j++){
                            Log.e("ismail 2","=="+listSecurityItem.get(index+j+1).getName());
                            CheckBox checkBox=(CheckBox)getActivity().findViewById(index+j+1);
                            checkBox.setChecked(b);
                        }*/
                    }else{
                        if(b==false){
                            Log.e("ismail 4","===============================");
                            CheckBox checkBox=(CheckBox)getActivity().findViewById(getindexItem(listSecurityItem,listSecurityItem.get(finalI).getSubItems()));
                            checkBox.setChecked(b);
                        }
                    }
                }
            });

            layoutListSettingsItems.addView(checkbox);

        }




    }

    public int getindex(ArrayList<SecurityItem> list, String name){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getName().equals(name))
                return i;
        }
        return -1;
    }

    public int getindexItem(ArrayList<SecurityItem> list, int groupeId){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getSubItems()==groupeId)
                return i;
        }
        return -1;
    }



}



