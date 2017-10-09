/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.model.dao;

import br.ifpe.model.util.erro.ErroSistema;
import java.util.List;

/**
 *
 * @author izaquiel
 * @param <E>
 */
public interface CrudDao<E> {
    
    public void inserirOUatualizar(E entidade) throws ErroSistema;

    public E buscar(E entidade) throws ErroSistema;
    
    public List<E> buscarTodos() throws ErroSistema;
    
    public void deletar(E entidade)throws ErroSistema;
    
}
