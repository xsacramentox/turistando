package com.example.meguie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.meguie.dao.BancoDeDados;
import com.example.meguie.design.GuiaAdapter;
import com.example.meguie.model.Guia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class guias extends AppCompatActivity {

    private BancoDeDados mBancoDeDados;
    private ListView lvGuia;
    private List<Guia> listGuia = new ArrayList<Guia>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guias);

        Intent intent = getIntent();
        int idRoteiro = (int) intent.getSerializableExtra("id");
        String tituloRoteiro = (String) intent.getSerializableExtra("titulo");
        String cidadeRoteiro = (String) intent.getSerializableExtra("cidade");
        String precoRoteiro = (String) intent.getSerializableExtra("preco");
        String duracaoRoteiro = (String) intent.getSerializableExtra("duracao");
        String descricaoRoteiro = (String) intent.getSerializableExtra("descricao");

        String idCliente = (String) intent.getSerializableExtra("idCliente");
        String nomeCliente = (String) intent.getSerializableExtra("nomeCliente");

        inicializarComponentes();

        popularLista();


        lvGuia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(guias.this, checkout.class);

                intent.putExtra("id", listGuia.get(position).getIdGuia());
                intent.putExtra("nome", listGuia.get(position).getNome());
                intent.putExtra("instagram", listGuia.get(position).getInstagram());
                intent.putExtra("cnpj", listGuia.get(position).getCnpj());
                intent.putExtra("idRoteiro", idRoteiro);
                intent.putExtra("tituloRoteiro", tituloRoteiro);
                intent.putExtra("cidadeRoteiro", cidadeRoteiro);
                intent.putExtra("precoRoteiro", precoRoteiro);
                intent.putExtra("duracaoRoteiro", duracaoRoteiro);
                intent.putExtra("descricaoRoteiro", descricaoRoteiro);
                intent.putExtra("idCliente", idCliente);
                intent.putExtra("nomeCliente", nomeCliente);

                startActivity(intent);
            }
        });
    }

    private void inicializarComponentes() {
        lvGuia = (ListView) findViewById(R.id.lvGuia);
    }

    private void popularLista() {
        mBancoDeDados = new BancoDeDados(this);
        listGuia.clear();
        listGuia = mBancoDeDados.allGuia();

        ArrayList<Guia> arrayList = new ArrayList<>();

        for (Guia guia : listGuia){
            arrayList.add(new Guia(R.drawable.ic_baseline_account_circle_24, guia.getNome(), guia.getInstagram(), guia.getDescricao()));
        }

        GuiaAdapter guiaAdapter = new GuiaAdapter(this,R.layout.list_roll,arrayList);

        lvGuia.setAdapter(guiaAdapter);

    }
}