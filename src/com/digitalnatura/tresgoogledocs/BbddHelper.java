package com.digitalnatura.tresgoogledocs;


import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import  android.database.SQLException;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;




public class BbddHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME= "escaleta.db";
	private static final int SCHEMA_VERSION = 1;
	
	
	
	
	

	public BbddHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DATABASE_NAME, factory, SCHEMA_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE escaleta (_id INTEGER NOT NULL  UNIQUE  DEFAULT CURRENT_TIMESTAMP, titulo TEXT NOT NULL  UNIQUE, URL TEXT NOT NULL  UNIQUE );");
		db.execSQL("CREATE TABLE apariciones (personaje INTEGER NOT NULL , nombre TEXT NOT NULL , secuencia INTEGER NOT NULL , escaleta INTEGER NOT NULL );");
		db.execSQL("CREATE TABLE localizacions (id INTEGER NOT NULL  DEFAULT CURRENT_TIMESTAMP, lugar TEXT NOT NULL , secuencia INTEGER NOT NULL , escaleta INTEGER NOT NULL );");
		db.execSQL("CREATE TABLE secuencias (id INTEGER NOT NULL  UNIQUE  DEFAULT CURRENT_TIMESTAMP, orden INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , int_ext TEXT NOT NULL , dia_noche TEXT NOT NULL , localizacion TEXT NOT NULL , accion TEXT NOT NULL , escaleta INTEGER NOT NULL );");
		db.execSQL("CREATE TABLE acotaciones (acotaciones TEXT UNIQUE )");
		}

	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// no-op, since will not be called until 2nd schema
		// version exists

		
		
	}
	
	
	
	public void insertarTitulo(String _id, String titulo, String url){
		ContentValues cv = new ContentValues();
		
		cv.put("_id", _id);
		cv.put("titulo", titulo);
		cv.put("URL", url);
		
		
		getWritableDatabase().insert("escaleta", "escaleta", cv);
		
		
	}

	
	
//	este metodo e un xenerador de id, colle a fecha hora minuto e segundo e convirtea para ter unha id unica
	public String getID() {
		// TODO Auto-generated method stub
	        	        
	        Calendar c = Calendar.getInstance();
	        
	        int dia= c.get(Calendar.DAY_OF_MONTH);
	        int ano= c.get(Calendar.YEAR);
	        int mes= c.get(Calendar.MONTH)+1;
	        
	        int hora= c.get(Calendar.HOUR_OF_DAY);
	        int minuto=c.get(Calendar.MINUTE);
	        int segundo=c.get(Calendar.SECOND);

		return ""+ano+mes+dia+hora+minuto+segundo;
	}
	
	

}
