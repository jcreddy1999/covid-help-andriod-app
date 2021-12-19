package com.bosscoder.covidhelp.CovidVacciine;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bosscoder.covidhelp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class VaccineActivity extends AppCompatActivity {

    private EditText pinnum;
    private ImageView date_choose;
    private TextView button, date_show;
    String strdate = "";
    final Calendar myCalender = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);

        pinnum = findViewById(R.id.pin_input);
        date_choose = findViewById(R.id.image_view);
        button = findViewById(R.id.button);
        date_show = findViewById(R.id.date_show);


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalender.set(Calendar.YEAR, year);
                myCalender.set(Calendar.MONTH, month);
                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        date_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(VaccineActivity.this, date, myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String string_pin = pinnum.getText().toString();

                if (TextUtils.isEmpty(string_pin) || TextUtils.isEmpty(strdate)){
                    Toast.makeText(VaccineActivity.this, "Enter Pin and Date correctly", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent1 = new Intent(VaccineActivity.this, ResaultShowActivity.class);
                    intent1.putExtra("PIN", string_pin);
                    intent1.putExtra("DATE", strdate);
                    startActivity(intent1);
                    pinnum.setText("");
                    strdate = "";
                    date_show.setText("dd-MM-yyyy");
                }
            }
        });

    }

    private void updateLabel(){
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        strdate = sdf.format(myCalender.getTime()) ;
        date_show.setText(sdf.format(myCalender.getTime()));
    }
}