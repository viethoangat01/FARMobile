package com.example.farmobile.data.prefs;

public interface PreferencesHelper {

    String getCurrentKafkaBroker();

    void setCurrentKafkaBroker(String kafkaBroker);

    String getCurrentKafkaTopic();

    void setCurrentKafkaTopic(String kafkaTopic);

    String getCurrentIpBanned();

    void setCurrentIpBanned(String ipBanned);
}
