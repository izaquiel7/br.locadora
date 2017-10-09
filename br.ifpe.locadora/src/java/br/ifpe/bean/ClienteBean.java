/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.bean;

import br.ifpe.model.ClienteModel;
import br.ifpe.model.entidades.Cliente;
import br.ifpe.model.util.erro.ErroSistema;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author izaquias
 */
@ManagedBean(name = "clienteBean")
@SessionScoped
public class ClienteBean implements CrudBean<Cliente> {

    private Cliente cliente;
    private ClienteModel model;

    public ClienteBean() {
    }

    public void init() throws ErroSistema {
        cliente = new Cliente();
        model = ClienteModel.getInstance();
    }

    @Override
    public String inserirOUatualizar() throws ErroSistema {
        model.inserirOUatualizar(this.cliente);
        return null;
    }

    @Override
    public Cliente buscar() throws ErroSistema {
        return model.buscar(this.cliente);
    }

    @Override
    public String buscarTodos() throws ErroSistema {
        model.buscarTodos();
        return null;
    }

    @Override
    public String deletar() throws ErroSistema {
        model.deletar(this.cliente);
        return null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteModel getModel() {
        return model;
    }

    public void setModel(ClienteModel model) {
        this.model = model;
    }

}
