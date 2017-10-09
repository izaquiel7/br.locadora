/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.model;

import br.ifpe.model.dao.ClienteDao;
import br.ifpe.model.entidades.Cliente;
import br.ifpe.model.util.erro.ErroSistema;
import java.util.List;

/**
 *
 * @author izaquias
 */
public class ClienteModel implements CrudModel<Cliente> {

    private static ClienteModel instance;
    private ClienteDao dao;
    private Cliente cliente;

    private ClienteModel() throws ErroSistema {
        dao = ClienteDao.getInstance();
        cliente = new Cliente();
    }

    public static ClienteModel getInstance() throws ErroSistema {
        if (instance == null) {
            instance = new ClienteModel();
        }
        return instance;
    }

    @Override
    public void inserirOUatualizar(Cliente entidade) throws ErroSistema {
        dao.inserirOUatualizar(entidade);
    }

    @Override
    public Cliente buscar(Cliente entidade) throws ErroSistema {
        if (dao.buscar(entidade) == null) {
            //mensagem de erro Faces
            return null;
        }

        return dao.buscar(entidade);

    }

    @Override
    public List<Cliente> buscarTodos() throws ErroSistema {
        List<Cliente> clientes = dao.buscarTodos();
        if (clientes == null) {
            //mensagem de erro Faces
            return null;
        }
        return clientes;

    }

    @Override
    public void deletar(Cliente entidade) throws ErroSistema {
        dao.deletar(entidade);
    }

    //********************************************************************//
    
    
    
    
    public ClienteDao getDao() {
        return dao;
    }

    public void setDao(ClienteDao dao) {
        this.dao = dao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    

}
