package com.sewciety.backend.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageUtils {

    public static boolean isBase64Encoded(String s) {
        String pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(s);
        return m.find();
    }

}
