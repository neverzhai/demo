package com.shuanger.demo.filterinterceptor.util;

/**
 * Created by wuxiaozhou on 2017/12/12.21:06
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

public class GsonUtils {
    public GsonUtils() {
    }

    public static String toJson(Object target) {
        return toJson(target, null, null);
    }

    public static String toJson(Object target, Type targetType, String datePattern) {
        if (target == null) {
            return "{}";
        }

        Gson gson = createGson(datePattern);
        String result = "{}";

        try {
            if (targetType == null) {
                result = gson.toJson(target);
            } else {
                result = gson.toJson(target, targetType);
            }
        } catch (Exception var7) {
            if (target instanceof Collection || target instanceof Iterator || target instanceof Enumeration || target.getClass().isArray()) {
                result = "[]";
            }
        }

        return result;
    }

    public static String toJson(Object target, Type targetType) {
        return toJson(target, targetType,  null);
    }

    public static <T> T fromJson(String json, TypeToken<T> token, String datePattern) {
        if (json == null || json.length() < 1) {
            return null;
        }

        Gson gson = createGson(datePattern);
        try {
            return gson.fromJson(json, token.getType());
        } catch (Exception var6) {
            return null;
        }

    }

    public static Object fromJson(String json, Type type, String datePattern) {
       if (json == null || json.length() < 1) {
          return null;
       }

        Gson gson = createGson(datePattern);
        try {
            return gson.fromJson(json, type);
        } catch (Exception var6) {
            return null;
        }
    }

    public static Object fromJson(String json, Type type) {
        return fromJson(json, (Type) type,  null);
    }

    public static <T> T fromJson(String json, TypeToken<T> token) {
        return (T) fromJson(json, (TypeToken) token,  null);
    }

    public static <T> T fromJson(String json, Class<T> clazz, String datePattern) {
       if (json == null || json.length() < 1) {
          return null;
       }

        Gson gson = createGson(datePattern);
        try {
            return gson.fromJson(json, clazz);
        } catch (Exception var6) {
            return null;
        }

    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return (T) fromJson(json, (Class) clazz,  null);
    }

    private static Gson createGson(String datePattern) {
        GsonBuilder builder = new GsonBuilder();
        if (datePattern == null || datePattern.length() < 1) {
            datePattern = "yyyy-MM-dd HH:mm:ss";
        }

        builder.setDateFormat(datePattern);
        builder.disableHtmlEscaping();
        return builder.create();
    }
}