package com.ida.Converter_text.ui.tools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ida.Converter_text.R;
import com.ida.Converter_text.SharedPref;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.mahfa.dnswitch.DayNightSwitch;
import com.mahfa.dnswitch.DayNightSwitchAnimListener;
import com.mahfa.dnswitch.DayNightSwitchListener;


public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    Switch darkenSwitch;
    public static final String KEY_DAY_NIGHT_SWITCH_STATE = "day_night_switch_state";
    public static final String TAG = "MainActivity";
    private DayNightSwitch dayNightSwitch;
    private final String SETTING = "setting";
    private Spinner spinner_lang, spinner_size;
    private LinearLayout layoutAd;
    private AdView adView;
    SharedPref sharedPref;
    Context context;
    View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

        layoutAd = (LinearLayout) root.findViewById(R.id.setting_ad_layout);
        adView = new AdView(getContext(), "623349368393641_628845394510705", AdSize.BANNER_HEIGHT_50);
        AdSettings.setTestMode(false);
        adView.setAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                if (adError.getErrorCode() != 1001) {
                    adView.loadAd();
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
            }

            @Override
            public void onAdClicked(Ad ad) {
            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });
        layoutAd.addView(adView);
        adView.loadAd();

        sharedPref = new SharedPref(getContext());
        dayNightSwitch = (DayNightSwitch) root.findViewById(R.id.switch1);
        view = root.findViewById(R.id.view);
        //DayNight();

//        preferences = getActivity().getSharedPreferences(SETTING, Context.MODE_PRIVATE);
//        darkenSwitch = ((Switch)root.findViewById(R.id.switch1));
//        darkenSwitch.setChecked(preferences.getBoolean("darken", false));
//        darkenSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                    preferences.edit().putBoolean("darken", b).apply();
//                    getActivity().finish();
//                    getActivity().startActivity(new Intent(getActivity(),getActivity().getClass()));
//            }
//        });

//        spinner_size=(Spinner) root.findViewById(R.id.spinner2);
//        spinner_size.setSelection(mySharedPref.getInt("size_id", 0));
//        spinner_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                try {
//                    mySharedPref.edit().putInt("size_id",adapterView.getSelectedItemPosition()).apply();
//                    mySharedPref.edit().putFloat("size", Float.parseFloat(adapterView.getSelectedItem().toString())).apply();
//                }
//                catch (Exception ex){
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        spinner_lang=(Spinner) root.findViewById(R.id.tools_lang_spinner);
//        if(mySharedPref.getString("lang", "uz").equals("uz")) {
//            spinner_lang.setSelection(0);
//        }else{
//            spinner_lang.setSelection(1);
//        }
//
//        spinner_lang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                try {
//                    if(i==0 && !mySharedPref.getString("lang", "uz").equals("uz")) {
//                        mySharedPref.edit().putString("lang", "uz").apply();
//                        getActivity().finish();
//                        getActivity().startActivity(new Intent(getActivity(),getActivity().getClass()));
//                    }else{
//                        if(i==1 && !mySharedPref.getString("lang", "uz").equals("ru")) {
//                            mySharedPref.edit().putString("lang", "ru").apply();
//                            getActivity().finish();
//                            getActivity().startActivity(new Intent(getActivity(),getActivity().getClass()));
//                        }
//                    }
//
//                }
//                catch (Exception ex){
//
//                }



        return root;
    }

    public void DayNight() {
        dayNightSwitch.setDuration(400);
        dayNightSwitch.setListener(new DayNightSwitchListener() {
            @Override
            public void onSwitch(boolean is_night) {
                Log.d(TAG, "onSwitch() called with: is_night = [" + is_night + "]");
                if (is_night) {
                    sharedPref.setNightModeState(true);
                    //view.setAlpha(1f);


            } else {
                    sharedPref.setNightModeState(false);
                    //view.setAlpha(0);

                }
            }


        });
        dayNightSwitch.setAnimListener(new DayNightSwitchAnimListener() {
            @Override
            public void onAnimStart() {
                Log.d(TAG, "onAnimStart() called");

            }

            @Override
            public void onAnimEnd() {
                Log.d(TAG, "onAnimEnd() called");
            }

            @Override
            public void onAnimValueChanged(float value) {
//                background_view.setAlpha(value); Log.d(TAG, "onAnimValueChanged() called with: value = [" + value + "]");
                
            }
        });
        if (sharedPref != null
                && sharedPref.mySharedPref.contains("NightMode")) dayNightSwitch
                .setIsNight(sharedPref.mySharedPref.getBoolean("NightMode", true), true);

    }

    private void restart() {
        getActivity().finish();
        getActivity().startActivity(new Intent(getActivity(),getActivity().getClass()));
    }

    @Override
    public void onResume(){
        super.onResume();

    }


}