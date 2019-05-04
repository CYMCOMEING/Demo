package com.example.libandroid.utils.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.libandroid.utils.sqlite.bean.Card;

import java.util.ArrayList;
import java.util.List;

public class DbManager {
    private static MySqliteHelper helper;
    public static MySqliteHelper getIntance(Context context){
        if (helper == null){
            helper = new MySqliteHelper(context);
        }
        return helper;
    }

    /**
     * 根据sql语句查询获得cursor对象
     * @param db 数据库对象
     * @param sql 查询的sql语句
     * @param selectionArgs 查询条件的占位符
     * @return 查询结果
     */
    public static Cursor selectDataSql(SQLiteDatabase db, String sql, String[] selectionArgs){
        Cursor cursor=null;
        if (db != null){
            db.rawQuery(sql, selectionArgs);
        }
        return cursor;
    }

    /**
     * 将查询的cursor对象转换成list集合
     * @param cursor
     * @return
     */
    public static List<Card> cursorToList(Cursor cursor){
        List<Card> list = new ArrayList<>();
        // moveToNext() 如果返回true表示一下条记录存在 否则表示游标中数据读取完毕
        while(cursor.moveToNext()){

        }
        return list;
    }
}
