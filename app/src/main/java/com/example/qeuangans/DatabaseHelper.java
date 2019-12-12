package com.example.qeuangans;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Qeuangan.db";
    public static final String TBLNAME = "tabungan";
    public static final String COL1 = "ID";
    public static final String COL2 = "JENIS_PEMASUKAN";
    public static final String COL3 = "PEMASUKAN";
    public static final String COL4 = "JENIS_PENGELUARAN";
    public static final String COL5 = "PENGELUARAN";
    public static final String COL6 = "TANGGAL";


    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TBLNAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, JENIS_PEMASUKAN STRING," +
                "PEMASUKAN NUMBER, JENIS_PENGELUARAN STRING, PENGELUARAN NUMBER, TANGGAL STRING)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TBLNAME);
        onCreate(db);
    }

    //UNTUK INPUT DATA
    public boolean insertData(String jenis_pemasukan, String pemasukan, String tanggal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, jenis_pemasukan);
        contentValues.put(COL3, pemasukan);
        contentValues.put(COL6, tanggal);

        long result = db.insert(TBLNAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    //SCRIPT MENAMPILKAN DATA
    public Cursor tampilkanData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res;
        res = db.rawQuery("SELECT *FROM "+TBLNAME,null);
        return res;
    }

    public Cursor saldo(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor saldo;
        saldo = db.rawQuery("SELECT SUM(" +COL3+")-SUM(" +COL5+") FROM "+TBLNAME, null);
        return saldo;
    }


    public void hapusData(long position) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TBLNAME, COL1+ "=" +position,null);
    }

    public Cursor datalaporankeluar(){ // Cannot return value from void method.  // Solusi ERROR 18. Issues #20 //
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor saldo;
        saldo = db.rawQuery("SELECT SUM(" +COL3+")-SUM(" +COL5+") FROM "+TBLNAME, null);
        return saldo;
    }
}
