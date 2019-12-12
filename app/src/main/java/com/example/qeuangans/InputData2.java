package com.example.qeuangans;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;


public class InputData2 extends AppCompatActivity {
    DatabaseHelper database;
    EditText jenispengeluaran, jmlpengeluaran;
    TextView tglpengeluaran;
    Button inputkeluar;
    private DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data2);

        database = new DatabaseHelper(this);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        jenispengeluaran = findViewById(R.id.jenispengeluaran);
        jmlpengeluaran = findViewById(R.id.jmlpengeluaran);
        tglpengeluaran = findViewById(R.id.tglpengeluaran);
        inputkeluar = findViewById(R.id.inputkeluar);

        //KALENDER
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tglpengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datepickerdialog = new DatePickerDialog(InputData2.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,year,month,day);
                datepickerdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datepickerdialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMont) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                tglpengeluaran.setText(date);
            }
        };
        //AKHIR KALENDER

        //Pemanggilan Method
        tambahData();
    }

    private void tambahData() { //penambahan method tambahData()
        inputkeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean mengambilData = database.insertDataPengeluaran(jenispengeluaran.getText().toString(),
                        jmlpengeluaran.getText().toString(), tglpengeluaran.getText().toString());
                if (mengambilData = true)
                    Toast.makeText(InputData2.this, "Input Berhasil", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(InputData2.this, "Input Data Gagal", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void pemasukan(View view) {
        Intent intent = new Intent(InputData2.this, InputData.class);
        startActivity(intent);

    }
}
