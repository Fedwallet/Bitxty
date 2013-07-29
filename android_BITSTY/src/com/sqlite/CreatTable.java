package com.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class CreatTable extends SQLiteOpenHelper{
		
	private static final int	DB_VERSION = 1;
	// ���ݿ�����Ϊdata
	private static final String	DB_NAME	= "users.db";

	//�������������б�
	private static final String	news_table = "CREATE TABLE news(ID INTEGER PRIMARY KEY," +
                                                               "   TEXT," +
                                                               "news_time   TEXT," +
                                                               "news_cache TEXT)"; //school/RSS
	
	
                                                               
	
	// ִ��open���������ݿ�ʱ�����淵�ص����ݿ����

	// ��SQLiteOpenHelper�̳й���
	//private DatabaseHelper		mDatabaseHelper	= null;
	public CreatTable(Context context)
	{
		//������getWritableDatabase() 
		//�� getReadableDatabase()����ʱ
		//�򴴽�һ�����ݿ�
		super(context, DB_NAME, null, DB_VERSION);
		
		
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// ���ݿ�û�б�ʱ����һ��
//					db.execSQL(newslist_table);
//					db.execSQL(user_table);
					db.execSQL(news_table);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS notes");
		onCreate(db);
		
	}
	

	
}
