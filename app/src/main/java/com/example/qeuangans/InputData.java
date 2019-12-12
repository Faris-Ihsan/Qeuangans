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
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class InputData extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText jenispemasukan, jmlpemasukan;
    TextView tglpemasukan;
    Button inputmasuk;
    private DatePickerDialog.OnDateSetListener  setListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        myDB = new DatabaseHelper(this);

        //CASTING
        jenispemasukan = findViewById(R.id.jenispemasukan);
        jmlpemasukan = findViewById(R.id.jmlpemasukan);
        tglpemasukan = findViewById(R.id.tglpemasukan);
        tglpemasukan.setText(currentDate);
        inputmasuk = findViewById(R.id.inputmasuk);

        //KALENDER
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tglpemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        InputData.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                tglpemasukan.setText(date);
            }
        };
        //AKHIR KALENDER


        //PEMANGGILAN METHOD
        AddData();
    }

    //SCRIPT TAMBAH DATA DATABASE
    private void AddData() {
        inputmasuk.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            boolean isInseted = myDB.insertData(jenispemasukan.getText().toString(),
                                    jmlpemasukan.getText().toString(), tglpemasukan.getText().toString());
                            if (isInseted = true)
                                Toast.makeText(InputData.this, "INPUT BERHASIL", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(InputData.this, "INPUT GAGAL", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void pengeluaran(View view) {
        Intent intent = new Intent(InputData.this, InputData2.class);
        startActivity(intent);
    }
}
