package com.example.farmobile.utils;

import android.app.Activity;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    private static final String IPV4_REGEX =
            "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private static final Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);

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

    public static boolean isValidInet4Address(String ip)
    {
        if (ip == null) return false;
        Matcher matcher = IPv4_PATTERN.matcher(ip);
        return matcher.matches();
    }


}
