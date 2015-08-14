package com.test.videoplayer.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Model extends SQLiteOpenHelper{
	
	protected SQLiteDatabase db;
	private static String DataBase = "dbVideos";
	private static int VersionDB = 1;
	private String tabla;
	private String campos;
	private String query = "";
	private Cursor cursor;
	
	public Model(Context context, String tabla, String campos){
		super(context, DataBase, null, VersionDB);
		this.tabla = tabla;
		this.campos = campos;
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		query = "Create table " + this.tabla + "(" + this.campos +" )";
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	protected void insertInTo(String tabla, String columnas, String valores){
		query = "INSERT OR REPLACE INTO " + tabla + "(" + columnas + ") VALUES (" + valores + ")";
		db.execSQL(query);
	}
	
	protected Cursor selectAll(String tabla, String columnas){
		cursor = db.rawQuery("SELECT " + columnas + " FROM " + tabla, null);
		Log.d("QUERY",""+cursor.getCount());
		Log.d("","SELECT " + columnas + " FROM " + tabla);
		return cursor;
	}
	
}
