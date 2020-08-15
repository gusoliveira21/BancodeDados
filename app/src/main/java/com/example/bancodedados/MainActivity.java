package com.example.bancodedados;

//import android.content.Context;
import android.os.Bundle;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    ListView mostraConteudo;
    TextView campoNome;
    TextView campoEmail;
    TextView campoNumero;
    TextView campoID;
    TextView contagem;
    TextView campoApelido;
    Button botaoDelete;

    Button botaoAlterar;
    Button botaoInserir;
    //String AUX = null;
    //String text = "";

    BancoDeDados banco = new BancoDeDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Oculta o actionBar
        getSupportActionBar().hide();


        this.mostraConteudo = findViewById(R.id.mostraConteudo);
        this.campoNumero = findViewById(R.id.campoNumero);
        this.campoApelido = findViewById(R.id.campoApelido);
        this.campoNome = findViewById(R.id.campoNome);
        this.campoEmail = findViewById(R.id.campoEmail);
        this.campoID = findViewById(R.id.campoID);
        this.contagem = findViewById(R.id.contagem);
        this.botaoAlterar = findViewById(R.id.botaoAlterar);
        this.botaoDelete = findViewById(R.id.botaoDelete);
        this.botaoInserir = findViewById(R.id.botaoInserir);

    }


    public void setBotaoInserir(View view) {

       if (campoID.getText().toString().isEmpty())
            Toast.makeText(this, " Informe o ID para Inserir  ", Toast.LENGTH_SHORT).show();
        else if ((campoNome.getText().toString().isEmpty() || campoNumero.getText().toString().isEmpty())||campoEmail.getText().toString().isEmpty()||campoApelido.getText().toString().isEmpty())
            Toast.makeText(this, " Digite todos os campos  ", Toast.LENGTH_SHORT).show();
        else
        {
            Contatos contato = new Contatos();
            String resultado;

            contato.setId(Integer.parseInt(campoID.getText().toString()));
            contato.setNome(campoNome.getText().toString());
            contato.setApelido(campoApelido.getText().toString());
            contato.setEmail(campoEmail.getText().toString());
            contato.setCelular(campoNumero.getText().toString());
            resultado = banco.insereContatos(contato);

            Toast.makeText(this,resultado, Toast.LENGTH_SHORT).show();
            AtualizaContagem ();
            setBotaoListar(view);
        }
    }

    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    public void setBotaoListar(View view) {

        List<Contatos> contatos = banco.listaTodosContatos();

        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);
        mostraConteudo.setAdapter(adapter);

        for (Contatos c : contatos) {

            arrayList.add("=========================================\n"+"ID: " + c.getId() + "\nNOME: " + c.getNome()+ "\nAPELIDO: "+ c.getApelido() + "\nEMAIL: "+c.getEmail()+"\nCELULAR: " + c.getCelular()+"\n=========================================");
            adapter.notifyDataSetChanged();
            AtualizaContagem ();
        }
    }

    public void setBotaoDelete (View view) {

        if (campoID.getText().toString().isEmpty()) {

            Toast.makeText(this, " Insira o ID a ser Excluido! ", Toast.LENGTH_SHORT).show();
        } else {

            Contatos contato = new Contatos();

            contato.setId(Integer.parseInt(campoID.getText().toString()));
            banco.deletar(contato);
            AtualizaContagem();
            setBotaoListar(view);
        }

    }

    public void setBotaoAlterar(View view)
    {
        if (campoID.getText().toString().isEmpty())
            Toast.makeText(this, " Informe o ID para alterar  ", Toast.LENGTH_SHORT).show();
        else if ((campoNome.getText().toString().isEmpty() || campoNumero.getText().toString().isEmpty())||campoEmail.getText().toString().isEmpty()||campoApelido.getText().toString().isEmpty())
            Toast.makeText(this, " Digite todos os campos  ", Toast.LENGTH_SHORT).show();
        else
        {
        Contatos contato = new Contatos();
        contato.setId(Integer.parseInt(campoID.getText().toString()));
        contato.setNome(campoNome.getText().toString());
        contato.setApelido(campoApelido.getText().toString());
        contato.setEmail(campoEmail.getText().toString());
        contato.setCelular(campoNumero.getText().toString());
        AtualizaContagem ();
        setBotaoListar(view);
        Toast.makeText(this, " Alterado com sucesso ", Toast.LENGTH_SHORT).show();
        banco.atualizaContatos(contato);
    }
    }




    public void AtualizaContagem (){

        int qtdContatos = banco.consultaQuantidadeContatos ();

        this.contagem.setText(String.valueOf(qtdContatos));

    }


}
