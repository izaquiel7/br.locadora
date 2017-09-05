/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import br.locadora.model.ClienteModel;
import br.locadora.model.entidade.Cliente;

/**
 *
 * @author Izaquiel
 */
public class TestCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ClienteModel cm = ClienteModel.getInstance();
        Cliente c = new Cliente();
        
        c.setEmail("i@z");
        c.setNome("iz");
        c.setSenha("123");
        
        cm.cadastra(c);
        
        System.out.println(cm.recupera(c).getNome());
    }
    
}
