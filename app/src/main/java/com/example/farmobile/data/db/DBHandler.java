package com.example.farmobile.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.farmobile.utils.AppConstants.DB_NAME;
import static com.example.farmobile.utils.AppConstants.DB_VERSION;

public class DBHandler extends SQLiteOpenHelper {

    private static final String TABLE_NAME_KAFKA="tblKafka";
    private static final String TABLE_NAME_HISTORY="tblHistory";
    private static final String COL_ID="id";
    private static final String COL_PREDICTION="prediction";
    private static final String COL_MODELVERSION="model_version";
    private static final String COL_TESTDATETIME="test_datetime";
    private static final String COL_IP="ip";
    private static final String COL_UN="un";
    private static final String COL_FAILITEM="fail_item";
    private static final String COL_KAFKATIME="kafka_time";
    private static final String COL_QUERYTIME="query_time";

    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query1="CREATE TABLE "+TABLE_NAME_KAFKA+"("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COL_PREDICTION+ " TEXT,"
                +COL_MODELVERSION+" INTEGER,"
                +COL_TESTDATETIME+" TEXT,"
                +COL_IP+" TEXT,"
                +COL_UN+" TEXT,"
                +COL_FAILITEM+" TEXT,"
                +COL_KAFKATIME+" TEXT)";

        String query2="CREATE TABLE "+TABLE_NAME_HISTORY+"("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COL_PREDICTION+ " TEXT,"
                +COL_MODELVERSION+" INTEGER,"
                +COL_TESTDATETIME+" TEXT,"
                +COL_IP+" TEXT,"
                +COL_UN+" TEXT,"
                +COL_FAILITEM+" TEXT,"
                +COL_KAFKATIME+" TEXT,"
                +COL_QUERYTIME+" TEXT)";

        sqLiteDatabase.execSQL(query1);
        sqLiteDatabase.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int olderVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_KAFKA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_HISTORY);
        onCreate(sqLiteDatabase);
    }

    public void addNewKafka(String prediction, int modelVersion, String testDatetime, String ip, String un, String failItem, String kafkaTime){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(COL_PREDICTION,prediction);
        values.put(COL_MODELVERSION,modelVersion);
        values.put(COL_TESTDATETIME,testDatetime);
        values.put(COL_IP,ip);
        values.put(COL_UN,un);
        values.put(COL_FAILITEM,failItem);
        values.put(COL_KAFKATIME,kafkaTime);

        db.insert(TABLE_NAME_KAFKA,null,values);
        db.close();
    }

    public void addNewHistory(String prediction, int modelVersion, String testDatetime, String ip, String un, String failItem, String kafkaTime, String queryTime){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(COL_PREDICTION,prediction);
        values.put(COL_MODELVERSION,modelVersion);
        values.put(COL_TESTDATETIME,testDatetime);
        values.put(COL_IP,ip);
        values.put(COL_UN,un);
        values.put(COL_FAILITEM,failItem);
        values.put(COL_KAFKATIME,kafkaTime);
        values.put(COL_QUERYTIME,queryTime);

        db.insert(TABLE_NAME_HISTORY,null,values);
        db.close();
    }

}
