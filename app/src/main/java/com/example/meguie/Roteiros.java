package com.example.meguie;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.meguie.dao.BancoDeDados;
import com.example.meguie.design.GuiaAdapter;
import com.example.meguie.design.RoteiroAdapter;
import com.example.meguie.model.Guia;
import com.example.meguie.model.Roteiro;

import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
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
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Roteiros#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Roteiros extends Fragment {

    private ListView lvRoteiros;
    private List<Roteiro> listRoteiros = new ArrayList<Roteiro>();
    private BancoDeDados mBancoDeDados;
    private Context mContext;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Roteiros() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Roteiros.
     */
    // TODO: Rename and change types and number of parameters
    public static Roteiros newInstance(String param1, String param2) {
        Roteiros fragment = new Roteiros();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_roteiros, container, false);
            lvRoteiros = (ListView) view.findViewById(R.id.listRoteiros);
            popularLista();

            Intent intent = getActivity().getIntent();
            String idCliente = (String) intent.getSerializableExtra("id");
            String nomeCliente = (String) intent.getSerializableExtra("nome");

            lvRoteiros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), guias.class);

                    intent.putExtra("id", listRoteiros.get(position).getId());
                    intent.putExtra("titulo", listRoteiros.get(position).getTitulo());
                    intent.putExtra("cidade", listRoteiros.get(position).getCidade());
                    intent.putExtra("preco", listRoteiros.get(position).getPreco());
                    intent.putExtra("duracao", listRoteiros.get(position).getDuracao());
                    intent.putExtra("descricao", listRoteiros.get(position).getDescricao());
                    intent.putExtra("idCliente", idCliente);
                    intent.putExtra("nomeCliente", nomeCliente);

                    startActivity(intent);

                }

            });
        return view;
    }

    private void popularLista() {
        mBancoDeDados = new BancoDeDados(this.getActivity());
        listRoteiros.clear();
        listRoteiros = mBancoDeDados.allRoteiros();

        ArrayList<Roteiro> arrayList = new ArrayList<>();

        for (Roteiro roteiro : listRoteiros){
            arrayList.add(new Roteiro(roteiro.getTitulo(), roteiro.getCidade(), roteiro.getPreco(), roteiro.getDuracao(), roteiro.getDescricao(), R.mipmap.spimage_foreground));
        }

        //GuiaAdapter guiaAdapter = new GuiaAdapter(getActivity(),R.layout.list_roll,arrayList);
        RoteiroAdapter roteiroAdapter = new RoteiroAdapter(this.getActivity(),R.layout.list_roll_roteiros,arrayList);

        lvRoteiros.setAdapter(roteiroAdapter);

    }

}
