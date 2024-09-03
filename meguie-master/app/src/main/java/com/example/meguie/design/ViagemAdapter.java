package com.example.meguie.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.meguie.R;
import com.example.meguie.dao.ViagemDados;
import com.example.meguie.model.Roteiro;

import java.util.ArrayList;

public class ViagemAdapter extends ArrayAdapter<ViagemDados> {


    private Context mContext;
    private int mResource;

    public ViagemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ViagemDados> objects) {
        super(context, resource, objects);
        this.mContext=context;
        this.mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource,parent,false);

        ImageView imageView = convertView.findViewById(R.id.imagemSPHome);

        TextView textoTituloHome = convertView.findViewById(R.id.textoTituloHome);

        TextView textoCidadeHome = convertView.findViewById(R.id.textoCidadeHome);

        TextView textoDescricaoHome = convertView.findViewById(R.id.textoDescricaoHome);

        TextView textoDataHome = convertView.findViewById(R.id.textoDataHome);

        TextView textStatusHome = convertView.findViewById(R.id.textoStatusHome);


        imageView.setImageResource(getItem(position).getImagem());

        textoTituloHome.setText(getItem(position).getTituloRoteiro());

        textoCidadeHome.setText(getItem(position).getCidade());

        textoDescricaoHome.setText(getItem(position).getDescricaoRoteiro());

        textoDataHome.setText(getItem(position).getDataViagem());

        textStatusHome.setText("Status: " + getItem(position).getStatusViagem());

        return convertView;
    }

}
