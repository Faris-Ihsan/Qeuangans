package com.example.qeuangans;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int NOTIFICATION_ID = 1;
    DatabaseHelper db;
    TextView textsaldo;
    Button list, tombolLaporan; // Solusi ERROR 13. Issues #14 //

    //Notification
    public static final String CHANNEL_ID = "01";
    public static final String Name = "Keuangans";
    public static final String Desc = "Desc";
    //private  TimerClass timerClass; // tai mer
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this); //Issues #19

        //CASTING
        textsaldo = findViewById(R.id.textsaldo);
        list = findViewById(R.id.List);
        tombolLaporan = findViewById(R.id.tombolLaporan); // Solusi ERROR 13. Issues #15

        //PEMANGGILAN METHOD
        jmlSaldo();

    }


    //METHOD SALDO
    private void jmlSaldo() {
        Cursor cursor = db.saldo();
        while (cursor.moveToNext()) {
            textsaldo.append("Rp " + cursor.getString(0) + ",00");
        }
    }

    //PINDAH TOMBOL INPUT DATA
    public void Pindah(View view) {
        Intent intent = new Intent(MainActivity.this, InputData.class);
        startActivity(intent);
    }

    public void list(View view) {
        Intent intent = new Intent(MainActivity.this, List.class);
        startActivity(intent);
    }

    public void Laporan(View view) {// Solusi ERROR 12. Issues #14 //
        Intent intent = new Intent(MainActivity.this, laporan.class);
        startActivity(intent);
    }


    public void Notiftest(View view) {
        long[] polagetar = {100, 100};
    NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Saldo Tipis")
            .setAutoCancel(true)
            .setContentText("Saldo anda kurang dari Rp200.000,-")
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)) //Menambahkan Suara Notifikasi buat sasa
            .setVibrate(polagetar); //menambahkan getar buat sasa
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,builder.build());

    }
}
