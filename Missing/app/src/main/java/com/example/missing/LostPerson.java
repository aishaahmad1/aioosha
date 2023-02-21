package com.example.missing;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Calendar;

public class LostPerson extends AppCompatActivity {

    Database db = new Database(LostPerson.this);
    EditText etSelectData, Location, name, age, phone, email,ID;
    ImageButton imagebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lostperson);

        etSelectData = (EditText) findViewById(R.id.etSelectData);
        Location = (EditText) findViewById(R.id.location);
        name = (EditText) findViewById(R.id.Name);
        age = (EditText) findViewById(R.id.age);
        phone = (EditText) findViewById(R.id.phone);
        ID = (EditText) findViewById(R.id.N_ID);
        email = (EditText) findViewById(R.id.email);
        imagebutton = (ImageButton) findViewById(R.id.imageButton);

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etSelectData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(LostPerson.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        etSelectData.setText(date);
                    }
                }, year, month, day);
                dialog.show();
            }
        });

    }

    public void openGallerie(View view) {
        Intent intentimg = new Intent(Intent.ACTION_GET_CONTENT);
        intentimg.setType("image/*");
        startActivityForResult(intentimg, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 100) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                imagebutton.setImageBitmap(decodeStream);
            } catch (Exception ex) {
                Log.e("ex", ex.getMessage());
            }
        }
    }

    public void btn_add_data(View view) {
        boolean resulte = false;
        String Name = name.getText().toString();
        String Age = age.getText().toString();
        String Phone = phone.getText().toString();
        String Id = ID.getText().toString();
        String Email = email.getText().toString();
        String Date = etSelectData.getText().toString();
        String LOCATION = Location.getText().toString();

            if (!Name.isEmpty() && !Age.isEmpty()&& !Id.isEmpty() && !Phone.isEmpty() && !Date.isEmpty() && !LOCATION.isEmpty()) {
                resulte = db.insertData(Name, Age, Phone,Id, Email, Date, LOCATION);
            }
            if (resulte == true) {
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                name.setText("");
                age.setText("");
                phone.setText("");
                ID.setText("");
                email.setText("");
                etSelectData.setText("");
                Location.setText("");
            } else {
                if (Name.isEmpty() || Age.isEmpty() || Phone.isEmpty() ||Id.isEmpty() ||Date.isEmpty() || LOCATION.isEmpty()) {
                    {
                        Toast.makeText(this, "Empty field not allowed !", Toast.LENGTH_SHORT).show();
                    }
            }
        }

    }
}