package com.example.meguie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meguie.dao.BancoDeDados;
import com.example.meguie.dao.ViagemDados;
import com.example.meguie.design.RoteiroAdapter;
import com.example.meguie.design.RoteiroAdapterHome;
import com.example.meguie.design.ViagemAdapter;
import com.example.meguie.model.Pagamento;
import com.example.meguie.model.Roteiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView txtOla;
    private ListView lvRoteiroAleatorio;
    private ListView lvRoteiroHome;
    private List<Roteiro> listRoteirosHome = new ArrayList<Roteiro>();
    private BancoDeDados mBancoDeDados;
    private Context mContext;

    private ListView lvViagens;
    private List<ViagemDados> listViagens = new ArrayList<ViagemDados>();

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Intent intent = getActivity().getIntent();
        String idCliente = (String) intent.getSerializableExtra("id");
        String nomeCliente = (String) intent.getSerializableExtra("nome");

        txtOla = view.findViewById(R.id.txtOla);
        txtOla.setText("Olá, " + nomeCliente);

        lvRoteiroHome = (ListView) view.findViewById(R.id.listRoteiroHome);
        lvViagens = (ListView) view.findViewById(R.id.listViewViagens);

        popularLista();
        popularlistaViagem(idCliente);

        lvViagens.setEmptyView(view.findViewById(R.id.txtEmpty));


        lvRoteiroHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), guias.class);

                intent.putExtra("id", listRoteirosHome.get(position).getId());
                intent.putExtra("titulo", listRoteirosHome.get(position).getTitulo());
                intent.putExtra("cidade", listRoteirosHome.get(position).getCidade());
                intent.putExtra("preco", listRoteirosHome.get(position).getPreco());
                intent.putExtra("duracao", listRoteirosHome.get(position).getDuracao());
                intent.putExtra("descricao", listRoteirosHome.get(position).getDescricao());
                intent.putExtra("idCliente", idCliente);
                intent.putExtra("nomeCliente", nomeCliente);

                startActivity(intent);

            }
        });
        return view;
    }

    private void popularLista() {
        mBancoDeDados = new BancoDeDados(this.getActivity());
        listRoteirosHome.clear();
        listRoteirosHome = mBancoDeDados.allRoteiros();

        ArrayList<Roteiro> arrayList = new ArrayList<Roteiro>();
        ArrayList<Roteiro> arrayListAleatorio = new ArrayList<Roteiro>();

        for (Roteiro roteiro : listRoteirosHome){
            arrayList.add(new Roteiro(roteiro.getTitulo(), roteiro.getCidade(), roteiro.getPreco(), roteiro.getDuracao(), roteiro.getDescricao(), R.mipmap.spimage_foreground));
        }

        Random rnd = new Random();

        arrayListAleatorio.add(arrayList.get(rnd.nextInt(arrayList.size())));

        RoteiroAdapterHome roteiroAdapterHome = new RoteiroAdapterHome(this.getActivity(),R.layout.list_roteiro_home,arrayListAleatorio);

        lvRoteiroHome.setAdapter(roteiroAdapterHome);

    }

    private void popularlistaViagem(String idCliente) {

        mBancoDeDados = new BancoDeDados(this.getActivity());
        listViagens.clear();
        listViagens = mBancoDeDados.allViagens(idCliente);

        ArrayList<ViagemDados> arrayList = new ArrayList<>();

        for (ViagemDados viagem : listViagens){
            arrayList.add(new ViagemDados(viagem.getDataViagem(), viagem.getNomeGuia(), viagem.getTituloRoteiro(), viagem.getStatusViagem(), viagem.getCidade(), viagem.getDescricaoRoteiro(), R.mipmap.spimage_foreground));
        }

        ViagemAdapter viagemAdapter = new ViagemAdapter(this.getActivity(),R.layout.list_roll_passeios,arrayList);

        lvViagens.setAdapter(viagemAdapter);

    }

}