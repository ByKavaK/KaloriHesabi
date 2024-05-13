package com.example.kalorihesabi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KayitOlActivity extends AppCompatActivity {

    EditText kayitOlIsim, kayitOlEmail, kayitOlKAdi, kayitOlSifre;
    TextView loginGec;
    Button kayitOlButton;
    FirebaseDatabase db;
    DatabaseReference r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayitol);

        kayitOlIsim = findViewById(R.id.kayitOlIsim);
        kayitOlEmail = findViewById(R.id.kayitOlEmail);
        kayitOlKAdi = findViewById(R.id.kayitOlKAdi);
        kayitOlSifre = findViewById(R.id.kayitOlSifre);
        loginGec = findViewById(R.id.loginGec);
        kayitOlButton = findViewById(R.id.kayitOlButton);

        kayitOlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db = FirebaseDatabase.getInstance();
                r = db.getReference("kullanicilar");

                String isim = kayitOlIsim.getText().toString();
                String email = kayitOlEmail.getText().toString();
                String kAdi = kayitOlKAdi.getText().toString();
                String sifre = kayitOlSifre.getText().toString();

                HelperClass helperClass = new HelperClass(isim, email, kAdi, sifre);
                r.child(kAdi).setValue(helperClass);

                Toast.makeText(KayitOlActivity.this, "Başarıyla Kayıt Oldunuz...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(KayitOlActivity.this, GirisYapActivity.class);
                startActivity(intent);

            }
        });

        loginGec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KayitOlActivity.this, GirisYapActivity.class);
                startActivity(intent);
            }
        });


    }
}