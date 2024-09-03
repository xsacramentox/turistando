package com.example.meguie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meguie.dao.BancoDeDados;
import com.example.meguie.model.Cliente;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private BancoDeDados mBancoDeDados;
    Button btnTrocar, btnLogin;
    EditText editEmail, editSenha;
    BancoDeDados DB = new BancoDeDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intentCadastro = new Intent(this, cadastro.class);
        Intent intentBypass = new Intent(this, homepage.class);

        inicializarComponentes();
        inicializarBancoDeDados();
        mudarTelaCadastro(intentCadastro);
        fazerLogin();

    }


    private void mudarTelaCadastro(Intent intent) {
        btnTrocar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
    private void fazerLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String senha = editSenha.getText().toString();

                if (email.equals("")|| senha.equals("")){
                    Toast.makeText(MainActivity.this,"Preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    Cliente checkLogin = DB.checkEmailSenha(email,senha);
                    if (checkLogin!= null){
                        Toast.makeText(MainActivity.this,"Login feito com sucesso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), homepage.class);

                        intent.putExtra("id", checkLogin.getId());
                        intent.putExtra("nome", checkLogin.getNome());

                        startActivity(intent);

                        finish();

                    } else {
                        Toast.makeText(MainActivity.this,"Email ou senha inválidos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void inicializarComponentes() {
        btnTrocar = findViewById(R.id.btnTrocar);
        btnLogin = findViewById(R.id.btnLogin);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
    }

    private void inicializarBancoDeDados() {
        mBancoDeDados = new BancoDeDados(this);

        File database = getApplicationContext().getDatabasePath(BancoDeDados.NOMEDB);
        if (database.exists() == false){
            mBancoDeDados.getReadableDatabase();
            if (copiaBanco(this)){
                Toast.makeText(MainActivity.this,"Banco copiado com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this,"Erro ao copiar banco", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean copiaBanco(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(BancoDeDados.NOMEDB);
            String outFile = BancoDeDados.LOCALDB + BancoDeDados.NOMEDB;
            OutputStream outputStream = new FileOutputStream(outFile);

            byte[] buff = new byte[1024];
            int length = 0;

            while ((length = inputStream.read(buff)) > 0){
                outputStream.write(buff, 0 , length);
            }
            outputStream.flush();
            outputStream.close();
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}