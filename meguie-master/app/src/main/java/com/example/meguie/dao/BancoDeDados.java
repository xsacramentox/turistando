package com.example.meguie.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.meguie.model.Cliente;
import com.example.meguie.model.Guia;
import com.example.meguie.model.Pagamento;
import com.example.meguie.model.Roteiro;
import com.example.meguie.model.Viagem;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends SQLiteOpenHelper {

    public static final String NOMEDB = "DBMG2";
    public static final String LOCALDB = "/data/data/com.example.meguie/databases/";
    public static final int VERSION = 1;
    private Context mContext;
    private SQLiteDatabase mSqLiteDatabase;


    public BancoDeDados(@Nullable Context context) {
        super(context, NOMEDB, null, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void openDataBase(){
        String dbPath = mContext.getDatabasePath(NOMEDB).getPath();
        if(mSqLiteDatabase != null && mSqLiteDatabase.isOpen()){
            return;
        }
        mSqLiteDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDataBase() {
        if (mSqLiteDatabase != null){
            mSqLiteDatabase.close();
        }
    }

    public List<Guia> allGuia(){
        openDataBase();
        mSqLiteDatabase = this.getWritableDatabase();
        List<Guia> listGuia = new ArrayList<Guia>();
        String sql = "SELECT * FROM TB_GUIA";
        Cursor cursor = mSqLiteDatabase.rawQuery(sql, null);
        if (cursor.getCount() > 0){
            if (cursor.moveToFirst()){
                do {
                    Guia g = new Guia();
                    g.setIdGuia(cursor.getInt(0));
                    g.setNome(cursor.getString(1));
                    g.setEmail(cursor.getString(2));
                    g.setInstagram(cursor.getString(4));
                    g.setCnpj(cursor.getString(5));
                    g.setEndereco(cursor.getString(6));
                    g.setDescricao(cursor.getString(7));
                    listGuia.add(g);
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
        mSqLiteDatabase.close();
        return listGuia;
    }

    public List<Pagamento> allPagamentos(){

        openDataBase();
        mSqLiteDatabase = this.getWritableDatabase();
        List<Pagamento> listPagamento = new ArrayList<Pagamento>();
        String sql = "SELECT * FROM TB_TIPO_PAGAMENTO";
        Cursor cursor = mSqLiteDatabase.rawQuery(sql, null);
        if (cursor.getCount() > 0){
            if (cursor.moveToFirst()){
                do {
                    Pagamento p= new Pagamento();
                    p.setId(cursor.getInt(0));
                    p.setDescricao(cursor.getString(1));
                    listPagamento.add(p);
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
        mSqLiteDatabase.close();
        return listPagamento;

    }

    public List<Roteiro> allRoteiros(){
        openDataBase();
        mSqLiteDatabase = this.getWritableDatabase();
        List<Roteiro> listRoteiro = new ArrayList<Roteiro>();
        String sql = "SELECT * FROM TB_ROTEIRO";
        Cursor cursor = mSqLiteDatabase.rawQuery(sql, null);
        if (cursor.getCount() > 0){
            if (cursor.moveToFirst()){
                do {
                    Roteiro r = new Roteiro();
                    r.setId(cursor.getInt(0));
                    r.setDescricao(cursor.getString(1));
                    r.setTitulo(cursor.getString(2));
                    r.setCidade(cursor.getString(3));
                    r.setPreco(cursor.getString(4));
                    r.setDuracao(cursor.getString(5));
                    listRoteiro.add(r);
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
        mSqLiteDatabase.close();
        return listRoteiro;
    }

    public List<ViagemDados> allViagens(String idCliente) {

        openDataBase();
        mSqLiteDatabase = this.getWritableDatabase();
        List<ViagemDados> listViagem = new ArrayList<ViagemDados>();
        String sql = "select V.ID_VIAGEM, V.DATA_VIAGEM, C.NOME, G.NOME, R.TITULO, R.CIDADE, R.DESCRICAO, SV.DESCRICAO, TP.DESCRICAO from TB_VIAGEM as V \n" +
                "inner join TB_CLIENTE as C on V.ID_CLIENTE == C.ID_CLIENTE \n" +
                "inner join TB_GUIA as G on V.ID_GUIA == G.ID_GUIA \n" +
                "inner join TB_ROTEIRO AS R on R.ID_ROTEIRO == V.ID_ROTEIRO \n" +
                "inner join TB_STATUS_VIAGEM as SV on V.ID_STATUS_VIAGEM == SV.ID_STATUS \n" +
                "inner join TB_TIPO_PAGAMENTO as TP on V.ID_PAGAMENTO == TP.ID_PAGAMENTO \n" +
                "where V.ID_CLIENTE == " + idCliente;

        Cursor cursor = mSqLiteDatabase.rawQuery(sql, null);
        if (cursor.getCount() > 0){
            if (cursor.moveToFirst()){
                do {
                    ViagemDados v = new ViagemDados();
                    v.setId(cursor.getInt(0));
                    v.setDataViagem(cursor.getString(1));
                    v.setNomeCliente(cursor.getString(2));
                    v.setNomeGuia(cursor.getString(3));
                    v.setTituloRoteiro(cursor.getString(4));
                    v.setCidade(cursor.getString(5));
                    v.setDescricaoRoteiro(cursor.getString(6));
                    v.setStatusViagem(cursor.getString(7));
                    v.setTipoPagamento(cursor.getString(8));
                    listViagem.add(v);
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
        mSqLiteDatabase.close();
        return listViagem;

    }

    public boolean salvarDadosCliente(Cliente cliente){

        openDataBase();
        mSqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME",cliente.getNome());
        contentValues.put("EMAIL",cliente.getEmail());
        contentValues.put("SENHA",cliente.getSenha());
        contentValues.put("TELEFONE",cliente.getTelefone());
        contentValues.put("CPF",cliente.getCpf());

        long result = mSqLiteDatabase.insert("TB_CLIENTE", null,contentValues);
        mSqLiteDatabase.close();
        if (result==1) return false;
        else
            return true;
    }

    public boolean salvarDadosViagem(Viagem viagem) {

        openDataBase();
        mSqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATA_VIAGEM",viagem.getDataViagem());
        contentValues.put("ID_CLIENTE",viagem.getIdCliente());
        contentValues.put("ID_GUIA",viagem.getIdGuia());
        contentValues.put("ID_ROTEIRO",viagem.getIdRoteiro());
        contentValues.put("ID_STATUS_VIAGEM",viagem.getIdStatusViagem());
        contentValues.put("ID_PAGAMENTO",viagem.getIdPagamento());


        long result = mSqLiteDatabase.insert("TB_VIAGEM", null,contentValues);
        mSqLiteDatabase.close();
        if (result==1) return false;
        else
            return true;

    }

    public boolean checkEmail(String email){

        openDataBase();
        mSqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = mSqLiteDatabase.rawQuery("select * from TB_CLIENTE where EMAIL = ?", new String[]{email});

        if (cursor.getCount()>0){
            cursor.close();
            mSqLiteDatabase.close();
            return true;
        }
        else{
            cursor.close();
            mSqLiteDatabase.close();
            return false;
        }
    }

    public Cliente checkEmailSenha(String email, String senha){

        openDataBase();
        mSqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = mSqLiteDatabase.rawQuery("select * from TB_CLIENTE where EMAIL = ? and SENHA = ?", new String[]{email, senha});
        Cliente cliente = new Cliente();

        if (cursor.getCount()>0){

            if (cursor.moveToFirst()){
                do {

                    cliente.setId(cursor.getString(0));
                    cliente.setNome(cursor.getString(1));
                }while (cursor.moveToNext());
            }

            cursor.close();
            mSqLiteDatabase.close();
            return cliente;
        }
        else{
            cursor.close();
            mSqLiteDatabase.close();
            return null;
        }
    }

    public String checkPag(String pag){

        openDataBase();
        mSqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = mSqLiteDatabase.rawQuery("select * from TB_TIPO_PAGAMENTO where DESCRICAO = ?", new String[]{pag});
        String idPag = "0";

        if (cursor.getCount()>0){

            if (cursor.moveToFirst()){
                do {
                    idPag = cursor.getString(0);
                }while (cursor.moveToNext());
            }

            cursor.close();
            mSqLiteDatabase.close();
            return idPag;
        }
        else{
            cursor.close();
            mSqLiteDatabase.close();
            return null;
        }
    }

}
