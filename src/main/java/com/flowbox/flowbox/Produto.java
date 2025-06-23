package com.flowbox.flowbox;

import java.math.BigDecimal;
import java.util.UUID;

public class Produto {
    private final String id;
    private String nome;
    private BigDecimal preco;
    private long estoqueProduto;

    public Produto (){
        id = UUID.randomUUID().toString();
    }

    public Produto ( String nome, BigDecimal preco, long estoqueProduto){
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.preco = preco;
        this.estoqueProduto = estoqueProduto;
    }

    public String getId (){
        return this.id;
    }

    public String getNome (){
        return this.nome;
    }

    public void setNome (String nome){
        this.nome = nome;
    }

    public BigDecimal getPreco (){
        return this.preco;
    }

    public void setPreco (BigDecimal preco){
        this.preco = preco;
    }

    public long getEstoqueProduto (){
        return this.estoqueProduto;
    }

    public void setEstoqueProduto (long estoqueProduto){
        this.estoqueProduto = estoqueProduto;
    }
}
