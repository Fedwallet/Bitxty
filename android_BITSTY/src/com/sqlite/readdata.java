package com.sqlite;

import com.bitxty.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import java.util.ArrayList;
public class readdata
{
	// ���ڴ�ӡlog
	private static final String	TAG				= "MyDataBaseAdapter";
	// ����һ�����ݵ�����
	public static final String	KEY_ID = "_id";												

	// ����һ�����ݵ�����
	public static final String	KEY_NUM	= "num";												

	// ����һ�����ݵ�id
	public static final String	KEY_DATA = "data";

	// ���ݿ�����Ϊdata

	
	// ���ݿ����
	private static final String	DB_TABLE = "table1";
	  
	// ���ݿ�汾
	private static final int	DB_VERSION = 1;

	// ����Context����
	private Context				mContext = null;
	
		
	// ִ��open���������ݿ�ʱ�����淵�ص����ݿ����
	private SQLiteDatabase		database	= null;

	// ��SQLiteOpenHelper�̳й���
	private CreatTable		mCreatTable	= new CreatTable(mContext);
	
	
	private String	DB_NAME;
	public static final String PACKAGE_NAME = "com.bitxty";
	public String DB_PATH = "/data"
          + Environment.getDataDirectory().getAbsolutePath() + "/"
          + PACKAGE_NAME+"/databases";  //���ֻ��������ݿ��λ��
	
	/* ���캯��-ȡ��Context */
	public readdata(Context context,String str)
	{
		mContext = context;
		DB_NAME = str;
		open();
	}
	
	public void creat() throws SQLException
	{
		mCreatTable = new CreatTable(mContext);
		database = mCreatTable.getWritableDatabase();
		return;
	}
	
	public void open()
	{
		database = SQLiteDatabase.openOrCreateDatabase(DB_PATH + "/"+DB_NAME, null);
	}
	// �ر����ݿ�
	public void close()
	{
		database.close();
	}

	/* ����һ�������б�*/
	public long insertnewslist(String webname, String url, int type, int DL, int fistUser, String webserves)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put("list_webname", webname);
		initialValues.put("list_url", url);
		initialValues.put("list_type", type);
		initialValues.put("list_DL", DL);
		initialValues.put("list_firstUser", fistUser);
		initialValues.put("list_websserves", webserves);
		return database.insert("newslist_table", "ID", initialValues);
	}
	
	public long insertUser(String name, String password, int type)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put("User_name", name);
		initialValues.put("User_password", password);
		initialValues.put("type", type);

		return database.insert("user_table", "ID", initialValues);
	}


	/* ɾ��һ������ */
	public boolean deleteData(String table ,long rowId)
	{
		return database.delete(table, "ID" + "=" + rowId, null) > 0;
	}
	public boolean deleteData(String table ,String type,String vuale)
	{
		return database.delete(table, type+ "=" + vuale, null) > 0;
	}

	/* ͨ��Cursor��ѯ�������� */
	public Cursor fetchAllData(String table)
	{
		return database.query(table, new String[] {"*"}, null, null, null, null, null);
	}
	public Cursor fetchAllData(String table,String str1)
	{
		return database.query(table, new String[] { str1}, null, null, null, null, null);
	}
	public Cursor fetchAllData(String table,String str1,String str2)
	{
		return database.query(table, new String[] {"*"}, null, null, null, null, null);		
	}
	public Cursor fetchAllData(String table,String str1,String str2,String str3)
	{
		return database.query(table, new String[] { str1}, null, null, null, null, null);
	}

	/* ��ѯָ������ */
	public Cursor fetchData(String table,long rowId) throws SQLException
	{

		Cursor mCursor =

			database.query(true, table, new String[] {"*"}, "id" + "=" + rowId, null, null, null, null, null);

		if (mCursor != null)
		{
			mCursor.moveToFirst();
		}
		return mCursor;

	}
	public Cursor fetchData(String table,String type,int rowId) throws SQLException
	{

		Cursor mCursor =

			database.query(true, table, new String[] {"*"},type + "=" + rowId, null, null, null, null, null);

		if (mCursor != null)
		{
			mCursor.moveToFirst();
		}
		return mCursor;

	}
	public Cursor fetchData(String table,String type,String rowId) throws SQLException
	{

		Cursor mCursor =

			database.query(true, table, new String[] {"*"},type + "=" + rowId, null, null, null, null, null);

		if (mCursor != null)
		{
			mCursor.moveToFirst();
		}
		return mCursor;

	}
	public Cursor fetchData(String table,String type1,String rowId1,String type2,String str2) throws SQLException
	{

		Cursor mCursor =

			database.query(true, table, new String[] {"*"},type1 + "=" + rowId1+"and"+type2 + "=" + str2, null, null, null, null, null);

		if (mCursor != null)
		{
			mCursor.moveToFirst();
		}
		return mCursor;

	}
	public Cursor fetchData(String table,String type1,String rowId1,String type2,String str2,String type3,String str3) throws SQLException
	{

		Cursor mCursor =

			database.query(true, table, new String[] {"*"},type1 + "=" + rowId1+" and "+type2 + "=" + str2+" and "+type3 + "=" + str3, null, null, null, null, null);

		if (mCursor != null)
		{
			mCursor.moveToFirst();
		}
		return mCursor;

	}
	
	

	/* ����һ������ */
	public boolean updateData(long rowId, int num, String data)
	{
		ContentValues args = new ContentValues();
		args.put(KEY_NUM, num);
		args.put(KEY_DATA, data); 

		return database.update(DB_TABLE, args, KEY_ID + "=" + rowId, null) > 0;
	}
	public boolean updatelog(String table, int value,String id)
	{
		ContentValues args = new ContentValues();
		args.put("islog", value);
		args.put("user_id" ,id);
		return database.update(table, args,"id=?", new String[]{"1"}) > 0;
	}
	/* ��ѯһ�������б� */
	public Cursor fetchnews(long rowId) throws SQLException
	{

		Cursor mCursor =

			database.query(true, "newslist_table", new String[] { KEY_ID, KEY_NUM, KEY_DATA }, KEY_ID + "=" + rowId, null, null, null, null, null);

		if (mCursor != null)
		{
			mCursor.moveToFirst();
		}
		return mCursor;

	}
	/* �������������б� */
	public Cursor allnewslist()
	{
		open();
		return database.query("newslist_table", new String[] { "ID", "list_webname" }, null, null, null, null, null);
	}
	public ArrayList readCursor(Cursor cur)
	{	
		if (cur != null) {
            int NUM_CITY = cur.getCount();
            
    		ArrayList result =new ArrayList();

             if (cur.moveToFirst()) {
                do {
                	int column = cur.getColumnCount();
                	ArrayList arr = new ArrayList();
                	for(int j=0;j<column;j++)
                	{
                		arr.add(cur.getString(j));
                	}
                	result.add(arr);
                   
                  //  System.out.println(name);  //�������һ�䣬��select������Ϣ�����Logcat
                 
                } 
                while (cur.moveToNext());
            }
            return result;
        } else {
            return null;
        }
	}
	public boolean UpdateTb(String sql) {
        int flag =-1;
        try {
        	database.execSQL(sql);
        } catch (SQLException e) {
        	return false;
        }
        database.close();
        return true;
    }
	
}

