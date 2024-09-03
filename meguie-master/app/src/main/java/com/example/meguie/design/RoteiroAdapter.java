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
import com.example.meguie.model.Roteiro;

import java.util.ArrayList;

public class RoteiroAdapter extends ArrayAdapter<Roteiro> {

    private Context mContext;
    private int mResource;

    public RoteiroAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Roteiro> objects) {
        super(context, resource, objects);
        this.mContext=context;
        this.mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource,parent,false);

        ImageView imageView = convertView.findViewById(R.id.image_rot);

        TextView textoTitulo = convertView.findViewById(R.id.textoTitulo);

        TextView textoCidade = convertView.findViewById(R.id.textoCidade);

        TextView textoDescricao = convertView.findViewById(R.id.textoDescricao);

        TextView textoDuracao = convertView.findViewById(R.id.textoDuracao);

        TextView textoPreco = convertView.findViewById(R.id.textoPreco);


        imageView.setImageResource(getItem(position).getImagem());

        textoTitulo.setText(getItem(position).getTitulo());

        textoCidade.setText(getItem(position).getCidade());

        textoDescricao.setText(getItem(position).getDescricao());

        textoDuracao.setText(getItem(position).getDuracao()+" horas");

        textoPreco.setText("R$ "+getItem(position).getPreco()+",00");

        //textoDes.setText(getItem(position).getIdGuia());

        return convertView;
    }

}
