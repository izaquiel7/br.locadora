/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.bean;

import br.ifpe.model.util.erro.ErroSistema;
import java.util.List;

/**
 *
 * @author izaquias
 * @param <E>
 */
public interface CrudBean<E> {
    
    public String inserirOUatualizar() throws ErroSistema;

    public E buscar() throws ErroSistema;
    
    public String buscarTodos() throws ErroSistema;
    
    public String deletar() throws ErroSistema;
    
}