/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.locadora.model;

import br.locadora.model.dao.ClienteDAO;
import br.locadora.model.entidade.Cliente;
import java.util.List;

/**
 *
 * @author Izaquiel
 */
public class ClienteModel {

    private static ClienteModel instance;
    private ClienteDAO cdao;

    private ClienteModel() {
        cdao = ClienteDAO.getinstance();
    }

    public static ClienteModel getInstance() {
        if (instance == null) {
            instance = new ClienteModel();
        }
        return instance;
    }

    public void cadastra(Cliente c) {
        cdao.inseri(c);
    }

    public void altera(Cliente c) {
        cdao.altera(c);
    }

    public Cliente recupera(Cliente cliente) {

        return cdao.recupera(cliente);
    }

    public List recuperaTodos() {
        return cdao.recuperaTodos();
    }

    public Cliente loga(String email, String senha) {
        return cdao.recuperaLogin(email, senha);
    }

}
