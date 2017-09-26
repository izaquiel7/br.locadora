/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.locadora.bean;

/**
 *
 * @author Izaquiel
 * @param <E>
 */
public interface BeanGeral<E> {

    public String cadastrar(E entidade);

    public void alterar(E entidade);

    public String buscar(E entidade);

    public String buscarTodos();

    public String deletar(E entidade);
}
