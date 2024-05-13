package com.example.kalorihesabi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentBilgi extends Fragment
{
    View view;
    RecyclerView rv;
    Button del;
    List<Kayit> bilgilist;
    DatabaseReference databaseReference;

    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_bilgi, container, false);
        del = view.findViewById(R.id.deletebutton);
        pull();
        return view;
    }

    private void pull()
    {
        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("hhttps://kalorihesabi-40267-default-rtdb.europe-west1.firebasedatabase.app/");
        rv=view.findViewById(R.id.myrecycler);

        bilgilist= new ArrayList<>();
        Intent ii=getActivity().getIntent();
        String data = ii.getStringExtra("kAdi");

        Query query=databaseReference.child("kullanicilar").child(data).child("Bilgi");

        query.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    String kalori2=dataSnapshot.child("Kalori").getValue().toString();
                    String tarih2=dataSnapshot.child("Tarih").getValue().toString();
                    String vakit2=dataSnapshot.child("Vakit").getValue().toString();
                    bilgilist.add(new Kayit(tarih2,kalori2,vakit2));
                }
                rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
                rv.setAdapter(new MyAdapterBilgiler(view.getContext(),bilgilist));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("kullanicilar").child(data).child("Bilgi").removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@NonNull DatabaseError error, @NonNull DatabaseReference ref) {
                        if (error == null) {
                            bilgilist.clear();
                            rv.getAdapter().notifyDataSetChanged();
                        } else {
                            Log.e("Firebase", "Veri silinirken hata oluştu: " + error.getMessage());
                        }
                    }
                });
            }
        });

    }
}
