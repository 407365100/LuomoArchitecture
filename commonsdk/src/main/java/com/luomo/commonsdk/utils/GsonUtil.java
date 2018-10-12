package com.luomo.commonsdk.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.tools
 * @date :2018/5/7 14:11
 * @description:Gson工具
 */
public class GsonUtil {
    static Gson gson;

    private static void getInstance() {
        if (gson == null) {
            gson = new Gson();
        }
    }

    /**
     * 将gsonString转成泛型bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T gsonToBean(String gsonString, Class<T> cls) {
        getInstance();
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * 转成list
     * 泛型在编译期类型被擦除导致报错
     *
     * @param gsonString
     * @param clazz
     * @return
     */
    private static <T> List<T> gsonToList(String gsonString, Class clazz) {
        getInstance();
        List<T> list = null;
        list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
        }.getType());
        return list;
    }

    /**
     * 转成list
     * 泛型在编译期类型被擦除导致报错
     *
     * @param gsonString
     * @return
     */
    /*public static List<StationInfo> gsonToList(String gsonString) {
        getInstance();
        List<StationInfo> list = null;
        list = gson.fromJson(gsonString, new TypeToken<List<StationInfo>>() {
        }.getType());
        return list;
    }*/


    /**
     * 转成list
     * 解决泛型问题
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        getInstance();
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }

    /**
     * 转成list
     * 解决泛型问题
     *
     * @param list
     * @return
     */
    public static String listToJson(List list) {
        getInstance();
        return gson.toJson(list);
    }
}
