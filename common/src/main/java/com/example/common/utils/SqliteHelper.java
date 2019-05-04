package com.example.common.utils;


public class SqliteHelper {

    private static SqliteHelper mSqliteHelper;

    private void SqliteHelper(){}

    public static SqliteHelper getInstance(){
        if (mSqliteHelper == null){
            mSqliteHelper = new SqliteHelper();
        }
        return mSqliteHelper;
    }
}
