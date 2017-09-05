/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.locadora.model.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Izaquiel
 */
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue
    private int id;
    @Column(length = 32, nullable = false, unique = true)
    private String email;
    @Column(length = 12, nullable = false)
    private String senha;
    @Column(length = 32, nullable = false)
    private String nome;

    public Cliente() {

    }

    public Cliente(String email, String senha, String nome) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
