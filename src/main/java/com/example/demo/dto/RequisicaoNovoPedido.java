package com.example.demo.dto;

import com.example.demo.model.Pedido;
import com.example.demo.model.StatusPedido;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;


public class RequisicaoNovoPedido {

    @NotBlank
    private String nomeProduto;
    @NotBlank
    private String urlProduto;
    @NotBlank
    private String urlImagem;
    @NotBlank
    private  String descricao;

    public String getUrlProduto() {
        return urlProduto;
    }

    public void setUrlProduto(String urlProduto) {
        this.urlProduto = urlProduto;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Pedido toPedido() {
        Pedido pedido = new Pedido();
        pedido.setNomeDoProduto(nomeProduto);
        pedido.setUrlProduto(urlProduto);
        pedido.setDescricao(descricao);
        pedido.setUrlImagem(urlImagem);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        return pedido;
    }
}
