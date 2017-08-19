/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofresim;

import java.io.Serializable;

/**
 *
 * @author Alefe Lucas
 */

public class Senha implements Serializable {

    public Senha(String nome, String descricao, String senha, String senhaMaster) {
        this.nome = nome;
        this.descricao = descricao;
        this.senha = senha;
        this.senhaMaster = senhaMaster;
    }

    public Senha(int id, String nome, String descricao, String senha, String senhaMaster) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.senha = senha;
        this.senhaMaster = senhaMaster;
    }
    
    private int id;
    private String nome;
    private String descricao;
    private String senha;
    private String senhaMaster;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaMaster() {
        return senhaMaster;
    }

    public void setSenhaMaster(String senhaMaster) {
        this.senhaMaster = senhaMaster;
    }
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String description) {
        this.descricao = description;
    }

    @Override
    public String toString() {
        return nome;
    }

  
    
    



    
}
