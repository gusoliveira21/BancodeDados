package com.example.bancodedados;

public class Contatos
{
    int id;
    String nome;
    String apelido;
    String email;
    String numeroCelular;

    public Contatos()
    {

    }


    //this
    public Contatos(String nome, String numeroCelular, String apelido, String email)
    {
        this.nome = nome;
        this.email = email;
        this.apelido = apelido;
        this.numeroCelular = numeroCelular;

    }

    //this
    public Contatos(int id, String nome, String numeroCelular, String apelido, String email)
    {
        this.id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.numeroCelular = numeroCelular;
    }

    public int getId()
    {
        return id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCelular()
    {
        return numeroCelular;
    }

    public void setCelular(String numeroCelular)

    {
        this.numeroCelular = numeroCelular;
    }
}
