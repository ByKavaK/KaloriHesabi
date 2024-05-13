package com.example.kalorihesabi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterBilgiler extends RecyclerView.Adapter<MyAdapterBilgiler.MyViewHolder>
{

    Context context;
    List<Kayit> kayitList;

    public MyAdapterBilgiler(Context context, List<Kayit> kayitList)
    {
        this.context = context;
        this.kayitList = kayitList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new MyAdapterBilgiler.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.bilgiler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        Kayit kayit = kayitList.get(position);

        String tarih= kayit.getTarih();
        String kalori= kayit.getKalori();
        String vakit= kayit.getVakit();

        holder.texttarih.setText(tarih);
        holder.textkalori.setText(kalori);
        holder.textvakit.setText(vakit);

    }

    @Override
    public int getItemCount()
    {
        return kayitList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView texttarih,textvakit,textkalori;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            texttarih = itemView.findViewById(R.id.textViewTarih);
            textkalori = itemView.findViewById(R.id.textViewKalori);
            textvakit = itemView.findViewById(R.id.textViewVakit);
        }
    }

}
