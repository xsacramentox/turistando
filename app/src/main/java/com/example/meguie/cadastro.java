package com.example.meguie;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.meguie.dao.BancoDeDados;
import com.example.meguie.model.Cliente;
import com.example.meguie.model.Guia;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.time.temporal.TemporalUnit;


public class cadastro extends AppCompatActivity {

    private EditText editNome, editEmail, editSenha, editCPF, editTel;
    private Button btnCadastrar;
    BancoDeDados DB = new BancoDeDados(this);
    private Cliente cliente = new Cliente();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        InicializarComponentes();

        SimpleMaskFormatter smf = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(editTel,smf);
        editTel.addTextChangedListener(mtw);

        SimpleMaskFormatter smfcpf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtwcpf = new MaskTextWatcher(editCPF,smfcpf);
        editCPF.addTextChangedListener(mtwcpf);

        Cadastrar();


    }

    private void Cadastrar() {
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cliente.setNome(editNome.getText().toString());
                cliente.setSenha(editSenha.getText().toString());
                cliente.setEmail(editEmail.getText().toString());
                cliente.setCpf(editCPF.getText().toString());
                cliente.setTelefone(editTel.getText().toString());

                if (cliente.getNome().equals("") || cliente.getSenha().equals("") || cliente.getEmail().equals("") || cliente.getCpf().equals("") || cliente.getTelefone().equals("")){
                    Toast.makeText(cadastro.this,"Preencha todos os campos!", Toast.LENGTH_SHORT).show();

                } else if (cliente.getCpf().length()<14) {

                    Toast.makeText(cadastro.this,"CPF inválido", Toast.LENGTH_SHORT).show();

                } else if (cliente.getTelefone().length()<15) {

                    Toast.makeText(cadastro.this,"Número de celular inválido", Toast.LENGTH_SHORT).show();

                } else {

                    AlertDialog dialog = new AlertDialog.Builder(cadastro.this)
                            .setTitle("Confirmar")
                            .setMessage("Deseja realizar o cadastro?")
                            .setPositiveButton("Sim", null)
                            .setNegativeButton("Cancelar", null)
                            .show();

                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Boolean insert = DB.salvarDadosCliente(cliente);
                            if (insert==true){
                                Toast.makeText(cadastro.this,"Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),guias.class);
                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(cadastro.this,"Cadastro falhou", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void InicializarComponentes() {
        editNome = findViewById(R.id.editTextNome);
        editEmail = findViewById(R.id.editTextEmail);
        editSenha = findViewById(R.id.editTextSenha);
        editCPF = findViewById(R.id.editTextCpf);
        editTel = findViewById(R.id.editTextTelefone);

        btnCadastrar = findViewById(R.id.btnCadastrar);
    }
}