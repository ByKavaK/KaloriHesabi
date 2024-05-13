package com.example.kalorihesabi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class GirisYapActivity extends AppCompatActivity
{

    EditText girisKAdi, girisSifre;
    Button girisButton;
    TextView kayitGec;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girisyap);

        girisKAdi = findViewById(R.id.girisKAdi);
        girisSifre = findViewById(R.id.girisSifre);
        girisButton = findViewById(R.id.girisButton);
        kayitGec = findViewById(R.id.kayitGec);

        girisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!kAdiDogrula() | !sifreDogrula())
                {

                }
                else
                {
                    kullaniciKontrol();
                }
            }
        });

        kayitGec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GirisYapActivity.this, KayitOlActivity.class);
                startActivity(intent);
            }
        });
    }

    public Boolean kAdiDogrula()
    {
        String val = girisKAdi.getText().toString();
        if(val.isEmpty())
        {
            girisKAdi.setError("Kullanici Adi Bos Birakilamaz...");
            return false;
        }
        else
        {
            girisKAdi.setError(null);
            return true;
        }
    }

    public Boolean sifreDogrula()
    {
        String val = girisSifre.getText().toString();
        if(val.isEmpty())
        {
            girisSifre.setError("Sifre Bos Birakilamaz...");
            return false;
        }
        else
        {
            girisSifre.setError(null);
            return true;
        }
    }

    public void kullaniciKontrol()
    {
        String kKAdi = girisKAdi.getText().toString().trim();
        String kSifre = girisSifre.getText().toString().trim();

        DatabaseReference r = FirebaseDatabase.getInstance().getReference("kullanicilar");
        Query kullaniciDbKontrol = r.orderByChild("kAdi").equalTo(kKAdi);

        kullaniciDbKontrol.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    girisKAdi.setError(null);
                    String dbSifre = snapshot.child(kKAdi).child("sifre").getValue(String.class);

                    if (dbSifre.equals(kSifre))
                    {
                        girisKAdi.setError(null);
                        Intent intent = new Intent(GirisYapActivity.this, MainActivity.class);
                        intent.putExtra("kAdi", kKAdi);
                        startActivity(intent);
                        Toast.makeText(GirisYapActivity.this, "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        girisSifre.setError("Gecersiz Giris Bilgileri...");
                        girisSifre.requestFocus();
                    }
                }
                else
                {
                    girisKAdi.setError("Kullanici Bulunamadi...");
                    girisKAdi.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}