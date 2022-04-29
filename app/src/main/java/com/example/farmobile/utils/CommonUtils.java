package com.example.farmobile.utils;

import android.app.Activity;

import java.io.IOException;
import java.io.InputStream;

public class CommonUtils {

    public static String loadJSONFromAsset(Activity activity, String filePath) {
        String json = null;
        try {
            InputStream is = activity.getAssets().open(filePath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
