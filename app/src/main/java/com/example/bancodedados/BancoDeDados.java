package com.example.bancodedados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends SQLiteOpenHelper
{
    private static final int VERSAO_BANCO_DADOS = 5;
    private static final String NOME_BANCO_DADOS = "gerenciaContatos";
    private static final String TABELA_CONTATOS = "contatos";
    private static final String CAMPO_ID= "id";
    private static final String CAMPO_NOME = "nome";
    private static final String CAMPO_APELIDO = "apelido";
    private static final String CAMPO_EMAIL = "email";
    private static final String CAMPO_CELULAR = "celular";


    public BancoDeDados(Context context)
    {

        super(context, NOME_BANCO_DADOS, null, VERSAO_BANCO_DADOS);
    }


// cria banco de dados
    @Override
    public void onCreate(SQLiteDatabase banco)
        {
            String CriaTabelaContatos = "CREATE TABLE " + TABELA_CONTATOS + "("
                    + CAMPO_ID + " INTEGER PRIMARY KEY autoincrement,"
                    + CAMPO_NOME + " TEXT,"
                    + CAMPO_APELIDO + " TEXT,"
                    + CAMPO_EMAIL + " TEXT,"
                    + CAMPO_CELULAR + " TEXT " + ")";
            banco.execSQL(CriaTabelaContatos);
        }



//onUpgrade é usado pra atualizar o app, funciona caso mude a versao
    @Override
    public void onUpgrade(SQLiteDatabase banco, int i, int i1)
    {
        banco.execSQL("DROP TABLE IF EXISTS " + TABELA_CONTATOS); //DROP TABLE IF EXISTS contatos
        onCreate(banco);

    }

    //DELETA TABELA
    public void deletar(Contatos contatos) {

        SQLiteDatabase banco = this.getWritableDatabase();
        banco.delete(TABELA_CONTATOS, CAMPO_ID + "=?", new String[]{String.valueOf(contatos.getId())});


    }


    //INSERE NA TABELA
    public String insereContatos(Contatos contatos)
    {
        SQLiteDatabase banco = this.getWritableDatabase(); //É usado pra escrever no BD
        ContentValues values = new ContentValues();
        long resultado;
        values.put(CAMPO_ID,contatos.getId());
        values.put(CAMPO_NOME, contatos.getNome());
        values.put(CAMPO_APELIDO, contatos.getApelido());
        values.put(CAMPO_EMAIL, contatos.getEmail());
        values.put(CAMPO_CELULAR, contatos.getCelular());
        resultado = banco.insert(TABELA_CONTATOS,null ,values);
        if (resultado == 1) {
            return " CONTATO DE NOME: " + contatos.getNome() + " inserido com sucesso ";
        } else {
            return " O ID " + contatos.getId() + "já está sendo usado ";
        }


    }







    //LISTA TODOS OS CONTATOS
    public List<Contatos> listaTodosContatos()
    {
        List<Contatos>  listaContatos = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABELA_CONTATOS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst())
        {
            do
            {
                Contatos contatos = new Contatos();
                contatos.setId(Integer.parseInt(cursor.getString(0)));
                contatos.setNome(cursor.getString(1));
                contatos.setApelido(cursor.getString(2));
                contatos.setEmail(cursor.getString(3));
                contatos.setCelular(cursor.getString(4));
                listaContatos.add(contatos);
            }
            while (cursor.moveToNext());
        }
        return listaContatos;
    }


    //ATUALIZA CONTATOS
    public void atualizaContatos (Contatos contatos)
    {
        SQLiteDatabase banco = this.getWritableDatabase(); //Abre o banco de dadods
        ContentValues values =  new ContentValues();
        values.put(CAMPO_NOME, contatos.getNome());
        values.put(CAMPO_APELIDO, contatos.getApelido());
        values.put(CAMPO_EMAIL, contatos.getEmail());
        values.put(CAMPO_CELULAR, contatos.getCelular());
         banco.update(TABELA_CONTATOS, values, CAMPO_ID + "=?", new String[] {String.valueOf(contatos.getId())});
    }


    //VER QUANTIDADE DE CONTATOS
    public int consultaQuantidadeContatos()
    {
        String countQuery = "SELECT * FROM " + TABELA_CONTATOS;
        SQLiteDatabase banco = this.getReadableDatabase();
        Cursor cursor = banco.rawQuery(countQuery, null);
        return cursor.getCount();
    }
}
