package com.example.fxb.note;

import java.io.Serializable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcel;
import android.os.Parcelable;

public class Database extends SQLiteOpenHelper{
	final String CREATAE_TABLE_SQL="create table note(_id integer primary key autoincrement,time,text)";
	public Database(Context context, String name,
			int version) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
		
	}


@Override
public void onCreate(SQLiteDatabase db) {
	// TODO Auto-generated method stub
	db.execSQL(CREATAE_TABLE_SQL);
}

@Override
public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	// TODO Auto-generated method stub
	
}



 
		 
}
