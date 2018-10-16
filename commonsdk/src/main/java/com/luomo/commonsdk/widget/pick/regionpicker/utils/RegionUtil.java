package com.luomo.commonsdk.widget.pick.regionpicker.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.luomo.commonsdk.widget.pick.regionpicker.domain.RegionDomain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.ezhantu.module.gasstation.utils
 * @date :2018/5/14 19:53
 * @description:地区数据库
 */
public class RegionUtil {
    private static String DB_NAME = "region_data.db";
    private static String TABLE_REGION = "e_region";
    private static SQLiteDatabase db;

    //把assets目录下的db文件复制到dbpath下
    private static void getDatabase(Context context) {
        try {
            if(db != null){
                return;
            }
//            String dbPath = "/data/data/" + context.getPackageName() + "/databases/" + DB_NAME;
            String dbPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ezhantu/databases/" + DB_NAME;
            File file = new File(dbPath);
            if (!file.exists()) {
                try {
                    boolean flag =file.getParentFile().mkdirs();
                    boolean newFile = file.createNewFile();
                    FileOutputStream out = new FileOutputStream(dbPath);
                    InputStream in = context.getAssets().open(DB_NAME);
                    byte[] buffer = new byte[1024];
                    int readBytes = 0;
                    while ((readBytes = in.read(buffer)) != -1) {
                        out.write(buffer, 0, readBytes);
                    }
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            db = SQLiteDatabase.openOrCreateDatabase(dbPath, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询省列表
     *
     * @return
     */
    public static List<RegionDomain> getProvince(Context context) {
        getDatabase(context);
        String[] columns = new String[]{"region_id","parent_id","region_name","region_type"};
        String selection = "parent_id = ?";
        String[] selectionArgs = new String[]{"1"};
        String orderBy = "region_id";
        //boolean distinct, String table, String[] columns,String selection, String[] selectionArgs, String groupBy,String having, String orderBy, String limit, CancellationSignal cancellationSignal
        //select * from e_region where parent_id = 1 order by region_id
        Cursor cursor = db.query(TABLE_REGION,columns,selection,selectionArgs,null, null,orderBy);
        return convertList(cursor);
    }

    /**
     * 查询市列表
     *
     * @param context
     * @param provinceId
     * @return
     */
    public static List<RegionDomain> getCity(Context context,int provinceId){
        getDatabase(context);
        String[] columns = new String[]{"region_id","parent_id","region_name","region_type"};
        String selection = "parent_id = ?";
        String[] selectionArgs = new String[]{String.valueOf(provinceId)};
        String orderBy = "region_id";
        //"select * from e_region where parent_id = "+provinceId+" order by region_id"
        Cursor cursor = db.query(TABLE_REGION,columns,selection,selectionArgs,null, null,orderBy);
        return convertList(cursor);
    }

    private synchronized static List<RegionDomain> convertList(Cursor cursor) {
        List<RegionDomain> list = new ArrayList<RegionDomain>();
        try {
            while (cursor.moveToNext()) {
                int regionId = cursor.getInt(cursor.getColumnIndex("region_id"));
                int parentId = cursor.getInt(cursor.getColumnIndex("parent_id"));
                String regionName = cursor.getString(cursor.getColumnIndex("region_name"));
                int regionType = cursor.getInt(cursor.getColumnIndex("region_type"));
                list.add(new RegionDomain(regionId, parentId, regionName, regionType));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return list;
    }
}