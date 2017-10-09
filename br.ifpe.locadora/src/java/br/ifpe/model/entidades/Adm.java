/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.model.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Izack
 */
@Entity
@Table(name = "Administrador")
public class Adm implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "adm_nome", nullable = false, length = 25)
    private String nome;

    @Column(name = "adm_cpf", nullable = false, length = 13, unique = true)
    private String cpf;

    @Column(name = "adm_email", nullable = false, length = 25, unique = true)
    private String email;

    @Column(name = "adm_senha", nullable = false, length = 64)
    private String senha;

    @Column(name = "adm_telefone", nullable = false, length = 15)
    private String telefone;

    @ManyToOne
    private Game game;

    public Adm() {
    }

    public Adm(String nome, String cpf, String email, String senha, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
