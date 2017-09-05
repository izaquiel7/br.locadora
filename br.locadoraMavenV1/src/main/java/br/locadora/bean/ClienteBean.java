/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.locadora.bean;

import br.locadora.model.ClienteModel;
import br.locadora.model.entidade.Cliente;

/**
 *
 * @author Izaquiel
 */
public class ClienteBean implements BeanGeral<Cliente> {

    private ClienteModel cm;

    public ClienteBean() {
        cm = ClienteModel.getInstance();
    }

    @Override
    public String cadastrar(Cliente entidade) {
        cm.cadastra(entidade);
        return "menuCliente.xhtml";
    }

    @Override
    public void alterar(Cliente entidade) {
        cm.altera(entidade);
    }

    @Override
    public String buscar(Cliente entidade) {
        cm.recupera(entidade);
        return "menuCliente.xhtml";
    }

    @Override
    public String buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deletar(Cliente entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
