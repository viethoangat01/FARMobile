package com.example.farmobile.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_CURRENT_KAFKA_BROKER = "PREF_KEY_CURRENT_KAFKA_BROKER";
    private static final String PREF_KEY_CURRENT_KAFKA_TOPIC = "PREF_KEY_CURRENT_KAFKA_TOPIC";
    private static final String PREF_KEY_CURRENT_IP_BANNED = "PREF_KEY_CURRENT_IP_BANNED";

    private final SharedPreferences mPrefs;

    public AppPreferencesHelper(Context context, String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getCurrentKafkaBroker() {
        return mPrefs.getString(PREF_KEY_CURRENT_KAFKA_BROKER, null);
    }

    @Override
    public void setCurrentKafkaBroker(String kafkaBroker) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_KAFKA_BROKER, kafkaBroker).apply();
    }

    @Override
    public String getCurrentKafkaTopic() {
        return mPrefs.getString(PREF_KEY_CURRENT_KAFKA_TOPIC, null);
    }

    @Override
    public void setCurrentKafkaTopic(String kafkaTopic) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_KAFKA_TOPIC, kafkaTopic).apply();
    }

    @Override
    public String getCurrentIpBanned() {
        return mPrefs.getString(PREF_KEY_CURRENT_IP_BANNED, null);
    }

    @Override
    public void setCurrentIpBanned(String ipBanned) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_IP_BANNED, ipBanned).apply();
    }


}
