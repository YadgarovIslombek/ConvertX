package com.ida.Converter_text;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ida.Converter_text.ui.tools.ToolsFragment;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.material.navigation.NavigationView;
import com.mahfa.dnswitch.DayNightSwitch;
import com.mahfa.dnswitch.DayNightSwitchAnimListener;
import com.mahfa.dnswitch.DayNightSwitchListener;

import java.util.Locale;

import xyz.hanks.library.bang.SmallBangView;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private AppBarConfiguration mAppBarConfiguration;
    public EditText Lotin, Kiril;
    public RadioButton rbtn1, rbtn2, rbtn3;
    private final String SETTING = "setting";
    private SharedPreferences preferences;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Intent intent, chooser;
    SharedPref sharedPref;
    private DayNightSwitch day_night_switch;
    ToolsFragment toolsFragment;
    View view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = new SharedPref(this);
//        preferences = this.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        if(sharedPref.loadNightMode()==true) {
            setTheme(R.style.AppTheme_Dark);
        }
        else{
            setTheme(R.style.AppTheme_NoActionBar);
        }
        setContentView(R.layout.activity_main);

        AudienceNetworkAds.initialize(this);
        day_night_switch =(DayNightSwitch)findViewById(R.id.sw);
        day_night_switch.setDuration(450);
        sharedPref = new SharedPref(getApplicationContext());
        view = findViewById(R.id.v1);
        day_night_switch.setListener(new DayNightSwitchListener() {
            @Override
            public void onSwitch(boolean is_night) {
                Log.d(TAG, "onSwitch() called with: is_night = [" + is_night + "]");
                try {
                    if (is_night) {
                        sharedPref.setNightModeState(true);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                    } else {
                        sharedPref.setNightModeState(false);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }


        });
        day_night_switch.setAnimListener(new DayNightSwitchAnimListener() {
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
//                    Intent intent = new Intent(getApplication(),MainActivity.class);
//                    startActivity(intent);
//                    finish();
            }
        });
        if (sharedPref != null
                && sharedPref.mySharedPref.contains("NightMode")) day_night_switch
                .setIsNight(sharedPref.mySharedPref.getBoolean("NightMode", false), false);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final SmallBangView like_heart = findViewById(R.id.like_heart);
        like_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(like_heart.isSelected()){
                    like_heart.setSelected(false);
                }else{
                    like_heart.setSelected(true);
                    like_heart.likeAnimation(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            toast("Rahmat");
                        }
                    });
                }
            }
        });
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_tools, R.id.nav_about)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        drawer.openDrawer(Gravity.LEFT);
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    public void RadiButtonLotinEvent(View view) {

        Lotin=(EditText) findViewById(R.id.editText1);
        Kiril=(EditText) findViewById(R.id.editText2);
        rbtn1=(RadioButton)findViewById(R.id.rbtnKattaLotin);
        rbtn2=(RadioButton)findViewById(R.id.rbtnKichikLotin);
        rbtn3=(RadioButton)findViewById(R.id.rbtnNormalLotin);

//        if (rbtn1.isChecked()) {
//            Kiril.setText(Kiril.getText().toString().toUpperCase());
//            Lotin.setText(Lotin.getText().toString().toUpperCase());
//        }
//        if (rbtn2.isChecked()) {
//            Lotin.setText(Lotin.getText().toString().toLowerCase());
//            Kiril.setText(Kiril.getText().toString().toLowerCase());
//        }
//        if (rbtn3.isChecked()) {
//            Lotin.setText(toNormalCase(Lotin.getText().toString()));
//            Kiril.setText(toNormalCase(Kiril.getText().toString()));
//        }


    }

    private String toNormalCase(String toString) {
        toString=toString.toLowerCase();
        String text = "";
        try {
            if (toString.contains(".")) {
                String[] strings = toString.split("[.]");
                for (int i = 0; i < strings.length; i++) {
                    while (strings[i].startsWith(" ")) {
                        strings[i] = strings[i].replaceFirst(" ", "");
                    }
                    strings[i] = strings[i].replaceFirst(strings[i].substring(0, 1), strings[i].substring(0, 1).toUpperCase());
                    text += strings[i] + ". ";
                }
            } else {
                if (toString != "") {
                    while (toString.startsWith(" ")) {
                        toString = toString.replaceFirst(" ", "");
                    }
                    toString = toString.replaceFirst(toString.substring(0, 1), toString.substring(0, 1).toUpperCase());

                    return toString;
                }
            }
        }
        catch(Exception ex){
            //Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }
        return text;
    }



    public void btnCopy(View view) {

        Lotin = (EditText) findViewById(R.id.editText1);
        Kiril = (EditText) findViewById(R.id.editText2);

        new AlertDialog.Builder(this).setTitle(getString(R.string.choose_text))
                .setItems(new CharSequence[]{getString(R.string.lotin), getString(R.string.kiril)}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        if (i == 0) {
                            ClipData clipData = ClipData.newPlainText(getString(R.string.lotin), Lotin.getText().toString());
                            clipboardManager.setPrimaryClip(clipData);
                            Toast.makeText(getApplicationContext(), getString(R.string.copied_lotin), Toast.LENGTH_LONG).show();
                        } else {
                            ClipData clipData = ClipData.newPlainText(getString(R.string.kiril), Kiril.getText().toString());
                            clipboardManager.setPrimaryClip(clipData);
                            Toast.makeText(getApplicationContext(), getString(R.string.copied_kiril), Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .create()
                .show();
    }

    public void OpenSetting(MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.navigate(R.id.nav_tools);
    }

   /* public void ShareClick(MenuItem item) {
        intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=com.ARCompany.Lotin_Kiril");
        intent.setType("text/plain");
        startActivity(intent);
    }*/

    public void SendClick(MenuItem item) {
        intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://t.me/ID221423A"));
        startActivity(intent);
    }
    public void sw (MenuItem menuItem){

    }





//    public void ChannelClick(View view) {
//        intent=new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("https://t.me/CSharp_N1"));
//        startActivity(intent);
//    }

//    public void RateClick(MenuItem item) {
//        intent=new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.ARCompany.Lotin_Kiril"));
//        chooser=Intent.createChooser(intent, getString(R.string.menu_rate));
//        startActivity(chooser);
//    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(updateBaseContextLocale(newBase));
    }

    @Override
    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
        super.applyOverrideConfiguration(overrideConfiguration);
    }

    private Context updateBaseContextLocale(Context context) {
        preferences = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String language=preferences.getString("lang", "uz");
        Locale locale =new Locale(language);
        Locale.setDefault(locale);

        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.N){
            return updateResourcesLocale(context, locale);
        }
        return  updateResourcesLocaleLegacy(context, locale);
    }

    private Context updateResourcesLocaleLegacy(Context context, Locale locale) {
        Resources resources=context.getResources();
        Configuration configuration=resources.getConfiguration();
        configuration.locale=locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private Context updateResourcesLocale(Context context, Locale locale) {
        Resources resources=context.getResources();
        Configuration configuration=resources.getConfiguration();
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState); outState.putBoolean("NightMode", day_night_switch.isNight());
    }

}
